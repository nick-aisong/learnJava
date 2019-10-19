package cn.ch04.ch04_1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// 8.创建一个名为Server的类，它将执行通过执行器接收到的每一个任务
public class Server {
	// 9.声明一个名为executor的ThreadPoolExecutor属性
	private ThreadPoolExecutor executor;
	// 10.实现类的构造器，通过Executors类来初始化ThreadPoolExecutor对象
	public Server() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}
	// 11. 实现executeTask()方法。它接收一个Task对象作为参数，并将Task对象发送给执行器
	// 在控制台输出一条信息表示新的任务已经到达
	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		// 12.调用执行器的execute()方法将任务发送给Task
		executor.execute(task);
		// 13.在控制台输出一些执行器相关的数据来观察执行器的状态
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}
	// 14.实现endServer()方法。在这个方法里，调用执行器的shutdown()方法来结束它的执行
	public void endServer() {
		executor.shutdown();
	}
}
