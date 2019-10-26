package cn.ch07.ch07_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
// 本例我们将学习如何实现自己的线程类，以及用来生成这个线程类对象的线程工厂类
// 同时也将学习如何在执行器中使用工厂类，使执行器将执行创建的线程

// 1.把7.4节中的类文件MyThread、MyThreadFactory、和MyTask复制到项目中，本节范例也将使用它们
// 2.创建名为Main的主类，并实现main()方法

// 在Executor对象中使用ThreadFactory
public class Main {

    public static void main(String[] args) throws Exception {
        // 3.创建一个新的MyThreadFactory对象，命名为threadFactory
        MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");
        // 4.使用Executors类的newCachedThreadPool()方法创建一个新Executor对象
        // 传递刚创建的工厂对象作为参数。新的Executor对象将使用工厂创建必须的线程，它将执行MyThread线程
        ExecutorService executor = Executors.newCachedThreadPool(threadFactory);
        // 5.创建一个新Task对象，并使用submit()方法将其传递到执行器
        MyTask task = new MyTask();
        executor.submit(task);
        // 6.使用shutdown()方法关闭执行器
        executor.shutdown();
        // 7.使用awaitTermination()方法等待执行器结束
        executor.awaitTermination(1, TimeUnit.DAYS);
        // 8.在控制台输出一条消息表示程序结束
        System.out.printf("Main: End of the program.\n");
    }
}

// 在上一节的“范例实现”部分中，已经详细地解释了MyThread、MyThreadFactory和MyTask类的工作原理
// 本例的main()方法使用newCachedThreadPool()方法创建了一个Executor对象
// 并以创建的工厂对象作为传入参数，所以创建的Executor对象将使用这个工厂创建它需要的线程
// 并且将执行MyThread类的线程对象

// Main: End of the program.
// MyThreadFactory 1:  Creation Date: Sat Oct 26 00:15:16 CST 2019 : Running time: 2020 Milliseconds.