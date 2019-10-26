package cn.ch07.ch07_2;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 本例将学习如何实现一个执行器，范例使用优先级队列存放传递给它的任务

// 8.创建名为Main的主类，并添加main()方法

// 实现基于优先级的Executor类
public class Main {

	public static void main(String[] args) {
		// 9.创建一个ThreadPoolExecutor对象，命名为executor
		// 传入参数PriorityBlockingQueue的泛型参数为Runnable接口，它用来存放等待执行的任务
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS,
				new PriorityBlockingQueue<Runnable>());
		// 10.传递4个任务到执行器，使用循环计算器作为任务的优先级
		// 使用execute()方法发送任务到执行器
		for (int i = 0; i < 4; i++) {
			MyPriorityTask task = new MyPriorityTask("Task " + i, i);
			executor.execute(task);
		}
		// 11.使当前线程休眠1s
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 12.再传递4个任务到执行器，使用循环计算器作为任务的优先级。使用execute()方法发送任务到执行器
		for (int i = 4; i < 8; i++) {
			MyPriorityTask task = new MyPriorityTask("Task " + i, i);
			executor.execute(task);
		}
		// 13.使用shutdown()方法关闭执行器
		executor.shutdown();
		// 14.使用awaitTermination()方法等待执行器结束
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 15.将程序执行结束的信息输出到控制台
		System.out.printf("Main: End of the program.\n");
	}
}
