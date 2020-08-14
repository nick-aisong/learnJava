package com.wisely.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SomeHystrixService {

	@Autowired
	RestTemplate restTemplate; //1在Spring Boot下使用Ribbon，我们只需注入一个RestTemplate即可，Spring Boot已为我们做好了配置

	@HystrixCommand(fallbackMethod = "fallbackSome") //2使用@HystrixCommand的fallbackMethod参数指定，当本方法调用失败时调用后备方法fallbackSome
	public String getSome() {
		return restTemplate.getForObject("http://some/getsome", String.class);
	}
	
	public String fallbackSome(){ 
		return "some service模块故障";
	}
}
