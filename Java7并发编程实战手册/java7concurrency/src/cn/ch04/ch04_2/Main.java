package cn.ch04.ch04_2;

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

// 在这个示例中，使用Executors工厂类的newFixedThreadPool()方法来创建执行器
// 这个方法创建了具有线程最大数量值的执行器
// 如果发送超过线程数的任务给执行器，剩余的任务将被阻塞直到线程池里有空闲的线程来处理它们
// newFixedThreadPool()方法接收执行器将拥有的线程数量的最大值作为参数
// 这个例子创建了一个线程数量的最大值为5的执行器

// 为了在程序中输出相关信息，已经使用的ThreadPoolExecutor类的一些方法如下
// ·getPoolSize(): 返回执行器中线程的实际数量
// ·getActiveCount(): 返回执行器正在执行任务的线程数量
// 将看到，控制台输出的信息是5，表示执行器拥有5个线程，并且执行器不会超过这个最大的线程连接数

// 当发送最后一个任务给执行器时，由于执行器只有5个活动的线程，所以剩余的95个任务只能等待空闲线程
// getTaskCount()方法可以用来显示有多少个任务已经发送给执行器

// Executors工厂类也提供newSingleThreadExecutor()方法
// 这是一个创建固定大小线程执行器的极端场景，它将创建一个只有单个线程的执行器
// 因此，这个执行器只能在同一时间执行一个任务

