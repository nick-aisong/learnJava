package com.wisely.ch11_1;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@ConfigurationProperties(prefix = "endpoints.status", ignoreUnknownFields = false) // 1通过@ConfigurationProperties的设置，我们可以在application.properties中通过endpoints.status配置我们的端点
public class StatusEndPoint extends AbstractEndpoint<String> implements ApplicationContextAware {// 2继承AbstractEndpoint类，AbstractEndpoint是Endpoint接口的抽象实现，当前类一定要重写invoke方法。实现ApplicationContextAware接口可以让当前类对Spring容器的资源有意识，即可访问容器的资源

	ApplicationContext context;

	public StatusEndPoint() {
		super("status");
	}

	@Override
	public String invoke() { // 3通过重写invoke方法，返回我们要监控的内容
		StatusService statusService = context.getBean(StatusService.class);
		return "The Current Status  is :" + statusService.getStatus();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;
	}

}
