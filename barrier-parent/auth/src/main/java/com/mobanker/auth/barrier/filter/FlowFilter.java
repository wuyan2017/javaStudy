package com.mobanker.auth.barrier.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mobanker.auth.barrier.dao.FakeDao;
import com.mobanker.auth.barrier.entity.FakeEntity;
import com.mobanker.auth.barrier.service.impl.SpringContainer;

/**
 * @author guanliming
 *
 */
public class FlowFilter implements Filter {

	private AtomicInteger nowProcessCount = new AtomicInteger();
	private Logger logger = LoggerFactory.getLogger(FlowFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.debug("FlowFilter#trace,runningCount:{}", nowProcessCount.get());
		try {
			if (nowProcessCount.incrementAndGet() > 50) {
				FakeDao fakeDao = SpringContainer.getBean(FakeDao.class);
				FakeEntity fe = new FakeEntity();
				fe.setCode(new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS")
						.format(new Date()));
				fe.setResponse(String.valueOf(nowProcessCount.get()));
				fakeDao.insert(fe);
			}
		} catch (Exception e) {
			logger.error("FlowFilter#error");
		}
		chain.doFilter(request, response);
		nowProcessCount.decrementAndGet();
	}

	@Override
	public void destroy() {
	}

}
