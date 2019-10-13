package cn.ch01.ch01_10;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// 2.创建一个名为SearchTask的类，它实现了Runnable接口
public class SearchTask implements Runnable {

	// 3.声明一个Result类的私有属性，并实现带参数的构造器(Constructor), 来为这个属性设置值
	private Result result;

	public SearchTask(Result result) {
		this.result = result;
	}

	// 4.实现run()方法。 它将调用doTask()方法， 并等待它完成或者抛出一个InterruptedException异常
	// run()方法也将打印出线程的开始、结束或者中断等信息
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.printf("Thread %s: Start\n", name);
		try {
			doTask();
			result.setName(name);
		} catch (InterruptedException e) {
			System.out.printf("Thread %s: Interrupted\n", name);
			return;
		}
		System.out.printf("Thread %s: End\n", name);
	}

	// 5.实现doTask()方法。它创建Random对象来生成一个随机数，并用它做为传入参数调用sleep()方法
	private void doTask() throws InterruptedException {
		Random random = new Random(new Date().getTime());
		int value = (int) (random.nextDouble() * 100);
		System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
		TimeUnit.SECONDS.sleep(value);
	}
}
