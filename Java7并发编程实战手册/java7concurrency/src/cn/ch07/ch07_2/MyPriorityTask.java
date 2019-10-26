package cn.ch07.ch07_2;

import java.util.concurrent.TimeUnit;

// 1.创建名为MyPriorityTask的类，并实现Runnable和Comparable接口，泛型参数
public class MyPriorityTask implements Comparable<MyPriorityTask>, Runnable {
    // 2.声明一个名为priority的私有int属性
    private int priority;
    // 3.声明一个名为name的私有String属性
    private String name;

    public MyPriorityTask(String name, int priority) {
        // 4.实现类构造器来初始化属性
        this.name = name;
        this.priority = priority;
    }

    // 5.实现一个方法返回优先级属性的值
    public int getPriority() {
        return priority;
    }

    // 6.实现在Comparable接口中声明compareTo()方法。它接收一个MyPriorityTask对象作为参数
    // 然后比较当前和参数对象的优先级值。让高优先级的任务先于低优先级的任务执行
    @Override
    public int compareTo(MyPriorityTask o) {
        if (this.getPriority() < o.getPriority()) {
            return 1;
        }
        if (this.getPriority() > o.getPriority()) {
            return -1;
        }
        return 0;
    }

    // 7.实现run()方法。使当前线程休眠2s
    @Override
    public void run() {
        System.out.printf("MyPriorityTask: %s Priority : %d\n", name, priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 把一个普通的执行器转换为基于优先级的执行器是非常简单的
// 只需要把PriorityBlockingQueue对象作为其中一个传入参数
// 并且要求它的泛型参数是Runnable接口即可

// 使用这种执行器的时候，存放在优先队列中的所有对象必须实现Comparable接口
// 任务类MyPriorityTask实现了Runnable接口以成为执行任务，也实现了Comarable接口以被存放在优先队列中
// 这个类有一个priority属性用来存放任务的优先级
// 一个任务的优先级属性值越高，它越早被执行
// compareTo()方法决定了优先队列中的任务顺序

// Main主类传递了8个不同优先级的任务到执行器
// 发送到的第一个任务 是第一个被执行的任务
// 当执行器空闲并等待任务时，第一批任务到达，它们将立即被执行
// 这里我们只创建了两个线程执行器，所以前两个任务将被首批执行
// 接下来，剩余任务基于它们优先级被依次执行

// 可以配置Executor使用BlockingQueue接口的任意实现，比较有趣的一个是DelayQueue
// 这个类用来存放带有延迟激活的元素，提供了只返回活动对象的方法
// 可以使用ScheduledThreadPoolExecutor类定制自己的类

// MyPriorityTask: Task 0 Priority : 0
// MyPriorityTask: Task 1 Priority : 1
// MyPriorityTask: Task 7 Priority : 7
// MyPriorityTask: Task 6 Priority : 6
// MyPriorityTask: Task 5 Priority : 5
// MyPriorityTask: Task 4 Priority : 4
// MyPriorityTask: Task 3 Priority : 3
// MyPriorityTask: Task 2 Priority : 2
// Main: End of the program.