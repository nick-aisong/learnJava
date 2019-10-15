package cn.ch01.ch01_3;

// 5.现在我们来实现这个范例的主类Main，并且实现main()方法
public class Main {
	
	public static void main(String[] args) {
		// 6.创建PrimeGenerator类的一个对象，并且运行这个线程对象
		Thread task = new PrimeGenerator();
		task.start();

		// 7.等待5秒钟后，中断PrimeGenerator线程
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
	}
}

// Thread类有一个表明线程被中断与否的属性，它存放的是布尔值
// 线程的interrupt()方法被调用时，这个属性就会被设置为true
// isInterrupted()方法只是返回这个属性的值

// 还有一个方法可以检查线程是否已被中断，即Thread类的静态方法interrupted()，用来检查当前执行的线程是否被中断
// isInterrupted()和interrupted()方法有一个很大的区别。isInterrupted()不能改变interrupted属性的值，但是后者能设置interrupted属性为false
// 因为interrupted()是一个静态方法，更推荐使用isInterrupted()方法

// Number 1 is Prime
// Number 2 is Prime
// Number 3 is Prime
// Number 5 is Prime
// Number 7 is Prime
// Number 11 is Prime
// Number 13 is Prime
// Number 17 is Prime
// Number 19 is Prime
// ......
// Number 91009 is Prime
// Number 91019 is Prime
// Number 91033 is Prime
// The Prime Generator has been Interrupted