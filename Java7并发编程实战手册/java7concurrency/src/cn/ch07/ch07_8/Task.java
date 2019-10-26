package cn.ch07.ch07_8;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	private MyLock lock;
	private String name;

	public Task(String name, MyLock lock) {
		this.lock = lock;
		this.name = name;
	}

	@Override
	public void run() {
		lock.lock();
		System.out.printf("Task: %s: Take the lock\n", name);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
