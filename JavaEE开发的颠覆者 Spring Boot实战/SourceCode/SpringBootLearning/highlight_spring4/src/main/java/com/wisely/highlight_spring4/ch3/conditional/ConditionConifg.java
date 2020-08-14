package com.wisely.highlight_spring4.ch3.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConifg {
	@Bean
	@Conditional(WindowsCondition.class) // 1通过@Conditional注解，符合Windows条件则实例化windowsListService
	public ListService windowsListService() {
		return new WindowsListService();
	}

	@Bean
	@Conditional(LinuxCondition.class) // 2通过@Conditional注解，符合Linux条件则实例化linuxListService
	public ListService linuxListService() {
		return new LinuxListService();
	}

}
