package cn.ch02.ch02_5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 1.创建一个价格信息类PricesInfo，并且存放两个产品的价格
public class PricesInfo {
    // 2.声明两个double属性price1和price2
    private double price1;
    private double price2;

    // 3.声明读写锁ReadWriteLock对象lock
    private ReadWriteLock lock;

    // 4.通过构造器初始化这3个属性。对于lock属性，我们为它创建ReentrantReadWriteLock实例
    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    // 5.实现getPrice1()方法来返回属性price1的值。它使用读锁来获取对这个属性的访问
    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    // 6.实现getPrice2()方法来返回属性price2的值。它使用读锁来获取对这个属性的访问
    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    // 7.实现setPrices()方法为这两个price属性赋值，它使用了写锁来控制对这两个属性的访问
    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }
}
