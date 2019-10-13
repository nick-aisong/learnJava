package cn.ch01.ch01_7;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

// 5.创建CleanerTask类并继承Thread类
public class CleanerTask extends Thread {

	// 6.声明存放Event对象的队列，并实现一个带参数的构造器,来初始化这个队列对象
	// 同时，在这个构造器中，通过setDaemon()方法把这个线程设置为守护线程
	private Deque<Event> deque;

	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		setDaemon(true);
	}

	// 7.实现run()方法。它将无限制的重复运行，在每次运行中，将获取当前时间，并调用clean()方法
	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	// 8.实现clean()方法。clean()将读取队列的最后一个事件对象，如果这个事件是10秒钟前创建的，就将它删除并且检查下一个
	// 如果有事件被删除，clean()将 打印出这个被删除事件的信息，也打印出队列的长度，这样，我们就可以看到程序的演化过程
	private void clean(Date date) {
		long difference;
		boolean delete;

		System.out.println(deque.size());

		if (deque.size() == 0) {
			return;
		}
		delete = false;
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000) {
				System.out.printf("Cleaner: %s\n", e.getEvent());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);

		if (delete) {
			System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
		}
	}
}
