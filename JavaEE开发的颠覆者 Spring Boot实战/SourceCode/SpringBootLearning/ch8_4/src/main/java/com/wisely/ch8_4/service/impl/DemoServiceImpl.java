package com.wisely.ch8_4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisely.ch8_4.dao.PersonRepository;
import com.wisely.ch8_4.domain.Person;
import com.wisely.ch8_4.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	PersonRepository personRepository; // 1可以直接注入我们的RersonRepository的Bean

	@Transactional(rollbackFor = { IllegalArgumentException.class }) // 2使用@Transactional注解的rollbackFor属性，指定特定异常时，数据回滚
	public Person savePersonWithRollBack(Person person) {
		Person p = personRepository.save(person);

		if (person.getName().equals("汪云飞")) {
			throw new IllegalArgumentException("汪云飞已存在，数据将回滚"); // 3硬编码手动触发异常
		}
		return p;
	}

	@Transactional(noRollbackFor = { IllegalArgumentException.class }) // 4使用@Transactional注解的noRollbackFor属性，指定特定异常时，数据回滚
	public Person savePersonWithoutRollBack(Person person) {
		Person p = personRepository.save(person);

		if (person.getName().equals("汪云飞")) {
			throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
		}
		return p;
	}
}
