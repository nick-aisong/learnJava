package cn.ch04.ch04_4;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// 1.创建一个名为UserValidator的类，它将实现用户验证的过程
public class UserValidator {
	// 2.声明一个名为name的私有String属性，用来存储用户验证系统的名称
	private String name;
	// 3.实现类的构造器，用来初始化类的属性
	public UserValidator(String name) {
		this.name = name;
	}
	// 4.实现validate()方法。它接收两个String参数，分别取名为用户名name和密码password，这两个参数也将被用来进行用户验证
	public boolean validate(String name, String password) {
		// 5.创建一个名为random的Random类型的随机对象
		Random random = new Random();
		try {
			// 6.等待一段随机时间来模拟用户验证的过程
			long duration = (long) (Math.random() * 10);
			System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			return false;
		}
		// 7.返回随机的boolean 值。当用户通过验证时，这个方法返回true 值，如果用户没有通过验证则返回false值
		return random.nextBoolean();
	}
	// 8.实现getName()方法。这个方法返回name属性值
	public String getName() {
		return name;
	}
}
