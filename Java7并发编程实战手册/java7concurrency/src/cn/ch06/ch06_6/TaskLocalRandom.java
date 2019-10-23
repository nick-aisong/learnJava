package cn.ch06.ch06_6;

import java.util.concurrent.ThreadLocalRandom;

// 1.创建名为TaskLocalRandom的类并实现Runnable接口
public class TaskLocalRandom implements Runnable {
    // 2.实现类构造器。使用current()方法为当前线程初始化随机数生成器
    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    // 3.实现run()方法。获取执行本任务的线程名称，使用nextInt()方法生成10个随机数并打印到控制台
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
        }
    }
}
