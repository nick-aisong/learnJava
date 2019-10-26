package cn.ch07.ch07_5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 1.创建一个类名为MyScheduledTask的类，泛型参数为V。它继承了FutureTask类，并实现了RunnableScheduledFuture接口

public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
    // 2.声明一个名为task的私有RunnableScheduledFuture属性
    private RunnableScheduledFuture<V> task;
    // 3.声明一个名为executor的私有ScheduledThreadPoolExecutor属性
    private ScheduledThreadPoolExecutor executor;
    // 4.声明一个名为period的私有long属性
    private long period;
    // 5.声明一个名为startDate的私有long属性
    private long startDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 6.实现类构造器。它接收将被任务执行的Runnable对象，并且返回执行的结果
    // RunnableScheduledFuture任务将用来创建MyScheduledTask任务对象，ScheduledThreadPoolExecutor对象将执行这个任务
    // 这个方法先调用父类的构造器，然后存放task和executor属性
    public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task,
                           ScheduledThreadPoolExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    // 7.实现getDelay()方法。如果是周期性任务且startDate属性值不等于0，则计算startDate属性和当前时间的时间差作为返回值
    // 否则返回存放在task属性中的延迟值。需要注意的是，返回结果需要被转化为参数指定的时间单位
    @Override
    public long getDelay(TimeUnit unit) {
        if (!isPeriodic()) {
            return task.getDelay(unit);
        } else {
            if (startDate == 0) {
                return task.getDelay(unit);
            } else {
                Date now = new Date();
                long delay = startDate - now.getTime();
                return unit.convert(delay, TimeUnit.MILLISECONDS);
            }
        }
    }

    // 8.实现compareTo()方法。调用原来任务的compareTo()方法
    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }

    // 9.实现isPeriodic()方法。调用原来任务的isPeriodic()方法
    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }

    // 10.实现run()方法。如果是周期性任务，则需要用任务下一次执行的开始时间更新它的startDate属性
    // 即用当前时间加上周期间隔作为下一次执行的开始时间
    // 然后，再次增加任务到ScheduledThreadPoolExecutor对象的队列中
    @Override
    public void run() {
        if (isPeriodic() && (!executor.isShutdown())) {
            Date now = new Date();
            startDate = now.getTime() + period;
            executor.getQueue().add(this);
        }
        // 11.在控制台输出当前时间，然后调用runAndReset()方法执行任务，并且将任务执行后的当前时间输出到控制台
        System.out.printf("Pre-MyScheduledTask: %s\n", LocalDateTime.now().format(formatter));
        System.out.printf("MyScheduleTask: Is Periodic: %s\n", isPeriodic());
        super.runAndReset();
        System.out.printf("Post-MyScheduledTask: %s\n", LocalDateTime.now().format(formatter));
    }

    // 12.实现setPeriod()方法来设置任务的周期
    public void setPeriod(long period) {
        this.period = period;
    }
}
