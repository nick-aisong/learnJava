package cn.ch08.ch08_2;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

// 1.创建一个名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 2.声明一个名为time的私有int属性
    private int time;
    // 3.声明一个名为phaser的私有Phaser属性
    private Phaser phaser;

    // 4.实现类的构造器，用来初始化它的属性
    public Task(int time, Phaser phaser) {
        this.time = time;
        this.phaser = phaser;
    }

    // 5.实现run()方法。使用phaser属性的arrive()方法启动任务
    @Override
    public void run() {
        phaser.arrive();
        // 6.在控制台输出一条消息表示阶段一开始，然后让线程休眠time属性指定的时长
        // 接着在控制台输出一条消息表示阶段一结束，然后调用phaser属性的方法arriveAndAwaitAdvance()与其他的线程同步
        System.out.printf("%s: Entering phase 1.\n", Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Finishing phase 1.\n", Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();
        // 7.为阶段二和阶段三重复上述行为
        // 在阶段三结束时，使用arriveAndDeregister()方法，而不是arriveAndAwaitAdvance()方法
        System.out.printf("%s: Entering phase 2.\n", Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Finishing phase 2.\n", Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s: Entering phase 3.\n", Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Finishing phase 3.\n", Thread.currentThread().getName());
        phaser.arriveAndDeregister();
    }
}
