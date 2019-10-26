package cn.ch08.ch08_3;

import java.util.concurrent.TimeUnit;

// 1.创建一个名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 2.声明一个名为milliseconds的私有long属性
    private long milliseconds;

    // 3.实现类的构造器，用来初始化它的属性
    public Task(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    // 4.实现run()方法。让线程休眠由milliseconds属性指定的毫秒数
    @Override
    public void run() {
        System.out.printf("%s: Begin\n", Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: End\n", Thread.currentThread().getName());
    }
}
