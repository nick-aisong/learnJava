package cn.ch08.ch08_5;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Test;

public class Task implements Runnable {

	@Override
	public void run() {
		Logger logger = MyLogger.getLogger(this.getClass().getName());

		// entering 记录在文件里
		logger.entering(Thread.currentThread().getName(), "run()");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.exiting(Thread.currentThread().getName(), "run()", Thread.currentThread());
	}

	@Test
	public void test() {
		System.out.println(this.getClass());
		System.out.println(this.getClass().getName());
		Logger logger = Logger.getLogger(this.getClass().getName());

		// entering 记录在文件里
		logger.entering(Thread.currentThread().getName(), "test()");
	}

}
