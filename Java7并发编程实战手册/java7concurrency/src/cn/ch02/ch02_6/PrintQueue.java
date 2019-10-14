package cn.ch02.ch02_6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 1.创建一个打印队列类PrintQueue
public class PrintQueue {
    // 2.声明一个锁对象，并且用ReentrantLock类初始化
    // 在打印队列类PrintQueue中，修改锁对象的构造方法，新的指令如下
    private final Lock queueLock = new ReentrantLock(true);

    // 3.实现打印方法printJob()，它的参数是对象型，并且不返回值
    // 修改printJob()方法。将打印模拟分成两个代码块，在它们之间释放锁
    public void printJob(Object document) {
        // 4.在打印方法printJob()内部，通过调用lock()方法获取对锁对象的控制
        queueLock.lock();
        // 5.使用以下的代码模拟文档的打印
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 6.通过unlock()释放对锁对象的控制
            queueLock.unlock();
        }
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 6.通过unlock()释放对锁对象的控制
            queueLock.unlock();
        }
    }
}
