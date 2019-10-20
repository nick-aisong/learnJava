package cn.ch04.ch04_9;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

// 5.实现一个名为ResultTask的类，并继承FutureTask类。FutureTask类的泛型参数为String类型
public class ResultTask extends FutureTask<String> {
    // 6.声明一个名为name的私有String属性，用来存储任务的名称
    private String name;

    // 7.实现类构造器。它接收一个Callable对象作为参数，调用父类构造器，并用接收到的任务属性来初始化name属性
    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name = ((ExecutableTask) callable).getName();
    }

    // 8.覆盖done()方法。检查isCancelled()方法的返回值，然后根据这个返回值在控制台输出不同的信息
    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("%s: Has been cancelled\n", name);
        } else {
            System.out.printf("%s: Has finished\n", name);
        }
    }
}
