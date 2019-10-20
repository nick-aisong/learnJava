package cn.ch04.ch04_7;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// 5.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 6.通过调用Executors工厂类的newScheduledThreadPool()方法创建ScheduledThreadPoolExecutor执行器对象，传递1作为这个方法的参数
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        // 7.在控制台输出实际时间
        System.out.printf("Main: Starting at: %s\n", new Date());
        // 8.创建一个新的Task对象
        Task task = new Task("Task");
        // 9.调用scheduledAtFixRate()方法将这个任务发送给执行器
        // 传递给这个方法的参数分别为上一步创建的task对象、数字1、数字2，以及TimeUnit.SECONDS常量
        // 这个方法返回一个用来控制任务状态的ScheduledFuture对象
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        // 10.创建一个10步的循环，在控制台输出任务下一次将要执行的剩余时间
        // 在循环体内，用ScheduledFuture类的getDelay()方法来获取任务下一次将要执行的毫秒数,然后将线程休眠500毫秒
        for (int i = 0; i < 10; i++) {
            System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 11.调用shutdown()方法结束执行器
        executor.shutdown();
        // 12.将线程休眠5秒，等待周期性的任务全部执行完成
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 13.在控制台输出信息表示程序结束
        System.out.printf("Main: Finished at: %s\n", new Date());
    }
}

// 想要通过执行器框架来执行一个周期性任务时，需要一个ScheduledExecutorService对象同创建执行器一样
// 在Java中推荐使用Executors工厂来创建ScheduledExecutorService对象
// Executors类就是执行器对象的工厂
// 在这个例子中，可以使用newScheduledThreadPool()方法来创建一个ScheduledExecutorService对象
// 这个方法接收一个表示线程池中的线程数来作为参数
// 在这个范例中，因为仅有一个任务，所以只需要传递数字1作为参数即可
// 一旦有了可以执行周期性任务的执行器，就可以发送任务给这个执行器
// 在范例中，我们使用scheduledAtFixedRate()方法发送任务
// 这个方法接收4个参数，分别为将被周期性执行的任务，任务第一次执行后的延时时间，两次执行的时间周期，以及第2个和第3个参数的时间单位
// 这个单位是TimeUnit枚举的常量。TimeUnit 是一个枚举类，有如下的常量: DAYS、HOURS 、MICROSECONDS 、MILLISECONDS 、MINUTES 、NANOSECONDS和SECONDS
// 另一个需要注意的是，两次执行之间的周期是指任务在两次执行开始时的时间间隔
// 如果有一个周期性的任务需要执行5秒钟，但是却让它每3秒钟执行一次，那么，在任务执行的过程中将会有两个任务实例同时存在
// scheduleAtFixedRate()方法返回一个ScheduledFuture对象，ScheduledFuture 接口则扩展了Future接口，于是它带有了定时任务的相关操作方法
// ScheduledFuture是一个泛型参数化的接口
// 在这个示例中，任务是Runnable对象，并没有泛型参数化，必须通过?符号作为参数来泛型化它们
// 我们已经使用过ScheduledFuture接口中的一个方法。getDelay0方法返回任务到下一次执行时所要等待的剩余时间
// 这个方法接收一个TimeUnit常量作为时间单位

// ScheduledThreadPoolExecutor类还提供了其他方法来安排周期性任务的运行，比如，scheduleWithFixedRate()方法
// 这个方法与scheduledAtFixedRate()方法具有相同的参数，但是略有一些不同需要引起注意
// 在scheduledAtFixedRate()方法中，第3个参数表示任务两次执行开始时间的间隔
// 而在schedulledWithFixedDelay()方法中，第3个参数则是表示任务上一次执行结束的时间与任务下一次开始执行的时间的间隔
// 也可以配置ScheduledThreadPoolExecutor实现shutdown()方法的行为，默认行为是当调用shutdown()方法后，定时任务就结束了
// 可以通过ScheduledThreadPoolExecutor类的setContinueExistingPeriodicTasksAfterShutdownPolicy()方法来改变这个行为
// 传递参数true给这个方法，这样调用shutdown()方法后，周期性任务仍将继续执行

// Main: Starting at: Sun Oct 20 14:24:13 CST 2019
// Main: Delay: 999
// Main: Delay: 499
// Task: Starting at: Sun Oct 20 14:24:14 CST 2019
// Main: Delay: 1998
// Main: Delay: 1498
// Main: Delay: 996
// Main: Delay: 494
// Task: Starting at: Sun Oct 20 14:24:16 CST 2019
// Main: Delay: 1993
// Main: Delay: 1493
// Main: Delay: 992
// Main: Delay: 491
// Task: Starting at: Sun Oct 20 14:24:18 CST 2019
// Main: Finished at: Sun Oct 20 14:24:23 CST 2019
