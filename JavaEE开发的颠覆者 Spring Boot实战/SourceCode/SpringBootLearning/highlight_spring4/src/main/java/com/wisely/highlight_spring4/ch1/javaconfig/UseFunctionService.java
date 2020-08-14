package com.wisely.highlight_spring4.ch1.javaconfig;

import com.wisely.highlight_spring4.ch1.javaconfig.FunctionService;

//1此处没有使用@Service声明Bean
public class UseFunctionService {
	// 2此处没有使用@Autowired注解注入Bean
	FunctionService functionService;

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public String SayHello(String word) {
		return functionService.sayHello(word);
	}

}
