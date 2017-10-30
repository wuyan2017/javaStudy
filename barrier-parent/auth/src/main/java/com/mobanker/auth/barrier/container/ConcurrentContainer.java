package com.mobanker.auth.barrier.container;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author guanliming
 *
 */
public class ConcurrentContainer {

	private static final Map<String, ThreadPoolExecutor> map = new HashMap<String, ThreadPoolExecutor>();

	public static boolean put(String key, ThreadPoolExecutor es) {
		if (es == null) {
			return false;
		}
		if (!map.containsKey(key)) {
			map.put(key, es);
			return true;
		}
		return false; 
	}
	
	public static ThreadPoolExecutor get(String key){
		return map.get(key);
	}

	
}
