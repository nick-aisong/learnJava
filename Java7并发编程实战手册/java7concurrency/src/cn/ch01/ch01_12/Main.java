package cn.ch01.ch01_12;

// 6.创建范例的主程序。创建一个包含main()方法的主类Main
public class Main {

	public static void main(String[] args) {

		// 7.创建一个MyThreadFactory对象，创建一个Task对象
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
		Task task = new Task();
		// 8.使用工厂类MyThreadFactory创建10个线程对象，并且启动它们
		Thread thread;
		System.out.printf("Starting the Thread\n");
		for (int i = 0; i < 10; i++) {
			thread = factory.newThread(task);
			thread.start();
		}
		// 9.将线程工厂的统计打印到控制台
		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());
	}
}

// 可以通过增加一些变化来强化实现方法覆盖
//   ·创建一个性化线程， 如本范例使用一个特殊的格式作为线程名，或者通过继承Thread类来创建自己的线程类
//   ·保存新创建的线程的统计数据，如本节的范例那样
//   ·限制创建的线程的数量
//   ·对生成的线 程进行验证
//   ·更多你可以想到的

// 使用工厂设计模式是一个很好的编程实践，但是，如果是通过实现ThreadFactory接口
// 来创建线程，你必须检查代码，以保证所有的线程都是使用这个工厂创建的

// Starting the Thread
// Factory stats:
// Created thread 11 with name MyThreadFactory-Thread_0 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 12 with name MyThreadFactory-Thread_1 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 13 with name MyThreadFactory-Thread_2 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 14 with name MyThreadFactory-Thread_3 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 15 with name MyThreadFactory-Thread_4 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 16 with name MyThreadFactory-Thread_5 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 17 with name MyThreadFactory-Thread_6 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 18 with name MyThreadFactory-Thread_7 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 19 with name MyThreadFactory-Thread_8 on Sun Oct 13 21:36:56 CST 2019
//
// Created thread 20 with name MyThreadFactory-Thread_9 on Sun Oct 13 21:36:56 CST 2019