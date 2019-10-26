package cn.ch07.ch07_3;

import java.util.concurrent.ThreadFactory;

// 10.创建MyThreadFactory类，实现ThreadFactory接口
public class MyThreadFactory implements ThreadFactory {
    // 11.声明一个名为counter的私有int属性
    private int counter;
    // 12.声明一个名为prefix的私有String属性
    private String prefix;

    // 13.实现类构造器，初始化属性
    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = 1;
    }

    // 14.实现newThread()方法。创建MyThread对象并增加counter属性
    @Override
    public Thread newThread(Runnable r) {
        MyThread myThread = new MyThread(r, prefix + " " + counter);
        counter++;
        return myThread;
    }
}
