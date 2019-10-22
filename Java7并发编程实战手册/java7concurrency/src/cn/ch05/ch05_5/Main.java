package cn.ch05.ch05_5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
// 该范例将寻找数组中某个数字所处的位置
// 第一个任务是寻找可以被取消的剩余任务数
// 由于Fork/Join框架没有提供取消功能，我们将创建一个辅助类来实现取消任务的操作

// 27.实现范例的主类，创建Main主类，并实现main()方法
public class Main {
    public static void main(String[] args) {
        // 28.用ArrayGenerator类创建一个容量为1,000的数字数组
        ArrayGenerator generator = new ArrayGenerator();
        int numbers[] = generator.generateArray(1000);
        // 29.创建一个TaskManager对象
        TaskManager manager = new TaskManager();
        // 30.通过默认的构造器创建一个ForkJoinPool对象
        ForkJoinPool pool = new ForkJoinPool();
        // 31.创建一个SearchNumberTask对象用来处理第28步生成的数组
        SearchNumberTask task = new SearchNumberTask(numbers, 0, 1000, 5, manager);
        // 32.调用execute()方法采用异步方式执行线程池中的任务
        pool.execute(task);
        // 33.调用shutdown()方法关闭线程池
        pool.shutdown();
        // 34.调用ForkJoinPool类的awaitTermination()方法等待任务执行结束
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 35.在控制台输出信息表示程序结束
        System.out.printf("Main: The program has finished\n");
    }
}

// ForkJoinTask类提供的cancel()方法允许取消一个仍没有被执行的任务，这是非常重要的一点
// 如果任务已经开始执行，那么调用cancel()方法 也无法取消
// 这个方法接收一个名为mayInterruptIfRunning的boolean值参数
// 顾名思义，如果传递true值给这个方法，即使任务正在运行也将被取消
// JavaAPI文档指出，ForkJoinTask类的默认实现，这个属性没有起作用
// 如果任务还没有开始执行，那么这些任务将被取消
// 任务的取消对于已发送到线程池中的任务没有影响，它们将继续执行

// Fork/Join框架的局限性在于，ForkJoinPool线程池中的任务不允许被取消
// 为了克服这种局限性，我们实现了TaskManager类，它存储发送到线程池中的所有任务，可以用一个方法来取消存储的所有任务
// 如果任务正在运行或者已经执行结束，那么任务就不能被取消，cancel()方法返回false 值，因此可以尝试去取消所有的任务而不用担心可能带来的间接影响
// 这个范例实现在数字数组中寻找一个数字。根据Fork/Join框架的推荐，我们将问题拆分为更小的子问题
// 由于我们仅关心数字的一次出现，因此，当找到它时，就会取消其他的所有任务

// Task: 0:1000
// Task: 0:500
// Task: 0:250
// Task: 0:125
// Task: 0:62
// Task: 0:31
// Task: 0:15
// Task: 0:7
// Task: 500:1000
// Task: 500:750
// Task: 500:625
// Task: 500:562
// Task: 500:531
// Task: 500:515
// Task: 500:507
// Task: 250:500
// Task: 250:375
// Task: 250:312
// Task: 250:281
// Task: 250:265
// Task: 250:257
// Task: 750:1000
// Task: 750:875
// Task: 750:812
// Task: 750:781
// Task: 750:765
// Task: 750:757
// Task: Number 5 found in position 751
// Task: Cancelled task from 0 to 500
// Task: Cancelled task from 500 to 1000
// Task: Cancelled task from 0 to 250
// Task: Cancelled task from 250 to 500
// Task: Cancelled task from 0 to 125
// Task: Cancelled task from 125 to 250
// Task: Cancelled task from 0 to 62
// Task: Cancelled task from 62 to 125
// Task: Cancelled task from 0 to 31
// Task: Cancelled task from 31 to 62
// Task: Cancelled task from 0 to 15
// Task: Cancelled task from 15 to 31
// Task: Cancelled task from 0 to 7
// Task: Cancelled task from 7 to 15
// Task: Cancelled task from 500 to 750
// Task: Cancelled task from 750 to 1000
// Task: Cancelled task from 500 to 625
// Task: Cancelled task from 625 to 750
// Task: Cancelled task from 500 to 562
// Task: Cancelled task from 562 to 625
// Task: Cancelled task from 500 to 531
// Task: Cancelled task from 531 to 562
// Task: Cancelled task from 500 to 515
// Task: Cancelled task from 515 to 531
// Task: Cancelled task from 500 to 507
// Task: Cancelled task from 507 to 515
// Task: Cancelled task from 250 to 375
// Task: Cancelled task from 375 to 500
// Task: Cancelled task from 250 to 312
// Task: Cancelled task from 312 to 375
// Task: Cancelled task from 250 to 281
// Task: Cancelled task from 281 to 312
// Task: Cancelled task from 250 to 265
// Task: Cancelled task from 265 to 281
// Task: Cancelled task from 250 to 257
// Task: Cancelled task from 257 to 265
// Task: Cancelled task from 750 to 875
// Task: Cancelled task from 875 to 1000
// Task: Cancelled task from 750 to 812
// Task: Cancelled task from 812 to 875
// Task: Cancelled task from 750 to 781
// Task: Cancelled task from 781 to 812
// Task: Cancelled task from 750 to 765
// Task: Cancelled task from 765 to 781
// Task: Cancelled task from 757 to 765
// Main: The program has finished
