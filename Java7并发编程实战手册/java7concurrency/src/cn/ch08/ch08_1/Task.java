package cn.ch08.ch08_1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

// 4.创建一个名为Task的类，并且实现Runnable接口
public class Task implements Runnable {
    // 5.声明一个名为lock的私有Lock属性
    private Lock lock;

    // 6.实现类的构造器，用来初始化它的属性
    public Task(Lock lock) {
        this.lock = lock;
    }

    // 7.实现run()方法。创建一个5次循环
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // 8.使用lock()方法取得锁，并在控制台输出一条消息
            lock.lock();
            System.out.printf("%s: Get the Lock.\n", Thread.currentThread().getName());
            // 9.将线程休眠500毫秒。调用unlock()方法释放锁并在控制台输出一条消息
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.printf("%s: Free the Lock.\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
