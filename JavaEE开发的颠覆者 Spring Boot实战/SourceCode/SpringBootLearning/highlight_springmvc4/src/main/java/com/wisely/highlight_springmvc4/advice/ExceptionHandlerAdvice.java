package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // 1@ControllerAdvice声明一个控制器建言，@ControllerAdvice组合了@Component注解，所以自动注册为Spring的Bean
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = Exception.class) // 2@ExceptionHandler在此处定义全局处理，通过@ExceptionHandler的value属性可过滤拦截的条件，在此处我们可以看出我们拦截所有的Exception
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");// error页面
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}

	@ModelAttribute // 3此处使用@ModelAttribute注解将键值对添加到全局，所有注解的@RequestMapping的方法可获得此键值对
	public void addAttributes(Model model) {
		model.addAttribute("msg", "额外信息"); // 3
	}

	@InitBinder // 4通过@InitBinder注解定制WebDataBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id"); // 5此处演示忽略request参数的id，更多关于WebDataBinder的配置，请参考WebDataBinder的API文档
	}
}
