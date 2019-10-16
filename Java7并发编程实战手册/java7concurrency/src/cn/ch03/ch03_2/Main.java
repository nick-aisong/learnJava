package cn.ch03.ch03_2;

public class Main {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue), "Thread" + i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}

// 这个范例的核心是打印队列类PrintQueue，在它的构造器中使用3作为传入参数来创建信号量对象
// 在本例中，最开始调用acquire()方法的3个线程将获得对临界区的访问，其余的线程将被阻塞
// 当一个线程完成了对临界区的访问并且释放了信号量，另一个线程，将获得这个信号量

// acquire()、acquireUninterruptibly()、tryAcquire()和release()方法都有另一种实现方式，即提供了一个int型的传入参数
// 这个参数声明了线程试图获取或者释放的共享资源数目，也就是这个线程想要在信号量内部计数器上删除或增加的数目
// 对于acquire()、acquireUninterruptibly()、tryAcquire()方法来讲，如果计数器的值少于参数对应的值，那么线程将被阻塞直到计数器重新累加到或者超过这个值

// Thread0: Going to print a job
// Thread9: Going to print a job
// Thread8: Going to print a job
// Thread7: Going to print a job
// Thread0: PrintQueue: Printing a Job in Printer 0 during 1 seconds
// Thread9: PrintQueue: Printing a Job in Printer 1 during 1 seconds
// Thread8: PrintQueue: Printing a Job in Printer 2 during 1 seconds
// Thread6: Going to print a job
// Thread5: Going to print a job
// Thread4: Going to print a job
// Thread3: Going to print a job
// Thread2: Going to print a job
// Thread1: Going to print a job
// Thread0: The document has been printed
// Thread9: The document has been printed
// Thread8: The document has been printed
// Thread7: PrintQueue: Printing a Job in Printer 0 during 7 seconds
// Thread5: PrintQueue: Printing a Job in Printer 2 during 0 seconds
// Thread6: PrintQueue: Printing a Job in Printer 1 during 8 seconds
// Thread5: The document has been printed
// Thread4: PrintQueue: Printing a Job in Printer 2 during 0 seconds
// Thread4: The document has been printed
// Thread3: PrintQueue: Printing a Job in Printer 2 during 9 seconds
// Thread7: The document has been printed
// Thread2: PrintQueue: Printing a Job in Printer 0 during 1 seconds
// Thread6: The document has been printed
// Thread1: PrintQueue: Printing a Job in Printer 1 during 0 seconds
// Thread1: The document has been printed
// Thread2: The document has been printed
// Thread3: The document has been printed