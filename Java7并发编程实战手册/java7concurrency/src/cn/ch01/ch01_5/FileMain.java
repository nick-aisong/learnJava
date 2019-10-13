package cn.ch01.ch01_5;

import java.util.concurrent.TimeUnit;

// 4.实现范例的主类。创建-一个名为FileMain的类并包含main()方法
public class FileMain {

	public static void main(String[] args) {

		// 5.创建FileClock类的一个 对象，并用它作为传入参数来创建一个Thread对象，然后运行这个线程
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		thread.start();

		// 6.调用TimeUnit类的SECONDS属性的sleep()方法，休眠5秒钟
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 7.中断FileClock线程
		thread.interrupt();
	}
}

// 当调用sleep()方法之后，线程会释放CPU并且不再继续执行任务
// 在这段时间内，线程不占用CPU时钟，所以CPU可以执行其他的任务

// 如果休眠中线程被中断，该方法就会立即抛出InterruptedException异常，而不需要等待到线程休眠时间结束

// Java并发API还提供了另外一个方法来使线程对象释放CPU,即yield()方法，它将通知JVM这个线程对象可以释放CPU了
// JVM并不保证遵循这个要求
// 通常来说，yield()方法只做调试使用

// Sun Oct 13 15:20:00 CST 2019
// Sun Oct 13 15:20:01 CST 2019
// Sun Oct 13 15:20:02 CST 2019
// Sun Oct 13 15:20:03 CST 2019
// Sun Oct 13 15:20:04 CST 2019
// The FileClock has been interrupted Sun Oct 13 15:20:05 CST 2019
// Sun Oct 13 15:20:06 CST 2019
// Sun Oct 13 15:20:07 CST 2019
// Sun Oct 13 15:20:08 CST 2019
// Sun Oct 13 15:20:09 CST 2019