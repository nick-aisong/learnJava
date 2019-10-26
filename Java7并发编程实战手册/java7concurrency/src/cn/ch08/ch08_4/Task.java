package cn.ch08.ch08_4;

import java.util.concurrent.RecursiveAction;

// 1.创建一个名为Task的类，并继承RecursiveAction类
public class Task extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    // 2.声明一个名为array的私有int数组属性，用来存储将要增加的元素
    private int[] array;
    // 3.声明两个分别名为start和end的私有int属性，用来存储任务将要处理的元素块的开始和结束位置
    private int start;
    private int end;

    // 4.实现类的构造器，用来初始化它的属性
    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // 5.实现compute()方法，它包含了任务的主要步骤
    // 如果任务要处理的元素多于100个，则拆分元素集为两部分，再创建两个任务来执行这两部分
    // 调用fork()方法开始执行，然后用join()方法等待这两个任务的结束
    @Override
    protected void compute() {
        if (end - start > 100) {
            int mid = (start + end) / 2;
            Task task1 = new Task(array, start, mid);
            Task task2 = new Task(array, mid, end);

            task1.fork();
            task2.fork();

            task1.join();
            task2.join();
            // 6.如果任务要处理的元素少于或等于100个，则通过循环将每个元素加1
            // 在每次操作后让线程休眠5毫秒，然后继续执行元素加1的操作
        } else {
            for (int i = start; i < end; i++) {
                array[i]++;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// 在本节，我们使用一个ForkJoinPool类和一个继承了RecursiveAction类的Task类，实现了一个任务一对一个数组中的元素加1
// RecursiveAction类型的任务是能够在ForkJoinPool类中执行的一种
// 当任务正处理数组时，在控制台输出关于ForkJoinPool类的状态信息
// 使用下列方法获取ForkJoinPool类的状态信息
// ·getPoolSize()：这个方法返回一个int整数，表示Fork/Join线程池中工作者线程(Worker Thread)的数量
// ·getParallelism()：这个方法返回为池建立的期望的并发级别
// ·getActiveThreadCount()：这个方法返回当前正在执行任务的线程数
// ·getRunningThreadCount()：这个方法返回当前正在工作且未在任何同步机制中被阻塞的线程数
// ·getQueuedSubmissionCount()：这个方法返回已经提交到线程池但未开始执行的任务数
// ·getQueuedTaskCount()：这个方法返回已经提交到线程池且已开始执行的任务数
// ·hasQueuedSubmissions()：这个方法返回一个boolean布尔值，表示在线程池中是否有未开始执行的等待任务
// ·getStealCount()：这个方法返回一个long长整数，表示一个工作者线程从另一个线程中偷得的任务的次数
// ·isTerminated()：这个方法返回一个boolean布尔值，表示Fork/Join池是否已经完成执行

// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 38
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 34
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 34
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 30
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 34
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 26
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 26
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 22
// Main: Fork/Join Pool: Steal Count: 1
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 27
// Main: Fork/Join Pool: Steal Count: 4
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 27
// Main: Fork/Join Pool: Steal Count: 4
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 23
// Main: Fork/Join Pool: Steal Count: 4
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 4
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 17
// Main: Fork/Join Pool: Steal Count: 6
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 3
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 8
// Main: Fork/Join Pool: Steal Count: 6
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 3
// Main: Fork/Join Pool: Running Thread Count: 1
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 7
// Main: Fork/Join Pool: Steal Count: 8
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 2
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 10
// Main: Fork/Join Pool: Steal Count: 8
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 2
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 8
// Main: Fork/Join Pool: Steal Count: 8
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 4
// Main: Fork/Join Pool: Active Thread Count: 2
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 3
// Main: Fork/Join Pool: Steal Count: 8
// Main: Fork/Join Pool: Terminated : false
// *******************************
// *******************************
// Main: Fork/Join Pool log
// Main: Fork/Join Pool: Parallelism: 4
// Main: Fork/Join Pool: Pool Size: 0
// Main: Fork/Join Pool: Active Thread Count: 0
// Main: Fork/Join Pool: Running Thread Count: 0
// Main: Fork/Join Pool: Queued Submission: 0
// Main: Fork/Join Pool: Queued Tasks: 0
// Main: Fork/Join Pool: Steal Count: 12
// Main: Fork/Join Pool: Terminated : true
// *******************************
// Main: End of the program.