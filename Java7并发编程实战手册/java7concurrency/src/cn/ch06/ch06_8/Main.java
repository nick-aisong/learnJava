package cn.ch06.ch06_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

// 9.创建范例主类Main，并实现main()方法

// 使用原子数组
public class Main {

    public static void main(String[] args) {
        // 10.声明名为THREADS的常量，赋值100。创建一个有1,000元素的AtomicIntegerArray对象
        final int THREADS = 100;
        AtomicIntegerArray vector = new AtomicIntegerArray(1000);
        // 11.创建Incrementer任务
        Incrementer incrementer = new Incrementer(vector);
        // 12.创建Decrementer任务
        Decrementer decrementer = new Decrementer(vector);
        // 13.创建两个线程数组，每个数组的长度是100，用来存放100个Thread对象
        Thread[] threadIncrementers = new Thread[THREADS];
        Thread[] threadDecrementres = new Thread[THREADS];
        // 14.创建并执行100个线程来执行Incrementer任务和另外100个线程来执行Decrementer任务
        // 并存放线程到之前创建的数组中
        for (int i = 0; i < THREADS; i++) {
            threadIncrementers[i] = new Thread(incrementer);
            threadDecrementres[i] = new Thread(decrementer);

            threadIncrementers[i].start();
            threadDecrementres[i].start();
        }
        // 15.使用join()方法等待线程执行结束
        for (int i = 0; i < THREADS; i++) {
            try {
                threadIncrementers[i].join();
                threadDecrementres[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 16.通过get()方法获取原子数组的各个元素，然后在控制台上输出原子数组中不为0的元素
        for (int i = 0; i < vector.length(); i++) {
            if (vector.get(i) != 0) {
                System.out.println("Vector[" + i + "] : " + vector.get(i));
            }
        }
        // 17.在控制台输出表示程序执行结束的字符串
        System.out.println("Main: End fo the example");
    }
}

// 本例使用AtomicIntegerArray对象实现了下面两个不同的任务
// ·Incrementer任务：这个类使用getAndIncrement()方法增加数组中所有元素的值
// ·Decrementer任务：这个类使用getAndDecrement()方法减少数组中所有元素的值
// 在Main类中，创建了1,000个元素的AtomicIntegerArray数组，执行了100个Incrementer
// 任务和100个Decrementer任务
// 在任务的结尾，如果没有不一致的错误，数组中的所有元素值都必须是0
// 执行程序后将会看到，程序只将最后的消息打印到控制台，因为所有元素值为0

// 现今，Java还提供了另一个原子数组类，即AtomicLongArray类，它的方法与AtomicIntegerArray类相同
// 这些原子数组还提供了其他方法
// ·get(int i)：返回数组中由参数指定位置的值
// ·set(int i, int newValue)：设置由参数指定位置的新值

// Main: End fo the example
