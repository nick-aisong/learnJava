package cn.ch01.ch01_6;

import java.util.Date;

// 4.创建一个包含main()方法的Main类
public class Main {

	public static void main(String[] args) {
		// 5.创建一个DataSourcesLoader对象，并用它作为传入参数来创建一个线程
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader);
		// 6.创建一个NetworkConnectionsLoader对象，并用它作为传入参数来创建一个线程
		NetworkConnectionLoader ncLoader = new NetworkConnectionLoader();
		Thread thread2 = new Thread(ncLoader);

		// 7.调用start()方法启动这两个线程对象
		thread1.start();
		thread2.start();

		// 8.使用join()方法等待两个线程的终止
		// join()方法会抛出InterruptedException 异常，我们必须捕获并处理这个异常
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 9.程序运行结束时，打印出信息
		System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
	}
}

// Java提供了另外两种形式的join()方法:
// join(long milliseconds)
// join(long miliseconds, long nanos)
// 当一个线程调用其他某个线程的join()方法时，如果使用的是第一种join()方式，那么
// 它不必等到被调用线程运行终止，如果参数指定的毫秒时钟已经到达，它将继续运行
// 例如，thread1 中有这样的代码thread2.join(1000)，thread1将挂起运行，直到满足下面两个条件之一:
//		·thread2运行已经完成
//		·时钟已经过去1000毫秒
// 当两个条件中的任何一条 成立时，join()方法将返回
// 第二种join()方法跟第-种相似，只是需要接受毫秒和纳秒两个参数


// Beginning network connection loading: Sun Oct 13 15:46:27 CST 2019
// Beginning data sources loading: Sun Oct 13 15:46:27 CST 2019
// Data sources loading has finished: Sun Oct 13 15:46:31 CST 2019
// Network connection loading has finished: Sun Oct 13 15:46:33 CST 2019
// Main: Configuration has been loaded: Sun Oct 13 15:46:33 CST 2019