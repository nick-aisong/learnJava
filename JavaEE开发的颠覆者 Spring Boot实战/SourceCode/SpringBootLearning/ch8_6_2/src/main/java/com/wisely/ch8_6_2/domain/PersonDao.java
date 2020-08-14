package com.wisely.ch8_6_2.domain;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.wisely.ch8_6_2.dao.Person;

@Repository
public class PersonDao {

	@Autowired
	StringRedisTemplate stringRedisTemplate; // 1Spring Boot已为我们配置StringRedisTemplate，在此处可以直接注入

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr; // 3可以使用@Resource注解指定stringRedisTemplate，可注入基于字符串的简单属性操作方法

	@Autowired
	RedisTemplate<Object, Object> redisTemplate; // 2Spring Boot已为我们配置RedisTemplate，在此处可以直接注入

	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOps; // 4可以使用@Resource注解指定redisTemplate，可注入基于对象的简单属性操作方法

	public void stringRedisTemplateDemo() { // 5通过set方法，存储字符串类型
		valOpsStr.set("xx", "yy");
	}

	public void save(Person person) { // 6通过set方法，存储对象类型
		valOps.set(person.getId(), person);
	}

	public String getString() {// 7通过get方法，获得字符串
		return valOpsStr.get("xx");
	}

	public Person getPerson() {// 8通过get方法，获得对象
		return (Person) valOps.get("1");
	}

}
