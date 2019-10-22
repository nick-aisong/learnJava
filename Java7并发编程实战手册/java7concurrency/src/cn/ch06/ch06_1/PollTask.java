package cn.ch06.ch06_1;

import java.util.concurrent.ConcurrentLinkedDeque;

// 5.创建名为PollTask的类，并实现Runnable接口
public class PollTask implements Runnable {
    // 6.声明一个私有的ConcurrentLinkedDeque属性list，并指定它的泛型参数是String型的
    private ConcurrentLinkedDeque<String> list;

    // 7.实现类的构造器来初始化属性
    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    // 8.实现run()方法。这个方法将列表中的10,000个字符串取出，总共取5,000次，每次取两个元素
    @Override
    public void run() {
        for (int i = 0; i < 50040; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
