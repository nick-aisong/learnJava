package com.wisely.ui.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.ui.domain.Person;

// 我们只需通过简单的在接口中声明方法即可调用Person服务的REST服务
@FeignClient("person")
public interface PersonService {
	 @RequestMapping(method = RequestMethod.POST, 
			         value = "/save",
	                 produces = MediaType.APPLICATION_JSON_VALUE, 
	                 consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody List<Person> save(@RequestBody String  name);
}
