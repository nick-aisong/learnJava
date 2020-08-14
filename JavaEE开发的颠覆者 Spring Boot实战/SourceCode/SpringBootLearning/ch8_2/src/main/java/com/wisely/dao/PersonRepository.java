package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisely.domain.Person;
import com.wisely.support.CustomRepository;

// 只需让实体类Repository继承我们自定义的Repository接口，即可使用我们在自定义Respository中实现的功能
public interface PersonRepository extends CustomRepository<Person, Long> {
	// 1使用方法名查询，接受一个address参数，返回值为列表
	List<Person> findByAddress(String address);

	// 2使用方法名查询，接受name和address，返回值为单个对象
	Person findByNameAndAddress(String name, String address);

	// 3使用@Query查询，参数按照名称绑定
	@Query("select p from Person p where p.name= :name and p.address= :address")
	Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

	// 4使用@NamedQuery查询，请注意我们在实体类中做的@NamedQuery的定义
	Person withNameAndAddressNamedQuery(String name, String address);

}
