package cn.ch01.ch01_7;

import java.util.ArrayDeque;
import java.util.Deque;

// 9.现在实现主类。创建一个包含main(方法的Main类
public class Main {

	public static void main(String[] args) {
		// 10.创建一个队列对象Deque，用来存放事件
		Deque<Event> deque = new ArrayDeque<Event>();

		// 11. 创建三个WriterTask线程和一个CleanerTask线程，并启动它们
		WriterTask writer = new WriterTask(deque);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(writer);
			thread.start();
		}
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();
	}
}

// setDaemon()方法只能在start()方法被调用之前设置。一旦线程开始运行，将不能再修改守护状态
// isDaemon()方法被用来检查一个线程是不是守护线程，返回值true表示这个线程是守护线程，false表示这个线程是用户线程

// 可能因为我电脑CPU是4核的，导致实际的结果和书上解释有些出入
// 书上说队列size维持在30个左右，实际后来超过100