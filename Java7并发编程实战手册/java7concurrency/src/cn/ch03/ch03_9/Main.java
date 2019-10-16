package cn.ch03.ch03_9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

// 在本例中，我们将学习如何使用Phaser类同步三个并发任务
// 这三个任务将在三个不同的文件夹及其子文件夹中查找过去24小时内修改过扩展名为.log的文件
// 这个任务分成以下三个步骤：
// 1.在指定的文件夹及其子文件夹中获得扩展名为.log的文件
// 2.对第一步的结果进行过滤，删除修改时间超过24小时的文件
// 3.将结果打印到控制台

// 在第一步和第二步结束的时候，都会检查所查找到的结果列表是不是有元素存在
// 如果结果列表是空的，对应的线程将结束执行，并且从phaser中删除

public class Main {

	public static void main(String[] args) {
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();
		
		Exchanger<List<String>> exchanger = new Exchanger<>();
		
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);

		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}

}
