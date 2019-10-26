package cn.ch08.ch08_6;

import java.util.concurrent.locks.ReentrantLock;

// 使用FindBugs分析并发代码
public class Main {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();

		for (int i = 0; i < 10; i++) {
			Task task = new Task(lock);
			Thread thread = new Thread(task);
			thread.run();
		}
	}
}
