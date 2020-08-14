package com.wisely.ch5_2_2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication // 1@SpringBootApplication是Spring
						// Boot项目的核心注解，主要目的是开启自动配置。我们将在6.1.2节中做更详细的讲解
public class Ch522Application {

	@Value("${book.author}")
	private String bookAuthor;
	@Value("${book.name}")
	private String bookName;

	@RequestMapping("/")
	String index() {

		return "book name is:" + bookName + " and book author is:" + bookAuthor;
	}

	public static void main(String[] args) { // 2这是一个标准的Java应用的main方法，主要作用是作为项目启动的入口。我们将在6.1.1节做更详细的讲解
		SpringApplication.run(Ch522Application.class, args);
	}
}
