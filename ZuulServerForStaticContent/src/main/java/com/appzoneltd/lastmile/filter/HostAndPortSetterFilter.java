package com.appzoneltd.lastmile.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.web.util.UrlPathHelper;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class HostAndPortSetterFilter extends ZuulFilter {
	private static Logger LOG = LoggerFactory.getLogger(HostAndPortSetterFilter.class);
	

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String urlStringValue = request.getRequestURL().toString();

		try {
			URL url = new URL(urlStringValue);

			String protocol = url.getProtocol();
			String rootHost = url.getHost();
			int port = url.getPort();
			String hostAndPort = protocol + "://" + rootHost + ":" + port;

			context.addZuulRequestHeader("ourHostAndPort", hostAndPort);
			
						
		} catch (MalformedURLException e) {
			LOG.error("An error has occured in Zuul", e);

		}

		return null;
	}
}
