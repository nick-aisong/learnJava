package cn.ch04.ch04_11;

import java.util.concurrent.TimeUnit;

// 2.创建一个名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 3.声明一个名为name的私有String属性，用来存储任务的名称
    private String name;

    // 4.实现类的构造器，用来初始化类的属性
    public Task(String name) {
        this.name = name;
    }

    // 5.实现run()方法。在控制台输出信息表示方法开始执行
    @Override
    public void run() {
        System.out.println("Task " + name + ": Starting");
        try {
            // 6.让线程休眠一段随机时间
            long duration = (long) (Math.random() * 10);
            System.out.printf("Task: %s: ReportGenerator: Generating a report during %d seconds\n", name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 7.在控制台输出信息表示方法执行结束
        System.out.printf("Task %s: Ending\n", name);
    }

    // 8.覆盖toString()方法，返回任务的名称
    @Override
    public String toString() {
        return this.name;
    }
}
