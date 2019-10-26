package cn.ch08.ch08_3;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
// 5.实现范例的主类，创建Main主类，并实现main()方法

// 监控执行器框架
public class Main {

    public static void main(String[] args) throws Exception {
        // 6.调用Executors工厂类的newCachedThreadPool()方法创建一个新的Executor对象
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        // 7.创建并提交10个Task任务对象到executor对象。用随机数来初始化这些任务对象
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task(random.nextInt(10000));
            executor.submit(task);
        }
        // 8.创建一个5次循环。在每一次循环中，调用showLog()方法输出关于executor的信息并让线程休眠1秒钟
        for (int i = 0; i < 5; i++) {
            showLog(executor);
            TimeUnit.SECONDS.sleep(1);
        }
        // 9.调用shutdown()方法关闭executor
        executor.shutdown();
        // 10.创建另一个5次循环。在每一次循环中，调用showLog()方法输出关于executor的信息并让线程休眠1秒钟
        for (int i = 0; i < 5; i++) {
            showLog(executor);
            TimeUnit.SECONDS.sleep(1);
        }
        // 11.调用awaitTermination()方法等待executor结束
        executor.awaitTermination(1, TimeUnit.DAYS);
        // 12.在程序结束时输出一条消息
        System.out.printf("Main: End of the program.\n");
    }

    // 13.实现接收Executor作为参数的showLog()方法。输出线程池的大小、任务数和执行器executor的状态
    private static void showLog(ThreadPoolExecutor executor) {
        System.out.printf("******************************\n");
        System.out.printf("Main: Executor Log\n");
        System.out.printf("Main: Executor: Core Pool Size: %d\n", executor.getCorePoolSize());
        System.out.printf("Main: Executor: Pool Size: %d\n", executor.getPoolSize());
        System.out.printf("Main: Executor: Active Count: %d\n", executor.getActiveCount());
        System.out.printf("Main: Executor: Task Count: %d\n", executor.getTaskCount());
        System.out.printf("Main: Executor: Completed Task Count: %d\n", executor.getCompletedTaskCount());
        System.out.printf("Main: Executor: Shutdown: %s\n", executor.isShutdown());
        System.out.printf("Main: Executor: Terminating: %s\n", executor.isTerminating());
        System.out.printf("Main: Executor: Terminated: %s\n", executor.isTerminated());
        System.out.printf("******************************\n");
    }
}

// 在本节，我们实现了一个任务，这个任务阻塞它的执行线程随机毫秒数
// 接下来，发送10个任务到执行器中，并等待它们执行结束，在控制台输出关于执行器的状态信息
// 使用下列方法获取Executor对象的状态
// ·getCorePoolSize()：这个方法返回一个int整数，表示线程池中的核心线程数
// 它是当执行器不执行任务时，在内部线程池中的最小线程数
// ·getPoolSize()：这个方法返回一个int整数，它是内部线程池的实际大小
// ·getActiveCount()：这个方法返回一个int整数，它是当前正在执行任务的线程数
// ·getTaskCount()：这个方法返回一个long长整数，它是计划将被执行的任务数
// ·getCompletedTaskCount()：这个方法返回一个long长整数，它是已被执行器执行的且已完成执行的任务数
// ·isShutdown()：当调用了执行器的shutdown()方法来结束它的执行时，方法会返
// 回一个boolean布尔值
// ·isTerminating()：当执行器正在执行shutdown()方法但还没有执行完成时，这个
// 方法返回一个boolean布尔值
// ·isTerminated()：当执行器已完成执行时，方法返回一个boolean布尔值

// pool-1-thread-1: Begin
// pool-1-thread-10: Begin
// pool-1-thread-9: Begin
// pool-1-thread-8: Begin
// pool-1-thread-7: Begin
// pool-1-thread-6: Begin
// pool-1-thread-5: Begin
// pool-1-thread-4: Begin
// pool-1-thread-3: Begin
// ******************************
// pool-1-thread-2: Begin
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 10
// Main: Executor: Active Count: 10
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 0
// Main: Executor: Shutdown: false
// Main: Executor: Terminating: false
// Main: Executor: Terminated: false
// ******************************
// pool-1-thread-5: End
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 10
// Main: Executor: Active Count: 9
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 1
// Main: Executor: Shutdown: false
// Main: Executor: Terminating: false
// Main: Executor: Terminated: false
// ******************************
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 10
// Main: Executor: Active Count: 9
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 1
// Main: Executor: Shutdown: false
// Main: Executor: Terminating: false
// Main: Executor: Terminated: false
// ******************************
// pool-1-thread-10: End
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 10
// Main: Executor: Active Count: 8
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 2
// Main: Executor: Shutdown: false
// Main: Executor: Terminating: false
// Main: Executor: Terminated: false
// ******************************
// pool-1-thread-1: End
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 10
// Main: Executor: Active Count: 7
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 3
// Main: Executor: Shutdown: false
// Main: Executor: Terminating: false
// Main: Executor: Terminated: false
// ******************************
// pool-1-thread-9: End
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 6
// Main: Executor: Active Count: 6
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 4
// Main: Executor: Shutdown: true
// Main: Executor: Terminating: true
// Main: Executor: Terminated: false
// ******************************
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 6
// Main: Executor: Active Count: 6
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 4
// Main: Executor: Shutdown: true
// Main: Executor: Terminating: true
// Main: Executor: Terminated: false
// ******************************
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 6
// Main: Executor: Active Count: 6
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 4
// Main: Executor: Shutdown: true
// Main: Executor: Terminating: true
// Main: Executor: Terminated: false
// ******************************
// pool-1-thread-3: End
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 5
// Main: Executor: Active Count: 5
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 5
// Main: Executor: Shutdown: true
// Main: Executor: Terminating: true
// Main: Executor: Terminated: false
// ******************************
// pool-1-thread-7: End
// pool-1-thread-4: End
// pool-1-thread-6: End
// ******************************
// Main: Executor Log
// Main: Executor: Core Pool Size: 0
// Main: Executor: Pool Size: 2
// Main: Executor: Active Count: 2
// Main: Executor: Task Count: 10
// Main: Executor: Completed Task Count: 8
// Main: Executor: Shutdown: true
// Main: Executor: Terminating: true
// Main: Executor: Terminated: false
// ******************************
// pool-1-thread-8: End
// pool-1-thread-2: End
// Main: End of the program.