package cn.ch04.ch04_8;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 2.实现范例主类，创建Main主类，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 3.通过Executors工厂类的newCachedThreadPool()方法创建一个ThreadPoolExecutor执行器对象
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        // 4.创建一个新的Task对象
        Task task = new Task();
        // 5.调用submit()方法将任务发送给执行器
        System.out.printf("Main: Executing the Task\n");
        Future<String> result = executor.submit(task);
        // 6.让主线程休眠2秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 7.执行器的submit()方法返回名为result的Future对象，调用Future对象的cancel()方法来取消任务的执行
        // 传递参数true给这个cancel()方法
        System.out.printf("Main: Canceling the Task\n");
        result.cancel(true);
        // 8.在控制台输出调用isCancelled()方法和isDone()方法的结果，来验证任务已经被取消和已完成
        System.out.printf("Main: Cancelled: %s\n", result.isCancelled());
        System.out.printf("Main: Done: %s\n", result.isDone());
        // 9.调用shutdown()方法结束执行器，然后在控制台输出信息表示程序执行结束
        executor.shutdown();
        System.out.printf("Main: The executor has finished\n");
    }
}

// 如果想取消一个已经发送给执行器的任务，可以使用Future接口的cancel()方法
// 根据调用cancel()方法时所传递的参数以及任务的状态，这个方法的行为有些不同
// ·如果任务已经完成，或者之前已经被取消，或者由于某种原因而不能被取消，那么方法将返回false并且任务也不能取消
// ·如果任务在执行器中等待分配Thread对象来执行它，那么任务被取消，并且不会开始执行，如果任务已经在运行，那么它依赖于调用cancel()方法时所传递的参数
// 如果传递的参数为true并且任务正在运行，那么任务将被取消
// 如果传递的参数为false 并且任务正在运行，那么任务不会被取消

// 如果Future对象所控制任务已经被取消，那么使用Future对象的get()方法时将抛出CancellationException异常

// Main: Executing the Task
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Task: Test
// Main: Canceling the Task
// Main: Cancelled: true
// Main: Done: true
// Main: The executor has finished