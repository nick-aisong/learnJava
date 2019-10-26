package cn.ch08.ch08_1;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

// 1.创建一个名为MyLock的类，继承ReentrantLock类
public class MyLock extends ReentrantLock {
    private static final long serialVersionUID = 1L;

    // 2.实现getOwnerName()方法
    // 它使用了Lock类受保护的(访问权限为protected)方法getOwner()，返回当前获得了锁(如果有)的线程的名字
    public String getOwnerName() {
        if (this.getOwner() == null) {
            return "None";
        }
        return this.getOwner().getName();
    }

    // 3.实现getThreads()方法
    // 它使用了Lock类受保护的方法getQueuedThreads()，返回正等待获取此锁的线程列表
    public Collection<Thread> getThreads() {
        return this.getQueuedThreads();
    }
}
