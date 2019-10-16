package cn.ch03.ch03_1;

// 使用信号量类Semaphore来实现二进制信号量(BinarySemaphore)
// 二进制信号量是一种比较特殊的信号量，用来保护对唯一共享资源的访问，因而它的内部计数器只有0和1两个值
// 实现一个打印队列，并发任务将使用它来完成打印。这个打印队列受二进制信号量保护，因而同时只有一个线程可以执行打印

// 15.实现范例的主类Main,并实现main()方法
public class Main {

	public static void main(String[] args) {
		// 16.创建打印队列对象printQueue
		PrintQueue printQueue = new PrintQueue();
		// 17.将工作类Job对象作为传入参数创建10个线程，因而每个线程都将发送文档到打印队列
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Job(printQueue), "Thread" + i);
		}
		// 18.最后，启动这10个线程
		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}
	}
}

// 这个范例的核心部分是打印队列类PrintQueue的printJob()方法
// 它指出了使用信号量实现临界区必须遵循的三个步骤，从而保护对共享资源的访问:
// ·首先，必须通过acquire()方法获得信号量
// ·其次，使用共享资源执行必要的操作
// ·最后，必须通过release()方法释放信号量

// Semaphore类还有其他两种acquire()方法
// ·acquireUninterruptibly(): 它其实就是acquire()方法。当信号量的内部计数器变成0的时候，信号量将阻塞线程直到其被释放
// 线程在被阻塞的这段时间中，可能会被中断，从而导致acquire()方法抛出InterruptedException异常
// 而acquireUninterruptibly()方法会忽略线程的中断并且不会抛出任何异常
// ·tryAcquire(): 这个方法试图获得信号量。如果能获得就返回true；如果不能，就返回false，从而避开线程的阻塞和等待信号量的释放
// 我们可以根据返回值是true还是flase来做出恰当的处理

// 信号量的公平性
// 在Java语言中，只要一个类可能出现多个线程被阻塞并且等待同步资源的释放(例如信号量)，就会涉及公平性概念
// 默认的模式是非公平模式。在这种模式中，被同步的资源被释放后，所有等待的线程中会有一个被选中来使用共享资源，而这个选择是没有任何条件的
// 公平模式则不然，它选择的是等待共享资源时间最长的那个线程
// 跟其他的类一样，Semaphore类的构造器也提供了第二个传入参数。这个参数是boolean型的
// 如果传入false值，那么创建的信号量就是非公平模式的，与不使用这个参数的效果一样
// 如果传入true值，那么创建的信号量是公平模式的

// Thread0: Going to print a job
// Thread9: Going to print a job
// Thread8: Going to print a job
// Thread7: Going to print a job
// Thread6: Going to print a job
// Thread5: Going to print a job
// Thread4: Going to print a job
// Thread3: Going to print a job
// Thread2: Going to print a job
// Thread1: Going to print a job
// Thread0: PrintQueue: Printing a Job during 6 seconds
// Thread0: The document has been printed
// Thread9: PrintQueue: Printing a Job during 4 seconds
// Thread9: The document has been printed
// Thread8: PrintQueue: Printing a Job during 2 seconds
// Thread8: The document has been printed
// Thread7: PrintQueue: Printing a Job during 6 seconds
// Thread7: The document has been printed
// Thread6: PrintQueue: Printing a Job during 8 seconds
// Thread6: The document has been printed
// Thread5: PrintQueue: Printing a Job during 6 seconds
// Thread5: The document has been printed
// Thread4: PrintQueue: Printing a Job during 6 seconds
// Thread4: The document has been printed
// Thread3: PrintQueue: Printing a Job during 9 seconds
// Thread3: The document has been printed
// Thread2: PrintQueue: Printing a Job during 0 seconds
// Thread1: PrintQueue: Printing a Job during 2 seconds
// Thread2: The document has been printed
// Thread1: The document has been printed