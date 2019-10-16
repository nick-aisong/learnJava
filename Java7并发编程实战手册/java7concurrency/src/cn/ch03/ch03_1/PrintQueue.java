package cn.ch03.ch03_1;

import java.util.concurrent.Semaphore;

// 1.创建一个打印队列类PrintQueue
public class PrintQueue {

    // 2.声明一个信号量对象semaphore
    private final Semaphore semaphore;

    // 3.实现这个类的构造器。它初始化了信号量对象，以保护对打印队列的访问
    public PrintQueue() {
        semaphore = new Semaphore(1);
    }

    // 4.实现printJob()方法，它模拟了文档的打印。它的传入参数是文档对象document
    public void printJob(Object document) {
        try {
            // 5.通过调用acquire()方法获得信号量，它会抛出InterruptedException异常，然后必须捕获并处理这个异常
            semaphore.acquire();
            // 6.实现模拟文档的打印，然后等待一段随机时间
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(),
                    duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 7.通过调用信号量的release()方法释放信号量
            semaphore.release();
        }
    }
}
