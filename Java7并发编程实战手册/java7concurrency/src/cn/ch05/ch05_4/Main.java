package cn.ch05.ch05_4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// 10.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 11.创建一个名为array并能容纳100个整数的int数组
        int[] array = new int[100];
        // 12.创建一个Task对象来处理这个数组
        Task task = new Task(array, 0, 100);
        // 13.通过默认的构造器创建ForkJoinPool对象
        ForkJoinPool pool = new ForkJoinPool();
        // 14.调用execute()方法在线程池中执行任务
        pool.execute(task);
        // 15.调用shutdown()方法关闭线程池
        pool.shutdown();
        // 16.调用awaitTermination()方法等待任务执行结束
        // 如果想一直等待到任务执行完成，那就传递值1和TimeUnit.DAYS作为参数给这个方法
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 17.调用isCompletedAbnormally()方法来检查主任务或者它的子任务之一是否抛出了异常
        // 在这个示例中，在控制台输出信息就表示有异常抛出。调用ForkJoinTask类的getException()方法来获取异常信息
        if (task.isCompletedAbnormally()) {
            System.out.printf("Main: An exception has ocurred\n");
            System.out.printf("Main: %s\n", task.getException());
        }
        System.out.printf("Main: Result: %d", task.join());
    }
}

// 在本节，我们实现的Task类用来处理一个数字数组
// 它检查要处理的数字块规模是否包含有10个或更多个元素
// 在这个情况下，Task类拆分这个数字块为两部分，然后创建两个新的Task对象用来处理这两部分
// 否则，它将寻找位于数组中第4个位置(索引位为3)的元素
// 如果这个元素位于任务处理块中，它将抛出一个RuntimeException异常
// 虽然运行这个程序时将抛出异常，但是程序不会停止
// 在Main主类中，调用原始任务ForkJoinTask类的isCompletedAbnormally()方法，如果主任务或者它的子任务之一拋出了异常，这个方法将返回true
// 也可以使用getException()方法来获得抛出的Exception对象
// 当任务抛出运行时异常时，会影响它的父任务(发送到ForkJoinPool类的任务)，以及父任务的父任务，以此类推
// 查阅程序的输出结果，将会发现有一些任务没有结束的信息
// 那些任务的开始信息如下：
// Task: Starting form 0 to 100
// Task: Starting form 0 to 50
// Task: Starting form 0 to 25
// Task: Starting form 0 to 12
// Task: Starting form 0 to 6
// 这些任务是那些抛出异常的任务和它的父任务。所有这些任务都是异常结束的
// 记住一点：在用ForkJoinPool对象和ForkJoinTask对象开发一个程序时， 它们是会抛出异常的，如果不想要这种行为，就得采用其他方式

// 在范例中，不采用抛出异常，而调用ForkJoinTask类的completeExceptionally()方法也可以获得同样的结果
// 代码如下所示：
// Exception e = new Exception("This task throws an Exception: " + "Task from " + start + " to " + end);
// completeExceptionally(e);

// Task: Start from 0 to 100
// Task: Start from 0 to 50
// Task: Start from 0 to 25
// Task: Start from 0 to 12
// Task: Start from 0 to 6
// Task: Start from 6 to 12
// Task: Start from 50 to 100
// Task: Start from 50 to 75
// Task: Start from 50 to 62
// Task: Start from 50 to 56
// Task: Start from 25 to 50
// Task: Start from 75 to 100
// Task: Start from 25 to 37
// Task: Start from 75 to 87
// Task: Start from 25 to 31
// Task: Start from 75 to 81
// Task: End from 6 to 12
// Task: Start from 12 to 25
// Task: Start from 12 to 18
// Task: End from 50 to 56
// Task: Start from 56 to 62
// Task: End from 25 to 31
// Task: Start from 31 to 37
// Task: End from 75 to 81
// Task: Start from 81 to 87
// Task: End from 12 to 18
// Task: Start from 18 to 25
// Task: End from 56 to 62
// Task: End from 50 to 62
// Task: Start from 62 to 75
// Task: Start from 62 to 68
// Task: End from 81 to 87
// Task: End from 75 to 87
// Task: Start from 87 to 100
// Task: Start from 87 to 93
// Task: End from 31 to 37
// Task: End from 25 to 37
// Task: Start from 37 to 50
// Task: Start from 37 to 43
// Task: End from 18 to 25
// Task: End from 12 to 25
// Task: Start from 93 to 100
// Task: End from 62 to 68
// Task: Start from 68 to 75
// Task: End from 87 to 93
// Task: End from 37 to 43
// Task: Start from 43 to 50
// Task: End from 93 to 100
// Task: End from 87 to 100
// Task: End from 75 to 100
// Task: End from 68 to 75
// Task: End from 62 to 75
// Task: End from 50 to 75
// Task: End from 50 to 100
// Exception in thread "main" java.lang.RuntimeException: java.lang.RuntimeException: This task throws an Exception: Task from 0 to 6
// 	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
// 	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
// 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
// 	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
// 	at java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:593)
// 	at java.util.concurrent.ForkJoinTask.reportException(ForkJoinTask.java:677)
// 	at java.util.concurrent.ForkJoinTask.join(ForkJoinTask.java:720)
// 	at cn.ch05.ch05_4.Main.main(Main.java:33)
// Caused by: java.lang.RuntimeException: This task throws an Exception: Task from 0 to 6
// 	at cn.ch05.ch05_4.Task.compute(Task.java:30)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:7)
// 	at java.util.concurrent.RecursiveTask.exec(RecursiveTask.java:94)
// 	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
// 	at java.util.concurrent.ForkJoinTask.doInvoke(ForkJoinTask.java:401)
// 	at java.util.concurrent.ForkJoinTask.invokeAll(ForkJoinTask.java:759)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:43)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:7)
// 	at java.util.concurrent.RecursiveTask.exec(RecursiveTask.java:94)
// 	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
// 	at java.util.concurrent.ForkJoinTask.doInvoke(ForkJoinTask.java:401)
// 	at java.util.concurrent.ForkJoinTask.invokeAll(ForkJoinTask.java:759)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:43)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:7)
// 	at java.util.concurrent.RecursiveTask.exec(RecursiveTask.java:94)
// 	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
// 	at java.util.concurrent.ForkJoinTask.doInvoke(ForkJoinTask.java:401)
// 	at java.util.concurrent.ForkJoinTask.invokeAll(ForkJoinTask.java:759)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:43)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:7)
// 	at java.util.concurrent.RecursiveTask.exec(RecursiveTask.java:94)
// 	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
// 	at java.util.concurrent.ForkJoinTask.doInvoke(ForkJoinTask.java:401)
// 	at java.util.concurrent.ForkJoinTask.invokeAll(ForkJoinTask.java:759)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:43)
// 	at cn.ch05.ch05_4.Task.compute(Task.java:7)
// 	at java.util.concurrent.RecursiveTask.exec(RecursiveTask.java:94)
// 	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
// 	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
// 	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
// 	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
// Task: End from 43 to 50
// Task: End from 37 to 50
// Task: End from 25 to 50
// Main: An exception has ocurred
// Main: java.lang.RuntimeException: java.lang.RuntimeException: This task throws an Exception: Task from 0 to 6
