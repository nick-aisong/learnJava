package cn.ch01.ch01_4;

import java.util.concurrent.TimeUnit;

// 6.实现这个范例的主类。实现一个包含main()方法的Main类
public class Main {

	public static void main(String[] args) {
		// 7.创建FileSearch类的一个对象，并用它作为传入参数来创建一个线程对象， 然后启动线程执行任务
		FileSearch fileSearch = new FileSearch("C:\\Windows\\System32", "hosts");
		Thread thread = new Thread(fileSearch);
		thread.start();

		// 8.等待1秒钟，然后中断线程
		// 如果等线程跑完，还没中断它，那线程就会正常退出
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}

// 当运行这个范例时，程序将进入文件夹查找是否包含指定的文件
// 例如，如果要查找的文件夹目录结构是\b\c\d,这个程序将递归调用processDirectory()方法3次
// 不管递归调用了多少次，只要线程检测到它已经被中断了
// 就会立即抛出InterruptedException异常，然后继续执行run()方法

// Thread-0 : C:\Windows\System32\drivers\etc\hosts
// Thread-0: The search has been interrupted