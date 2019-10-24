package cn.ch07.ch07_1;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 1.创建名为MyExecutor的类，并继承ThreadPoolExecutor类
public class MyExecutor extends ThreadPoolExecutor {
	// 2.声明一个私有ConcurrentHashMap属性，其泛型参数为String和Date，命名为startTimes
	private ConcurrentHashMap<String, Date> startTimes;
	// 3.实现类构造器。使用super关键字调用父类构造器，然后初始化startTime属性
	public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		startTimes = new ConcurrentHashMap<>();
	}
	// 4.覆盖shutdown()方法。将已执行过的任务、正在执行的任务和等待执行的任务的信息输出到控制台
	// 接下来，使用super关键字调用父类的shutdown()方法
	@Override
	public void shutdown() {
		System.out.printf("MyExecutor: Going to shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
		System.out.printf("MyExecutor: Pending tasks %d\n", getQueue().size());
		super.shutdown();
	}
	// 5.覆盖shutdownNoww()方法。将已执行过的任务、正在执行的任务和等待执行的任务的信息输出到控制台
	// 接下来，使用super关键字调用父类的shutdownNow()方法
	@Override
	public List<Runnable> shutdownNow() {
		System.out.printf("MyExecutor: Going to immediately shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
		System.out.printf("MyExecutor: Pending tasks %d\n", getQueue().size());
		return super.shutdownNow();
	}
	// 6.覆盖beforeExecute()方法。输出将要执行的线程的名字、任务的哈希码(HashCode)、开始日期存放到HashMap中
	// 它是以任务的哈希码值作为主键的
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.printf("MyExecutor: A task is beginning: %s : %s\n", t.getName(), r.hashCode());
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}
	// 7.覆盖afterExecute()方法。将任务的执行结果输出到控制台，用当前时间减去存放在并发HashMap中的起始日期来计算任务的运行时间
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Future<?> result = (Future<?>) r;
		try {
			System.out.printf("***************************************\n");
			System.out.printf("MyExecutor: A task is finishing.\n");
			System.out.printf("MyExecutor: Result: %s\n", result.get());
			Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
			Date finishDate = new Date();
			long diff = finishDate.getTime() - startDate.getTime();
			System.out.printf("MyExecutor: Duration: %d\n", diff);
			System.out.printf("***************************************\n");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
