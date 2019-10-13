package cn.ch01.ch01_12;

import java.util.concurrent.TimeUnit;

// 5.创建名为Task的类并且实现Runnable接口
// 在这个范例中，线程除了只休眠1秒
public class Task implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
