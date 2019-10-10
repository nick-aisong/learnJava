package cn.nks.ch07_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 在Executor对象中使用ThreadFactory
public class Main {

	public static void main(String[] args) throws Exception {
		MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");
		ExecutorService executor = Executors.newCachedThreadPool(threadFactory);
		MyTask task = new MyTask();
		executor.submit(task);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.printf("Main: End of the program.\n");
	}

}
