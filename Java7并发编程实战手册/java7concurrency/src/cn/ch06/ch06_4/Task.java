package cn.ch06.ch06_4;

import java.util.Date;
import java.util.concurrent.DelayQueue;

// 6.创建名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 7.声明私有的int属性，命名为id，用来存放task的编号
    private int id;
    // 8.声明私有的DelayQueue属性，命名为queue，并指定泛型参数是Event类型
    private DelayQueue<Event> queue;

    // 9.实现类的构造器来初始化属性
    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    // 10.实现run()方法。计算要创建的event对象的激活日期，用实际日期加.上当前Task编号id对应的秒数
    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + (id * 1000));
        System.out.printf("Thread %s: %s\n", id, delay);
        // 11.调用queue对象的addQ方法将100个event对象添加到queue中
        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }
}
