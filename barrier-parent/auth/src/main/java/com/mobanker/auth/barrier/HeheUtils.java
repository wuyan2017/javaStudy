package com.mobanker.auth.barrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*import com.mobanker.auth.hehe.constants.HeheConstant;
import com.mobanker.auth.barrier.RecognizeBankCardRequest;
import com.mobanker.auth.hehe.request.RecognizeIdCardRequest;
import com.mobanker.auth.hehe.response.RecognizeBankCardResponse;
import com.mobanker.auth.hehe.response.RecognizeIdCardResponse;*/

/**
 * @类说明:合合Utils类
 * @类名:HeheUtils.java
 * @作者:xiaoshijie
 * @创建日期:2016年9月7日
 */
public class HeheUtils {

	private static final Logger logger = LoggerFactory.getLogger(HeheUtils.class);

	/**
	 * 请求合合的身份证识别接口
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static RecognizeIdCardResponse recognizeIdCard(RecognizeIdCardRequest request) throws Exception {
		try {
			MultipartFormDataClient client = new MultipartFormDataClient(
					"http://192.168.33.142:30006/icr/recognize_id_card");
			Map<String, String> strParams = HeheMapper.getHeheMap(request, Arrays.asList("fileName", "url"));
			List<MultipartFormDataFile> files = new ArrayList<MultipartFormDataFile>();
			files.add(request.getFile());
			String str = client.doPost(strParams, files, false);
			logger.info("合合-身份证识别,HeheUtils#recognizeIdCard post,returnStr:{}", str);
			return HeheMapper.getResponseObject(str, RecognizeIdCardResponse.class);
		} catch (Exception e) {
			logger.error("***合合-身份证识别-访问第三方失败：recognizeIdCard()");
			throw e;
		}
	}

	/**
	 * 请求合合的银行卡识别接口
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static RecognizeBankCardResponse recognizeBankCard(RecognizeBankCardRequest request) throws Exception {
		MultipartFormDataClient client = new MultipartFormDataClient(
				"http://192.168.33.142:30007/icr/recognize_bank_card");
		Map<String, String> strParams = HeheMapper.getHeheMap(request, Arrays.asList("fileName", "url"));
		List<MultipartFormDataFile> files = new ArrayList<MultipartFormDataFile>();
		files.add(request.getFile());
		String str = client.doPost(strParams, files, false);
		logger.info("合合-银行卡识别,HeheUtils#recognizeBankCard post,returnStr:{}", str);
		RecognizeBankCardResponse response = HeheMapper.getResponseObject(str, RecognizeBankCardResponse.class);
		response.setCardNumber(CommonUtils.trimSpace(response.getCardNumber()));
		return response;
	}


}
