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
