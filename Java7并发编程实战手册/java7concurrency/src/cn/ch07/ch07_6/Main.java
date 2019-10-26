package cn.ch07.ch07_6;

import java.util.concurrent.ForkJoinPool;
// 在本例，我们将学习如何实现一个定制的工作线程(Worker Thread)
// 它被ForkJoinPool类使用，此外我们还将学习如何通过工厂模式来使用它

// 定制运行在Fork / Join框架中的任务
public class Main {

	public static void main(String[] args) {
		int[] array = new int[10000];
		ForkJoinPool pool = new ForkJoinPool();
		Task task = new Task("Task", array, 0, array.length);
		pool.invoke(task);
		pool.shutdown();
		System.out.printf("Main: End of the program.\n");
	}
}
