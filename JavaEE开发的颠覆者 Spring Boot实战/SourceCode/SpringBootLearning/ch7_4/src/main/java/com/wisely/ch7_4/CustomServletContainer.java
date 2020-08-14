package com.wisely.ch7_4;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8888); // 1配置端口号
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html")); //2 配置错误页面，根据HttpStatus中的错误状态信息，直接转向错误页面，其中404.html放置在src/main/resources/static下即可
		container.setSessionTimeout(10, TimeUnit.MINUTES);  // 配置Servlet容器用户会话（session）过期时间

	}

}
