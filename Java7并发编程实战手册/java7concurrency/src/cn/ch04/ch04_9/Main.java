package cn.ch04.ch04_9;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 9.实现范例的主类，创建Main主类，然后实现main()方法
public class Main {

    public static void main(String[] args) {
        // 10.调用Executors工厂类的newCachedThreadPool()方法创建一个ExecutorService执行器对象
        ExecutorService executor = Executors.newCachedThreadPool();
        // 11.创建一个数组用来存储5个ResultTask对象
        ResultTask[] resultTasks = new ResultTask[5];
        // 12.初始化ResultTask对象。在数组的每一个位置上，必须创建ExecutorTask对象
        // 然后创建ResultTask对象来使用ExecutorTask对象
        // 最后调用submit()方法将ResultTask任务发送给执行器
        for (int i = 0; i < 5; i++) {
            ExecutableTask executableTask = new ExecutableTask("Task " + i);
            resultTasks[i] = new ResultTask(executableTask);
            executor.submit(resultTasks[i]);
        }
        // 13.将主线程休眠5秒钟
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 14.取消已经发送给执行器的所有任务
        for (int i = 0; i < resultTasks.length; i++) {
            resultTasks[i].cancel(true);
        }
        // 15.通过调用ResultTask对象的get()方法，在控制台上输出还没有被取消的任务结果
        for (int i = 0; i < resultTasks.length; i++) {
            try {
                if (!resultTasks[i].isCancelled()) {
                    System.out.printf("%s\n", resultTasks[i].get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 16.调用shutdown()方法结束执行器
        executor.shutdown();
    }
}

// 当任务执行结束时，FutureTask类就会调用done()方法
// 在这个范例中，我们实现了一个Callable类、一个ExecutableTask类以及一个FutureTask类的子类ResultTask
// 这个子类用来控制ExecutableTask对象的执行
// 在创建好返回值以及改变任务状态为isDone之后，FutureTask类就会在内部调用done()方法
// 虽然我们无法改变任务的结果值，也无法改变任务的状态，但是可以通过任务来关闭系统资源、输出日志信息、发送通知等

// Task 0: Waiting 8 seconds for results.
// Task 4: Waiting 1 seconds for results.
// Task 3: Waiting 4 seconds for results.
// Task 2: Waiting 5 seconds for results.
// Task 1: Waiting 3 seconds for results.
// Task 4: Has finished
// Task 1: Has finished
// Task 3: Has finished
// Task 0: Has been cancelled
// java.lang.InterruptedException: sleep interrupted
// Task 2: Has been cancelled
// Hello, world. I'm Task 1
// Hello, world. I'm Task 3
// Hello, world. I'm Task 4
// 	at java.lang.Thread.sleep(Native Method)
// 	at java.lang.Thread.sleep(Thread.java:340)
// 	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
// 	at cn.ch04.ch04_9.ExecutableTask.call(ExecutableTask.java:26)
// 	at cn.ch04.ch04_9.ExecutableTask.call(ExecutableTask.java:7)
// 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
// 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
// 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
// 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
// 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
// 	at java.lang.Thread.run(Thread.java:748)
// java.lang.InterruptedException: sleep interrupted
// 	at java.lang.Thread.sleep(Native Method)
// 	at java.lang.Thread.sleep(Thread.java:340)
// 	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
// 	at cn.ch04.ch04_9.ExecutableTask.call(ExecutableTask.java:26)
// 	at cn.ch04.ch04_9.ExecutableTask.call(ExecutableTask.java:7)
// 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
// 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
// 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
// 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
// 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
// 	at java.lang.Thread.run(Thread.java:748)
