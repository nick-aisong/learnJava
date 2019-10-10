package cn.nks.ch06_6;

import cn.nks.ch06_5.TaskLocalRandom;

// 生成并发随机数
public class Main {

	public static void main(String[] args) {
		Thread[] threads = new Thread[3];
		for (int i = 0; i < 3; i++) {
			TaskLocalRandom task = new TaskLocalRandom();
			threads[i] = new Thread(task);
			threads[i].start();
		}
	}
}
