package cn.ch01.ch01_6;

import java.util.Date;

public class Main {

	public static void main(String[] args) {

		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader);
		NetworkConnectionLoader ncLoader = new NetworkConnectionLoader();
		Thread thread2 = new Thread(ncLoader);

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Configuration has been loaded: %s\n", new Date());

	}
}
