package cn.ch07.ch07_9;

// 22.实现一个名为Producer的类，并实现Runnable接口
public class Producer implements Runnable {
    // 23.声明一个名为buffer的私有MyPriorityTransferQueue属性，它的泛型参数是Event类，用来存储由这个类生成的事件
    private MyPriorityTransferQueue<Event> buffer;

    // 24.实现类构造器来初始化它的属性
    public Producer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    // 25.实现类的run()方法。创建100个Event对象
    // 使用创建序号作为优先级(事件创建的越晚优先级越高)，并使用put()方法将它们插入到队列中
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event(Thread.currentThread().getName(), i);
            buffer.put(event);
        }
    }
}
