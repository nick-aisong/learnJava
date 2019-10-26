package cn.ch07.ch07_7;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

// 1.创建一个名为MyWorkerTask的类，并继承ForkJoinTask类，且泛型参数为Void类型
public abstract class MyWorkerTask extends ForkJoinTask<Void> {
    private static final long serialVersionUID = 1L;
    // 2.声明一个名为name的私有String属性，存放任务的名字
    private String name;

    // 3.实现类构造器来初始化属性
    public MyWorkerTask(String name) {
        this.name = name;
    }

    // 4.实现getRawResult()方法。这是一个ForkJoinTask类的抽象方法，当MyWorkerTask任务不返回任何结果时，这个方法必须返回null值
    @Override
    public Void getRawResult() {
        return null;
    }

    // 5.实现setRawResult()方法。这是ForkJoinTask ，类的另一个抽象方法，用于当MyWorkerTask任务不返回任何结果时，设置方法体为空
    @Override
    protected void setRawResult(Void value) {
    }

    // 6.实现exec()方法。这是类的主方法。本例将任务的逻辑委托到compute()方法。计算这个方法的执行时间并输出到控制台
    @Override
    protected boolean exec() {
        Date startDate = new Date();
        compute();
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n", name, diff);
        return true;
    }

    // 7.实现getName()方法来返回任务名
    public String getName() {
        return name;
    }

    // 8.声明抽象方法compute()。如之前提到的，这个方法实现的是任务的逻辑，MyWorkerTask类的子类必须实现这个方法
    protected abstract void compute();
}