// Server: A new task has arrived
// Server: Pool Size: 1
// pool-1-thread-1: Task Task 0: Created on: Sat Oct 19 15:56:17 CST 2019
// pool-1-thread-1: Task Task 0: Started on: Sat Oct 19 15:56:18 CST 2019
// Server: Task Count: 1
// Server: Active Count: 1
// Server: Completed Tasks: 0
// pool-1-thread-1: Task Task 0: Doing a task during 4 seconds
// Server: A new task has arrived
// Server: Pool Size: 2
// Server: Task Count: 2
// Server: Active Count: 2
// Server: Completed Tasks: 0
// Server: A new task has arrived
// Server: Pool Size: 3
// Server: Task Count: 3
// Server: Active Count: 3
// Server: Completed Tasks: 0
// Server: A new task has arrived
// Server: Pool Size: 4
// Server: Task Count: 4
// Server: Active Count: 4
// Server: Completed Tasks: 0
// Server: A new task has arrived
// Server: Pool Size: 5
// pool-1-thread-2: Task Task 1: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 1: Started on: Sat Oct 19 15:56:18 CST 2019
// Server: Task Count: 5
// pool-1-thread-2: Task Task 1: Doing a task during 0 seconds
// pool-1-thread-3: Task Task 2: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 2: Started on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 4: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 4: Started on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 1: Finished on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 3: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 3: Started on: Sat Oct 19 15:56:18 CST 2019
// Server: Active Count: 5
// pool-1-thread-4: Task Task 3: Doing a task during 9 seconds
// pool-1-thread-5: Task Task 4: Doing a task during 5 seconds
// pool-1-thread-3: Task Task 2: Doing a task during 6 seconds
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 6
// pool-1-thread-2: Task Task 5: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 5: Started on: Sat Oct 19 15:56:18 CST 2019
// Server: Active Count: 5
// pool-1-thread-2: Task Task 5: Doing a task during 1 seconds
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 7
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 8
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 9
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 10
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 11
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 12
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 13
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 14
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 15
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 16
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 17
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 18
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 19
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 20
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 21
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 22
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 23
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 24
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 25
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 26
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 27
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 28
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 29
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 30
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 31
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 32
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 33
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 34
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 35
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 36
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 37
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 38
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 39
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 40
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 41
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 42
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 43
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 44
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 45
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 46
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 47
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 48
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 49
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 50
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 51
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 52
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 53
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 54
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 55
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 56
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 57
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 58
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 59
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 60
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 61
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 62
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 63
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 64
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 65
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 66
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 67
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 68
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 69
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 70
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 71
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 72
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 73
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 74
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 75
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 76
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 77
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 78
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 79
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 80
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 81
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 82
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 83
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 84
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 85
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 86
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 87
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 88
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 89
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 90
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 91
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 92
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 93
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 94
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 95
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 96
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 97
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 98
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 99
// Server: Active Count: 5
// Server: Completed Tasks: 1
// Server: A new task has arrived
// Server: Pool Size: 5
// Server: Task Count: 100
// Server: Active Count: 5
// Server: Completed Tasks: 1
// pool-1-thread-2: Task Task 5: Finished on: Sat Oct 19 15:56:19 CST 2019
// pool-1-thread-2: Task Task 6: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 6: Started on: Sat Oct 19 15:56:19 CST 2019
// pool-1-thread-2: Task Task 6: Doing a task during 6 seconds
// pool-1-thread-1: Task Task 0: Finished on: Sat Oct 19 15:56:22 CST 2019
// pool-1-thread-1: Task Task 7: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 7: Started on: Sat Oct 19 15:56:22 CST 2019
// pool-1-thread-1: Task Task 7: Doing a task during 1 seconds
// pool-1-thread-1: Task Task 7: Finished on: Sat Oct 19 15:56:23 CST 2019
// pool-1-thread-1: Task Task 8: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 8: Started on: Sat Oct 19 15:56:23 CST 2019
// pool-1-thread-1: Task Task 8: Doing a task during 8 seconds
// pool-1-thread-5: Task Task 4: Finished on: Sat Oct 19 15:56:23 CST 2019
// pool-1-thread-5: Task Task 9: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 9: Started on: Sat Oct 19 15:56:23 CST 2019
// pool-1-thread-5: Task Task 9: Doing a task during 9 seconds
// pool-1-thread-3: Task Task 2: Finished on: Sat Oct 19 15:56:24 CST 2019
// pool-1-thread-3: Task Task 10: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 10: Started on: Sat Oct 19 15:56:24 CST 2019
// pool-1-thread-3: Task Task 10: Doing a task during 2 seconds
// pool-1-thread-2: Task Task 6: Finished on: Sat Oct 19 15:56:25 CST 2019
// pool-1-thread-2: Task Task 11: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 11: Started on: Sat Oct 19 15:56:25 CST 2019
// pool-1-thread-2: Task Task 11: Doing a task during 1 seconds
// pool-1-thread-3: Task Task 10: Finished on: Sat Oct 19 15:56:26 CST 2019
// pool-1-thread-3: Task Task 12: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 12: Started on: Sat Oct 19 15:56:26 CST 2019
// pool-1-thread-3: Task Task 12: Doing a task during 7 seconds
// pool-1-thread-2: Task Task 11: Finished on: Sat Oct 19 15:56:26 CST 2019
// pool-1-thread-2: Task Task 13: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 13: Started on: Sat Oct 19 15:56:26 CST 2019
// pool-1-thread-2: Task Task 13: Doing a task during 0 seconds
// pool-1-thread-2: Task Task 13: Finished on: Sat Oct 19 15:56:26 CST 2019
// pool-1-thread-2: Task Task 14: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 14: Started on: Sat Oct 19 15:56:26 CST 2019
// pool-1-thread-2: Task Task 14: Doing a task during 5 seconds
// pool-1-thread-4: Task Task 3: Finished on: Sat Oct 19 15:56:27 CST 2019
// pool-1-thread-4: Task Task 15: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 15: Started on: Sat Oct 19 15:56:27 CST 2019
// pool-1-thread-4: Task Task 15: Doing a task during 1 seconds
// pool-1-thread-4: Task Task 15: Finished on: Sat Oct 19 15:56:28 CST 2019
// pool-1-thread-4: Task Task 16: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 16: Started on: Sat Oct 19 15:56:28 CST 2019
// pool-1-thread-4: Task Task 16: Doing a task during 5 seconds
// pool-1-thread-1: Task Task 8: Finished on: Sat Oct 19 15:56:31 CST 2019
// pool-1-thread-1: Task Task 17: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 17: Started on: Sat Oct 19 15:56:31 CST 2019
// pool-1-thread-1: Task Task 17: Doing a task during 4 seconds
// pool-1-thread-2: Task Task 14: Finished on: Sat Oct 19 15:56:31 CST 2019
// pool-1-thread-2: Task Task 18: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 18: Started on: Sat Oct 19 15:56:31 CST 2019
// pool-1-thread-2: Task Task 18: Doing a task during 1 seconds
// pool-1-thread-5: Task Task 9: Finished on: Sat Oct 19 15:56:32 CST 2019
// pool-1-thread-5: Task Task 19: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 19: Started on: Sat Oct 19 15:56:32 CST 2019
// pool-1-thread-5: Task Task 19: Doing a task during 5 seconds
// pool-1-thread-2: Task Task 18: Finished on: Sat Oct 19 15:56:32 CST 2019
// pool-1-thread-2: Task Task 20: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 20: Started on: Sat Oct 19 15:56:32 CST 2019
// pool-1-thread-2: Task Task 20: Doing a task during 6 seconds
// pool-1-thread-3: Task Task 12: Finished on: Sat Oct 19 15:56:33 CST 2019
// pool-1-thread-3: Task Task 21: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 21: Started on: Sat Oct 19 15:56:33 CST 2019
// pool-1-thread-3: Task Task 21: Doing a task during 3 seconds
// pool-1-thread-4: Task Task 16: Finished on: Sat Oct 19 15:56:33 CST 2019
// pool-1-thread-4: Task Task 22: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 22: Started on: Sat Oct 19 15:56:33 CST 2019
// pool-1-thread-4: Task Task 22: Doing a task during 0 seconds
// pool-1-thread-4: Task Task 22: Finished on: Sat Oct 19 15:56:33 CST 2019
// pool-1-thread-4: Task Task 23: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 23: Started on: Sat Oct 19 15:56:33 CST 2019
// pool-1-thread-4: Task Task 23: Doing a task during 2 seconds
// pool-1-thread-1: Task Task 17: Finished on: Sat Oct 19 15:56:35 CST 2019
// pool-1-thread-1: Task Task 24: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 24: Started on: Sat Oct 19 15:56:35 CST 2019
// pool-1-thread-1: Task Task 24: Doing a task during 7 seconds
// pool-1-thread-4: Task Task 23: Finished on: Sat Oct 19 15:56:35 CST 2019
// pool-1-thread-4: Task Task 25: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 25: Started on: Sat Oct 19 15:56:35 CST 2019
// pool-1-thread-4: Task Task 25: Doing a task during 8 seconds
// pool-1-thread-3: Task Task 21: Finished on: Sat Oct 19 15:56:36 CST 2019
// pool-1-thread-3: Task Task 26: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 26: Started on: Sat Oct 19 15:56:36 CST 2019
// pool-1-thread-3: Task Task 26: Doing a task during 0 seconds
// pool-1-thread-3: Task Task 26: Finished on: Sat Oct 19 15:56:36 CST 2019
// pool-1-thread-3: Task Task 27: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 27: Started on: Sat Oct 19 15:56:36 CST 2019
// pool-1-thread-3: Task Task 27: Doing a task during 0 seconds
// pool-1-thread-3: Task Task 27: Finished on: Sat Oct 19 15:56:36 CST 2019
// pool-1-thread-3: Task Task 28: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 28: Started on: Sat Oct 19 15:56:36 CST 2019
// pool-1-thread-3: Task Task 28: Doing a task during 9 seconds
// pool-1-thread-5: Task Task 19: Finished on: Sat Oct 19 15:56:37 CST 2019
// pool-1-thread-5: Task Task 29: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 29: Started on: Sat Oct 19 15:56:37 CST 2019
// pool-1-thread-5: Task Task 29: Doing a task during 4 seconds
// pool-1-thread-2: Task Task 20: Finished on: Sat Oct 19 15:56:38 CST 2019
// pool-1-thread-2: Task Task 30: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 30: Started on: Sat Oct 19 15:56:38 CST 2019
// pool-1-thread-2: Task Task 30: Doing a task during 6 seconds
// pool-1-thread-5: Task Task 29: Finished on: Sat Oct 19 15:56:41 CST 2019
// pool-1-thread-5: Task Task 31: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 31: Started on: Sat Oct 19 15:56:41 CST 2019
// pool-1-thread-5: Task Task 31: Doing a task during 8 seconds
// pool-1-thread-1: Task Task 24: Finished on: Sat Oct 19 15:56:42 CST 2019
// pool-1-thread-1: Task Task 32: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 32: Started on: Sat Oct 19 15:56:42 CST 2019
// pool-1-thread-1: Task Task 32: Doing a task during 9 seconds
// pool-1-thread-4: Task Task 25: Finished on: Sat Oct 19 15:56:43 CST 2019
// pool-1-thread-4: Task Task 33: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 33: Started on: Sat Oct 19 15:56:43 CST 2019
// pool-1-thread-4: Task Task 33: Doing a task during 1 seconds
// pool-1-thread-4: Task Task 33: Finished on: Sat Oct 19 15:56:44 CST 2019
// pool-1-thread-4: Task Task 34: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 34: Started on: Sat Oct 19 15:56:44 CST 2019
// pool-1-thread-2: Task Task 30: Finished on: Sat Oct 19 15:56:44 CST 2019
// pool-1-thread-2: Task Task 35: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 35: Started on: Sat Oct 19 15:56:44 CST 2019
// pool-1-thread-4: Task Task 34: Doing a task during 7 seconds
// pool-1-thread-2: Task Task 35: Doing a task during 8 seconds
// pool-1-thread-3: Task Task 28: Finished on: Sat Oct 19 15:56:45 CST 2019
// pool-1-thread-3: Task Task 36: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 36: Started on: Sat Oct 19 15:56:45 CST 2019
// pool-1-thread-3: Task Task 36: Doing a task during 9 seconds
// pool-1-thread-5: Task Task 31: Finished on: Sat Oct 19 15:56:49 CST 2019
// pool-1-thread-5: Task Task 37: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 37: Started on: Sat Oct 19 15:56:49 CST 2019
// pool-1-thread-5: Task Task 37: Doing a task during 4 seconds
// pool-1-thread-1: Task Task 32: Finished on: Sat Oct 19 15:56:51 CST 2019
// pool-1-thread-1: Task Task 38: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 38: Started on: Sat Oct 19 15:56:51 CST 2019
// pool-1-thread-1: Task Task 38: Doing a task during 9 seconds
// pool-1-thread-4: Task Task 34: Finished on: Sat Oct 19 15:56:51 CST 2019
// pool-1-thread-4: Task Task 39: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 39: Started on: Sat Oct 19 15:56:51 CST 2019
// pool-1-thread-4: Task Task 39: Doing a task during 5 seconds
// pool-1-thread-2: Task Task 35: Finished on: Sat Oct 19 15:56:52 CST 2019
// pool-1-thread-2: Task Task 40: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 40: Started on: Sat Oct 19 15:56:52 CST 2019
// pool-1-thread-2: Task Task 40: Doing a task during 2 seconds
// pool-1-thread-5: Task Task 37: Finished on: Sat Oct 19 15:56:53 CST 2019
// pool-1-thread-5: Task Task 41: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 41: Started on: Sat Oct 19 15:56:53 CST 2019
// pool-1-thread-5: Task Task 41: Doing a task during 9 seconds
// pool-1-thread-3: Task Task 36: Finished on: Sat Oct 19 15:56:54 CST 2019
// pool-1-thread-3: Task Task 42: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 42: Started on: Sat Oct 19 15:56:54 CST 2019
// pool-1-thread-3: Task Task 42: Doing a task during 8 seconds
// pool-1-thread-2: Task Task 40: Finished on: Sat Oct 19 15:56:54 CST 2019
// pool-1-thread-2: Task Task 43: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 43: Started on: Sat Oct 19 15:56:54 CST 2019
// pool-1-thread-2: Task Task 43: Doing a task during 9 seconds
// pool-1-thread-4: Task Task 39: Finished on: Sat Oct 19 15:56:56 CST 2019
// pool-1-thread-4: Task Task 44: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 44: Started on: Sat Oct 19 15:56:56 CST 2019
// pool-1-thread-4: Task Task 44: Doing a task during 3 seconds
// pool-1-thread-4: Task Task 44: Finished on: Sat Oct 19 15:56:59 CST 2019
// pool-1-thread-4: Task Task 45: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 45: Started on: Sat Oct 19 15:56:59 CST 2019
// pool-1-thread-4: Task Task 45: Doing a task during 4 seconds
// pool-1-thread-1: Task Task 38: Finished on: Sat Oct 19 15:57:00 CST 2019
// pool-1-thread-1: Task Task 46: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 46: Started on: Sat Oct 19 15:57:00 CST 2019
// pool-1-thread-1: Task Task 46: Doing a task during 4 seconds
// pool-1-thread-5: Task Task 41: Finished on: Sat Oct 19 15:57:02 CST 2019
// pool-1-thread-5: Task Task 47: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 47: Started on: Sat Oct 19 15:57:02 CST 2019
// pool-1-thread-5: Task Task 47: Doing a task during 5 seconds
// pool-1-thread-3: Task Task 42: Finished on: Sat Oct 19 15:57:02 CST 2019
// pool-1-thread-3: Task Task 48: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 48: Started on: Sat Oct 19 15:57:02 CST 2019
// pool-1-thread-3: Task Task 48: Doing a task during 5 seconds
// pool-1-thread-2: Task Task 43: Finished on: Sat Oct 19 15:57:03 CST 2019
// pool-1-thread-2: Task Task 49: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 49: Started on: Sat Oct 19 15:57:03 CST 2019
// pool-1-thread-2: Task Task 49: Doing a task during 5 seconds
// pool-1-thread-4: Task Task 45: Finished on: Sat Oct 19 15:57:03 CST 2019
// pool-1-thread-4: Task Task 50: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 50: Started on: Sat Oct 19 15:57:03 CST 2019
// pool-1-thread-4: Task Task 50: Doing a task during 5 seconds
// pool-1-thread-1: Task Task 46: Finished on: Sat Oct 19 15:57:04 CST 2019
// pool-1-thread-1: Task Task 51: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 51: Started on: Sat Oct 19 15:57:04 CST 2019
// pool-1-thread-1: Task Task 51: Doing a task during 4 seconds
// pool-1-thread-5: Task Task 47: Finished on: Sat Oct 19 15:57:07 CST 2019
// pool-1-thread-5: Task Task 52: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 52: Started on: Sat Oct 19 15:57:07 CST 2019
// pool-1-thread-5: Task Task 52: Doing a task during 7 seconds
// pool-1-thread-3: Task Task 48: Finished on: Sat Oct 19 15:57:07 CST 2019
// pool-1-thread-3: Task Task 53: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 53: Started on: Sat Oct 19 15:57:07 CST 2019
// pool-1-thread-3: Task Task 53: Doing a task during 3 seconds
// pool-1-thread-1: Task Task 51: Finished on: Sat Oct 19 15:57:08 CST 2019
// pool-1-thread-1: Task Task 54: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 54: Started on: Sat Oct 19 15:57:08 CST 2019
// pool-1-thread-1: Task Task 54: Doing a task during 4 seconds
// pool-1-thread-2: Task Task 49: Finished on: Sat Oct 19 15:57:08 CST 2019
// pool-1-thread-2: Task Task 55: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 55: Started on: Sat Oct 19 15:57:08 CST 2019
// pool-1-thread-4: Task Task 50: Finished on: Sat Oct 19 15:57:08 CST 2019
// pool-1-thread-4: Task Task 56: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 56: Started on: Sat Oct 19 15:57:08 CST 2019
// pool-1-thread-4: Task Task 56: Doing a task during 1 seconds
// pool-1-thread-2: Task Task 55: Doing a task during 7 seconds
// pool-1-thread-4: Task Task 56: Finished on: Sat Oct 19 15:57:09 CST 2019
// pool-1-thread-4: Task Task 57: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 57: Started on: Sat Oct 19 15:57:09 CST 2019
// pool-1-thread-4: Task Task 57: Doing a task during 8 seconds
// pool-1-thread-3: Task Task 53: Finished on: Sat Oct 19 15:57:10 CST 2019
// pool-1-thread-3: Task Task 58: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 58: Started on: Sat Oct 19 15:57:10 CST 2019
// pool-1-thread-3: Task Task 58: Doing a task during 7 seconds
// pool-1-thread-1: Task Task 54: Finished on: Sat Oct 19 15:57:12 CST 2019
// pool-1-thread-1: Task Task 59: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 59: Started on: Sat Oct 19 15:57:12 CST 2019
// pool-1-thread-1: Task Task 59: Doing a task during 2 seconds
// pool-1-thread-5: Task Task 52: Finished on: Sat Oct 19 15:57:14 CST 2019
// pool-1-thread-5: Task Task 60: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 60: Started on: Sat Oct 19 15:57:14 CST 2019
// pool-1-thread-5: Task Task 60: Doing a task during 6 seconds
// pool-1-thread-1: Task Task 59: Finished on: Sat Oct 19 15:57:14 CST 2019
// pool-1-thread-1: Task Task 61: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 61: Started on: Sat Oct 19 15:57:14 CST 2019
// pool-1-thread-1: Task Task 61: Doing a task during 2 seconds
// pool-1-thread-2: Task Task 55: Finished on: Sat Oct 19 15:57:15 CST 2019
// pool-1-thread-2: Task Task 62: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 62: Started on: Sat Oct 19 15:57:15 CST 2019
// pool-1-thread-2: Task Task 62: Doing a task during 6 seconds
// pool-1-thread-1: Task Task 61: Finished on: Sat Oct 19 15:57:16 CST 2019
// pool-1-thread-1: Task Task 63: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 63: Started on: Sat Oct 19 15:57:16 CST 2019
// pool-1-thread-1: Task Task 63: Doing a task during 9 seconds
// pool-1-thread-3: Task Task 58: Finished on: Sat Oct 19 15:57:17 CST 2019
// pool-1-thread-3: Task Task 64: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 64: Started on: Sat Oct 19 15:57:17 CST 2019
// pool-1-thread-3: Task Task 64: Doing a task during 9 seconds
// pool-1-thread-4: Task Task 57: Finished on: Sat Oct 19 15:57:17 CST 2019
// pool-1-thread-4: Task Task 65: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 65: Started on: Sat Oct 19 15:57:17 CST 2019
// pool-1-thread-4: Task Task 65: Doing a task during 4 seconds
// pool-1-thread-5: Task Task 60: Finished on: Sat Oct 19 15:57:20 CST 2019
// pool-1-thread-5: Task Task 66: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 66: Started on: Sat Oct 19 15:57:20 CST 2019
// pool-1-thread-5: Task Task 66: Doing a task during 9 seconds
// pool-1-thread-2: Task Task 62: Finished on: Sat Oct 19 15:57:21 CST 2019
// pool-1-thread-2: Task Task 67: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 67: Started on: Sat Oct 19 15:57:21 CST 2019
// pool-1-thread-4: Task Task 65: Finished on: Sat Oct 19 15:57:21 CST 2019
// pool-1-thread-4: Task Task 68: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 68: Started on: Sat Oct 19 15:57:21 CST 2019
// pool-1-thread-4: Task Task 68: Doing a task during 8 seconds
// pool-1-thread-2: Task Task 67: Doing a task during 3 seconds
// pool-1-thread-2: Task Task 67: Finished on: Sat Oct 19 15:57:24 CST 2019
// pool-1-thread-2: Task Task 69: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 69: Started on: Sat Oct 19 15:57:24 CST 2019
// pool-1-thread-2: Task Task 69: Doing a task during 8 seconds
// pool-1-thread-1: Task Task 63: Finished on: Sat Oct 19 15:57:25 CST 2019
// pool-1-thread-1: Task Task 70: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 70: Started on: Sat Oct 19 15:57:25 CST 2019
// pool-1-thread-1: Task Task 70: Doing a task during 0 seconds
// pool-1-thread-1: Task Task 70: Finished on: Sat Oct 19 15:57:25 CST 2019
// pool-1-thread-1: Task Task 71: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 71: Started on: Sat Oct 19 15:57:25 CST 2019
// pool-1-thread-1: Task Task 71: Doing a task during 8 seconds
// pool-1-thread-3: Task Task 64: Finished on: Sat Oct 19 15:57:26 CST 2019
// pool-1-thread-3: Task Task 72: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 72: Started on: Sat Oct 19 15:57:26 CST 2019
// pool-1-thread-3: Task Task 72: Doing a task during 4 seconds
// pool-1-thread-5: Task Task 66: Finished on: Sat Oct 19 15:57:29 CST 2019
// pool-1-thread-5: Task Task 73: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 73: Started on: Sat Oct 19 15:57:29 CST 2019
// pool-1-thread-5: Task Task 73: Doing a task during 0 seconds
// pool-1-thread-5: Task Task 73: Finished on: Sat Oct 19 15:57:29 CST 2019
// pool-1-thread-5: Task Task 74: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 74: Started on: Sat Oct 19 15:57:29 CST 2019
// pool-1-thread-5: Task Task 74: Doing a task during 3 seconds
// pool-1-thread-4: Task Task 68: Finished on: Sat Oct 19 15:57:29 CST 2019
// pool-1-thread-4: Task Task 75: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 75: Started on: Sat Oct 19 15:57:29 CST 2019
// pool-1-thread-4: Task Task 75: Doing a task during 3 seconds
// pool-1-thread-3: Task Task 72: Finished on: Sat Oct 19 15:57:30 CST 2019
// pool-1-thread-3: Task Task 76: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 76: Started on: Sat Oct 19 15:57:30 CST 2019
// pool-1-thread-3: Task Task 76: Doing a task during 1 seconds
// pool-1-thread-3: Task Task 76: Finished on: Sat Oct 19 15:57:31 CST 2019
// pool-1-thread-3: Task Task 77: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 77: Started on: Sat Oct 19 15:57:31 CST 2019
// pool-1-thread-3: Task Task 77: Doing a task during 8 seconds
// pool-1-thread-5: Task Task 74: Finished on: Sat Oct 19 15:57:32 CST 2019
// pool-1-thread-5: Task Task 78: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 78: Started on: Sat Oct 19 15:57:32 CST 2019
// pool-1-thread-5: Task Task 78: Doing a task during 9 seconds
// pool-1-thread-4: Task Task 75: Finished on: Sat Oct 19 15:57:32 CST 2019
// pool-1-thread-4: Task Task 79: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 79: Started on: Sat Oct 19 15:57:32 CST 2019
// pool-1-thread-4: Task Task 79: Doing a task during 1 seconds
// pool-1-thread-2: Task Task 69: Finished on: Sat Oct 19 15:57:32 CST 2019
// pool-1-thread-2: Task Task 80: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 80: Started on: Sat Oct 19 15:57:32 CST 2019
// pool-1-thread-2: Task Task 80: Doing a task during 5 seconds
// pool-1-thread-1: Task Task 71: Finished on: Sat Oct 19 15:57:33 CST 2019
// pool-1-thread-1: Task Task 81: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 81: Started on: Sat Oct 19 15:57:33 CST 2019
// pool-1-thread-1: Task Task 81: Doing a task during 1 seconds
// pool-1-thread-4: Task Task 79: Finished on: Sat Oct 19 15:57:33 CST 2019
// pool-1-thread-4: Task Task 82: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 82: Started on: Sat Oct 19 15:57:33 CST 2019
// pool-1-thread-4: Task Task 82: Doing a task during 3 seconds
// pool-1-thread-1: Task Task 81: Finished on: Sat Oct 19 15:57:34 CST 2019
// pool-1-thread-1: Task Task 83: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 83: Started on: Sat Oct 19 15:57:34 CST 2019
// pool-1-thread-1: Task Task 83: Doing a task during 7 seconds
// pool-1-thread-4: Task Task 82: Finished on: Sat Oct 19 15:57:36 CST 2019
// pool-1-thread-4: Task Task 84: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 84: Started on: Sat Oct 19 15:57:36 CST 2019
// pool-1-thread-4: Task Task 84: Doing a task during 5 seconds
// pool-1-thread-2: Task Task 80: Finished on: Sat Oct 19 15:57:37 CST 2019
// pool-1-thread-2: Task Task 85: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 85: Started on: Sat Oct 19 15:57:37 CST 2019
// pool-1-thread-2: Task Task 85: Doing a task during 4 seconds
// pool-1-thread-3: Task Task 77: Finished on: Sat Oct 19 15:57:39 CST 2019
// pool-1-thread-3: Task Task 86: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 86: Started on: Sat Oct 19 15:57:39 CST 2019
// pool-1-thread-3: Task Task 86: Doing a task during 9 seconds
// pool-1-thread-5: Task Task 78: Finished on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-5: Task Task 87: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 87: Started on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-5: Task Task 87: Doing a task during 2 seconds
// pool-1-thread-1: Task Task 83: Finished on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-1: Task Task 88: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 88: Started on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-1: Task Task 88: Doing a task during 3 seconds
// pool-1-thread-4: Task Task 84: Finished on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-4: Task Task 89: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 89: Started on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-4: Task Task 89: Doing a task during 5 seconds
// pool-1-thread-2: Task Task 85: Finished on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-2: Task Task 90: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 90: Started on: Sat Oct 19 15:57:41 CST 2019
// pool-1-thread-2: Task Task 90: Doing a task during 8 seconds
// pool-1-thread-5: Task Task 87: Finished on: Sat Oct 19 15:57:43 CST 2019
// pool-1-thread-5: Task Task 91: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 91: Started on: Sat Oct 19 15:57:43 CST 2019
// pool-1-thread-5: Task Task 91: Doing a task during 4 seconds
// pool-1-thread-1: Task Task 88: Finished on: Sat Oct 19 15:57:44 CST 2019
// pool-1-thread-1: Task Task 92: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 92: Started on: Sat Oct 19 15:57:44 CST 2019
// pool-1-thread-1: Task Task 92: Doing a task during 6 seconds
// pool-1-thread-4: Task Task 89: Finished on: Sat Oct 19 15:57:46 CST 2019
// pool-1-thread-4: Task Task 93: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 93: Started on: Sat Oct 19 15:57:46 CST 2019
// pool-1-thread-4: Task Task 93: Doing a task during 2 seconds
// pool-1-thread-5: Task Task 91: Finished on: Sat Oct 19 15:57:47 CST 2019
// pool-1-thread-5: Task Task 94: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 94: Started on: Sat Oct 19 15:57:47 CST 2019
// pool-1-thread-5: Task Task 94: Doing a task during 1 seconds
// pool-1-thread-3: Task Task 86: Finished on: Sat Oct 19 15:57:48 CST 2019
// pool-1-thread-3: Task Task 95: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-3: Task Task 95: Started on: Sat Oct 19 15:57:48 CST 2019
// pool-1-thread-3: Task Task 95: Doing a task during 7 seconds
// pool-1-thread-5: Task Task 94: Finished on: Sat Oct 19 15:57:48 CST 2019
// pool-1-thread-5: Task Task 96: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-5: Task Task 96: Started on: Sat Oct 19 15:57:48 CST 2019
// pool-1-thread-5: Task Task 96: Doing a task during 5 seconds
// pool-1-thread-4: Task Task 93: Finished on: Sat Oct 19 15:57:48 CST 2019
// pool-1-thread-4: Task Task 97: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-4: Task Task 97: Started on: Sat Oct 19 15:57:48 CST 2019
// pool-1-thread-4: Task Task 97: Doing a task during 8 seconds
// pool-1-thread-2: Task Task 90: Finished on: Sat Oct 19 15:57:49 CST 2019
// pool-1-thread-2: Task Task 98: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-2: Task Task 98: Started on: Sat Oct 19 15:57:49 CST 2019
// pool-1-thread-2: Task Task 98: Doing a task during 5 seconds
// pool-1-thread-1: Task Task 92: Finished on: Sat Oct 19 15:57:50 CST 2019
// pool-1-thread-1: Task Task 99: Created on: Sat Oct 19 15:56:18 CST 2019
// pool-1-thread-1: Task Task 99: Started on: Sat Oct 19 15:57:50 CST 2019
// pool-1-thread-1: Task Task 99: Doing a task during 1 seconds
// pool-1-thread-1: Task Task 99: Finished on: Sat Oct 19 15:57:51 CST 2019
// pool-1-thread-5: Task Task 96: Finished on: Sat Oct 19 15:57:53 CST 2019
// pool-1-thread-2: Task Task 98: Finished on: Sat Oct 19 15:57:54 CST 2019
// pool-1-thread-3: Task Task 95: Finished on: Sat Oct 19 15:57:55 CST 2019
// pool-1-thread-4: Task Task 97: Finished on: Sat Oct 19 15:57:56 CST 2019
