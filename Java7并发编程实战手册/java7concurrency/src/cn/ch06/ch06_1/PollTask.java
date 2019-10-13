package cn.ch06.ch06_1;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable {

	private ConcurrentLinkedDeque<String> list;

	public PollTask(ConcurrentLinkedDeque<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50000; i++) {
			list.pollFirst();
			list.peekLast();
		}
	}
}
