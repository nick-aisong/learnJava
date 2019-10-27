package cn.ch08.ch08_6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// 1.创建一个名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 2.声明一个名为lock的私有ReentrantLock属性
    private ReentrantLock lock;

    // 3.实现类的构造器
    public Task(ReentrantLock lock) {
        this.lock = lock;
    }

    // 4.实现run()方法。获取对锁的控制，使线程休眠2秒后，释放锁
    @Override
    public void run() {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(2);
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
