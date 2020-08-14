package com.wisely.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.getEnvironment().setActiveProfiles("prod"); // 1先将活动的Profile设置为prod
		context.register(ProfileConfig.class);// 2后置注册Bean配置类，不然会报Bean未定义的错误
		context.refresh(); // 3刷新容器

		DemoBean demoBean = context.getBean(DemoBean.class);

		System.out.println(demoBean.getContent());

		context.close();
	}
}
