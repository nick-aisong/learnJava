package cn.ch07.ch07_7;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// 15.创建名为Main的主类，并实现main()方法
public class Main {
    public static void main(String[] args) throws Exception {
        // 16.创建一个名为factory的MyWorkerThreadFactory对象
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
        // 17.创建一个名为pool的ForkJoinPool对象。把之前创建的工厂对象传递到构造器
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);
        // 18.创建一个有100,000个整数的数组array。初始化所有元素值为1
        int array[] = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        // 19.创建一个Task对象，用来对这个数组的所有元素求和
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
        // 20.使用execute()方法将求和任务发送到pool
        pool.execute(task);
        // 21.使用join()方法等待任务结束
        task.join();
        pool.shutdown();
        // 23.使用awaitTermination()方法等待执行器的结束
        pool.awaitTermination(1, TimeUnit.DAYS);
        // 24.使用get()方法在控制台输出任务的结果
        System.out.printf("Main: Result: %d\n", task.get());
        // 25.在控制台输出一条消息表示程序的结束
        System.out.printf("Main: End of the program\n");
    }
}

// 在Fork/Join框架里使用的线程被称为工作线程(WorkerThread)
// Java提供了ForkJoinWorkerThread类，它继承了Thread类并实现了可在Fork/Join框架里使用的工作线程

// 本节范例实现了MyWorkerThread类，它继承了ForkJoinWorkerThread类，并覆盖了类的两个方法
// 这里我们在每个工作线程中实现一个计数器，以统计一个工作线程已执行了多少任务，然后我们使用了ThreadLocal类型的计数器
// 这样每个线程都有自己的计数器，这对于程序员来讲是透明的

// 覆盖ForkJoinWorkerThread类的onStart()方法，以初始化任务计数器
// 当工作线程开始执行这个方法时会被自动调用
// 也覆盖了onTermination()方法，用来输出任务计数器中的值到控制台
// 当工作线程完成执行这个方法时也会被自动调用
// 我们也实现了一个addTask()方法，用来增加每个线程的任务计数器

// 与Java并发API中的所有执行器一样，ForkJoinPool类也使用工厂模式来创建它的线程，所以如果想在ForkJoinPool类中使用MyWorkerThread线程，必须实现自己的线程工厂
// 在Fork/Join框架里，这个工厂必须实现ForkJoinPool.ForkJoinWorkerThreadFactory接口
// 针对这个目的，我们实现了MyWorkerThreadFactory类。它只有一个newThread0方法，用来创建一个新的MyWorkerThread对象
// 最后，我们需要使用刚创建的工厂来初始化ForkJoinPool类，这是在Main主类中使用ForkJoinPool类的构造器来实现的

// 当一个线程正常结束或抛出Exception异常时，ForkJoinWorkerThread类提供的onTermination()方法都会被自动调用
// 这个方法接收一个Throwable对象作为参数。如果参数为null值，工作线程正常结束；如果参数有一个值，线程将抛出异常
// 所以我们必须编写相应的代码来处理这种异常情况

// MyWorkerThread 11: Initializing task counter.
// MyWorkerThread 12: Initializing task counter.
// MyWorkerThread 13: Initializing task counter.
// MyWorkerThread 14: Initializing task counter.
// MyWorkerThread 13: 511
// MyWorkerThread 12: 512
// MyWorkerThread 14: 511
// MyWorkerThread 11: 513
// Main: Result: 100000
// Main: End of the program