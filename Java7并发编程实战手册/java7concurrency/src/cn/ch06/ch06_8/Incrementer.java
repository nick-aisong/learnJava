package cn.ch06.ch06_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

// 1.创建名为Incrementer的类实现Runnable接口
public class Incrementer implements Runnable {
    // 2.声明一个私有AtomicIntegerArray属性vector，用来存放一个整型数字数组
    private AtomicIntegerArray vector;

    // 3.实现类构造器初始化属性
    public Incrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    // 4.实现run()方法。使用getAndIncrement()方法增加数组中的所有元素
    @Override
    public void run() {
        for (int i = 0; i < vector.length(); i++) {
            vector.getAndIncrement(i);
        }
    }
}
