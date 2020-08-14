package com.wisely.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 1利用@Controller注解声明是一个控制器
public class HelloController {

	// @RequestMapping("/index")//2利用@RequestMapping配置URL和方法之间的映射
	// public String hello(){
	//
	// return "index"; //
	// 3通过上面ViewResolver的Bean配置，返回值为index，说明我们的页面放置的路径为/WEB-INF/classes/views/index.jsp
	// }

}
