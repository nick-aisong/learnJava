package cn.ch07.ch07_8;

import java.util.concurrent.TimeUnit;
// 本例将学习如何实现自定义Lock对象，通过实现Lock接口，来保护临界区代码

// 20.创建名为Main的主类，并实现main()方法

// 实现定制Lock类
public class Main {

    public static void main(String[] args) {
        // 21.创建一个名为lock的MyLock对象
        MyLock lock = new MyLock();
        // 22.创建并执行10个Task任务
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task-" + i, lock);
            Thread thread = new Thread(task);
            thread.start();
        }
        // 23.使用tryLock()方法获取锁。等待1秒如果取不到锁，就在控制台输出一条消息后重新去尝试获取锁
        boolean value;
        do {
            try {
                value = lock.tryLock(1, TimeUnit.SECONDS);
                if (!value) {
                    System.out.printf("Main: Trying to get the Lock\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        } while (!value);
        // 24.在控制台输出一条消息表示获得了锁，然后释放线程
        System.out.printf("Main: Got the lock\n");
        lock.unlock();
        // 25.在控制台输出一条消息表示程序线束
        System.out.printf("Main: End of the program\n");
    }
}

// Java并发API提供了AbstractQueuedSynchronizer类，它用来实现带有锁或信号特性的同步机制
// 顾名思义，AbstractQueuedSynchronizer是一个抽象类
// 它提供操作来对临界区代码的访问进行控制，并对等待访问临界区代码的阻塞线程队列进行管理
// 它有下面两个方法：
// ·tryAcquire()：当访问临界区代码时调用这个方法。如果访问成功，返回true值；否则返回false值
// ·tryRelease()：当释放对临界区代码的访问时调用这个方法。如果释放成功，返回true值；否则返回false值
// 在这两个方法中，我们需要实现对临界区代码的并发访问控制

// 在例子中，我们实现了继承自AbstractQueuedSyncrhonizer类的MyQueuedSynchonizer类
// 并覆盖了这两个抽象方法，在覆盖的时候使用了AtomicInteger变量对临界区代码的访问进行控制
// 锁可以被获取的时候，变量值为0，这时允许一个线程访问临界区代码
// 锁在不可用的时候，变量值为1，这时不允许任何线程访问临界区代码

// 我们使用AtomicInteger类的compareAndSet()方法试图把第一个参数的值设置为第二个参数指定值
// 在实现tryAcquire()方法的时候，又使用compareAndSet()方法试图把原子变量从0设置为1
// 同样，在实现tryRelease()方法的时候，我们使用compareAndSet()方法试图把原子变量从1设置为0
// 必须实现AbstractQueuedSynchronizer抽象类，因为这个类的其他实现(如在ReentrantLock中使用的)被实现为私有内部类
// 所以无法访问到这些内部类

// 接下来，我们实现了MyLock类，它实现了Lock接口，并且有一个MyQueuedSynchronizer属性
// 在实现Lock接口的所有方法中，我们具体使用了MyQueuedSynchronizer对象的方法
// 最后，我们应用Task类，实现了Runnable接口，并使用一个MyLock对象访问临界区
// 临界区将正在访问它的休眠线程2秒钟
// main主类创建了一个MyLock对象，并运行了10个Task对象，这10个Task对象共享MyLock对象
// main主类也使用了tryLock()方法来尝试获得这个锁
// 运行范例，我们可以看到只有一个线程能够访问临界区代码，当这个线程结束时，另一个线程才能访问临界区代码
// 通过定制锁，能够获得它的使用情况、控制临界区的锁定时间，还可以实现高级同步机制
// 例如，对仅在特定时间内才可用的资源进行访问控制

// AbstractQueuedSynchronizer抽象类提供了两个方法用来管理锁状态
// getState()和setState()
// 这两个方法接收并返回锁状态的整型值
// 你可能已经使用这些方法而不是AtomicInteger属性来存储锁状态
// Java并发API提供了另一个类来实现同步机制，即AbstractQueuedLongSynchronizer抽象类
// 它与AbstractQueuedSynchronizer抽象类是一样的，只是使用了一个long属性来存储线程的状态而已

// Exception in thread "main" java.lang.UnsupportedOperationException
// 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireShared(AbstractQueuedSynchronizer.java:1138)
// 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireSharedNanos(AbstractQueuedSynchronizer.java:1327)
// 	at cn.ch07.ch07_8.MyLock.tryLock(MyLock.java:46)
// 	at cn.ch07.ch07_8.Main.main(Main.java:24)
// Task: Task-0: Take the lock
// Task: Task-1: Take the lock
// Task: Task-2: Take the lock
// Task: Task-3: Take the lock
// Task: Task-4: Take the lock
// Task: Task-6: Take the lock
// Task: Task-5: Take the lock
// Task: Task-7: Take the lock
// Task: Task-8: Take the lock
// Task: Task-9: Take the lock