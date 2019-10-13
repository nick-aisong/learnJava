package cn.ch01.ch01_7;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

// 2.创建WriterTask类，用以实现Runnable接口
public class WriterTask implements Runnable {

	// 3.声明一个存放Event对象的队列，并实现一个带参数的构造器，来初始化这个队列对象
	private Deque<Event> deque;

	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}

	// 4.实现线程的run()方法。它将执行100次循环
	// 在每次循环中，都会创建一个新的Event对象，并放入到队列中，然后休眠1秒钟
	@Override
	public void run() {
		for (int i = 1; i < 100; i++) {
			Event event = new Event();
			event.setDate(new Date());
			event.setEvent(String.format("The thread %s has generate an event", Thread.currentThread().getId()));
			deque.addFirst(event);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
