package com.appzoneltd.lastmile.filter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.web.client.RestTemplate;

import com.appzoneltd.lastmile.model.EurekaInstance;
import com.appzoneltd.lastmile.model.EurekaInstanceInformation;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class DownloadServerDynamicRoutingFilter extends ZuulFilter {

	private static Logger LOG = LoggerFactory.getLogger(DownloadServerDynamicRoutingFilter.class);

	@Autowired
	private RouteLocator routeLocator;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String urlStringValue = request.getRequestURL().toString();
		String serverId = null;
		String fileId = null;
		String companyId = null;

		if (urlStringValue.contains("operation") && request.getMethod().equals("GET")) {
			System.out.println(">>>>>>>>>> REDIRECT TO OPERATION");

			String[] splitBasedOnOperation = urlStringValue.split("operation");
			String[] splitUsingSlash = splitBasedOnOperation[1].split("/");
			serverId = splitUsingSlash[1];
			companyId = splitUsingSlash[2];
			fileId = splitUsingSlash[3];

			System.out.println("urlStringValue "+ urlStringValue);
			System.out.println("serverId "+ serverId);
			System.out.println("companyId "+ companyId);
			System.out.println("fileId "+ fileId);

			if (serverId != null && fileId != null && companyId != null) {
				RestTemplate restTemplate = new RestTemplate();
				EurekaInstanceInformation eurekaInstanceInformation = restTemplate.getForObject(
						"http://localhost:8761/eureka/apps/STATICCONTENTSERVER", EurekaInstanceInformation.class);
				if (eurekaInstanceInformation != null) {
					System.out.println("eurekaInstanceInformation "+eurekaInstanceInformation.toString());
					List<EurekaInstance> eurekaInstances = eurekaInstanceInformation.getApplication().getInstance();

					if (eurekaInstances != null && !eurekaInstances.isEmpty()) {
						for (EurekaInstance eurekaInstance : eurekaInstances) {
							String instanceId = eurekaInstance.getInstanceId();
							String[] splitUsingColon = instanceId.split(":");
							String serverIdToCompareWith = splitUsingColon[2];

							if (serverIdToCompareWith.equals(serverId)) {
								String downloadOperationFullPath = eurekaInstance.getHomePageUrl();
								System.out.println("downloadOperationFullPath "+downloadOperationFullPath);
								try {
									context.setRouteHost(new URL(downloadOperationFullPath));
								} catch (MalformedURLException e) {
									e.printStackTrace();
								}
								break;
							}
						}
					}
				}
			}

		}
		return null;
	}

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
