package cn.ch01.ch01_9;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// 1.使要实现的范例具有之前提到的共享问题。创建一个名为UnsafeTask的类，它实现了Runnable接口
// 声明一个私有的java.util.Date属性
public class UnsafeTask implements Runnable {

	private Date startDate;

	@Override
	public void run() {
		// 2.实现run()方法，这个方法将初始化startDate属性，并且将值打印到控制台，让线程休眠一个随机时间，然后再次将startDate的值打印到控制台
		startDate = new Date();
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate);

		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate);
	}
}
