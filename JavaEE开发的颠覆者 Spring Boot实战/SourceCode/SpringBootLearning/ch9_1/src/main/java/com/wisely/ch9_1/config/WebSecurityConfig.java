package com.wisely.ch9_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.wisely.ch9_1.security.CustomUserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 1扩展Spring Security配置需继承WebSecurityConfigurerAdapter

	@Bean
	UserDetailsService customUserService() { // 2注册CustomUserService的Bean
		return new CustomUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()); // 3添加我们自定义的user detail service认证

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .anyRequest()
		    .authenticated() // 4所有请求需要认证即登录后才能访问
			.and()
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error")
			.permitAll() // 5定制登录行为，登录页面可任意访问
			.and()
			.logout()
			.permitAll(); // 6定制注销行为，注销请求可任意访问

	}

}
