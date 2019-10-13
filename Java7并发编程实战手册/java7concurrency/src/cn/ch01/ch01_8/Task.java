package cn.ch01.ch01_8;

// 2.实现一个抛出运行时异常的线程类，命名为Task, 它实现了Runnable接口，在实现run()方法时强制抛出运行时异常
// 例如，把一个String值转换成int值
public class Task implements Runnable {

	@Override
	public void run() {
		int number = Integer.parseInt("TTT");
	}
}
