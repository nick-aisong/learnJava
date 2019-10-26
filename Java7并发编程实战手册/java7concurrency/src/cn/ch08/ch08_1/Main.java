package cn.ch08.ch08_1;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
// 10. 实现范例的主类，创建Main主类，并实现main()方法

// 监控Lock接口
public class Main {

    public static void main(String[] args) throws Exception {
        // 11.创建一个名为lock的MyLock对象
        MyLock lock = new MyLock();
        // 12.创建一个长度为5的线程数组
        Thread[] threads = new Thread[5];
        // 13.创建并启动5个线程来执行5个Task对象
        for (int i = 0; i < 5; i++) {
            Task task = new Task(lock);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        // 14.创建一个15次的循环
        for (int i = 0; i < 15; i++) {
            // 15.在控制台输出锁的拥有者的名字
            System.out.printf("Main: Logging the Lock\n");
            System.out.printf("*************************\n");
            System.out.printf("Lock: Owner : %s\n", lock.getOwnerName());
            // 16.输出正等待获取此锁的线程数和名称
            System.out.printf("Lock: Queued Threads: %s\n", lock.hasQueuedThreads());
            if (lock.hasQueuedThreads()) {
                System.out.printf("Lock: Queue Length: %d\n", lock.getQueueLength());
                System.out.printf("Lock: Queue Threads: ");
                Collection<Thread> lockedThreads = lock.getThreads();
                for (Thread lockedThread : lockedThreads) {
                    System.out.printf("%s ", lockedThread.getName());
                }
                System.out.printf("\n");
            }
            // 17.输出Lock对象的公平(Fair)模式和状态信息
            System.out.printf("Lock: Fairness: %s\n", lock.isFair());
            System.out.printf("Lock: Locked: %s\n", lock.isLocked());
            System.out.printf("*************************\n");
            // 18. 将线程休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

// 本节范例通过继承ReentrantLock类实现MyLock类，用来返回ReentrantLock类的受保护数据
// ReentrantLock类中这些数据的默认访问权限是protected，所以这些数据可能无法访问
// MyLock类实现了如下方法
// ·getOwnerName()：只有一个线程能够执行Lock对象保护的临界区
// 锁存储了执行临界区代码的线程
// ReentrantLock类的受保护方法getOwner()返回这个线程的名称
// ·getThreads()：当一个线程正在执行临界区代码时，其他试图进入的线程将被休眠直至它们能够执行临界区代码
// ReentrantLock类的受保护方法getQueuedThreads()返回等待执行临界区代码的线程列表
// 这个方法直接返回getQueuedThreads()方法的返回结果

// 我们已使用ReentrantLock类实现的其他方法如下
// ·hasQueuedThreads()：返回的boolean值表明是否有线程正在等待获取锁
// ·getQueueLength()： 返回正在等待获取锁的线程数
// ·isLocked()： 返回的boolean值表示这个锁是否被一个线程占有
// ·isFair()：返回的boolean值表示该锁是否为公平模式

// ReentrantLock类还提供了其他方法来获取Lock对象信息
// ·getHoldCount()：返回当前线程获取到锁的次数
// ·isHeldByCurrentThread()：返回的boolean值表示当前线程是否正持有此锁

// Main: Logging the Lock
// Thread-0: Get the Lock.
// *************************
// Lock: Owner : Thread-0
// Lock: Queued Threads: true
// Lock: Queue Length: 4
// Lock: Queue Threads: Thread-4 Thread-3 Thread-2 Thread-1
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-0: Free the Lock.
// Thread-1: Get the Lock.
// Thread-1: Free the Lock.
// Thread-2: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-2
// Lock: Queued Threads: true
// Lock: Queue Length: 4
// Lock: Queue Threads: Thread-1 Thread-0 Thread-4 Thread-3
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-2: Free the Lock.
// Thread-3: Get the Lock.
// Thread-3: Free the Lock.
// Thread-3: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-3
// Lock: Queued Threads: true
// Lock: Queue Length: 4
// Lock: Queue Threads: Thread-2 Thread-1 Thread-0 Thread-4
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-3: Free the Lock.
// Thread-3: Get the Lock.
// Thread-3: Free the Lock.
// Thread-3: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-3
// Lock: Queued Threads: true
// Lock: Queue Length: 4
// Lock: Queue Threads: Thread-2 Thread-1 Thread-0 Thread-4
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-3: Free the Lock.
// Thread-3: Get the Lock.
// Thread-3: Free the Lock.
// Thread-4: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-4
// Lock: Queued Threads: true
// Lock: Queue Length: 3
// Lock: Queue Threads: Thread-2 Thread-1 Thread-0
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-4: Free the Lock.
// Thread-4: Get the Lock.
// Thread-4: Free the Lock.
// Thread-4: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-4
// Lock: Queued Threads: true
// Lock: Queue Length: 3
// Lock: Queue Threads: Thread-2 Thread-1 Thread-0
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-4: Free the Lock.
// Thread-4: Get the Lock.
// Thread-4: Free the Lock.
// Thread-4: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-4
// Lock: Queued Threads: true
// Lock: Queue Length: 3
// Lock: Queue Threads: Thread-2 Thread-1 Thread-0
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-4: Free the Lock.
// Thread-0: Get the Lock.
// Thread-0: Free the Lock.
// Thread-0: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-0
// Lock: Queued Threads: true
// Lock: Queue Length: 2
// Lock: Queue Threads: Thread-2 Thread-1
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-0: Free the Lock.
// Thread-0: Get the Lock.
// Thread-0: Free the Lock.
// Thread-1: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-1
// Lock: Queued Threads: true
// Lock: Queue Length: 2
// Lock: Queue Threads: Thread-0 Thread-2
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-1: Free the Lock.
// Thread-1: Get the Lock.
// Thread-1: Free the Lock.
// Thread-1: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-1
// Lock: Queued Threads: true
// Lock: Queue Length: 2
// Lock: Queue Threads: Thread-0 Thread-2
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-1: Free the Lock.
// Thread-1: Get the Lock.
// Thread-1: Free the Lock.
// Thread-2: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-2
// Lock: Queued Threads: true
// Lock: Queue Length: 1
// Lock: Queue Threads: Thread-0
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-2: Free the Lock.
// Thread-2: Get the Lock.
// Thread-2: Free the Lock.
// Thread-2: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-2
// Lock: Queued Threads: true
// Lock: Queue Length: 1
// Lock: Queue Threads: Thread-0
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-2: Free the Lock.
// Thread-2: Get the Lock.
// Thread-2: Free the Lock.
// Thread-0: Get the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : Thread-0
// Lock: Queued Threads: false
// Lock: Fairness: false
// Lock: Locked: true
// *************************
// Thread-0: Free the Lock.
// Main: Logging the Lock
// *************************
// Lock: Owner : None
// Lock: Queued Threads: false
// Lock: Fairness: false
// Lock: Locked: false
// *************************
// Main: Logging the Lock
// *************************
// Lock: Owner : None
// Lock: Queued Threads: false
// Lock: Fairness: false
// Lock: Locked: false
// *************************
