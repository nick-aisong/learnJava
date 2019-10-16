package cn.ch03.ch03_2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 一个打印队列，它将被三个不同的打印机使用
public class PrintQueue {

    // 1.声明一个boolean型数组freePrinters，用来存放打印机的状态，即空闲或正在打印
    private boolean freePrinters[];
    // 2.然后声明一个锁对象lockPrinters,用来保护对freePrinters数组的访问
    private Lock lockPrinters;
    private final Semaphore semaphore;

    // 3.修改构造器，并初始化这两个新声明的对象。将freePrinters数组的三个元素都初始化为true。信号量初始化为3
    public PrintQueue() {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];
        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    // 4.修改printJob()方法，它的传入参数是对象document
    public void printJob(Object document) {
        try {
            // 5.调用acquire()方法获得信号量。由于这个方法会抛出InterruptedException异常，所以必须捕获并处理这个异常
            semaphore.acquire();
            // 6.使用私有函数getPrinter()获得分配打印工作的打印机编号
            int assignedPrinter = getPrinter();
            // 7.实现模拟文档的打印，然后等待一段随机时间
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",
                    Thread.currentThread().getName(), assignedPrinter, duration);
            Thread.sleep(duration);
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 8.调用release()方法释放信号量，并将打印机标记为空闲，即将这个打印机对应的freePrinters数组中的值设置成true
            semaphore.release();
        }
    }

    // 9.实现getPrinter()方法。它是一个私有函数并且返回int值
    private int getPrinter() {
        // 10.声明一个int变量来存储打印机的编号
        int ret = -1;
        try {
            // 11.获得锁
            lockPrinters.lock();
            // 12.在freePrinters数组中找到第一个true值并把索引保存到ref变量中，将true值重置为false，意味着被定位的打印机将要执行打印工作
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 13.释放锁对象，并且返回刚刚获得的打印机编号
            lockPrinters.unlock();
        }
        return ret;
    }
}
