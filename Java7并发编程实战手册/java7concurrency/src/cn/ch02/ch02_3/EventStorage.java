package cn.ch02.ch02_3;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

// 1.创建数据存储类EventStorage,它有2个属性
// 一个是int型属性maxSize,另一个是LinkedList<Date>型属性storage
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    // 2.实现构造器，并初始化这两个属性
    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    // 3.实现同步方法set(),它保存数据到存储列表storage中
    // 首先，它将检查列表是不是满的，如果已满，就调用wait()方法挂起线程并等待空余空间的出现
    // 在这个方法的最后，我们调用notifyAll()方法唤醒所有因调用wait()方法进入休眠的线程
    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d\n", storage.size());
        notifyAll();
    }

    // 4.实现同步方法get(),它从存储列表storage中获取数据
    // 首先，它将检查列表中是不是有数据，如果没有，就调用wait()方法挂起线程并等待列表中数据的出现
    // 在这个方法的最后，我们调用notifyAll()方法唤醒所有因调用wait()方法进入休眠的线程
    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get:%d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
        notifyAll();
    }
}

// 一开始我一直在疑惑，set方法不是加了synchronized吗，为什么要循环判断来防止其它线程过多添加元素？
// 了解到了锁获取/释放的机制，终于得出了答案：
// 因为wait会释放锁
// 当容器满了的时候，第一个线程拿到锁，进入到while块内，调用wait方法并释放了锁。第二个线程也能拿到锁，遇到wait同样会等待
// 这时线程1，线程2，都在wait处等待了，这是问题的关键
// 当消费者消费了1个元素，调用了notiftyAll方法，线程1和线程2都唤醒了，它们会竞争锁，假设线程1拿到了锁，这时线程2继续在锁池中阻塞
// 线程1直接到执行下面的代码增加元素，增加完后容器已经满
// 但是线程1执行完，会释放锁，又因为线程2此时是醒着的，它将得到线程1释放的锁，继续从wait()处往下执行
// 如果是if，它不作判断就直接往满的容器中加元素，造成错误
// 如果是while，线程2往下执行时会再次进入到while判断。因为此时容器已经满了，所以线程2，再次被wait，保证了数据的安全
