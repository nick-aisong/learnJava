package cn.ch04.ch04_4;

import java.util.concurrent.Callable;

// 9.创建一个名为TaskValidator 的类，它将通过UserValidation对象作为并发任务来执行用户验证的过程
// 这个类实现了带有String泛型参数的Callable接口
public class TaskValidator implements Callable<String> {
	// 10.声明一个名为validator的私有UserValidator属性
	private UserValidator validator;
	// 11.声明两个私有的String属性，分别为用户名user和密码password
	private String user;
	private String password;

	// 12.实现类的构造器，用来初始化类的属性
	public TaskValidator(UserValidator validator, String user, String password) {
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	// 13.实现call()方法，并返回String对象
	@Override
	public String call() throws Exception {
		// 14.如果用户没有通过UserValidator对象的验证，就在控制台输出没有找到这个用户，表明该用户未通过验证，并抛出Exception类型的异常
		if (!validator.validate(user, password)) {
			System.out.printf("%s: The user has not been found\n", validator.getName());
			throw new Exception("Error validating user");
		}
		// 15.否则，就在控制台输出用户已经找到，表明该用户已经通过验证，然后返回UserValidator对象的名称
		System.out.printf("%s: The user has been found\n", validator.getName());
		return validator.getName();
	}
}
