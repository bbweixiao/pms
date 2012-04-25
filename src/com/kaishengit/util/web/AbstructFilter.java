package com.kaishengit.util.web;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class AbstructFilter implements Filter{

	public void destroy() {
	}

	public abstract void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain)  throws IOException, ServletException;

	public void init(FilterConfig arg0) throws ServletException {
	}

}
