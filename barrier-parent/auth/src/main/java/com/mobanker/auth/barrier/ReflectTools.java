package com.mobanker.auth.barrier;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 管黎明
 *
 *         创建时间:2015年10月15日
 */
public class ReflectTools {

	/**
	 * 通过反射进行设置。只能设置字符串的域
	 *
	 * @param target
	 *            赋值目标
	 * @param name
	 *            域名
	 * @param value
	 *            值
	 * @return
	 * @throws Exception
	 */
	public static <T> T setter(T target, String name, Object value) throws Exception {
		for (Field f : target.getClass().getDeclaredFields()) {
			if (!f.getType().equals(String.class)) {
				continue;
			}
			if (StringUtils.equals(f.getName(), name)) {
				boolean access = f.isAccessible();
				f.setAccessible(true);
				if (value instanceof JSONObject) {
					f.set(target, ((JSONObject) value).toJSONString());
					f.setAccessible(access);
					continue;
				}
				f.set(target, String.valueOf(value));
				f.setAccessible(access);
			}
		}
		return target;
	}

}
