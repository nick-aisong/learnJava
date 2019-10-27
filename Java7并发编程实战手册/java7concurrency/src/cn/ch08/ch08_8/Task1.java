package cn.ch08.ch08_8;

import java.util.concurrent.locks.Lock;

// 1.创建一个名为Task1的类，实现Runnable接口
public class Task1 implements Runnable {
    // 2.声明两个名为lock1和lock2的私有Lock属性
    private Lock lock1, lock2;

    // 3.实现类的构造器，用来初始化它的属性
    public Task1(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    // 4.实现run()方法。调用lock()方法获取到对lock1锁对象的控制，并在控制台输出一条消息表示已获取lock1锁
    @Override
    public void run() {
        lock1.lock();
        System.out.printf("Task 1: Lock 1 locked\n");
        // 5.调用lock()方法获取到对lock2锁对象的控制，并在控制台输出一条消息表示已获取lock2锁
        // 然后，释放这两个锁对象。先释放lock2锁对象，接着释放lock1锁对象
        lock2.lock();
        System.out.printf("Task 1: Lock 2 locked\n");
        lock2.unlock();
        lock1.unlock();
    }
}
