package com.mobanker.auth.barrier;

/**
 * @类说明:将字符串里的空格去掉
 * @类名:CommonUtils.java
 * @作者:xiaoshijie
 * @创建日期:2016年9月7日
 */
public class CommonUtils {

	/**
	 * 将字符串里的空格去掉
	 *
	 * @param target
	 * @return
	 */
	public static String trimSpace(String target) {
		StringBuffer sb = new StringBuffer();
		char[] chars = target.toCharArray();
		for (char aChar : chars) {
			if (aChar != 32) {
				sb.append(aChar);
			}
		}
		return sb.toString();
	}

}
