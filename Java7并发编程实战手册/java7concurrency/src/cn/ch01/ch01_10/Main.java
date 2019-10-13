package cn.ch01.ch01_10;

import java.util.concurrent.TimeUnit;

// 创建10个线程并让它们休眠一个随机时间(例如模拟一个查询)
// 当其中一个线程查找成功的时候，我们将中断其他的9个线程

// 6.创建一个包含main()方法的主类Main
public class Main {

	public static void main(String[] args) {
		// 7.创建一个标识为Searcher的线程组对象
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		// 8.创建一个Result对象，并用它作为传入参数创建一个SearchTask对象
		Result result = new Result();
		SearchTask searchTask = new SearchTask(result);

		// 9.使用创建的SearchTask对象作为传入参数创建10个线程对象
		// 当调用线程的构造器时，第一个参数是ThreadGroup对象，第二个参数是SearchTask对象
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 10.通过list()方法打印线程组对象的信息
		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		threadGroup.list();

		// 11.通过activeCount()方法获取线程组包含的线程数目，通过enumerate()方法获取线程组包含的线程列表
		// 这两个方法可以帮助我们获取每个线程的信息，如线程的状态
		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i = 0; i < threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}

		// 12.调用waitFinish()方法， 我们将在下面实现这个方法
		// 它将等到线程组的第一个线程运行结束
		waitFinish(threadGroup);
		// 13.使用interrupt()方法中断这个组中的其余线程
		threadGroup.interrupt();
	}

	// 14.实现waitFinish()方法。activeCount()方法被用来检测是否有线程运行结束
	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount() > 9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// Thread Thread-0: Start
// Thread Thread-0: 4
// Thread Thread-1: Start
// Thread Thread-1: 96
// Thread Thread-2: Start
// Thread Thread-2: 23
// Thread Thread-3: Start
// Thread Thread-3: 15
// Thread Thread-4: Start
// Thread Thread-4: 40
// Thread Thread-0: End
// Thread Thread-5: Start
// Thread Thread-5: 31
// Thread Thread-6: Start
// Thread Thread-6: 12
// Thread Thread-7: Start
// Thread Thread-7: 3
// Thread Thread-8: Start
// Thread Thread-8: 31
// Thread Thread-9: Start
// Thread Thread-9: 22
// Thread Thread-7: End
// Number of Threads: 9
// Information about the Thread Group
// java.lang.ThreadGroup[name=Searcher,maxpri=10]
//     Thread[Thread-1,5,Searcher]
//     Thread[Thread-2,5,Searcher]
//     Thread[Thread-3,5,Searcher]
//     Thread[Thread-4,5,Searcher]
//     Thread[Thread-5,5,Searcher]
//     Thread[Thread-6,5,Searcher]
//     Thread[Thread-8,5,Searcher]
//     Thread[Thread-9,5,Searcher]
// Thread Thread-1: TIMED_WAITING
// Thread Thread-2: TIMED_WAITING
// Thread Thread-3: TIMED_WAITING
// Thread Thread-4: TIMED_WAITING
// Thread Thread-5: TIMED_WAITING
// Thread Thread-6: TIMED_WAITING
// Thread Thread-8: TIMED_WAITING
// Thread Thread-9: TIMED_WAITING
// Thread Thread-2: Interrupted
// Thread Thread-5: Interrupted
// Thread Thread-4: Interrupted
// Thread Thread-3: Interrupted
// Thread Thread-1: Interrupted
// Thread Thread-9: Interrupted
// Thread Thread-8: Interrupted
// Thread Thread-6: Interrupted

