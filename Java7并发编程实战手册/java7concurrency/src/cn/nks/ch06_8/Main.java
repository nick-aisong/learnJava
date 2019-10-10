package cn.nks.ch06_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

// 使用原子数组
public class Main {

	public static void main(String[] args) {
		final int THREADS = 100;
		AtomicIntegerArray vector = new AtomicIntegerArray(1000);

		Incrementer incrementer = new Incrementer(vector);
		Decrementer decrementer = new Decrementer(vector);

		Thread[] threadIncrementers = new Thread[THREADS];
		Thread[] threadDecrementres = new Thread[THREADS];

		for (int i = 0; i < THREADS; i++) {
			threadIncrementers[i] = new Thread(incrementer);
			threadDecrementres[i] = new Thread(decrementer);

			threadIncrementers[i].start();
			threadDecrementres[i].start();
		}

		for (int i = 0; i < THREADS; i++) {
			try {
				threadIncrementers[i].join();
				threadDecrementres[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < vector.length(); i++) {
			if (vector.get(i) != 0) {
				System.out.println("Vector[" + i + "] : " + vector.get(i));
			}
		}
		System.out.println("Main: End fo the example");
	}
}
