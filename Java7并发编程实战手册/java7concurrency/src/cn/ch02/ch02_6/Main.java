package cn.ch02.ch02_6;

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
        // 修改Main类的启动线程的代码块。新的代码块如下:
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 这里，所有线程是按顺序创建的，每个线程都执行两个被锁保护的代码块
// 然而，访问时线程并没有按照创建的先后顺序
// 如同前面解释的，锁将选择任一个线程并让它访问锁保护的代码
// JVM没有对线程的执行顺序提供保障
// 它立即又请求锁，这个时候就有10个线程试图获取锁
// 在公平模式下，Lock接口将选择线程1，因为这个线程等待的时间最久，然后，它选择线程2，然后线程3，以此类推
// 在所有线程都执行完第一个被锁保护的代码块之前，它们都没有执行第二个被锁保护的代码块
// 当所有线程执行完第一个加锁代码块之后，又轮到了线程0，然后是线程1，以此类推

// 如果改为非公平模式，将传入锁构造器的参数设置为false
// 所有线程是按顺序创建的，每个线程都执行两个被锁保护的代码块
// 然而，访问时线程并没有按照创建的先后顺序
// 如同前面解释的，锁将选择任一个线程并让它访问锁保护的代码
// JVM没有对线程的执行顺序提供保障

// 读/写锁的构造器也有-一个公平策略的参数，这个参数的行为跟本例中的一样

// Thread 0: Going to print a document
// Thread 0: PrintQueue: Printing a Job during 2 seconds
// Thread 1: Going to print a document
// Thread 2: Going to print a document
// Thread 3: Going to print a document
// Thread 4: Going to print a document
// Thread 5: Going to print a document
// Thread 6: Going to print a document
// Thread 7: Going to print a document
// Thread 8: Going to print a document
// Thread 9: Going to print a document
// Thread 1: PrintQueue: Printing a Job during 9 seconds
// Thread 2: PrintQueue: Printing a Job during 8 seconds
// Thread 3: PrintQueue: Printing a Job during 2 seconds
// Thread 4: PrintQueue: Printing a Job during 2 seconds
// Thread 5: PrintQueue: Printing a Job during 0 seconds
// Thread 6: PrintQueue: Printing a Job during 1 seconds
// Thread 7: PrintQueue: Printing a Job during 0 seconds
// Thread 8: PrintQueue: Printing a Job during 2 seconds
// Thread 9: PrintQueue: Printing a Job during 2 seconds
// Thread 0: PrintQueue: Printing a Job during 2 seconds
// Thread 0: The document has been printed
// Thread 1: PrintQueue: Printing a Job during 7 seconds
// Thread 2: PrintQueue: Printing a Job during 9 seconds
// Thread 1: The document has been printed
// Thread 2: The document has been printed
// Thread 3: PrintQueue: Printing a Job during 0 seconds
// Thread 3: The document has been printed
// Thread 4: PrintQueue: Printing a Job during 6 seconds
// Thread 4: The document has been printed
// Thread 5: PrintQueue: Printing a Job during 4 seconds
// Thread 5: The document has been printed
// Thread 6: PrintQueue: Printing a Job during 6 seconds
// Thread 6: The document has been printed
// Thread 7: PrintQueue: Printing a Job during 3 seconds
// Thread 7: The document has been printed
// Thread 8: PrintQueue: Printing a Job during 0 seconds
// Thread 8: The document has been printed
// Thread 9: PrintQueue: Printing a Job during 1 seconds
// Thread 9: The document has been printed