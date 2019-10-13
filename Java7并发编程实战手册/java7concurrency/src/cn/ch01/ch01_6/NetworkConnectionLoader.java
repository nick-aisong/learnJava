package cn.ch01.ch01_6;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionLoader implements Runnable {

	@Override
	public void run() {
		System.out.printf("Beginning network connection loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Network connection loading has finished: %s\n", new Date());

	}

}
