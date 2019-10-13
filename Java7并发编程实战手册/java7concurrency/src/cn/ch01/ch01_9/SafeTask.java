package cn.ch01.ch01_9;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// 使用线程局部变量机制来解决这个问题
// 6.创建一个SafeTask类，用以实现Runnable接口
public class SafeTask implements Runnable {

	// 7.声明一个ThreadLocal<Date>对象。这个对象是在initialValue()方法中隐式实现的
	// 这个方法将返回当前日期
	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
		protected Date initialValue() {
			return new Date();
		}
	};

	// 8.实现run()方法。它跟UnsafeTask类的run(方法实现了一样的功能,但是访问startDate属性的方式改变了
	@Override
	public void run() {
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());
		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate.get());
	}
}
