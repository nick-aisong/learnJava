package cn.ch04.ch04_1;
// 模拟一个Web服务器来应对来自不同客户端的请求

// 15.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		for (int i = 0; i < 100; i++) {
			Task task = new Task("Task " + i);
			server.executeTask(task);
		}
		server.endServer();
	}
}

// 这个范例的核心在于Server类，这个类创建和使用ThreadPoolExecutor执行器来执行任务
// 第一个关键点是在Server类的构造器中创建ThreadPoolExecutor对象
// ThreadPoolExecutor类有4个不同的构造器，但是，由于这些构造器在使用上的复杂性,
// Java并发API提供Executors工厂类来构造执行器和其他相关的对象
// 虽然可以直接通过ThreadPoolExecutor其中之一的构造器来创建ThreadPoolExecutor对象，但是推荐使用Executors工厂类来创建它

// 在这个示例中，通过使用Executors工厂类的newCachedThreadPool()方法创建了一个缓存线程池
// 这个方法返回一个ExecutorService对象，因此它将被强制转换为ThreadPoolExecutor类型，并拥有所有的方法
// 如果需要执行新任务，缓存线程池就会创建新线程
// 如果线程所运行的任务执行完成后并且这个线程可用，那么缓存线程池将会重用这些线程
// 线程重用的优点是减少了创建新线程所花费的时间
// 然而，新任务固定会依赖线程来执行，因此缓存线程池也有缺点，如果发送过多的任务给执行器，系统的负荷将会过载

// 备注：当线程的数量是合理的或者线程只会运行很短的时间时，适合采用Executors工厂类的newCachedThreadPool()方法来创建执行器
// 一旦创建了执行器，就可以使用执行器的execute()方法来发送Runnable或Callable类型的任务
// 这个范例发送实现了Runnable接口的Task类型的对象给执行器
// 范例中也打印了一些执行器相关的日志信息，专门使用了如下方法
// ·getPoolSize():返回执行器线程池中实际的线程数
// ·getActiveCount():返回执行器中正在执行任务的线程数
// ·getCompletedTaskCount() :返回执行器已经完成的任务数

// 执行器以及ThreadPoolExecutor类一个重要的特性是，通常需要显示地去结束它
// 如果不这样做，那么执行器将继续执行，程序也不会结束
// 如果执行器没有任务可执行了，它将继续等待新任务的到来，而不会结束执行
// Java应用程序不会结束直到所有非守护线程结束它们的运行
// 因此，如果没有终止执行器，应用程序将永远不会结束
// 为了完成执行器的执行，可以使用ThreadPoolExecutor类的shutdown()方法
// 当执行器执行完成所有待运行的任务后，它将结束执行
// 调用shutdown()方法之后，如果尝试再发送另一个任务给执行器，任务将被拒绝，并且执行器也将抛出RejectedExecutionException异常

// ThreadPoolExecutor类提供了许多方法来获取自身状态的信息
// 在范例中，已经使用了getPoolSize()方法来获取线程池的大小
// 用getActiveCount()方法来获取线程池中活动线程的数量
// 用getCompletedTaskCount()方法来获取执行器完成的任务数量
// 也可以使用getLargestPoolSize()方法来返回曾经同时位于线程池中的最大线程数

// ThreadPoolExecutor类也提供了结束执行器的相关方法
// ·shutdownNow():这个方法会立即关闭执行器。执行器将不再执行那些正在等待执行的任务
// 这个方法将返回等待执行的任务列表。调用时，正在运行的任务将继续运行，但是这个方法并不等待这些任务完成
// ·isTerminated(): 如果调用了shutdown()或shutdownNow()方法，并且执行器完成了关闭的过程，那么这个方法将返回true
// ·isShutdown():如果调用了shutdown()方法， 那么这个方法将返回true
// ·awaitTermination(ong timeout, TimeUnit unit):这个方法将阻塞所调用的线程，直到执行器完成任务或者达到所指定的timeout值

// TimeUnit是一个枚举类，有如下的常量: DAYS、HOURS、MICROSECONDS、MILLISECONDS、MIINUTES、 NANOSECONDS和SECONDS
// 备注:如果想等待任务的结束，而不管任务的持续时间，可以使用一个大的超时时间，比如DAYS

