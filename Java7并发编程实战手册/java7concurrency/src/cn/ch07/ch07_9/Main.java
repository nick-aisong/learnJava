package cn.ch07.ch07_9;

import java.util.concurrent.TimeUnit;

// 实现基于优先级的传输队列
public class Main {

	public static void main(String[] args) throws Exception {
		
		MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<Event>();
		Producer producer = new Producer(buffer);
		Thread[] producerThreads = new Thread[10];
		for (int i = 0; i < producerThreads.length; i++) {
			producerThreads[i] = new Thread(producer);
			producerThreads[i].start();
		}

		Consumer consumer = new Consumer(buffer);
		Thread consumerTread = new Thread(consumer);
		consumerTread.start();
		
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
		Event myEvent = new Event("Core Event", 0);
		buffer.transfer(myEvent);
		System.out.printf("Main: My Event has been transfered.\n");

		for (int i = 0; i < producerThreads.length; i++) {
			producerThreads[i].join();
		}

		TimeUnit.SECONDS.sleep(1);
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());

		myEvent = new Event("Core Event 2", 0);
		buffer.transfer(myEvent);
		consumerTread.join();
		System.out.printf("Main: End of the program\n");
	}
}
