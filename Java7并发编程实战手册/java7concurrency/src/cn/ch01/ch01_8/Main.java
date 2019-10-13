package cn.ch01.ch01_8;

// 3.实现范例的主程序。实现一个包含main()方法的Main类
public class Main {

	public static void main(String[] args) {

		// 4.创建一个Task对象，并用它作为传入参数来创建一个Thread对象
		// 接着调用setUncaughtExceptionHandler()方法设置线程的运行时异常处理器，然后启动这个线程
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}
}

//当一个线程抛出了异常并且没有被捕获时(这种情况只可能是运行时异常)，JVM检查这个线程是否被预置了未捕获异常处理器
// 如果找到，JVM将调用线程对象的这个方法，并将线程对象和异常作为传入参数
// 如果线程没有被预置未捕获异常处理器,JVM将打印堆栈记录到控制台，并退出程序

// Thread类还有另一个方法可以处理未捕获到的异常，即静态方法setDefaultUncaughtExceptionHandler()
// 这个方法在应用程序中为所有的线程对象创建了一个异常处理器
//
// 当线程抛出一个未捕获到的异常时，JVM将为异常寻找以下三种可能的处理器
//
// 首先，它查找线程对象的未捕获异常处理器
// 如果找不到，JVM继续查找线程对象所在的线程组(ThreadGroup)的未捕获异常处理器
// 如果还是找不到，如同本节所讲的，JVM将继续查找默认的未捕获异常处理器
//
// 如果没有一个处理器存在，JVM则将堆栈异常记录打印到控制台，并退出程序

// An exception has been captured
// Thread: 11
// Exception: java.lang.NumberFormatException: For input string: "TTT"
// Stack Trace:
// java.lang.NumberFormatException: For input string: "TTT"
// 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
// 	at java.lang.Integer.parseInt(Integer.java:580)
// 	at java.lang.Integer.parseInt(Integer.java:615)
// 	at cn.ch01.ch01_8.Task.run(Task.java:9)
// 	at java.lang.Thread.run(Thread.java:748)
// Thread status: RUNNABLE