package cn.ch03.ch03_7;

import java.util.List;
import java.util.concurrent.Exchanger;

// 1.实现生产者类Producer, 并且实现Runnable接口
public class Producer implements Runnable {

	// 2.声明List<String>型对象buffer，它是生产者和消费者进行交换的数据结构
	private List<String> buffer;

	// 3.声明Exchanger<List<String>>型对象exchanger，用于同步生产者和消费者的交换对象
	private final Exchanger<List<String>> exchanger;

	// 4.实现构造器并初始化这两个属性
	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	// 5.实现run()方法。它循环执行10次数据交换
	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Producer: Cycle %d\n", cycle);
			// 6.在每个循环中，添加10个字符串到buffer列表中
			for (int j = 0; j < 10; j++) {
				String message = "Event " + ((i * 10) + j);
				System.out.printf("Producer: %s\n", message);
				buffer.add(message);
			}
			// 7.调用exchange()方法与消费者进行数据交换
			// 由于这个方法会抛出InterruptedException异常，必须捕获并处理这个异常
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 每次等Consumer把空的buffer传递过来，size都是0
			System.out.println("Producer: " + buffer.size());
			cycle++;
		}
	}
}
