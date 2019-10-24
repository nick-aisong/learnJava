package cn.ch06.ch06_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

// 5.创建名为Decrementer的类实现Runnable接口
public class Decrementer implements Runnable {
	// 6.声明一个私有AtomicIntegerArray属性，命名为vector，用来存放一个整型数字数组
	private AtomicIntegerArray vector;
	// 7.实现类构造器，初始化属性
	public Decrementer(AtomicIntegerArray vector) {
		this.vector = vector;
	}
	// 8.实现run()方法。使用getAndIncrement()方法增加数组中的所有元素
	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndDecrement(i);
		}
	}
}
