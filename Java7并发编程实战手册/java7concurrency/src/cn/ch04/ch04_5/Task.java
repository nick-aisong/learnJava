package cn.ch04.ch04_5;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// 4.创建一个名为Task的类，并实现Callable接口，接口的泛型参数为Result类型
public class Task implements Callable<Result> {
	// 5.声明一个名为name的私有String属性
	private String name;
	// 6.实现类的构造器，用来初始化类的属性
	public Task(String name) {
		this.name = name;
	}
	// 7.实现call()方法。在这个范例中，这个方法将返回一个Result类型的对象
	@Override
	public Result call() throws Exception {
		// 8.在控制台输出表示任务开始的信息
		System.out.printf("%s: Starting\n", this.name);
		// 9.等待一段随机时间
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 10.生成一个int值，准备作为返回Result对象中的int属性，这个int值为5个随机数的总和
		int value = 0;
		for (int i = 0; i < 5; i++) {
			value += (int) (Math.random() * 100);
		}
		// 11.创建一个Result对象,并用任务的名称和上一步计算的int值来对其进行初始化
		Result result = new Result();
		result.setName(this.name);
		result.setValue(value);
		// 12.在控制台输出信息表示任务执行结束
		System.out.println(this.name + ": Ends");
		// 13.返回Result对象
		return result;
	}
}
