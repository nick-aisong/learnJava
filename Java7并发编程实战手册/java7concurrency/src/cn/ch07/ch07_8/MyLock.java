package cn.ch07.ch07_8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 6.创建一个名为MyLock的类，并实现Lock接口
public class MyLock implements Lock {
    // 7.声明一个名为sync的私有AbstractQueuedSynchronizer属性
    private AbstractQueuedSynchronizer sync;

    // 8.实现类构造器，并创建一个新的MyAbstractQueueSynchronizer对象来初始化sync属性
    public MyLock() {
        sync = new MyAbstractQueuedSynchronizer();
    }

    // 9.实现lock()方法。然后在方法中调用sync对象的acquire()方法
    @Override
    public void lock() {
        sync.acquire(1);
    }

    // 10.实现lockInterruptibly()方法。然后在方法中调用sync对象的acquireInterruptibly()方法
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    // 11.实现tryLock()方法。然后在方法中调用sync对象的tryAcquireNanos()方法
    @Override
    public boolean tryLock() {
        try {
            return sync.tryAcquireSharedNanos(1, 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 12.实现另一种tryLock()方法。它带有两个参数：
    // 一个名为time的long型参数和一个名为unit的TimeUnit类型参数
    // 然后在方法中调用sync对象的tryAcquireNanos()方法
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
    }

    // 13.实现unlock()方法。然后在方法中调用sync对象的release()方法
    @Override
    public void unlock() {
        sync.release(1);
    }

    // 14.实现newCondition()方法。创建sync对象内部类ConditionObject的一个新对象
    @Override
    public Condition newCondition() {
        return sync.new ConditionObject();
    }
}
