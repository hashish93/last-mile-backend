package com.appzoneltd.lastmile.zuul;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreZuulFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PreZuulFilter.class);

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
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		StringBuilder builder = new StringBuilder();
		if (ctx.getRequest().getRequestURI().contains("oauth/token")) {
			byte[] encoded;
			builder.append(request.getParameter("client_id"));
			builder.append(":");
			builder.append(request.getParameter("client_secret"));
			try {
				encoded = Base64.encode(builder.toString().getBytes("UTF-8"));
				ctx.addZuulRequestHeader("Authorization", "Basic " + new String(encoded));

				log.debug("header added");
			} catch (UnsupportedEncodingException e) {
				log.error("Error occured in pre filter", e);
			}
		} else {
			ctx.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));

		}

		log.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		log.debug(String.format("Authorization Header : %s", request.getHeader("Authorization")));
		log.debug("Cookies ::" + request.getHeader("Cookie"));
		return null;
	}

}