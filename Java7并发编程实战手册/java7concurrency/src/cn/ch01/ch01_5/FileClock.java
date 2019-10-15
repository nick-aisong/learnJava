package cn.ch01.ch01_5;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// 1.创建一个名为FileClock的类，并且实现Runnable接口
public class FileClock implements Runnable {

	// 2.实现run()方法
	@Override
	public void run() {
		// 3.编写一个执行10次的循环。在每个循环中，创建一个 Date对象，并把它打印到控制台
		// 然后调用TimeUnit类的SECONDS属性的sleep()方法来挂起线程一秒钟，这个值将让线程休眠大概1秒钟
		// sleep()方法会抛出InterruptedException异常，我们必须捕获并处理这个异常
		// 最佳实践是，当线程被中断时，释放或者关闭线程正在使用的资源
		for (int i = 0; i < 100; i++) {
			System.out.printf("%s\n", new Date());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// 这里捕获到中断信号后，只是打印了一条语句，实际线程还会继续执行
				// 更好的处理方式是，释放资源，结束线程
				System.out.printf("The FileClock has been interrupted ");
			}
		}
	}

}
