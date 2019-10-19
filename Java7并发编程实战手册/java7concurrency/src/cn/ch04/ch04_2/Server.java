package cn.ch04.ch04_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

	private ThreadPoolExecutor executor;

	// 1.打开Server类并修改它的构造器，使用newFixedThreadPool()方法来创建执行器，并传递数字5作为它的参数
	public Server() {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}

	// 2.修改executeTask()方法，增加一行打印日志信息。 调用getTaskCount()方法来获取已发送到执行器上的任务数
	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Task Count: %d\n", executor.getTaskCount());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}

	public void endServer() {
		executor.shutdown();
	}
}
