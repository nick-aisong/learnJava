package com.wisely.ch8_6_1.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wisely.ch8_6_1.domain.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	Person findByName(String name); // 1支持方法名查询

	@Query("{'age': ?0}") // 2支持@Query查询，查询参数构造JSON字符串即可
	List<Person> withQueryFindByAge(Integer age);

}
