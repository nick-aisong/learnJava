package cn.ch03.ch03_1;

// 8.创建一个工作类Job，并且实现Runnable接口。这个类将文档发送到打印机
public class Job implements Runnable {

	// 9.声明一个打印队列对象printQueue
	private PrintQueue printQueue;

	// 10.实现构造器，用来初始化打印队列对象printQueue
	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	// 11.实现run()方法
	@Override
	public void run() {
		// 12. 首先，这个方法将已经开始运行的Job信息打印到控制台
		System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
		// 13.调用打印队列对象的printJob()方法
		printQueue.printJob(new Object());
		// 14.将工作完成的信息打印到控制台
		System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
	}
}
