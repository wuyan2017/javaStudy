package com.mobanker.auth.barrier.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mobanker.auth.barrier.*;
import com.mobanker.auth.barrier.container.ConcurrentContainer;
import com.mobanker.auth.barrier.dao.FakeDao;
import com.mobanker.auth.barrier.dao.MerchantKeyDao;
import com.mobanker.auth.barrier.entity.FakeEntity;
import com.mobanker.auth.barrier.entity.MerchantKey;
import com.mobanker.auth.barrier.service.impl.SpringContainer;
import com.mobanker.auth.barrier.util.DES;
import com.mobanker.auth.barrier.util.HttpClientUtils;
import com.mobanker.auth.barrier.util.RSA;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author 管黎明
 *
 *         All rights reserved.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 1, name = "myServlet")
public class MyServlet extends HttpServlet {

	private static int i = 0;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArrayBlockingQueue<FakeEntity> abq = new ArrayBlockingQueue<FakeEntity>(
			30);

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		this.doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		final FakeDao fakeDao = SpringContainer.getBean(FakeDao.class);
		fakeDao.dropTable("t_fake");
		Map<String, String> mm = new HashMap<String, String>();
		String sql = "CREATE TABLE t_fake(id        INT NOT NULL AUTO_INCREMENT COMMENT 'id',CODE                 VARCHAR(100) NOT NULL DEFAULT '' COMMENT '映射码',response             longtext COMMENT '返回值',delay_millisecond    INT NOT NULL DEFAULT 0 COMMENT '延迟返回时间(毫秒)',callback				INT NOT NULL DEFAULT 0 COMMENT '1-回调，0-不回调',callback_url			VARCHAR(100) NOT NULL DEFAULT '' COMMENT '回调地址',callback_str			VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '回调请求字符串',PRIMARY KEY (id)) ENGINE=INNODB DEFAULT CHARSET=utf8";
		mm.put("sql", sql);
		fakeDao.executeSql(mm);

		Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("data.sql"));
		while (sc.hasNext()) {
				String insertSql =null;
				try {
					insertSql = new String(sc.nextLine().getBytes("gbk"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if(StringUtils.isNotBlank(insertSql)){
					mm.put("sql", insertSql);
					fakeDao.executeSql(mm);
				}
		}
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		logger.info("请求的url:" + req.getRequestURI());
		// 封装部分请求数据
		Map<String, String> partReqDataMap = praseRequestData(req);
		final String code = partReqDataMap.get("serviceCode");
		Assert.isTrue(StringUtils.isNotBlank(code));
		final FakeDao fakeDao = SpringContainer.getBean(FakeDao.class);
		ConcurrentContainer.put(code, AuthExecutors.newCachedThreadPool());
		Callable<List<FakeEntity>> call = new Callable<List<FakeEntity>>() {
			@Override
			public List<FakeEntity> call() throws Exception {

				boolean b=code.startsWith("hehe");
				if(b){
					RecognizeIdCardRequest req_IdCard = new RecognizeIdCardRequest();
					//向req_IdCard中传参数
					req_IdCard.setUser(req.getParameter("user"));
					req_IdCard.setPass(req.getParameter("pass"));
					req_IdCard.setFileName(req.getParameter("fileName"));
					req_IdCard.setUrl(req.getRequestURL().toString());
					req_IdCard.setUrl("http://qianlong-test.cn-sh2.ufileos.com");
					req_IdCard.setFileName("/2017/09/08/64507dce-6f10-43e6-a171-1d7445cee933.jpeg");
					req_IdCard.setHeadPortrait("1");
					req_IdCard.setCropImage("1");
					req_IdCard.setEncoding("utf8");
					req_IdCard.setFile(new MultipartFormDataFile("upfile","fileName",
							"image/jpeg",req.getInputStream()));
					req_IdCard.setBillNo(req.getParameter("BillNo"));
					req_IdCard.setTest(true);

					/*test*/
//					req_IdCard.setHeadPortrait("1");
//					req_IdCard.setCropImage("1");
//					req_IdCard.setEncoding("utf8");
//					req_IdCard.setTest(true);
//					req_IdCard.setFile(new MultipartFormDataFile("upfile", "证反成功1.jpg", "image/jpeg", new FileInputStream(
//							"C://Users//wuyan//Desktop//card//证反成功1.jpg")));
//

					//调用HeheUtils
					RecognizeIdCardResponse resp_IdCard=HeheUtils.recognizeIdCard(req_IdCard);
					JSONObject json = new JSONObject();
					json.put("data",JSONObject.toJSONString(resp_IdCard));
					json.put("error","00000000");
					json.put("msg","请求成功");
					List<FakeEntity> list = new ArrayList<FakeEntity>();
					FakeEntity fake = new FakeEntity();
					fake.setCode(code);
					fake.setResponse(json.toJSONString());
					list.add(fake);
					return list;
				}else {
					return fakeDao.queryList(code);
				}
			}
		};
		Future<List<FakeEntity>> future = null;
		try {
			future = ConcurrentContainer.get(code).submit(call);
			// FakeEntity entitys = future.get(3, TimeUnit.SECONDS);
			List<FakeEntity> entitys = future.get(10, TimeUnit.SECONDS);
			if (CollectionUtils.isEmpty(entitys)) {
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("text/html; charset=utf-8");
				PrintWriter pw = resp.getWriter();
				pw.write("没有配置");
				pw.close();
				return;
			}
			if (entitys.get(0).getDelayMillisecond() != 0) {
				try {
					Thread.currentThread().sleep(
							entitys.get(0).getDelayMillisecond());
				} catch (InterruptedException e) {
					logger.error("excptions:{}", e);
				}
			}
			if (entitys.get(0).getCallback() == 1) {
				HttpClientUtils.doPost(entitys.get(0).getCallbackUrl(), entitys
						.get(0).getCallbackStr());
			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.write(packReturnStr(
					entitys.get(new Random().nextInt(entitys.size()))
							.getResponse(), partReqDataMap));
			pw.close();
		} catch (RejectedExecutionException e) {
			logger.error("arrive num:{}", ConcurrentContainer.get(code)
					.getPoolSize());
		} catch (TimeoutException e) {
			System.out.println(future.cancel(false));
			logger.error("wait timeout");
		} catch (NullPointerException e) {
			logger.error("task null");
		} catch (Exception e) {
			logger.error("unknow Exceptions:{}", e);
		}
	}

	/**
	 * 对外的接口返回的数据要做加密处理 对内的接口不需要做
	 * 
	 * @param map
	 *            如果 /api/2.0.0 这种返回就要加密了，否则就不需要
	 * @return
	 */
	public String packReturnStr(String response, Map<String, String> map) {
		response = StringEscapeUtils.unescapeHtml4(response);
		if ("out".equals(map.get("requestInOrOut"))) {
			return encrypt(response, map.get("merchantId"));
		}
		return response;
	}

	/**
	 * 对返回的数据进行加密
	 * 
	 * @param retStr
	 * @return
	 */
	public String encrypt(String retStr, String metchantID) {
		String str = "";
		try {
			MerchantKeyDao dao = SpringContainer.getBean(MerchantKeyDao.class);
			MerchantKey merchantKey = dao.queryConfirmMerchant(metchantID);
			// 获取加密需要的信息
			Map<String, Object> map = JSON.parseObject(retStr, Map.class);
			Map signMap = getSign(JSON.toJSONString(map.get("data")),
					merchantKey.getServerPrivateKey(), merchantKey.getDesKey());
			map.put("data", signMap);
			return JSON.toJSONString(map);
		} catch (Exception e) {
			logger.error("对数据加密失败：{}", e);
		}
		return str;
	}

	/**
	 * 加密并签名
	 * 
	 * @return 加密和签名后的结果Map
	 */
	public static Map<String, String> getSign(String resData,
			String privateKey, String desKey) {
		Map<String, String> data = new HashMap<String, String>();
		try {
			DES des = new DES(desKey);
			resData = des.encrypt(resData);
			data.put("data", resData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("sign", sign(resData, privateKey));
		return data;

	}

	/**
	 * 生成签名结果
	 * 
	 * @return 签名结果字符串
	 */
	public static String sign(String str, String privateKey) {
		String mysign = "";
		mysign = RSA.sign(str, privateKey);
		return mysign;
	}

	/**
	 * 初始化一下请求数据
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, String> praseRequestData(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String requestInOrOut = ""; // in out
		String serviceCode = "";
		String merchantId = "";
		try {
			// 对内提供的接口
			if (request.getRequestURI().contains("/auth/api/1.0.0/")) {
				requestInOrOut = "in";
				serviceCode = request.getRequestURI().replace(
						"/auth/api/1.0.0/", "");
			}

			// 对外提供的接口
			if (request.getRequestURI().contains("/auth/authentication.do")) {
				requestInOrOut = "out";
				// 从请求参数中取
				Map tmpMap = getJsonData(request);
				if (tmpMap != null) {
					tmpMap.put("requestInOrOut", requestInOrOut);
					return tmpMap;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("requestInOrOut", requestInOrOut);
		map.put("serviceCode", serviceCode);
		map.put("merchantId", merchantId);
		return map;
	}

	/**
	 * 对于外部请求接口，从输入流中获取json数据
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, String> getJsonData(HttpServletRequest request) {

		try {
			String reqStr = "";
			BufferedReader buf = request.getReader();
			String tmpStr = buf.readLine();
			while (tmpStr != null) {
				reqStr += tmpStr;
				tmpStr = buf.readLine();
			}
			// 解析reqStr
			String[] arg = URLDecoder.decode(reqStr, "utf-8").split("&");
			Map<String, String> paratMap = new HashMap<String, String>();
			for (int i = 0; i < arg.length; i++) {
				if (arg[i].contains("=")) {
					String[] s = arg[i].split("=");
					paratMap.put(s[0], s[1]);
				}
			}

			String merchantId = paratMap.get("merchantId");
			MerchantKeyDao dao = SpringContainer.getBean(MerchantKeyDao.class);
			MerchantKey merchantKey = dao.queryConfirmMerchant(merchantId);

			// 获取request
			String reqData = new DES(merchantKey.getDesKey()).decrypt(paratMap
					.get("reqData"));
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchantId", merchantId);
			map.put("serviceCode",
					JSON.parseObject(reqData).getString("serviceCode"));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
