package cn.ch07.ch07_5;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 13.创建一个名为MyScheduledThreadPoolExecutor的类
// 它继承了一个ScheduledThreadPoolExecutor类，用来执行MyScheduledTask任务
public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    // 14.实现类的构造器，仅调用父类的构造器
    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    // 15.实现decorateTask()方法。它接收将要被执行的Runnable对象作为参数
    // 另一个参数是RunnableScheduledFuture任务，用来执行这个Runnable对象
    // 这个方法使用这些对象构造并返回MyScheduledTask任务
    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        MyScheduledTask<V> myTask = new MyScheduledTask<V>(runnable, null, task, this);
        return myTask;
    }

    // 16.覆盖scheduledAtFixedRate()方法。调用父类的这个方法，把返回对象强制类型转换为一个MyScheduledTask对象
    // 并使用setPeriod()方法设置任务的周期
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> task = super.scheduleAtFixedRate(command, initialDelay, period, unit);
        MyScheduledTask<?> myTask = (MyScheduledTask<?>) task;
        myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
        return task;
    }
}
