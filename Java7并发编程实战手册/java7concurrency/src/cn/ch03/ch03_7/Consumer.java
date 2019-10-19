package cn.ch03.ch03_7;

import java.util.List;
import java.util.concurrent.Exchanger;

// 8.实现消费者类Consumer，并实现Runnable接口
public class Consumer implements Runnable {

	// 9.声明List<String>型对象buffer，它是生产者和消费者进行交换的数据结构
	private List<String> buffer;

	// 10.声明Exchanger<List<String>>型对象exchanger, 用于同步生产者和消费者的交换对象
	private final Exchanger<List<String>> exchanger;

	// 11.实现构造器并初始化这两个属性
	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	// 12.实现run()方法，它循环执行10次数据交换
	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Consumer: Cycle %d\n", cycle);
			// 13. 在每个循环中，消费者要消费数据，所以先调用exchange()方法与生产者同步
			// 由于这个方法会抛出InterruptedException异常，必须捕获并处理这个异常
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 14.将生产者放入消费者buffer列表中的10个字符串打印到控制台，并且从buffer列表中删除，保持为一个空的列表
			// // 每次等Producer把空的buffer传递过来，size都是10
			System.out.println("Consumer: " + buffer.size());
			for (int j = 0; j < 10; j++) {
				String message = buffer.get(0);
				System.out.println("Consumer: " + message);
				buffer.remove(0);
			}
			cycle++;
		}
	}
}
