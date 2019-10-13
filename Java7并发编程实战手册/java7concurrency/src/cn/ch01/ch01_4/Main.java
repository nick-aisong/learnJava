package cn.ch01.ch01_4;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		FileSearch fileSearch = new FileSearch("C:\\Windows\\System32", "hosts");
		Thread thread = new Thread(fileSearch);
		thread.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}

}
