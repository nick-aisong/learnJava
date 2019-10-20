package cn.ch04.ch04_11;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// 这些任务实现了RejectedExecutionHandler接口

// 9.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

	public static void main(String[] args) {
		// 10.创建RejectedTaskController对象来管理被拒绝的任务
		RejectedTaskController controller = new RejectedTaskController();
		// 11.调用Executors工厂类的newCachedThreadPool()方法创建ThreadPoolExecutor执行器对象
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		// 12.设置用于被拒绝的任务的处理程序
		executor.setRejectedExecutionHandler(controller);
		// 13.创建3个任务并发送给执行器
		System.out.printf("Main: Starting.\n");
		for(int i=0; i<3; i++) {
			Task task = new Task("Task "+i);
			executor.submit(task);
		}
		// 14.调用shutdown()方法关闭执行器
		System.out.printf("Main: Shutting down the Executor.\n");
		executor.shutdown();
		// 15. 创建另一个任务并发送给执行器
		System.out.printf("Main: Sending another Task.\n");
		Task task = new Task("RejectedTask");
		executor.submit(task);
		// 16.在控制台输出信息表示程序结束
		System.out.println("Main: End");
		System.out.printf("Main: End.\n");
	}
}

// 我们可以看到被拒绝的任务，当执行已经关闭，RejectecedTaskController在控制台输出任务和执行器的信息
// 为了处理在执行器中被拒绝的任务，需要创建一个实现RejectedExecutionHandler接口的处理类
// 这个接口有一个rejectedExecution()方法，其中有两个参数：
// ·一个Runnable对象，用来存储被拒绝的任务
// ·一个Executor对象，用来存储任务被拒绝的执行器，被执行器拒绝的每一个任务都将调用这个方法
// 需要先调用Executor类的setRejectedExecutionHandler()方法来设置用于被拒绝的任务的处理程序

// 当执行器接收一个任务并开始执行时，它先检查shutdown()方法是否已经被调用了
// 如果是，那么执行器就拒绝这个任务
// 首先，执行器会寻找通过setRejectedExecutionHandler()方法设置的用于被拒绝的任务的处理程序
// 如果找到一个处理程序，执行器就调用其rejectedExecution()方法
// 否则就抛出RejectedExecutionExeption异常这是一个运行时异常，因此并不需要catch语句来对其进行处理

// Main: Starting.
// Main: Shutting down the Executor.
// Main: Sending another Task.
// RejectedTaskController: The task java.util.concurrent.FutureTask@266474c2 has been rejected
// Task Task 2: Starting
// Task Task 1: Starting
// RejectedTaskController: java.util.concurrent.ThreadPoolExecutor@6f94fa3e[Shutting down, pool size = 3, active threads = 3, queued tasks = 0, completed tasks = 0]
// RejectedTaskController: Terminating: true
// RejectedTaskController: Terminated: false
// Main: End
// Main: End.
// Task Task 0: Starting
// Task: Task 2: ReportGenerator: Generating a report during 1 seconds
// Task: Task 1: ReportGenerator: Generating a report during 7 seconds
// Task: Task 0: ReportGenerator: Generating a report during 7 seconds
// Task Task 2: Ending
// Task Task 1: Ending
// Task Task 0: Ending