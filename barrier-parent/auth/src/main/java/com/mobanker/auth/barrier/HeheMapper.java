package com.mobanker.auth.barrier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 *@类说明:合合Mapper
 *@类名:HeheMapper.java
 *@作者:xiaoshijie
 *@创建日期:2016年9月7日
 */
public class HeheMapper {

	private static final Logger logger = LoggerFactory.getLogger(HeheMapper.class);

	/**
	 * 将obj中的属性转成map对象。map的key是obj中域名，如aBc转成a_bc
	 *
	 * @param obj 目标对象
	 * @param ignoreProperties 忽略属性
	 * @return
	 */
	public static Map<String, String> getHeheMap(Object obj, List<String> ignoreProperties) {
		Map<String, String> strParams = new HashMap<String, String>();
		int i = 0;
		for (Field f : obj.getClass().getDeclaredFields()) {
			logger.info("enter getMinShiMap count:{},field:{}", ++i, f.getName());
			StringBuffer sb = new StringBuffer();
			String fieldName = f.getName();
			if(ignoreProperties.contains(fieldName)){
				continue;
			}
			if (!(String.class.isAssignableFrom(f.getType()))) {
				continue;
			}
			char[] charArray = fieldName.toCharArray();
			for (char aChar : charArray) {
				if (aChar >= 65 && aChar <= 90) {
					sb.append("_" + (char) (aChar + 32));
				} else {
					sb.append(aChar);
				}
			}
			boolean access = f.isAccessible();
			f.setAccessible(true);
			Object valueObject = null;
			try {
				valueObject = f.get(obj);
			} catch (IllegalArgumentException e) {
				logger.error("MinShiUtils#getMinShiMap can't arrive here", e);
			} catch (IllegalAccessException e) {
				logger.error("MinShiUtils#getMinShiMap can't arrive here", e);
			}
			if (valueObject != null) {
				strParams.put(sb.toString(), String.valueOf(valueObject));
			}
			f.setAccessible(access);
		}
		return strParams;
	}

	/**
	 * 将目标字符串转成驼峰标示:a_b_cd->aBCd
	 *
	 * @param str
	 *            目标字符串
	 * @return
	 */
	public static String transferToCamel(String str) {
		char[] charArray = str.toCharArray();
		char sep = '_';
		boolean flag = false;
		StringBuffer sb = new StringBuffer();
		for (char aChar : charArray) {
			if (sep == aChar) {
				flag = true;
				continue;
			}
			if (flag && aChar >= 97 && aChar <= 122) {
				sb.append((char) (aChar - 32));
				flag = false;
				continue;
			}
			sb.append(aChar);
			flag = false;
		}
		return sb.toString();
	}

	/**
	 * 将合合响应字符串转成响应对象。响应对象中的值必须是字符串类型
	 *
	 * @param str
	 *            合合响应字符串
	 * @param type
	 *            返回对象类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("SingleStatementInBlock")
	public static <T> T getResponseObject(String str, Class<T> type) throws Exception {
		if (StringUtils.isBlank(str)||StringUtils.equals(str, "null")) {
			throw new RuntimeException("合合响应信息为空！");
		}
		Map<String, Object> responseMap = JSONObject.parseObject(str, Map.class);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		for (Entry<String, Object> mapEntry : responseMap.entrySet()) {
			String key = mapEntry.getKey();
			returnMap.put(HeheMapper.transferToCamel(key), responseMap.get(key));
		}
		T returnObj = type.newInstance();
		for (Entry<String, Object> mapEntry : returnMap.entrySet()) {
			ReflectTools.setter(returnObj, mapEntry.getKey(), mapEntry.getValue());
		}
		return returnObj;
	}
}
