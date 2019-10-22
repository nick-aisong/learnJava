package cn.ch05.ch05_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// 2.创建一个名为TaskManager的类。本示例将利用这个类来存储在ForkJoinPool中执行的任务
// 由于ForkJoinPool和ForkJoinTask类的局限性，将利用TaskManager类来取消ForkJoinPool类中所有的任务
public class TaskManager {
    // 3.声明一个名为tasks的对象列表，带有ForkJoinTask泛型参数，并且ForkJoinTask带有Integer泛型参数
    private List<ForkJoinTask<Integer>> tasks;

    // 4.实现类的构造器，用来初始化任务列表
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // 5.实现addTask()方法。增加一个ForkJoinTask对象到任务列表中
    public void addTask(ForkJoinTask<Integer> task) {
        tasks.add(task);
    }

    // 6.实现cancelTasks()方法。遍历存储在列表中的所有ForkJoinTask对象，然后调用cancel()方法取消之
    // cancelTasks()方法接收一个要取消剩余任务的ForkJoinTask对象作为参数，然后取消所有的任务
    public void cancelTasks(ForkJoinTask<Integer> cancelTask) {
        for (ForkJoinTask<Integer> task : tasks) {
            if (task != cancelTask) {
                task.cancel(true);
                ((SearchNumberTask) task).writeCancelMessage();
            }
        }
    }
}
