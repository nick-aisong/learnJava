package cn.ch04.ch04_4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// 范例允许用户可以通过两种验证机制进行验证，但是，只要有一种机制验证成功，那么这个用户就被验证通过了

// 16.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

	public static void main(String[] args) {
		// 17.创建两个String对象，分别取名为username和password, 并初始化这两个属性值为test
		String username = "test";
		String password = "test";
		// 18.创建两个UserValidator对象，分别取名为ldapValidator和dbValidator
		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");
		// 19.创建两个TaskValidator对象，分别取名为ldapTask和dbTask，并分别用ldapValidator和dbValidator来初始化他们
		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);
		// 20.创建一个名为taksList的TaskValidator类型列表，并将ldapTask和dbTask添加到列表中
		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);
		// 21.通过Executors工厂类的newCachedThreadPool()方法创建一个新的ThreadPoolExecutor执行器对象，并创建一个名为result的String对象
		ExecutorService executor = Executors.newCachedThreadPool();
		String result;
		// 22.调用执行器的invokeAny()方法。这个方法接收taskList作为参数，并返回String对象
		// 然后，在控制台上输出这个方法返回的String对象
		try {
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n", result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		// 23.通过shutdown()方法来终止执行器，并在控制台输出信息表示程序已经执行结束
		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");
	}
}

// 这个范例的关键点在Main主类中。ThreadPoolExecutor类的invokeAny()方法接收到一个任务列表
// 然后运行任务，并返回第一个完成任务并且没有抛出异常的任务的执行结果
// 这个方法返回的类型与任务里的call()方法返回的类型相同，在这个范例中，它将返回String类型值

// 范例中有两个UserValidator对象，它们返回随机的boolean值
// 每一个UserValidator对象被TaskValidator对象使用，TaskValidator对象实现了Callable接口
// 如果UserValidator类的validate()方法返回false值，那么TaskValidator类将抛出Exception异常，否则，返回true值
// 因此，我们有两个任务可以返回true值或抛出Exception异常。从而，可以有如下4种可能性

// ·如果两个任务都返回true 值，那么invokeAny()方法的结果就是首先完成任务的名称
// ·如果第一个任务返回true值，第二个任务抛出Exception异常，那么invokeAny()方法的结果就是第一个任务的名称
// ·如果第一个任务抛出Exception异常，第二个任务返回true值，那么invokeAny()方法的结果就是第二个任务的名称
// ·如果两个任务都抛出Exception异常，那么invokeAny()方法将抛出ExecutionException异常

// ThreadPoolExecutor类还提供了invokeAny()方法的其他版本：
// invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnitunit)
// 这个方法执行所有的任务，如果在给定的超时期满之前某个任务已经成功完成(也就是未抛出异常)，则返回其结果
// TimeUnit是一个枚举类，有如下的常量: DAYS、 HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

// 任意一个线程找到的结果：
// Validator DataBase: Validating a user during 4 seconds
// Validator LDAP: Validating a user during 8 seconds
// DataBase: The user has been found
// Main: Result: DataBase
// LDAP: The user has not been found
// Main: End of the Execution

// 所有线程都找不到的结果：
// Validator LDAP: Validating a user during 0 seconds
// LDAP: The user has not been found
// Validator DataBase: Validating a user during 0 seconds
// DataBase: The user has not been found
// java.util.concurrent.ExecutionException: java.lang.Exception: Error validating user
// 	at java.util.concurrent.FutureTask.report(FutureTask.java:122)
// 	at java.util.concurrent.FutureTask.get(FutureTask.java:192)
// 	at java.util.concurrent.AbstractExecutorService.doInvokeAny(AbstractExecutorService.java:193)
// 	at java.util.concurrent.AbstractExecutorService.invokeAny(AbstractExecutorService.java:215)
// 	at cn.ch04.ch04_4.Main.main(Main.java:33)
// Caused by: java.lang.Exception: Error validating user
// 	at cn.ch04.ch04_4.TaskValidator.call(TaskValidator.java:27)
// 	at cn.ch04.ch04_4.TaskValidator.call(TaskValidator.java:7)
// 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
// 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
// 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
// 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
// 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
// 	at java.lang.Thread.run(Thread.java:748)
// Main: End of the Execution