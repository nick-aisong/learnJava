package cn.ch01.ch01_6;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// 3.创建一个NetworkConnectionsLoader类，用以实现Runnable接口
// 实现run()方法的方式与DataSourcesLoade的run()方法类似，但是它休眠6秒钟
public class NetworkConnectionLoader implements Runnable {

	@Override
	public void run() {
		System.out.printf("Beginning network connection loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Network connection loading has finished: %s\n", new Date());
	}
}
