package cn.ch07.ch07_8;

import java.util.concurrent.TimeUnit;

// 15.创建一个名为Task的类，实现Runnable接口
public class Task implements Runnable {
    // 16.声明一个名为lock的私有MyLock属性
    private MyLock lock;
    // 17.声明一个名为name的私有String属性
    private String name;

    // 18.实现类构造器并初始化其属性
    public Task(String name, MyLock lock) {
        this.lock = lock;
        this.name = name;
    }

    // 19.实现run()方法。获取锁，然后让线程休眠2秒，释放lock对象
    @Override
    public void run() {
        lock.lock();
        System.out.printf("Task: %s: Take the lock\n", name);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
