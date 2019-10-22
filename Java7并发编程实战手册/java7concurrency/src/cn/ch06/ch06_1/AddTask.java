package cn.ch06.ch06_1;

import java.util.concurrent.ConcurrentLinkedDeque;

// 1.创建一个名为AddTask的类，实现Runnable接口
public class AddTask implements Runnable {
    // 2.声明一个私有的ConcurrentLinkedDeque属性list, 并指定它的泛型参数是String型的
    private ConcurrentLinkedDeque<String> list;

    // 3.实现类的构造器来初始化属性
    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    // 4.实现run()方法。这个方法将10,000个字符串存放到列表中，这些字符串由当前执行任务的线程的名称和数字组成
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + ": Element " + i);
        }
    }
}
