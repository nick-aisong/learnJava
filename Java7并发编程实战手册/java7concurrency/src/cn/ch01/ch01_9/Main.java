package cn.ch01.ch01_9;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

// 3.实现这个有问题的应用程序的主程序
// 创建一个包含main(方法的Main类。这个方法将创建一个UnsafeTask类对象
// 用它作为传入参数创建10个线程对象并启动这10个线程，每个线程的启动间隔2秒
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

// Starting Thread: 11 : Sun Oct 13 17:13:00 CST 2019
// Starting Thread: 12 : Sun Oct 13 17:13:02 CST 2019
// Starting Thread: 13 : Sun Oct 13 17:13:04 CST 2019
// Starting Thread: 14 : Sun Oct 13 17:13:06 CST 2019
// Thread Finished: 11 : Sun Oct 13 17:13:06 CST 2019
// Thread Finished: 12 : Sun Oct 13 17:13:06 CST 2019
// Starting Thread: 15 : Sun Oct 13 17:13:08 CST 2019
// Thread Finished: 15 : Sun Oct 13 17:13:08 CST 2019
// Starting Thread: 16 : Sun Oct 13 17:13:10 CST 2019
// Starting Thread: 17 : Sun Oct 13 17:13:12 CST 2019
// Thread Finished: 13 : Sun Oct 13 17:13:12 CST 2019
// Thread Finished: 14 : Sun Oct 13 17:13:12 CST 2019
// Starting Thread: 18 : Sun Oct 13 17:13:14 CST 2019
// Thread Finished: 18 : Sun Oct 13 17:13:14 CST 2019
// Thread Finished: 17 : Sun Oct 13 17:13:14 CST 2019
// Starting Thread: 19 : Sun Oct 13 17:13:16 CST 2019
// Thread Finished: 16 : Sun Oct 13 17:13:16 CST 2019
// Thread Finished: 19 : Sun Oct 13 17:13:16 CST 2019
// Starting Thread: 20 : Sun Oct 13 17:13:18 CST 2019
// Thread Finished: 20 : Sun Oct 13 17:13:18 CST 2019

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

// 线程局部变量分别为每个线程存储了各自的属性值，并提供给每个线程使用
// 你可以使用get()方法读取这个值，并用set()方法设置这个值
// 如果线程是第一次访问线程局部变
// 量，线程局部变量可能还没有为它存储值，这个时候initialValue()方法就会被调用，并且返回当前的时间值

// 线程局部变量也提供了remove()方法,用来为访问这个变量的线程删除已经存储的值
// Java并发API包含了InheritableThreadLocal类,如果一个线程是从其他某个线程中创建的，这个类将提供继承的值
// 如果一个线程A在线程局部变量已有值，当它创建其他某个线程B时，线程B的线程局部变量将跟线程A是一样的你可以覆盖childValue()方法
// 这个方法用来初始化子线程在线程局部变量中的值，它使用父线程在线程局部变量中的值作为传入参数

// Starting Thread: 11 : Sun Oct 13 17:21:40 CST 2019
// Starting Thread: 12 : Sun Oct 13 17:21:42 CST 2019
// Starting Thread: 13 : Sun Oct 13 17:21:44 CST 2019
// Starting Thread: 14 : Sun Oct 13 17:21:46 CST 2019
// Thread Finished: 12 : Sun Oct 13 17:21:42 CST 2019
// Thread Finished: 13 : Sun Oct 13 17:21:44 CST 2019
// Starting Thread: 15 : Sun Oct 13 17:21:48 CST 2019
// Thread Finished: 15 : Sun Oct 13 17:21:48 CST 2019
// Thread Finished: 11 : Sun Oct 13 17:21:40 CST 2019
// Starting Thread: 16 : Sun Oct 13 17:21:50 CST 2019
// Starting Thread: 17 : Sun Oct 13 17:21:52 CST 2019
// Thread Finished: 16 : Sun Oct 13 17:21:50 CST 2019
// Starting Thread: 18 : Sun Oct 13 17:21:54 CST 2019
// Thread Finished: 17 : Sun Oct 13 17:21:52 CST 2019
// Thread Finished: 18 : Sun Oct 13 17:21:54 CST 2019
// Thread Finished: 14 : Sun Oct 13 17:21:46 CST 2019
// Starting Thread: 19 : Sun Oct 13 17:21:56 CST 2019
// Starting Thread: 20 : Sun Oct 13 17:21:58 CST 2019
// Thread Finished: 20 : Sun Oct 13 17:21:58 CST 2019
// Thread Finished: 19 : Sun Oct 13 17:21:56 CST 2019
