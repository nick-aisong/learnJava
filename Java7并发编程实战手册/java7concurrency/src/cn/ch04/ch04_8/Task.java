package cn.ch04.ch04_8;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

	@Override
	public String call() throws Exception {
		while(true) {
			System.out.printf("Task: Test\n");
			Thread.sleep(100);
		}
	}

}
