package cn.ch07.ch07_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
// 本例将学习如何覆盖ThreadPoolExecutor类
// 通过范例来计算任务在执行器中执行的时间，执行结束后在控制台输出有关执行器的统计信息

// 9.创建名为Main的主类，并添加main()方法

// 定制ThreadPoolExecutor类
public class Main {

	public static void main(String[] args) {
		// 10.创建一个MyExecutor对象，命名为myExecutor
		MyExecutor myExecutor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		// 11.创建一个Future对象列表results, Future对象的泛型参数为String类
		// 这个列表用来存放即将传递给执行器的任务的返回结果
		List<Future<String>> results = new ArrayList<>();
		// 12.提交10个Task对象
		for (int i = 0; i < 10; i++) {
			SleepTwoSecondsTask task = new SleepTwoSecondsTask();
			Future<String> result = myExecutor.submit(task);
			results.add(result);
		}
		// 13.使用get()方法得到前5个任务的执行结果，并将结果输出到控制台
		for (int i = 0; i < 5; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// 14.使用shutdown()方法完成executor的执行
		myExecutor.shutdown();
		// 15.使用get()方法得到后面5个任务的执行结果，并将结果输出到控制台
		for (int i = 5; i < 10; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// 16.使用awaitTermination()方法等待执行器的完成
		try {
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 17.将程序执行结束的信息输出到控制台
		System.out.printf("Main: End of the program.\n");
	}
}

// 本节实现了一个定制的执行器，它继承了ThreadPoolExecutor类并覆盖了它的4个方法
// beforeExecute()和afterExecute()方法被用来计算任务的运行时间
// beforeExecute()方法在任务开始前执行
// 本例中，使用HashMap存放任务的开始时间
// afterExecute()方法在任务结束后执行
// 通过这两个方法分别获得了任务的开始时间和结束时间，它们的时间间隔就是当前任务的执行时间
// 我们也覆盖了shutdown()和shutdownNow()方法，将执行器执行的任务的统计信息输出到控制台：
// ·通过调用getCompletedTaskCount()方法获得已执行过的任务数
// ·通过调用getActiveCount()方法获得正在执行的任务数

// 对于等待执行的任务，执行器将它们存放在阻塞队列中，调用阻塞队列的size()方法就可以获得等待执行的任务数
// SleepTwoSecondsTask类实现了Callable接口，它让执行线程休眠2s
// Main主类传递了10个任务到执行器中，并使用它和其他类来演示它们的特性
// 运行程序，我们将看到每个任务的执行时间，同时也会看到通过调用shutdown()方法而输出统计的信息

// MyExecutor: A task is beginning: pool-1-thread-1 : 372282300
// MyExecutor: A task is beginning: pool-1-thread-2 : 1780437710
// ***************************************
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:06.346
// Main: Result for Task 0 : 2019-10-25T01:32:06.346
// MyExecutor: Duration: 2239
// ***************************************
// MyExecutor: Result: 2019-10-25T01:32:06.346
// MyExecutor: A task is beginning: pool-1-thread-2 : 1784750396
// Main: Result for Task 1 : 2019-10-25T01:32:06.346
// MyExecutor: Duration: 2239
// ***************************************
// MyExecutor: A task is beginning: pool-1-thread-1 : 426371555
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:08.403
// MyExecutor: Duration: 2000
// ***************************************
// Main: Result for Task 2 : 2019-10-25T01:32:08.403
// MyExecutor: A task is beginning: pool-1-thread-2 : 812307216
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:08.404
// MyExecutor: Duration: 2000
// ***************************************
// Main: Result for Task 3 : 2019-10-25T01:32:08.404
// MyExecutor: A task is beginning: pool-1-thread-1 : 1089962868
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:10.404
// Main: Result for Task 4 : 2019-10-25T01:32:10.404
// MyExecutor: Duration: 2001
// ***************************************
// MyExecutor: Going to shutdown.
// MyExecutor: A task is beginning: pool-1-thread-2 : 539086731
// MyExecutor: Executed tasks: 5
// MyExecutor: Running tasks: 2
// MyExecutor: Pending tasks 3
// Main: Result for Task 5 : 2019-10-25T01:32:10.406
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:10.406
// MyExecutor: Duration: 2001
// ***************************************
// MyExecutor: A task is beginning: pool-1-thread-1 : 425178153
// Main: Result for Task 6 : 2019-10-25T01:32:12.405
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:12.405
// MyExecutor: Duration: 2001
// ***************************************
// MyExecutor: A task is beginning: pool-1-thread-2 : 922405794
// Main: Result for Task 7 : 2019-10-25T01:32:12.407
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:12.407
// MyExecutor: Duration: 2001
// ***************************************
// MyExecutor: A task is beginning: pool-1-thread-1 : 38451933
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:14.407
// MyExecutor: Duration: 2001
// ***************************************
// Main: Result for Task 8 : 2019-10-25T01:32:14.407
// ***************************************
// MyExecutor: A task is finishing.
// MyExecutor: Result: 2019-10-25T01:32:14.409
// MyExecutor: Duration: 2001
// ***************************************
// Main: Result for Task 9 : 2019-10-25T01:32:14.409
// Main: End of the program.
