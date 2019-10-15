package cn.ch02.ch02_7;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 5.实现数据缓冲类Buffer，它将被生产者和消费者共享
public class Buffer {
    // 6.设置Buffer类的6个属性
    // LinkedList<String>属性buffer，用来存放共享数据
    // int属性maxSize, 用来存放buffer的长度
    // ReentrantLock对象lock，用来对修改buffer的代码块进行控制
    // 两个Condition属性lines和space
    // boolean类型pendingLines，用来表明缓冲区中是否还有数据
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition space;
    private boolean pendingLines;

    // 7.通过Buffer类的构造器，对这些属性进行初始化赋值
    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    // 8.实现insert()方法。这个方法的传入参数是字符串，它将把这个字符串写入到缓冲区中
    // 首先，insert()方法要获取锁，当获取锁之后，它将检查这个缓冲区是否还有空位
    // 如果缓冲区满了，它将调用条件space的await()方法等待空位出现
    // 当其他线程调用条件space的signal()或者signalAll()方法时，这个线程将被唤醒
    // 在有空位后，线程会将数据行保存到缓冲区中，并且调用条件lines的signallAll()方法
    // 一会儿我们会看到，条件lines将唤醒所有等待缓冲区中有数据的线程
    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    // 9.实现get()方法。它返回缓冲区中的第一个字符串
    // 首先，get()方法要获取锁。当获取锁之后，它将检查缓冲区是不是有数据行
    // 如果缓冲区是空的，就调用条件lines的await()方法来等待缓冲区数据的出现
    // 当其他线程调用条件lines的signal()或者signalAll()方法时，这个线程将被唤醒
    // 在有数据后，get()方法获取缓冲区中的第一行，并且调用条件space的signallAll()方法，并且返回这个数据行字符串
    public String get() {
        String line = null;
        lock.lock();
        try {
            while ((buffer.size() == 0) && (hasPendingLines())) {
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }

    // 10.实现setPendingLines()方法，它将为属性pendingLines设置值
    // 当生产者不再生产新的数据行时，线程将调用它
    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    // 11.实现hasPendingLines()方法。如果有数据行可以处理的时候返回true,否则返回false
    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }
}
