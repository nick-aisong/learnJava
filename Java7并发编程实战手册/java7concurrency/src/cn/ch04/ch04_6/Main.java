package cn.ch04.ch04_6;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 5.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 6.通过Executors工厂类的newScheduledThreadPool()方法创建一个ScheduledThreadPoolExecutor执行器，并传递1作为参数
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n", new Date());
        // 7.初始化一些任务(在我们的示例中是5个)，然后通过ScheduledThreadPoolExecutor实例的schedule()方法来启动这些任务
        for (int i = 0; i < 5; i++) {
            Task task = new Task("Task " + i);
            executor.schedule(task, i + 1, TimeUnit.SECONDS);
        }
        // 8.调用执行器的shutdown()方法来结束执行器
        executor.shutdown();
        // 9.调用执行器的awaitTermination()方法等待所有任务结束
        try {
            // 为了让main线程进入等待，交出执行权，直到其他线程运行结束再接着执行main线程
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 10.在控制台输出信息表示程序执行结束的时间
        System.out.printf("Main: Ends at: %s\n", new Date());
    }
}

// 这个范例的关键点在于Main主类和ScheduledThreadPoolExecutor执行器的管理
// 虽然可以通过ThreadPoolExecutor类来创建定时执行器，但是在Java并发API中则推荐利用Executors工厂类来创建
// 在这个范例中，必须使用newScheduledThreadPool()方法，并且传递数字1作为方法的参数，这个参数就是线程池里拥有的线程数
// 为了在定时执行器中等待一段给定的时间后执行一个任务，需要使用schedule()方法
// 这个方法接收如下的参数:
// ·即将执行的任务
// ·任务执行前所要等待的时间
// ·等待时间的单位，由TimeUnit类的一个常量来指定
// 在这个示例中，每个任务将等待N秒(TimeUnit.SECONDS)，这个N值则等于任务在数组中的位置加1

// 备注:如果想在一个给定的时间点来定时执行任务，那就需要计算这个给定时间点和当前时间的差异值，然后用这个差异值作为任务的延迟值

// 也可以使用Runnable接口来实现任务，因为ScheduledThreadPoolExecutor类的schedule()方法可以同时接受这两种类型的任务
// 虽然ScheduledThreadPoolExecutor类是ThreadPoolExecutor类的子类，因而继承了ThreadPoolExecutor类所有的特性
// 但是，Java推荐仅在开发定时任务程序时采用ScheduledThreadPoolExecutor类
// 最后，在调用shutdown()方法而仍有待处理的任务需要执行时，可以配置ScheduledThreadPoolExecutor的行为
// 默认的行为是不论执行器是否结束，待处理的任务仍将被执行
// 但是，通过调用ScheduledThreadPoolExecutor类的setExecuteExistingDelayedTasksAfterShutdownPolicy()方法则可以改变这个行为
// 传递false参数给这个方法，执行shutdown()方法后，待处理的任务将不会被执行

// Main: Starting at: Sun Oct 20 13:26:30 CST 2019
// Task 0: Starting at : Sun Oct 20 13:26:31 CST 2019
// Task 1: Starting at : Sun Oct 20 13:26:32 CST 2019
// Task 2: Starting at : Sun Oct 20 13:26:33 CST 2019
// Task 3: Starting at : Sun Oct 20 13:26:34 CST 2019
// Task 4: Starting at : Sun Oct 20 13:26:35 CST 2019
// Main: Ends at: Sun Oct 20 13:26:35 CST 2019
