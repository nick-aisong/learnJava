package cn.ch06.ch06_3;

import java.util.concurrent.PriorityBlockingQueue;

// 8.创建一个名为Task的类，实现Runnable接口
public class Task implements Runnable {
    // 9.声明一个私有int属性id，用来存放task的编号
    private int id;
    // 10.声明一个私有的PriorityBlockingQueue属性queue,并指定它的泛型参数是Event类，来存放task生成的event
    private PriorityBlockingQueue<Event> queue;
    // 11.实现类的构造器来初始化属性
    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }
    // 12.实现run()方法。它向队列中添加1000个event对象。使用task对象的id属性和自增数字作为传入参数创建每一个event对象
    // 其中自增数字是用来设定优先级的。调用add()方法将每个event加入到队列中
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id, i);
            queue.add(event);
        }
    }
}
