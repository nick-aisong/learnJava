package cn.ch08.ch08_8;

import java.util.concurrent.locks.Lock;

// 6.创建一个名为Task2的类，实现Runnable接口
public class Task2 implements Runnable {
    // 7.声明两个名为lock1和lock2的私有Lock属性
    private Lock lock1, lock2;

    // 8.实现类的构造器，用来初始化它的属性
    public Task2(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    // 9.实现run()方法。调用lock()方法获取对lock2锁对象的控制，在控制台输出一条消息表示已获取lock2锁
    @Override
    public void run() {
        lock2.lock();
        System.out.printf("Task 2: Lock 2 locked\n");
        // 10.使用lock()方法获取对lock1锁对象的控制，在控制台输出一条消息表示已获取lock1锁
        lock1.lock();
        System.out.printf("Task 2: Lock 1 locked\n");
        // 11.释放这两个锁对象。先释放lock1锁对象，接着释放lock2锁对象
        lock1.unlock();
        lock2.unlock();
    }
}
