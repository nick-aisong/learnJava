package cn.ch04.ch04_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 14. 实现范例的主类，创建Main主类，并实现main()方法
public class Main {

	public static void main(String[] args) {
		// 15.通过Executors工厂类的newCachedThreadPool()方法创建一个ThreadPoolExecutor执行器对象
		ExecutorService executor = Executors.newCachedThreadPool();
		// 16.创建一个Task类型的任务列表taskList。创建3个Task任务并将它们添加到任务列表taskList中
		List<Task> taskList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Task task = new Task(i + "");
			taskList.add(task);
		}
		// 17.创建一个Future类型的结果列表resultList。这些对象泛型参数为Result类型
		List<Future<Result>> resultList = null;
		try {
			// 18.调用ThreadPoolExecutor类的invokeAll()方法。这个方法将返回上一步所创建的Future类型的列表
			resultList = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 19.调用shutdown()方法结束执行器
		executor.shutdown();
		// 20.在控制台输出任务处理的结果，即Future类型列表中的Result结果
		System.out.println("Main: Printing the results");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Result> future = resultList.get(i);
			try {
				Result result = future.get();
				System.out.println(result.getName() + ": " + result.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

// 本例子展示了发送任务列表给执行器，并且通过invokeAll()方法等待所有任务的完成
// 这个方法接收一个Callable对象列表，并返回一个Future对象列表。在这个列表中，每一个任务对应一个Future对象
// Future对象列表中的第一个对象控制Callable列表中第一个任务，以此类推
// 需要注意的一点是，在存储结果的列表声明中，用在Future接口中的泛型参数的数据类型必须与Callable接口的泛型数据类型相兼容
// 在这个例子中，我们使用的是相同的数据类型：Result类
// 另一个关于invokeAll()方法重要的地方是，使用Future对象仅用来获取任务的结果
// 当所有的任务执行结束时这个方法也执行结束了，如果在返回的Future对象上调用isDone()方法，那么所有的调用将返回true值

// ExecutorService接口还提供了invokeAll()方法的另一个版本：
// invokeAll(Collection<? extends Callable <T>> tasks, long timeout,TimeUnit unit)：
// 当所有任务执行完成，或者超时的时候(无论哪个首先发生)，这个方法将返回保持任务状态和结果的Future列表
// TimeUnit是一个枚举类，有如下的常量: DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

// 0: Starting
// 2: Starting
// 1: Starting
// 0: Waiting 2 seconds for results.
// 2: Waiting 1 seconds for results.
// 1: Waiting 1 seconds for results.
// 2: Ends
// 1: Ends
// 0: Ends
// Main: Printing the results
// 0: 199
// 1: 234
// 2: 278