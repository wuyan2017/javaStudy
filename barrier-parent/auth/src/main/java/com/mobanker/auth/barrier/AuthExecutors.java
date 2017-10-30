package com.mobanker.auth.barrier;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author guanliming
 *
 */
public class AuthExecutors {

	public static ThreadPoolExecutor newCachedThreadPool() {
		 return new ThreadPoolExecutor(5, 30, 60L, TimeUnit.SECONDS,
		 new LinkedBlockingDeque<Runnable>(50));
//		return new ThreadPoolExecutor(5, 30, 60L, TimeUnit.SECONDS,
//				new SynchronousQueue<Runnable>());
	}

}
