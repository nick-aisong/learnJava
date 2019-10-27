package cn.ch08.ch08_8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// 这个程序模拟死锁发生

// 12.实现范例的主类，创建Main主类，并实现main()方法
public class Main {
    public static void main(String[] args) {
        // 13.创建两个名为lock1和lock2的Lock锁对象
        Lock lock1, lock2;
        lock1 = new ReentrantLock();
        lock2 = new ReentrantLock();
        // 14.创建一个名为task1的Task1对象
        Task1 task1 = new Task1(lock1, lock2);
        // 15.创建一个名为task2的Task2对象
        Task2 task2 = new Task2(lock1, lock2);
        // 16.使用两个线程执行两个任务
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
        // 17.当两个任务未完成执行时，每500毫秒在控制台输出一条消息。调用isAlive()方法检查线程是否依然在执行
        while ((thread1.isAlive()) && (thread2.isAlive())) {
            System.out.println("Main: The example is running");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

// 其他步骤略

// ·New breakpoints suspend：使用这个选项，可以配置NetBeans在线程中发现一个断点时，只暂停有断点的线程还是暂停所有线程
// ·Steps resume：使用这个选项，可以配置NetBeans在恢复线程时，只恢复当前线程还是恢复所有线程