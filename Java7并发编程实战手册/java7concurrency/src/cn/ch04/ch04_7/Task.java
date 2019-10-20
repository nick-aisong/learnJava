package cn.ch04.ch04_7;

import java.util.Date;

// 1.创建一个名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 2.声明一个名为name的私有String属性，用来存储任务的名称
    private String name;

    // 3.实现类的构造器，用来初始化类的属性
    public Task(String name) {
        this.name = name;
    }

    // 4.实现run()方法。在控制台输出实际的时间，用来检验任务将在指定的一段时间内执行
    @Override
    public void run() {
        System.out.printf("%s: Starting at: %s\n", name, new Date());
    }
}
