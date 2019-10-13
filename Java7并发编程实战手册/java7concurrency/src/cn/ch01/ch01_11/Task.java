package cn.ch01.ch01_11;

import java.util.Random;

// 3.创建一个Task类，它实现了Runnable接口
public class Task implements Runnable {

	// 4.实现run()方法。在这个方法里，我们要触发AritmethicException 异常
	// 为了达到目标，我们用1000除以一个随机数，当随机数生成器生成0，异常将被抛出
	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			result = 1000 / (int) (random.nextDouble() * 1000);
			System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}
	}
}
