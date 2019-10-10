package cn.nks.ch01_9;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class Main {

	public static void main(String[] args) {
		UnsafeTask task = new UnsafeTask();
		
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testSafeTask() {
		SafeTask task = new SafeTask();
		
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
