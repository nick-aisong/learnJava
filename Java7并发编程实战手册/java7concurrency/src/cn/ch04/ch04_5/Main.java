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