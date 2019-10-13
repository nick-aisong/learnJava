package cn.ch04.ch04_6;

import java.util.Date;
import java.util.concurrent.Callable;

//161
public class Task implements Callable<String> {

	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n", name, new Date());
		return "Hello, world";
	}

}
