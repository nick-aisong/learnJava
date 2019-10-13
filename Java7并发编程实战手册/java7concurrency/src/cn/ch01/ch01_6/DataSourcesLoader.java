package cn.ch01.ch01_6;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// 1.创建名为DataSourcesLoader的类，并且实现Runnable接口
public class DataSourcesLoader implements Runnable {

	// 2.实现run()方法。这个方法先显示一个表明它开始执行的信息，然后休眠4秒钟,再显示另一个信息表明已完成当前执行
	@Override
	public void run() {
		System.out.printf("Beginning data sources loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Data sources loading has finished: %s\n", new Date());
	}
}
