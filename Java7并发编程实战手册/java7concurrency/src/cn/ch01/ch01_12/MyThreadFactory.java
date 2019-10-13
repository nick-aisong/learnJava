package cn.ch01.ch01_12;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

// 1.创建名为MyThreadFactory的类，并且实现ThreadFactory接口
public class MyThreadFactory implements ThreadFactory {

	// 2.声明3个属性：
	// 整型数字counter,用来存储线程对象的数量
	// 字符串name，用来存放每个线程的名称
	// 字符串列表stats， 用来存放线程对象的统计数据
	// 同时实现带参数的构造器来初始化这3个属性
	private int counter;
	private String name;
	private List<String> stats;

	public MyThreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}

	// 3.实现newThread()方法。这个方法以Runnable接口对象为参数，并且返回参数对应的线程对象
	// 这里我们创建一个线程对象并生成线程名称，保存统计数据
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + "-Thread_" + counter);
		counter++;
		stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
		return t;
	}

	// 4.实现getStatistics()方法，返回一个字符串对象，用来表示所有线程对象的统计数据
	public String getStats() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		while (it.hasNext()) {
			buffer.append(it.next());
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
