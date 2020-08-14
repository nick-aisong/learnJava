package com.wisely.highlight_spring4.ch1.javaconfig;

//1此处没有使用@Service声明Bean
public class FunctionService {

	public String sayHello(String word) {
		return "Hello " + word + " !";
	}

}
