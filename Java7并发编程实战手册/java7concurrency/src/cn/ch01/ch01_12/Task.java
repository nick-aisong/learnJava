package cn.ch01.ch01_12;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("running-----" + Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep(5);
			System.out.println("stop-----" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