// Server: A new task has arrived
// Server: Pool Size: 1
// pool-1-thread-1: Task Task 0: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-1: Task Task 0: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Active Count: 1
// Server: Completed Tasks: 0
// Server: A new task has arrived
// Server: Pool Size: 2
// Server: Active Count: 2
// pool-1-thread-1: Task Task 0: Doing a task during 5 seconds
// Server: Completed Tasks: 0
// Server: A new task has arrived
// pool-1-thread-2: Task Task 1: Created on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 3
// pool-1-thread-2: Task Task 1: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-2: Task Task 1: Doing a task during 0 seconds
// pool-1-thread-2: Task Task 1: Finished on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-3: Task Task 2: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-3: Task Task 2: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-3: Task Task 2: Doing a task during 0 seconds
// pool-1-thread-3: Task Task 2: Finished on: Sat Oct 19 14:46:43 CST 2019
// Server: Active Count: 3
// Server: Completed Tasks: 2
// Server: A new task has arrived
// Server: Pool Size: 3
// Server: Active Count: 2
// Server: Completed Tasks: 2
// Server: A new task has arrived
// Server: Pool Size: 3
// Server: Active Count: 2
// Server: Completed Tasks: 2
// Server: A new task has arrived
// Server: Pool Size: 4
// pool-1-thread-2: Task Task 4: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-2: Task Task 4: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-2: Task Task 4: Doing a task during 7 seconds
// pool-1-thread-3: Task Task 3: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-3: Task Task 3: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-3: Task Task 3: Doing a task during 5 seconds
// pool-1-thread-4: Task Task 5: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-4: Task Task 5: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Active Count: 4
// pool-1-thread-4: Task Task 5: Doing a task during 4 seconds
// Server: Completed Tasks: 2
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Active Count: 5
// Server: Completed Tasks: 2
// pool-1-thread-5: Task Task 6: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-5: Task Task 6: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: A new task has arrived
// pool-1-thread-5: Task Task 6: Doing a task during 0 seconds
// pool-1-thread-5: Task Task 6: Finished on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 6
// Server: Active Count: 5
// Server: Completed Tasks: 3
// pool-1-thread-6: Task Task 7: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-6: Task Task 7: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: A new task has arrived
// Server: Pool Size: 6
// pool-1-thread-6: Task Task 7: Doing a task during 1 seconds
// Server: Active Count: 6
// pool-1-thread-5: Task Task 8: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-5: Task Task 8: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Completed Tasks: 3
// pool-1-thread-5: Task Task 8: Doing a task during 4 seconds
// Server: A new task has arrived
// Server: Pool Size: 7
// Server: Active Count: 7
// Server: Completed Tasks: 3
// Server: A new task has arrived
// pool-1-thread-7: Task Task 9: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-7: Task Task 9: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 8
// pool-1-thread-7: Task Task 9: Doing a task during 9 seconds
// pool-1-thread-8: Task Task 10: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-8: Task Task 10: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-8: Task Task 10: Doing a task during 4 seconds
// Server: Active Count: 8
// Server: Completed Tasks: 3
// Server: A new task has arrived
// Server: Pool Size: 9
// Server: Active Count: 9
// Server: Completed Tasks: 3
// Server: A new task has arrived
// Server: Pool Size: 10
// Server: Active Count: 10
// Server: Completed Tasks: 3
// Server: A new task has arrived
// pool-1-thread-9: Task Task 11: Created on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 11
// pool-1-thread-9: Task Task 11: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-10: Task Task 12: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-10: Task Task 12: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-10: Task Task 12: Doing a task during 7 seconds
// pool-1-thread-9: Task Task 11: Doing a task during 1 seconds
// pool-1-thread-11: Task Task 13: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-11: Task Task 13: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-11: Task Task 13: Doing a task during 9 seconds
// Server: Active Count: 11
// Server: Completed Tasks: 3
// Server: A new task has arrived
// Server: Pool Size: 12
// Server: Active Count: 12
// Server: Completed Tasks: 3
// Server: A new task has arrived
// Server: Pool Size: 13
// Server: Active Count: 13
// Server: Completed Tasks: 3
// Server: A new task has arrived
// Server: Pool Size: 14
// Server: Active Count: 14
// Server: Completed Tasks: 3
// Server: A new task has arrived
// Server: Pool Size: 15
// Server: Active Count: 15
// Server: Completed Tasks: 3
// Server: A new task has arrived
// pool-1-thread-12: Task Task 14: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-13: Task Task 15: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-14: Task Task 16: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-14: Task Task 16: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-14: Task Task 16: Doing a task during 9 seconds
// Server: Pool Size: 16
// pool-1-thread-13: Task Task 15: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-12: Task Task 14: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-13: Task Task 15: Doing a task during 4 seconds
// Server: Active Count: 16
// pool-1-thread-12: Task Task 14: Doing a task during 2 seconds
// Server: Completed Tasks: 3
// pool-1-thread-15: Task Task 17: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-15: Task Task 17: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: A new task has arrived
// pool-1-thread-15: Task Task 17: Doing a task during 0 seconds
// pool-1-thread-15: Task Task 17: Finished on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 17
// Server: Active Count: 16
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 17
// Server: Active Count: 16
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 18
// Server: Active Count: 17
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 19
// Server: Active Count: 18
// Server: Completed Tasks: 4
// Server: A new task has arrived
// pool-1-thread-15: Task Task 20: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-15: Task Task 20: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-16: Task Task 18: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-16: Task Task 18: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-16: Task Task 18: Doing a task during 6 seconds
// Server: Pool Size: 20
// Server: Active Count: 20
// Server: Completed Tasks: 4
// pool-1-thread-15: Task Task 20: Doing a task during 9 seconds
// Server: A new task has arrived
// Server: Pool Size: 21
// Server: Active Count: 21
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 22
// Server: Active Count: 22
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 23
// Server: Active Count: 23
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 24
// Server: Active Count: 24
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 25
// Server: Active Count: 25
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 26
// Server: Active Count: 26
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 27
// Server: Active Count: 27
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 28
// Server: Active Count: 28
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 29
// Server: Active Count: 29
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 30
// Server: Active Count: 30
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 31
// Server: Active Count: 31
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 32
// Server: Active Count: 32
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 33
// Server: Active Count: 33
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 34
// Server: Active Count: 34
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 35
// Server: Active Count: 35
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 36
// Server: Active Count: 36
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 37
// Server: Active Count: 37
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 38
// Server: Active Count: 38
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 39
// Server: Active Count: 39
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 40
// Server: Active Count: 40
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 41
// Server: Active Count: 41
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 42
// Server: Active Count: 42
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 43
// Server: Active Count: 43
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 44
// Server: Active Count: 44
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 45
// Server: Active Count: 45
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 46
// Server: Active Count: 46
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 47
// Server: Active Count: 47
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 48
// Server: Active Count: 48
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 49
// Server: Active Count: 49
// Server: Completed Tasks: 4
// Server: A new task has arrived
// Server: Pool Size: 50
// pool-1-thread-17: Task Task 19: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-17: Task Task 19: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-19: Task Task 22: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-19: Task Task 22: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Active Count: 50
// pool-1-thread-18: Task Task 21: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-18: Task Task 21: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Completed Tasks: 4
// pool-1-thread-19: Task Task 22: Doing a task during 1 seconds
// pool-1-thread-22: Task Task 25: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-21: Task Task 24: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-21: Task Task 24: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-17: Task Task 19: Doing a task during 3 seconds
// pool-1-thread-20: Task Task 23: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-20: Task Task 23: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-25: Task Task 28: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-25: Task Task 28: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-24: Task Task 27: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-24: Task Task 27: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-21: Task Task 24: Doing a task during 9 seconds
// pool-1-thread-23: Task Task 26: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-23: Task Task 26: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-22: Task Task 25: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: A new task has arrived
// pool-1-thread-18: Task Task 21: Doing a task during 7 seconds
// pool-1-thread-22: Task Task 25: Doing a task during 4 seconds
// pool-1-thread-23: Task Task 26: Doing a task during 5 seconds
// pool-1-thread-27: Task Task 30: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-24: Task Task 27: Doing a task during 1 seconds
// pool-1-thread-25: Task Task 28: Doing a task during 0 seconds
// pool-1-thread-25: Task Task 28: Finished on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-20: Task Task 23: Doing a task during 0 seconds
// pool-1-thread-26: Task Task 29: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-26: Task Task 29: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-20: Task Task 23: Finished on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-27: Task Task 30: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 51
// pool-1-thread-27: Task Task 30: Doing a task during 7 seconds
// Server: Active Count: 49
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 51
// Server: Active Count: 49
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 51
// Server: Active Count: 49
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 52
// pool-1-thread-26: Task Task 29: Doing a task during 2 seconds
// Server: Active Count: 52
// Server: Completed Tasks: 6
// Server: A new task has arrived
// pool-1-thread-25: Task Task 56: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-25: Task Task 56: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-20: Task Task 55: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-20: Task Task 55: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-28: Task Task 31: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-28: Task Task 31: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-25: Task Task 56: Doing a task during 5 seconds
// pool-1-thread-29: Task Task 32: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-29: Task Task 32: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-29: Task Task 32: Doing a task during 9 seconds
// Server: Pool Size: 53
// pool-1-thread-31: Task Task 34: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-31: Task Task 34: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-30: Task Task 33: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-28: Task Task 31: Doing a task during 7 seconds
// pool-1-thread-20: Task Task 55: Doing a task during 3 seconds
// pool-1-thread-30: Task Task 33: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-30: Task Task 33: Doing a task during 4 seconds
// pool-1-thread-33: Task Task 36: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-33: Task Task 36: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-33: Task Task 36: Doing a task during 2 seconds
// pool-1-thread-31: Task Task 34: Doing a task during 3 seconds
// pool-1-thread-32: Task Task 35: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-32: Task Task 35: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-32: Task Task 35: Doing a task during 6 seconds
// Server: Active Count: 53
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 54
// Server: Active Count: 54
// pool-1-thread-34: Task Task 37: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-34: Task Task 37: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-34: Task Task 37: Doing a task during 9 seconds
// pool-1-thread-36: Task Task 39: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-36: Task Task 39: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-36: Task Task 39: Doing a task during 3 seconds
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 55
// Server: Active Count: 55
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 56
// Server: Active Count: 56
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 57
// Server: Active Count: 57
// Server: Completed Tasks: 6
// pool-1-thread-35: Task Task 38: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-35: Task Task 38: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-35: Task Task 38: Doing a task during 4 seconds
// Server: A new task has arrived
// pool-1-thread-38: Task Task 41: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-38: Task Task 41: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-38: Task Task 41: Doing a task during 3 seconds
// pool-1-thread-39: Task Task 42: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-39: Task Task 42: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-39: Task Task 42: Doing a task during 4 seconds
// pool-1-thread-37: Task Task 40: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-37: Task Task 40: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-37: Task Task 40: Doing a task during 4 seconds
// pool-1-thread-40: Task Task 43: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-40: Task Task 43: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-40: Task Task 43: Doing a task during 6 seconds
// Server: Pool Size: 58
// Server: Active Count: 58
// Server: Completed Tasks: 6
// Server: A new task has arrived
// Server: Pool Size: 59
// Server: Active Count: 59
// Server: Completed Tasks: 6
// Server: A new task has arrived
// pool-1-thread-41: Task Task 44: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-41: Task Task 44: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-41: Task Task 44: Doing a task during 5 seconds
// pool-1-thread-42: Task Task 45: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-42: Task Task 45: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-42: Task Task 45: Doing a task during 5 seconds
// Server: Pool Size: 60
// Server: Active Count: 60
// Server: Completed Tasks: 6
// Server: A new task has arrived
// pool-1-thread-44: Task Task 47: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-44: Task Task 47: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-44: Task Task 47: Doing a task during 5 seconds
// Server: Pool Size: 61
// Server: Active Count: 61
// Server: Completed Tasks: 6
// Server: A new task has arrived
// pool-1-thread-43: Task Task 46: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-43: Task Task 46: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-43: Task Task 46: Doing a task during 8 seconds
// Server: Pool Size: 62
// Server: Active Count: 62
// Server: Completed Tasks: 6
// Server: A new task has arrived
// pool-1-thread-45: Task Task 48: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-45: Task Task 48: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-47: Task Task 50: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-48: Task Task 51: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-49: Task Task 52: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-50: Task Task 53: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-50: Task Task 53: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-51: Task Task 54: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-52: Task Task 57: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-52: Task Task 57: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-53: Task Task 58: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-53: Task Task 58: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-53: Task Task 58: Doing a task during 9 seconds
// pool-1-thread-54: Task Task 59: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-54: Task Task 59: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-54: Task Task 59: Doing a task during 0 seconds
// pool-1-thread-54: Task Task 59: Finished on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-56: Task Task 61: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-56: Task Task 61: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-56: Task Task 61: Doing a task during 3 seconds
// pool-1-thread-62: Task Task 67: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-62: Task Task 67: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-62: Task Task 67: Doing a task during 1 seconds
// pool-1-thread-55: Task Task 60: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-55: Task Task 60: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-55: Task Task 60: Doing a task during 2 seconds
// pool-1-thread-58: Task Task 63: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-58: Task Task 63: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-58: Task Task 63: Doing a task during 9 seconds
// pool-1-thread-57: Task Task 62: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-57: Task Task 62: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-57: Task Task 62: Doing a task during 9 seconds
// pool-1-thread-60: Task Task 65: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-60: Task Task 65: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-60: Task Task 65: Doing a task during 3 seconds
// pool-1-thread-59: Task Task 64: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-59: Task Task 64: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-59: Task Task 64: Doing a task during 3 seconds
// pool-1-thread-61: Task Task 66: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-61: Task Task 66: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-61: Task Task 66: Doing a task during 2 seconds
// pool-1-thread-46: Task Task 49: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-46: Task Task 49: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-46: Task Task 49: Doing a task during 0 seconds
// pool-1-thread-46: Task Task 49: Finished on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-52: Task Task 57: Doing a task during 8 seconds
// pool-1-thread-63: Task Task 68: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-63: Task Task 68: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-63: Task Task 68: Doing a task during 2 seconds
// pool-1-thread-51: Task Task 54: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-51: Task Task 54: Doing a task during 9 seconds
// pool-1-thread-50: Task Task 53: Doing a task during 4 seconds
// pool-1-thread-49: Task Task 52: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-49: Task Task 52: Doing a task during 6 seconds
// pool-1-thread-48: Task Task 51: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-48: Task Task 51: Doing a task during 3 seconds
// pool-1-thread-47: Task Task 50: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-47: Task Task 50: Doing a task during 0 seconds
// pool-1-thread-47: Task Task 50: Finished on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-45: Task Task 48: Doing a task during 3 seconds
// Server: Pool Size: 63
// Server: Active Count: 60
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 63
// Server: Active Count: 60
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 63
// Server: Active Count: 60
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 63
// Server: Active Count: 60
// Server: Completed Tasks: 9
// Server: A new task has arrived
// pool-1-thread-47: Task Task 69: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-47: Task Task 69: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-46: Task Task 70: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-46: Task Task 70: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-46: Task Task 70: Doing a task during 2 seconds
// pool-1-thread-54: Task Task 71: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-54: Task Task 71: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-54: Task Task 71: Doing a task during 6 seconds
// Server: Pool Size: 64
// Server: Active Count: 64
// Server: Completed Tasks: 9
// Server: A new task has arrived
// pool-1-thread-47: Task Task 69: Doing a task during 6 seconds
// Server: Pool Size: 65
// Server: Active Count: 65
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 66
// Server: Active Count: 66
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 67
// Server: Active Count: 67
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 68
// Server: Active Count: 68
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 69
// Server: Active Count: 69
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 70
// Server: Active Count: 70
// Server: Completed Tasks: 9
// pool-1-thread-64: Task Task 72: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-64: Task Task 72: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-64: Task Task 72: Doing a task during 7 seconds
// pool-1-thread-65: Task Task 73: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-65: Task Task 73: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-65: Task Task 73: Doing a task during 3 seconds
// pool-1-thread-66: Task Task 74: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-66: Task Task 74: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-66: Task Task 74: Doing a task during 5 seconds
// pool-1-thread-67: Task Task 75: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-67: Task Task 75: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-68: Task Task 76: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-68: Task Task 76: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-67: Task Task 75: Doing a task during 2 seconds
// pool-1-thread-68: Task Task 76: Doing a task during 8 seconds
// pool-1-thread-69: Task Task 77: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-69: Task Task 77: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-69: Task Task 77: Doing a task during 1 seconds
// pool-1-thread-70: Task Task 78: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-70: Task Task 78: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-70: Task Task 78: Doing a task during 2 seconds
// Server: A new task has arrived
// Server: Pool Size: 71
// Server: Active Count: 71
// Server: Completed Tasks: 9
// Server: A new task has arrived
// pool-1-thread-71: Task Task 79: Created on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 72
// pool-1-thread-71: Task Task 79: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Active Count: 72
// pool-1-thread-71: Task Task 79: Doing a task during 2 seconds
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 73
// Server: Active Count: 73
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 74
// Server: Active Count: 74
// pool-1-thread-72: Task Task 80: Created on: Sat Oct 19 14:46:43 CST 2019
// Server: Completed Tasks: 9
// pool-1-thread-72: Task Task 80: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: A new task has arrived
// pool-1-thread-72: Task Task 80: Doing a task during 6 seconds
// Server: Pool Size: 75
// Server: Active Count: 75
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 76
// Server: Active Count: 76
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 77
// Server: Active Count: 77
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 78
// Server: Active Count: 78
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 79
// Server: Active Count: 79
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 80
// Server: Active Count: 80
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 81
// Server: Active Count: 81
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 82
// Server: Active Count: 82
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 83
// Server: Active Count: 83
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 84
// Server: Active Count: 84
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 85
// Server: Active Count: 85
// pool-1-thread-73: Task Task 81: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-73: Task Task 81: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Completed Tasks: 9
// pool-1-thread-73: Task Task 81: Doing a task during 7 seconds
// Server: A new task has arrived
// Server: Pool Size: 86
// Server: Active Count: 86
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 87
// pool-1-thread-74: Task Task 82: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-75: Task Task 83: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-75: Task Task 83: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Active Count: 87
// pool-1-thread-75: Task Task 83: Doing a task during 4 seconds
// pool-1-thread-74: Task Task 82: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Completed Tasks: 9
// pool-1-thread-74: Task Task 82: Doing a task during 2 seconds
// Server: A new task has arrived
// Server: Pool Size: 88
// Server: Active Count: 88
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 89
// Server: Active Count: 89
// Server: Completed Tasks: 9
// Server: A new task has arrived
// pool-1-thread-76: Task Task 84: Created on: Sat Oct 19 14:46:43 CST 2019
// Server: Pool Size: 90
// pool-1-thread-76: Task Task 84: Started on: Sat Oct 19 14:46:43 CST 2019
// Server: Active Count: 90
// pool-1-thread-76: Task Task 84: Doing a task during 7 seconds
// Server: Completed Tasks: 9
// Server: A new task has arrived
// Server: Pool Size: 91
// Server: Active Count: 91
// Server: Completed Tasks: 9
// pool-1-thread-77: Task Task 85: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-77: Task Task 85: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-77: Task Task 85: Doing a task during 2 seconds
// pool-1-thread-78: Task Task 86: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-78: Task Task 86: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-78: Task Task 86: Doing a task during 7 seconds
// pool-1-thread-79: Task Task 87: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-79: Task Task 87: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-79: Task Task 87: Doing a task during 5 seconds
// pool-1-thread-80: Task Task 88: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-80: Task Task 88: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-80: Task Task 88: Doing a task during 4 seconds
// pool-1-thread-81: Task Task 89: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-81: Task Task 89: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-81: Task Task 89: Doing a task during 1 seconds
// pool-1-thread-82: Task Task 90: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-82: Task Task 90: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-82: Task Task 90: Doing a task during 1 seconds
// pool-1-thread-83: Task Task 91: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-83: Task Task 91: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-83: Task Task 91: Doing a task during 9 seconds
// pool-1-thread-85: Task Task 93: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-85: Task Task 93: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-86: Task Task 94: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-86: Task Task 94: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-86: Task Task 94: Doing a task during 4 seconds
// pool-1-thread-84: Task Task 92: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-84: Task Task 92: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-84: Task Task 92: Doing a task during 0 seconds
// pool-1-thread-84: Task Task 92: Finished on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-87: Task Task 95: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-87: Task Task 95: Started on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-87: Task Task 95: Doing a task during 2 seconds
// pool-1-thread-85: Task Task 93: Doing a task during 5 seconds
// pool-1-thread-88: Task Task 96: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-88: Task Task 96: Started on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-88: Task Task 96: Doing a task during 7 seconds
// pool-1-thread-89: Task Task 97: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-89: Task Task 97: Started on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-89: Task Task 97: Doing a task during 7 seconds
// pool-1-thread-90: Task Task 98: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-90: Task Task 98: Started on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-91: Task Task 99: Created on: Sat Oct 19 14:46:43 CST 2019
// pool-1-thread-91: Task Task 99: Started on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-91: Task Task 99: Doing a task during 2 seconds
// pool-1-thread-90: Task Task 98: Doing a task during 6 seconds
// pool-1-thread-6: Task Task 7: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-9: Task Task 11: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-19: Task Task 22: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-24: Task Task 27: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-62: Task Task 67: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-69: Task Task 77: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-82: Task Task 90: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-81: Task Task 89: Finished on: Sat Oct 19 14:46:44 CST 2019
// pool-1-thread-12: Task Task 14: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-26: Task Task 29: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-33: Task Task 36: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-55: Task Task 60: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-61: Task Task 66: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-63: Task Task 68: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-46: Task Task 70: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-67: Task Task 75: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-70: Task Task 78: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-71: Task Task 79: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-74: Task Task 82: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-77: Task Task 85: Finished on: Sat Oct 19 14:46:45 CST 2019
// pool-1-thread-87: Task Task 95: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-91: Task Task 99: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-17: Task Task 19: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-31: Task Task 34: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-20: Task Task 55: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-36: Task Task 39: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-38: Task Task 41: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-56: Task Task 61: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-60: Task Task 65: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-48: Task Task 51: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-45: Task Task 48: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-59: Task Task 64: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-65: Task Task 73: Finished on: Sat Oct 19 14:46:46 CST 2019
// pool-1-thread-4: Task Task 5: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-5: Task Task 8: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-8: Task Task 10: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-13: Task Task 15: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-22: Task Task 25: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-30: Task Task 33: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-35: Task Task 38: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-39: Task Task 42: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-37: Task Task 40: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-50: Task Task 53: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-75: Task Task 83: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-80: Task Task 88: Finished on: Sat Oct 19 14:46:47 CST 2019
// pool-1-thread-86: Task Task 94: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-1: Task Task 0: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-3: Task Task 3: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-23: Task Task 26: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-25: Task Task 56: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-41: Task Task 44: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-42: Task Task 45: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-44: Task Task 47: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-66: Task Task 74: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-79: Task Task 87: Finished on: Sat Oct 19 14:46:48 CST 2019
// pool-1-thread-85: Task Task 93: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-16: Task Task 18: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-32: Task Task 35: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-40: Task Task 43: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-49: Task Task 52: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-54: Task Task 71: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-47: Task Task 69: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-72: Task Task 80: Finished on: Sat Oct 19 14:46:49 CST 2019
// pool-1-thread-90: Task Task 98: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-2: Task Task 4: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-10: Task Task 12: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-18: Task Task 21: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-27: Task Task 30: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-28: Task Task 31: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-64: Task Task 72: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-73: Task Task 81: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-76: Task Task 84: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-78: Task Task 86: Finished on: Sat Oct 19 14:46:50 CST 2019
// pool-1-thread-89: Task Task 97: Finished on: Sat Oct 19 14:46:51 CST 2019
// pool-1-thread-88: Task Task 96: Finished on: Sat Oct 19 14:46:51 CST 2019
// pool-1-thread-43: Task Task 46: Finished on: Sat Oct 19 14:46:51 CST 2019
// pool-1-thread-52: Task Task 57: Finished on: Sat Oct 19 14:46:51 CST 2019
// pool-1-thread-68: Task Task 76: Finished on: Sat Oct 19 14:46:51 CST 2019
// pool-1-thread-7: Task Task 9: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-11: Task Task 13: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-14: Task Task 16: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-15: Task Task 20: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-21: Task Task 24: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-29: Task Task 32: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-34: Task Task 37: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-53: Task Task 58: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-58: Task Task 63: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-57: Task Task 62: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-51: Task Task 54: Finished on: Sat Oct 19 14:46:52 CST 2019
// pool-1-thread-83: Task Task 91: Finished on: Sat Oct 19 14:46:53 CST 2019

