package cn.ch04.ch04_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 9.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

	public static void main(String[] args) {
		// 10.通过Executors工厂类的newFixedThreadPool()方法创建ThreadPoolExecutor执行器来运行任务
		// 传递参数2给newFixedThreadPool()方法表示执行器将最多创建两个线程
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		// 11.创建一个Future<Integer>类型的列表对象resultLis
		List<Future<Integer>> resultList = new ArrayList<>();
		// 12.通过Random类创建一个名random的随机数字生成器
		Random random = new Random();
		// 13.生成10个介于0~10之间的随机整数
		for (int i = 0; i < 10; i++) {
			Integer number = random.nextInt(10);
			// 14.创建FactorialCaculator对象，并将随机数number传递给它作为参数
			FactorialCalculator calculator = new FactorialCalculator(number);
			// 15.调用执行器的submit()方法发送FactorialCalculator任务给执行器
			// 这个方法返回一个Future<Integer>对象来管理任务和得到的最终结果
			Future<Integer> result = executor.submit(calculator);
			// 16.将Future对象添加到前面创建的resultList列表中
			resultList.add(result);
		}
		// 17.创建一个do循环来监控执行器的状态
		do {
			// 18.通过执行器的getCompletedTaskNumber()方法，在控制台输出信息表示任务完成的数量
			System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());
			// 19.遍历resultList列表中的10个Future对象，通过调用isDone()方法来输出表示任务是否完成的信息
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}
			// 20.将线程休眠50毫秒
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		// 21.若执行器中完成的任务数量小于10，则一直重复执行这个循环
		} while (executor.getCompletedTaskCount() < resultList.size());
		// 22.在控制台上输出每一个任务得到的结果。对于每一个 Future对象来讲，通过调用get()方法将得到由任务返回的Integer对象
		System.out.printf("Main: Results\n");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				number = result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			// 23.在控制台上打印出数字number
			System.out.printf("Main: Task %d: %d\n", i, number);
		}
		// 24.调用执行器的shutdown()方法结束执行
		executor.shutdown();
	}
}

// 在本节，我们学习了如何使用Callable接口来启动并发任务并返回结果
// 我们编写了FactorialCalculator类，它实现了带有泛型参数Integer类型的Callable接口
// 因此，这个Integer类型将作为在调用call()方法时返回的类型
// 范例的另一个关键点在Main主类中。我们通过submit()方法发送一个Callable对象给执行器去执行
// 这个submit()方法接收Callable对象作为参数，并返回Future对象
// Future对象可以用于以下两个主要目的
// ·控制任务的状态:可以取消任务和检查任务是否已经完成。为了达到这个目的，可使用isDone()方法来检查任务是否已经完成
// ·通过call()方法获取返回的结果。为了达到这个目的，可使用get()方法。这个方法一直等待直到Callable对象的call()方法执行完成并返回结果
// 如果get()方法在等待结果时线程中断了，则将抛出一个InterruptedException 异常
// 如果call()方法抛出异常那么get()方法将随之抛出ExecutionException异常

// 在调用Future对象的get()方法时，如果Future对象所控制的任务并未完成，那么这个方法将一直阻塞到任务完成
// Future接口也提供了get()方法的其他调用方式
// ·get(long timeout, TimeUnit unit):如果调用这个方法时，任务的结果并未准备好，则方法等待所指定的timeout时间
// 如果等待超过了指定的时间而任务的结果还没有准备好，那么这个方法将返回null

// TimeUnit是一个枚举类，有如下的常量: DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

// Main: Number of Completed Tasks: 0
// Main: Task 0: false
// Main: Task 1: false
// Main: Task 2: false
// Main: Task 3: false
// Main: Task 4: false
// Main: Task 5: false
// Main: Task 6: false
// Main: Task 7: false
// Main: Task 8: false
// Main: Task 9: false
// pool-1-thread-2: 24
// Main: Number of Completed Tasks: 1
// Main: Task 0: false
// Main: Task 1: true
// Main: Task 2: false
// Main: Task 3: false
// Main: Task 4: false
// Main: Task 5: false
// Main: Task 6: false
// Main: Task 7: false
// Main: Task 8: false
// Main: Task 9: false
// pool-1-thread-1: 5040
// Main: Number of Completed Tasks: 1
// Main: Task 0: false
// Main: Task 1: true
// Main: Task 2: false
// Main: Task 3: false
// Main: Task 4: false
// Main: Task 5: false
// Main: Task 6: false
// Main: Task 7: false
// Main: Task 8: false
// Main: Task 9: false
// Main: Number of Completed Tasks: 2
// Main: Task 0: true
// Main: Task 1: true
// Main: Task 2: false
// Main: Task 3: false
// Main: Task 4: false
// Main: Task 5: false
// Main: Task 6: false
// Main: Task 7: false
// Main: Task 8: false
// Main: Task 9: false
// pool-1-thread-2: 5040
// Main: Number of Completed Tasks: 3
// Main: Task 0: true
// Main: Task 1: true
// Main: Task 2: true
// Main: Task 3: false
// Main: Task 4: false
// Main: Task 5: false
// Main: Task 6: false
// Main: Task 7: false
// Main: Task 8: false
// Main: Task 9: false
// pool-1-thread-1: 5040
// pool-1-thread-1: 1
// pool-1-thread-2: 120
// pool-1-thread-2: 1
// Main: Number of Completed Tasks: 7
// Main: Task 0: true
// Main: Task 1: true
// Main: Task 2: true
// Main: Task 3: true
// Main: Task 4: true
// Main: Task 5: true
// Main: Task 6: false
// Main: Task 7: true
// Main: Task 8: false
// Main: Task 9: false
// pool-1-thread-1: 6
// pool-1-thread-2: 6
// Main: Number of Completed Tasks: 9
// Main: Task 0: true
// Main: Task 1: true
// Main: Task 2: true
// Main: Task 3: true
// Main: Task 4: true
// Main: Task 5: true
// Main: Task 6: true
// Main: Task 7: true
// Main: Task 8: true
// Main: Task 9: false
// Main: Number of Completed Tasks: 9
// Main: Task 0: true
// Main: Task 1: true
// Main: Task 2: true
// Main: Task 3: true
// Main: Task 4: true
// Main: Task 5: true
// Main: Task 6: true
// Main: Task 7: true
// Main: Task 8: true
// Main: Task 9: false
// pool-1-thread-1: 720
// Main: Results
// Main: Task 0: 5040
// Main: Task 1: 24
// Main: Task 2: 5040
// Main: Task 3: 5040
// Main: Task 4: 120
// Main: Task 5: 1
// Main: Task 6: 6
// Main: Task 7: 1
// Main: Task 8: 6
// Main: Task 9: 720
