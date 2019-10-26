package cn.ch07.ch07_8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

// 1.创建名为MyAbstractQueuedSynchronizer的类，继承自AbstractQueuedSynchronizer类
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = 1L;
    // 2.声明一个名为state的私有AtomicInteger属性
    private AtomicInteger state;

    // 3.实现类构造器来初始化属性
    public MyAbstractQueuedSynchronizer() {
        this.state = new AtomicInteger(0);
    }

    // 4.实现tryAcquire()方法。它用来把state变量的值从0改为1
    // 如果成功，返回true，否则返回false
    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0, 1);
    }

    // 5.实现tryRelease()方法
    // 它用来把state变量的值从1改为0。如果成功，返回true，否则返回false
    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1, 0);
    }
}
