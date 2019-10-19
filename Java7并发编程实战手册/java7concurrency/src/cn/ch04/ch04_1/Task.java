package cn.ch04.ch04_1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// 1.实现将被Web服务器执行的任务。创建一个名为Task的类，并实现Runnable接口
public class Task implements Runnable {
	// 2.声明一个名为initDate的私有Date属性，用来存储任务的创建时间
	// 然后创建一个名为name的私有String属性，用来存储任务的名称
	private Date initDate;
	private String name;
	// 3.实现类的构造器，用来初始化这两个属性
	public Task(String name) {
		initDate = new Date();
		this.name = name;
	}
	// 4.实现run()方法
	@Override
	public void run() {
		// 5.在控制台上输出initDate属性和实际时间，即任务的开始时间
		System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());
		// 6.将任务休眠一段随机时间
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %s seconds\n", Thread.currentThread().getName(), name,
					duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 7.在控制台输入任务的完成时间
		System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
	}
}
