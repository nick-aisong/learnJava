package cn.ch02.ch02_4;

// 10.创建范例的主类Main，它包含main()方法
public class Main {
    public static void main(String args[]) {
        // 11.创建一个共享的打印队列对象
        PrintQueue printQueue = new PrintQueue();

        // 12.创建10个打印工作Job对象，并把它作为传入参数创建线程
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        // 13.启动这10个线程
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}

// Lock接口(和它的实现类ReentrantLock)还提供了另一个方法来获取锁,即tryLock()方法
// 跟lock()方法最大的不同是:线程使用tryLock()不能够获取锁，tryLock()会立即返回,它不会将线程置入休眠
// tryLock()方法返回一个布尔值,true表示线程获取了锁,false表示没有获取锁

// 备注:编程人员应该重视tryLock()方法的返回值及其对应的行为
// 如果这个方法返回false,则程序不会执行临界区代码。如果执行了，这个应用很可能会出现错误的结果

// ReentrantLock类也允许使用递归调用。如果一个线程获取了锁并且进行了递归调用，它将继续持有这个锁
// 因此调用lock()方法后 也将立即返回，并且线程将继续执行递归调用。再者，我们还可以调用其他的方法

// 必须很小心使用锁，以避免死锁。当两个或者多个线程被阻塞并且它们在等待的锁永远不会被释放时，就会发生死锁
// 例如，线程A获取了锁X,线程B获取了锁Y,现在，线程A试图获取锁Y，同时线程B也试图获取锁X,则两个线程都将被阻塞，而且它们等待的锁永远不会被释放

// Thread 1: Going to print a document
// Thread 9: Going to print a document
// Thread 8: Going to print a document
// Thread 7: Going to print a document
// Thread 5: Going to print a document
// Thread 6: Going to print a document
// Thread 4: Going to print a document
// Thread 3: Going to print a document
// Thread 2: Going to print a document
// Thread 0: Going to print a document
// Thread 1: PrintQueue: Printing a Job during 9 seconds
// Thread 1: The document has been printed
// Thread 9: PrintQueue: Printing a Job during 4 seconds
// Thread 9: The document has been printed
// Thread 8: PrintQueue: Printing a Job during 8 seconds
// Thread 8: The document has been printed
// Thread 7: PrintQueue: Printing a Job during 1 seconds
// Thread 7: The document has been printed
// Thread 5: PrintQueue: Printing a Job during 6 seconds
// Thread 6: PrintQueue: Printing a Job during 7 seconds
// Thread 5: The document has been printed
// Thread 6: The document has been printed
// Thread 4: PrintQueue: Printing a Job during 5 seconds
// Thread 4: The document has been printed
// Thread 3: PrintQueue: Printing a Job during 9 seconds
// Thread 3: The document has been printed
// Thread 2: PrintQueue: Printing a Job during 2 seconds
// Thread 2: The document has been printed
// Thread 0: PrintQueue: Printing a Job during 2 seconds
// Thread 0: The document has been printed
