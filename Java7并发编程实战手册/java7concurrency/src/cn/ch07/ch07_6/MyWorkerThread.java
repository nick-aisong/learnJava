package cn.ch07.ch07_6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

// 1. 创建一个名为MyWorkerThread的类，继承ForkJoinWorkerThread类
public class MyWorkerThread extends ForkJoinWorkerThread {
    // 2.声明并创建一个私有ThreadLocal属性，命名为taskCounter，并指定泛型参数是Integer
    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<Integer>();

    // 3.实现类构造器
    protected MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    // 4.覆盖onStart()方法。调用它的父类方法，并输出一条消息到控制台，然后设置本线程的taskCounter属性值为0
    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("MyWorkerThread %d: Initializing task counter. \n", getId());
        taskCounter.set(0);
    }

    // 5.覆盖onTermination()方法。输出本线程的taskCounter属性值到控制台
    @Override
    protected void onTermination(Throwable exception) {
        System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
        super.onTermination(exception);
    }

    // 6.实现addTask()方法。增加taskCounter属性的值
    public void addTask() {
        int counter = taskCounter.get().intValue();
        counter++;
        taskCounter.set(counter);
    }
}
