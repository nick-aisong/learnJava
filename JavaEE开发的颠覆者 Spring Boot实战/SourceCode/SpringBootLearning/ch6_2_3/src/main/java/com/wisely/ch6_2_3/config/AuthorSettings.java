package com.wisely.ch6_2_3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "author") // 1通过@ConfigurationProperties加载properties文件内的配置，通过prefix属性指定properties的配置的前缀，
//通过locations指定properties文件的位置，例如： @ConfigurationProperties(prefix = "author",locations = {"classpath:config/author.properties"})
public class AuthorSettings {
	private String name;
	private Long age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
}