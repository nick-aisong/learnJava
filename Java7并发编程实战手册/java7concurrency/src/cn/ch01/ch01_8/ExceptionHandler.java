package cn.ch01.ch01_8;

import java.lang.Thread.UncaughtExceptionHandler;

// 1.实现用来处理运行时异常的类。这个类实现UncaughtExceptionHandler接口并且实现这个接口的uncaughtException()方法
// 我们的范例将使用ExceptionHandler类的uncaughtException()方法打印出异常信息和抛出异常的线程代码
public class ExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());
	}
}
