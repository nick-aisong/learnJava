package cn.ch01.ch01_11;

// 5.实现这个范例的主程序，并且实现main()方法
public class Main {

	public static void main(String[] args) {
		// 6.创建一个MyThreadGroup线程组类对象
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
		// 7.创建一个Task类对象
		Task task = new Task();
		// 8.用刚创建的两个对象作为传入参数，创建10个线程对象
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
	}
}

// 13 : 1
// 20 : 1
// 19 : 1
// 18 : 1
// 17 : 1
// 16 : 1
// 15 : 1
// 14 : 1
// 11 : 1
// 12 : 1
// 11 : 2
// 14 : 1
// 15 : 9
// 16 : 1
// 17 : 1
// 18 : 5
// 19 : 2
// 20 : 4
// 13 : 2
// 20 : 1
// 19 : 1
// 18 : 2
// 18 : 2
// 17 : 12
// 16 : 1
// 15 : 1
// 14 : 2
// 11 : 1
// 12 : 3
// 11 : 38
// 14 : 2
// 15 : 6
// 16 : 30
// 17 : 1
// 18 : 1
// 19 : 3
// 20 : 6
// 13 : 19
// 20 : 6
// 19 : 3
// 18 : 4
// 17 : 22
// 16 : 1
// 15 : 1
// 14 : 3
// 11 : 5
// 12 : 2
// 11 : 23
// 14 : 16
// 15 : 1
// 16 : 1
// 17 : 4
// 18 : 1
// 18 : 1
// 19 : 1
// 20 : 3
// 20 : 2
// 13 : 1
// 20 : 1
// 19 : 1
// 18 : 1
// 17 : 1
// 16 : 7
// 16 : 1
// 15 : 1
// 14 : 5
// 11 : 2
// 12 : 1
// 11 : 1
// 14 : 1
// 15 : 2
// 16 : 1
// 17 : 1
// 18 : 62
// 19 : 1
// 20 : 15
// 13 : 2
// 20 : 1
// 19 : 1
// 18 : 13
// 17 : 1
// 16 : 142
// 15 : 1
// 14 : 1
// 11 : 2
// 12 : 25
// 11 : 1
// 14 : 1
// 15 : 1
// 16 : 3
// 17 : 333
// 18 : 5
// 19 : 83
// 20 : 1
// 13 : 12
// 20 : 1
// 19 : 5
// 18 : 2
// 17 : 2
// 16 : 1
// 15 : 1
// 14 : 2
// 11 : 7
// 12 : 2
// 12 : 3
// 11 : 1
// 14 : 3
// 15 : 1
// 16 : 1
// 17 : 1
// 18 : 3
// 19 : 2
// 20 : 2
// 13 : 1
// 20 : 4
// 19 : 1
// 18 : 3
// 17 : 1
// 16 : 3
// 15 : 1
// 14 : 1
// 11 : 5
// 11 : 2
// 12 : 2
// 12 : 1
// 11 : 1
// 14 : 1
// 15 : 2
// 16 : 2
// 17 : 1
// 18 : 2
// 19 : 1
// 20 : 1
// 20 : 3
// 13 : 2
// 20 : 2
// 19 : 1
// 19 : 3
// 18 : 9
// 18 : 17
// 17 : 125
// 16 : 14
// 16 : 4
// 15 : 1
// 14 : 4
// 11 : 1
// 12 : 1
// 11 : 1
// 14 : 41
// 15 : 1
// 16 : 8
// 17 : 1
// 18 : 1
// 19 : 1
// 20 : 4
// 13 : 1
// 20 : 1
// 20 : 2
// 19 : 1
// 18 : 4
// 17 : 1
// 16 : 1
// 15 : 4
// 14 : 1
// 11 : 2
// 11 : 5
// 12 : 1
// 11 : 3
// 11 : 1
// 14 : 1
// 15 : 2
// 16 : 1
// 17 : 1
// 18 : 3
// 19 : 1
// 20 : 1
// 13 : 1
// 20 : 1
// 19 : 1
// 19 : 55
// 19 : 12
// 19 : 2
// 19 : 3
// 19 : 15
// 18 : 1
// 17 : 1
// 16 : 1
// 16 : 2
// 16 : 1
// 16 : 10
// 16 : 1
// 15 : 19
// 14 : 11
// 14 : 2
// 11 : 1
// 11 : 1
// 12 : 1
// 11 : 3
// 16 : 1
// 14 : 31
// 15 : 1
// 17 : 3
// 18 : 11
// 19 : 1
// 20 : 1
// 20 : 3
// 20 : 1
// 20 : 4
// 20 : 15
// 20 : 6
// 13 : 1
// 20 : 1
// 19 : 3
// 18 : 1
// 17 : 2
// 15 : 1
// 14 : 1
// 16 : 4
// 11 : 3
// 12 : 1
// 11 : 1
// 16 : 1
// 14 : 2
// 14 : 1
// 14 : 11
// 14 : 1
// 15 : 1
// 17 : 1
// 18 : 7
// 19 : 1
// 20 : 1
// 13 : 1
// 20 : 4
// 19 : 1
// 18 : 2
// 18 : 2
// 17 : 1
// 15 : 1
// 14 : 13
// 16 : 23
// 11 : 4
// 12 : 1
// 11 : 2
// 16 : 7
// 14 : 2
// 15 : 2
// 17 : 1
// 18 : 3
// 19 : 90
// 20 : 5
// 13 : 15
// 20 : 5
// 20 : 1
// 20 : 1
// 20 : 1
// 20 : 4
// 19 : 58
// 18 : 6
// 18 : 1
// 18 : 2
// 18 : 2
// 18 : 2
// 18 : 1
// 17 : 1
// 15 : 100
// 14 : 1
// 16 : 1
// 16 : 3
// 16 : 1
// 16 : 1
// 16 : 47
// 16 : 2
// 16 : 1
// 16 : 1
// 16 : 2
// 16 : 7
// 16 : 1
// 16 : 1
// 16 : 5
// 16 : 2
// 16 : 4
// 16 : 23
// 16 : 4
// 11 : 4
// 12 : 2
// 12 : 1
// 12 : 1
// 12 : 250
// 12 : 52
// 12 : 7
// 12 : 2
// 12 : 3
// 12 : 1
// 12 : 4
// 12 : 1
// 12 : 2
// 12 : 1
// 12 : 1
// 12 : 2
// 12 : 1
// 12 : 1
// 12 : 3
// 12 : 3
// 12 : 2
// 12 : 22
// 12 : 2
// 12 : 1
// 12 : 8
// 11 : 1
// 11 : 2
// 11 : 1
// 11 : 1
// 11 : 3
// 11 : 2
// 11 : 1
// 11 : 2
// 11 : 1
// 11 : 1
// 11 : 2
// 11 : 5
// 11 : 2
// 11 : 1
// 16 : 3
// 16 : 2
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 6
// 16 : 1
// 16 : 1
// 16 : 3
// 16 : 2
// 16 : 1
// 16 : 32
// 16 : 2
// 16 : 2
// 16 : 27
// 16 : 3
// 16 : 6
// 16 : 10
// 16 : 1
// 16 : 1
// 16 : 2
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 7
// 16 : 20
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 6
// 16 : 8
// 16 : 3
// 16 : 1
// 16 : 11
// 16 : 1
// 16 : 1
// 16 : 3
// 16 : 3
// 16 : 1
// 16 : 1
// 16 : 2
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 1
// 16 : 2
// 14 : 3
// 14 : 2
// 14 : 2
// 14 : 1
// 14 : 3
// 14 : 1
// 14 : 2
// 14 : 10
// 14 : 3
// 14 : 1
// 14 : 2
// 14 : 1
// 14 : 1
// 14 : 2
// 14 : 1
// 14 : 1
// 14 : 1
// 14 : 9
// 14 : 3
// 14 : 1
// 14 : 1
// 14 : 1
// 14 : 1
// 14 : 1
// 14 : 3
// 15 : 5
// 17 : 3
// 18 : 1
// 18 : 2
// 18 : 1
// 18 : 4
// 18 : 2
// 18 : 2
// 18 : 1
// 18 : 3
// 18 : 1
// 18 : 1
// 18 : 2
// 18 : 2
// 18 : 38
// 18 : 2
// 18 : 1
// 18 : 1
// 18 : 3
// 18 : 1
// 18 : 1
// 18 : 3
// 18 : 2
// 18 : 4
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 2
// 18 : 1
// 18 : 4
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 3
// 18 : 1
// 18 : 3
// 18 : 4
// 18 : 7
// 18 : 4
// 18 : 1
// 18 : 1
// 18 : 7
// 18 : 1
// 18 : 3
// 18 : 1
// 18 : 1
// 18 : 2
// 18 : 1
// 18 : 1
// 18 : 4
// 18 : 2
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 3
// 18 : 10
// 18 : 3
// 18 : 1
// 18 : 25
// 18 : 1
// 18 : 1
// 18 : 2
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 3
// 18 : 1
// 18 : 1
// 18 : 28
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 14
// 18 : 1
// 18 : 1
// 18 : 3
// 18 : 1
// 18 : 14
// 18 : 5
// 18 : 2
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 15
// 18 : 12
// 18 : 1
// 18 : 2
// 18 : 2
// 18 : 100
// 18 : 1
// 18 : 19
// 18 : 5
// 18 : 1
// 18 : 83
// 18 : 23
// 18 : 1
// 18 : 1
// 18 : 1
// 18 : 2
// 18 : 1
// 18 : 2
// 18 : 6
// 18 : 1
// 18 : 2
// 18 : 5
// 18 : 2
// 18 : 1
// 18 : 5
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 3
// 19 : 1
// 20 : 1
// 20 : 1
// 20 : 1
// 20 : 2
// 20 : 2
// 20 : 3
// 20 : 1
// 20 : 1
// 20 : 4
// 13 : 3
// 20 : 1
// 20 : 5
// 20 : 4
// 20 : 1
// 20 : 4
// 20 : 1
// 20 : 3
// 20 : 1
// 20 : 13
// 20 : 9
// 20 : 1
// The thread 20 has thrown an Exception
// 19 : 1
// 19 : 10
// 19 : 2
// 19 : 1
// 19 : 6
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 4
// 19 : 7
// 19 : 2
// 19 : 6
// 19 : 3
// 19 : 2
// 19 : 4
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 2
// 19 : 2
// 19 : 3
// 19 : 1
// 19 : 37
// 19 : 1
// 19 : 1
// 19 : 8
// 19 : 2
// 19 : 2
// 19 : 55
// 19 : 1
// 19 : 1
// 19 : 33
// 19 : 2
// 19 : 2
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 1
// 19 : 7
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 2
// 19 : 4
// 19 : 2
// 19 : 3
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 4
// 19 : 1
// 19 : 1
// 19 : 3
// 19 : 6
// 19 : 1
// 19 : 1
// 19 : 8
// 19 : 28
// 19 : 1
// 19 : 2
// 19 : 2
// 19 : 5
// 19 : 2
// 19 : 2
// 19 : 32
// 19 : 1
// 19 : 1
// 19 : 58
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 17
// 19 : 23
// 19 : 3
// 19 : 2
// 19 : 1
// 19 : 2
// 19 : 21
// 19 : 2
// 19 : 1
// 19 : 2
// 19 : 3
// 19 : 3
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 50
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 6
// 19 : 1
// 19 : 1
// 19 : 3
// 19 : 5
// 19 : 7
// 19 : 1
// 19 : 1
// 19 : 4
// 19 : 1
// 19 : 1
// 19 : 5
// 19 : 1
// 19 : 1
// 19 : 4
// 19 : 2
// 19 : 3
// 19 : 2
// 19 : 1
// 19 : 3
// 19 : 6
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 9
// 19 : 5
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 7
// 19 : 2
// 19 : 2
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 15
// 19 : 1
// 19 : 10
// 19 : 8
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 55
// 19 : 1000
// 19 : 1
// 19 : 3
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 5
// 19 : 1
// 19 : 1
// 19 : 3
// 19 : 22
// 19 : 1
// 19 : 2
// 19 : 5
// 19 : 1
// 19 : 2
// 19 : 18
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 4
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 10
// 19 : 3
// 19 : 6
// 19 : 7
// 19 : 1
// 19 : 15
// 19 : 1
// 19 : 15
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 7
// 19 : 1
// 19 : 1
// 19 : 6
// 19 : 2
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 15
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 8
// 19 : 3
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 5
// 19 : 10
// 19 : 71
// 19 : 4
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 2
// 19 : 1
// 19 : 2
// 19 : 6
// 19 : 2
// 19 : 33
// 19 : 2
// 19 : 3
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 4
// 19 : 13
// 19 : 6
// 19 : 5
// 19 : 11
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 4
// 19 : 4
// 19 : 2
// 19 : 10
// 19 : 4
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 8
// 19 : 1
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 9
// 19 : 2
// 19 : 1
// 19 : 2
// 19 : 3
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 1
// 19 : 5
// 19 : 3
// 19 : 55
// 19 : 4
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 2
// 19 : 1
// 19 : 3
// 19 : 1
// 19 : 1
// 19 : 2
// 19 : 2
// 19 : 3
// 18 : 19
// 14 : 2
// 14 : 3
// 14 : 4
// 14 : 6
// 14 : 7
// 14 : 2
// 14 : 1
// 14 : 1
// 14 : 12
// 14 : 2
// 14 : 8
// 14 : 1
// 17 : 4
// 17 : 4
// 17 : 2
// 17 : 3
// 17 : 3
// 17 : 1
// 17 : 3
// 17 : 1
// 17 : 1
// 17 : 1
// 17 : 1
// 17 : 1
// 17 : 1
// 17 : 2
// 17 : 15
// 17 : 2
// 17 : 2
// 17 : 3
// 15 : 31
// 16 : 1
// 11 : 5
// 12 : 142
// 11 : 1
// 16 : 2
// 15 : 1
// 17 : 1
// 17 : 1
// 17 : 2
// 17 : 3
// 17 : 1
// 17 : 3
// 17 : 1
// 17 : 1
// 14 : 1
// 18 : 1
// 19 : 1
// java.lang.ArithmeticException: / by zero
// 	at cn.ch01.ch01_11.Task.run(Task.java:15)
// 	at java.lang.Thread.run(Thread.java:748)
// Terminating the rest of the Threads
// 13 : 1
// 19 : 1
// 18 : 2
// 14 : 26
// 14 : Interrupted
// 17 : 8
// 15 : 125
// 15 : Interrupted
// 16 : 1
// 16 : Interrupted
// 11 : 2
// 12 : 6
// 11 : Interrupted
// 17 : Interrupted
// 18 : Interrupted
// 19 : Interrupted
// 13 : Interrupted
// 12 : Interrupted