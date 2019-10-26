package cn.ch08.ch08_4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
// 7.实现范例的主类，创建Main主类，并实现main()方法

// 监控Fork/Join池
public class Main {

    public static void main(String[] args) throws Exception {
        // 8.创建一个名为pool的ForkJoinPool对象
        ForkJoinPool pool = new ForkJoinPool();
        // 9.创建一个名为array能够容纳10,000个元素的int数组
        int[] array = new int[10000];
        // 10.创建一个新Task对象来处理整个数组
        Task task1 = new Task(array, 0, array.length);
        // 11.调用execute()方法发送任务到线程池中去执行
        pool.execute(task1);
        // 12.当任务未执行完成时，调用showLog()方法输出ForkJoinPool类的状态信息并让线程休眠1秒钟
        while (!task1.isDone()) {
            showLog(pool);
            TimeUnit.SECONDS.sleep(1);
        }
        // 13.调用shutdown()方法关闭池
        pool.shutdown();
        // 14.调用awaitTermination()方法等待池的结束
        pool.awaitTermination(1, TimeUnit.DAYS);
        // 15.调用showLog()方法输出ForkJoinPool类的状态信息，并在控制台输出一条消息表示程序结束
        showLog(pool);
        System.out.printf("Main: End of the program.\n");
    }

    // 16.实现showLog()方法。它接收一个ForkJoinPool对象作为参数，并输出关于它的状态和正在执行的线程和任务的信息
    private static void showLog(ForkJoinPool pool) {
        System.out.printf("*******************************\n");
        System.out.printf("Main: Fork/Join Pool log\n");
        System.out.printf("Main: Fork/Join Pool: Parallelism: %d\n", pool.getParallelism());
        System.out.printf("Main: Fork/Join Pool: Pool Size: %d\n", pool.getPoolSize());
        System.out.printf("Main: Fork/Join Pool: Active Thread Count: %d\n", pool.getActiveThreadCount());
        System.out.printf("Main: Fork/Join Pool: Running Thread Count: %d\n", pool.getRunningThreadCount());
        System.out.printf("Main: Fork/Join Pool: Queued Submission: %d\n", pool.getQueuedSubmissionCount());
        System.out.printf("Main: Fork/Join Pool: Queued Tasks: %d\n", pool.getQueuedTaskCount());
        System.out.printf("Main: Fork/Join Pool: Steal Count: %d\n", pool.getStealCount());
        System.out.printf("Main: Fork/Join Pool: Terminated : %s\n", pool.isTerminated());
        System.out.printf("*******************************\n");
    }
}
