package cn.ch03.ch03_6;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

// 7.创建学生类Student并且实现Runnable接口。这个类将模拟学生考试
public class Student implements Runnable {
    // 8.声明Phaser对象phaser
    private Phaser phaser;

    // 9.实现构造器，并初始化这个Phaser对象
    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    // 10.实现run()方法，它将模拟考试的过程
    @Override
    public void run() {
        // 11.将一个学生到达考场的信息打印到控制台，并且调用phaser对象的arriveAndAwaitAdvance()方法等待其他的线程
        System.out.printf("%s: Has arrived to do the exam. %s \n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        // 12.打印当前学生正在做考题的信息到控制台。并且调用doExercise1()方法模拟做第一道考题的过程
        // 接着打印当前学生做完第一道题的信息到控制台，然后调用phaser的arriveAndAwaitAdvance()方法等待其他学生完成第一道考题
        System.out.printf("%s: Is going to do the first exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise1();
        System.out.printf("%s: Has done the first exercise. %s \n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        // 13.为第二道题和第三道题实现同样的代码
        System.out.printf("%s: Is going to do the second exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise2();
        System.out.printf("%S: Has done the second exercise. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%S: Is going to do the third exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise3();
        System.out.printf("%s: Has finished the exam. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
    }

    // 14.实现辅助方法doExercise1()，它将线程休眠一段时间
    private void doExercise1() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 15.实现辅助方法doExercise2()，它将线程休眠一段时间
    private void doExercise2() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 16.实现辅助方法doExercise3()，它将线程置于休眠一段时间
    private void doExercise3() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
