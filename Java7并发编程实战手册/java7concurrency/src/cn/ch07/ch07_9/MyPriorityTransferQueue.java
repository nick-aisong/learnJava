package cn.ch07.ch07_9;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

// 1.创建一个名为MyPriorityTransferQueue的类，继承自PriorityBlockingQueue类并实现TransferQueue接口
public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {
    private static final long serialVersionUID = 1L;
    // 2.声明一个名为counter的私有AtomicInteger属性，用来存储等待消费元素的消费者数量
    private AtomicInteger counter;
    // 3.声明一个名为transfered的私有LinkedBlockingQueue属性
    private LinkedBlockingQueue<E> transfered;
    // 4.声明一个名为lock的私有ReentrantLock属性
    private ReentrantLock lock;

    // 5.实现类构造器来初始化它的属性
    public MyPriorityTransferQueue() {
        counter = new AtomicInteger(0);
        lock = new ReentrantLock();
        transfered = new LinkedBlockingQueue<E>();
    }

    // 6.实现tryTransfer()方法。这个方法尝试立即将元素发送到一个正在等待的消费者
    // 如果没有等待中的消费者，该方法返回false
    @Override
    public boolean tryTransfer(E e) {
        lock.lock();
        boolean value;
        if (counter.get() == 0) {
            value = false;
        } else {
            put(e);
            value = true;
        }
        lock.unlock();
        return value;
}

    // 7.实现transfer()方法。这个方法尝试立即将元素发送到一个正在等待的消费者
    // 如果没有等待中的消费者，该方法将元素存储到transfered队列，并等待出现试图获得元素的第一个消费者
    // 在这之前，线程将被阻塞
    @Override
    public void transfer(E e) throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            put(e);
            lock.unlock();
        } else {
            transfered.add(e);
            lock.unlock();
            synchronized (e) {
                e.wait();
            }
        }
    }

    // 8.实现tryTransfer()方法，它接收3个参数：
    // 第一个参数用以表示生产和消费的元素
    // 第二个参数表示如果没有消费者则等待一个消费者的时间
    // 第三个参数表示等待时间的单位
    // 如果有消费者在等待，它就立即发送元素
    // 否则，将参数指定的时间转换为毫秒并使用wait()方法让线程休眠
    // 当消费者取元素时，如果线程仍在wait()方法中休眠，将使用notify()方法去唤醒它，这个实现在下面的部分讲解
    @Override
    public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            put(e);
            lock.unlock();
            return true;
        } else {
            transfered.add(e);
            long newTimeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
            e.wait(newTimeout);
            lock.lock();
            if (transfered.contains(e)) {
                transfered.remove(e);
                lock.unlock();
                return false;
            } else {
                lock.unlock();
                return true;
            }
        }
    }

    // 9.实现hasWaitingConsumer()方法
    // 使用counter属性的值来计算该方法的返回值，如果counter大于0，返回true，否则返回false
    @Override
    public boolean hasWaitingConsumer() {
        return (counter.get() != 0);
    }

    // 10.实现getWaitingConsumerCount()方法。返回counter属性的值
    @Override
    public int getWaitingConsumerCount() {
        return counter.get();
    }

    // 11. 实现take()方法。该方法将被准备消费元素的消费者调用
    // 首先，获得之前定义的锁，然后增加正在等待的消费者的数量
    @Override
    public E take() throws InterruptedException {
        lock.lock();
        counter.incrementAndGet();
        // 12.如果在transfered队列中没有元素，则释放锁并尝试使用take()方法从队列中取得一个元素并再次获取锁
        // 如果队列中没有元素，该方法将让线程休眠直至有元素可被消费
        E value = transfered.poll();
        if (value == null) {
            lock.unlock();
            value = super.take();
            lock.lock();
            // 13.否则，从transfered队列中取出value元素，并唤醒可能在等待元素被消费的线程
        } else {
            synchronized (value) {
                value.notify();
            }
        }
        // 14.减少正在等待的消费者的数量并释放锁
        counter.decrementAndGet();
        lock.unlock();
        return value;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E poll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E element() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        return null;
    }
}
