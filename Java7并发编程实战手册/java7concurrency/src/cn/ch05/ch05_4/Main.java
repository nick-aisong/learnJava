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
