package cn.ch01.ch01_11;

// 1.创建一个MyThreadGroup类，并继承ThreadGroup
// 必须声明带参数的构造器，因为ThreadGroup没有默认的不带参数的构造器
public class MyThreadGroup extends ThreadGroup {

	public MyThreadGroup(String name) {
		super(name);
	}

	// 2.覆盖uncaughtException()方法
	// 当线程组中的任何线程对象抛出异常的时候，这个方法就会被调用
	// 在这里，这个方法将打印异常信息和抛出异常的线程代码到控制台，还将中断线程组中的其他线程
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}
}
