package cn.ch05.ch05_3;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// 本例将学习如何使用ForkJoinPool和ForkJoinTask类所提供的异步方法来管理任务

// 我们将实现一个程序：在一个文件夹及其子文件夹中来搜索带有指定扩展名的文件
// ForkJoinTask类将实现处理这个文件夹的内容
// 而对于这个文件夹中的每一个子文件，任务将以异步的方式发送一个新的任务给ForkJoinPool类
// 对于每个文件夹中的文件，任务将检查任务文件的扩展名，如果符合条件就将其增加到结果列表中

// 17.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 18.通过默认的构造器创建ForkJoinPool线程池
        ForkJoinPool pool = new ForkJoinPool();
        // 19.创建3个FolderProcessor任务，并使用不同的文件夹路径来初始化这些任务
        FolderProcessor system = new FolderProcessor("C:\\Program Files", "log");
        FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
        FolderProcessor documents = new FolderProcessor("C:\\Program Files", "log");
        //  FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
        //  FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");
        // 20.调用execute()方法执行线程池里的3个任务
        pool.execute(system);
        pool.execute(apps);
        pool.execute(documents);
        // 21.在控制台上每隔1秒钟输出线程池的状态信息，直到这3个任务执行结束
        do {
            System.out.printf("*****************************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("*****************************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));
        // 22.调用shutdown()方法关闭ForkJoinPool线程池
        pool.shutdown();
        // 23.在控制台输出每一个任务产生的结果的大小
        List<String> results;
        results = system.join();
        System.out.printf("System: %d files found.\n", results.size());
        results = apps.join();
        System.out.printf("Apps: %d files found.\n", results.size());
        results = documents.join();
        System.out.printf("Documents: %d files found.\n", results.size());
    }
}

// 这个范例的重点在于FolderProcessor类。每一个任务处理一个文件夹中的内容
// 文件夹中的内容有以下两种类型的元素：
// ·文件
// ·其他文件夹

// 如果主任务发现一个文件夹，它将创建另一个Task对象来处理这个文件夹，调用fork()方法把这个新对象发送到线程池中
// fork()方法发送任务到线程池时，如果线程池中有空闲的工作者线程(WorkerThread)或者将创建一个新的线程
// 那么开始执行这个任务，fork()方法会立即返回
// 因此，主任务可以继续处理文件夹里的其他内容
// 对于每一个文件，任务开始比较它的文件扩展名，如果与要搜索的扩展名相同，那么将文件的完整路径增加到结果列表中

// 一旦主任务处理完指定文件夹里的所有内容，它将调用join()方法等待发送到线程池中的所有子任务执行完成
// join()方法在主任务中被调用，然后等待任务执行结束，并通过compute()方法返回值
// 主任务将所有的子任务结果进行合并，这些子任务发送到线程池中时带有自己的结果列表，然后通过调用compute()方法返回这个列表并作为主任务的返回值

// ForkJoinPool类也允许以异步的方式执行任务。调用execute()方法发送3个初始任务到线程池中
// 在Main主类中，调用shutdown()方法结束线程池，并在控制台输出线程池中任务的状态及其变化的过程
// ForkJoinPool类包含了多个方法可以实现这个目的

// 本范例使用join()方法来等待任务的结束，然后获取它们的结果
// 也可以使用get()方法以下的两个版本来完成这个目的
// ·get()：如果ForkJoinTask类执行结束，或者一直等到结束，那么get()方法的这个版本则返回由compute()方法返回的结果
// ·get(long timeout, TimeUnit unit)：如果任务的结果未准备好，那么get()方法的这个版本将等待指定的时间
// 如果超过指定的时间了，任务的结果仍未准备好，那么这个方法将返回null值
// TimeUnit 是一个枚举类，有如下的常量：DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

// get()方法和join()方法还存在两个主要的区别：
// ·join()方法不能被中断，如果中断调用join()方法的线程，方法将抛出InterruptedException异常
// ·如果任务抛出任何运行时异常， 那么get()方法将返回ExecutionException异常，但是join()方法将返回RuntimeException异常

// *****************************************************
// Main: Parallelism: 4
// Main: Active Threads: 4
// Main: Task Count: 44
// Main: Steal Count: 2
// *****************************************************
// *****************************************************
// Main: Parallelism: 4
// Main: Active Threads: 10
// Main: Task Count: 208
// Main: Steal Count: 77
// *****************************************************
// C:\Program Files\Realtek\Audio\HDA\rtkhdasetting: 103 tasks ran.
// C:\Program Files\Realtek\Audio\HDA\rtkhdasetting: 103 tasks ran.
// C:\Program Files\Realtek\Audio\HDA\rtkhdasetting: 103 tasks ran.
// *****************************************************
// Main: Parallelism: 4
// Main: Active Threads: 11
// Main: Task Count: 0
// Main: Steal Count: 549
// *****************************************************
// System: 48 files found.
// Apps: 48 files found.
// Documents: 48 files found.