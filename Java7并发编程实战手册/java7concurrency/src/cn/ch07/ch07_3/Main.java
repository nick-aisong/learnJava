package cn.ch07.ch07_3;

// 实现ThreadFactory接口生成定制线程
public class Main {

	public static void main(String[] args) throws Exception {

		MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");
		MyTask task = new MyTask();
		Thread thread = myFactory.newThread(task);
		thread.start();
		thread.join();
		System.out.printf("Main: Thread information.\n");
		System.out.printf("%s\n", thread);
		System.out.printf("Main: End of the example.\n");
	}
}
