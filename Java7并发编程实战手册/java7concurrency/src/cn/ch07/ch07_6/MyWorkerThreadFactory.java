package cn.ch07.ch07_6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

// 7.创建名为MyWorkerThreadFactory的类，它实现了ForkJoinWorkerThreadFactory接口
// 实现newThread()方法，用来创建并返回一个MyWorkerThread对象
public class MyWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyWorkerThread(pool);
    }
}
