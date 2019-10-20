package cn.ch04.ch04_11;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// 1.创建一个名为RejectedTaskController的类，并实现RejectedExecutionHandler接口
// 然后实现接口的rejectedExecution()方法，在控制台输出已被拒绝的任务的名称和执行器的状态
public class RejectedTaskController implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.printf("RejectedTaskController: The task %s has been rejected\n", r.toString());
		System.out.printf("RejectedTaskController: %s\n", executor.toString());
		System.out.printf("RejectedTaskController: Terminating: %s\n", executor.isTerminating());
		System.out.printf("RejectedTaskController: Terminated: %s\n", executor.isTerminated());
	}
}
