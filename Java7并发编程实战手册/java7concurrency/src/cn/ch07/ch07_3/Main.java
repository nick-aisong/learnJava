package cn.ch07.ch07_3;

// 本例将通过继承Thread类并增加一些新的功能来实现一个新的线程类，同时实现一个线程工厂来生成这个新的线程类对象

// 16.创建名为Main的主类，并添加main()方法

// 实现ThreadFactory接口生成定制线程
public class Main {

    public static void main(String[] args) throws Exception {
        // 17.创建MyThreadFactory对象
        MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");
        // 18.创建任务对象
        MyTask task = new MyTask();
        // 19.使用工厂的newThread()方法创建MyThread对象以执行任务
        Thread thread = myFactory.newThread(task);
        // 20.启动线程并等待它执行结束
        thread.start();
        thread.join();
        // 21.使用toString()方法输出关于线程的信息
        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End of the example.\n");
    }
}

// 本节实现了一个定制的MyThread类，它继承了Thread类
// MyThread类有三个属性分别存放它的创建时间，执行起始时间和执行结束时间
// getExecutionTime()方法使用了执行起始时间和结束时间两个属性，返回线程执行的时间
// 最后，覆盖了toString()方法来生成有关线程的信息

// 在实现了自己的线程类之后，我们通过实现ThreadFactory接口来完成工厂类，用来创建刚实现的线程类对象
// 如果使用工厂作为独立对象那么就可以不必实现ThreadFactory接口，但是如果这个工厂要与Java并发API的其他类一起使用，就必须实现ThreadFactory接口
// 实现ThreadFactory接口只有一个方法，即newThead()方法，它接收一个Runnable对象作为参数并返回一个执行Runnable对象的Thread对象

// 在这个例子中，返回一个MyThread对象
// 为了检查这两个类，我们需要实现MyTask类，用其实现Runnable接口
// 这是在MyThread线程中被执行的任务。MyTask对象能让执行线程休眠2s
// 在main()方法中，通过MyThreadFactory工厂创建了MyThread 对象来执行任务
// 运行这个程序，在控制台能看到执行线程的创建时间和执行时间的消息

// Java并发API提供了Executor类来生成执行线程，生成的执行线程通常是ThreadPoolExecutor类的对象
// 也可以使用这个类的defaultThreadFactory()方法获取ThreadFactory
// 接口的最基本实现，这个工厂能够生成基本的线程对象，并且生成的线程都属于同一个线程组对象
// 当然，你可以在程序中自由使用ThreadFactory接口，而不必拘泥于Executor框架

// Main: Thread information.
// MyThreadFactory 1:  Creation Date: Sat Oct 26 00:01:46 CST 2019 : Running time: 2001 Milliseconds.
// Main: End of the example.