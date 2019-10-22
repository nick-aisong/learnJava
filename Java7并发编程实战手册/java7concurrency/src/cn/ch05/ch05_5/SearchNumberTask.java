package cn.ch05.ch05_5;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

// 7.实现SearchNumberTask类，并继承RecursiveTask类，RecursiveTask类的泛型参数为Integer类型
// 这个类将寻找在整数数组元素块中的一个数字
public class SearchNumberTask extends RecursiveTask<Integer> {
    // 8.声明一个名为numbers的私有int数组
    private int numbers[];
    // 9.声明两个分别名为start和end的私有int属性。这两个属性将决定任务所要处理的数组的元素
    private int start, end;
    // 10.声明一个名为number的私有int属性，用来存储将要寻找的数字
    private int number;
    // 11.声明一个名为manager的私有TaskManager属性。利用这个对象来取消所有的任务
    private TaskManager manager;
    // 12.声明一个int常量，并初始化其值为-1。当任务找不到数字时将返回这个常量
    private final static int NOT_FOUND = -1;

    // 13.实现类的构造器，用来初始化它的属性
    public SearchNumberTask(int numbers[], int start, int end, int number, TaskManager manager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    // 14.实现compute()方法。在控制台输出信息表示任务开始，并输出start和end的属性值
    @Override
    protected Integer compute() {
        System.out.println("Task: " + start + ":" + end);
        int ret;
        // 15.如果start和end属性值的差异大于10 (任务必须处理大于10个元素的数组)
        // 那么，就调用launchTasksQ方法将这个任务拆分为两个子任务
        if (end - start > 10) {
            ret = launchTasks();
            // 16.否则，寻找在数组块中的数字，调用lookForNumber()方法处理这个任务
        } else {
            ret = lookForNumber();
        }
        // 17.返回任务的结果
        return ret;
    }

    // 18.实现lookForNumber()方法
    private int lookForNumber() {
        // 19.遍历任务所要处理的数组块中的所有元素，将元素中存储的数字和将要寻找的数字进行比较
        // 如果它们相等，就在控制台输出信息表示找到了，并用TaskManager对象的cancelTasks()方法取消所有的任务
        // 然后返回已找到的这个元素所在的位置
        for (int i = start; i < end; i++) {
            if (numbers[i] == number) {
                System.out.printf("Task: Number %d found in position %d\n", number, i);
                manager.cancelTasks(this);
                return i;
            }
            // 20.在循环体中，将任务休眠1秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 21.返回-1表示没有找到元素
        return NOT_FOUND;
    }

    // 22.实现launchTasks()方法。将这个任务要处理的元素块拆分成两部分，然后创建两个SearchNumberTask对象来处理它们
    private int launchTasks() {
        int mid = (start + end) / 2;
        SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, manager);
        SearchNumberTask task2 = new SearchNumberTask(numbers, mid, end, number, manager);
        // 23.增加任务到TaskMlanager对象中
        manager.addTask(task1);
        manager.addTask(task2);
        // 24.调用fork()方法采用异步方式执行这两个任务
        task1.fork();
        task2.fork();
        // 25.等待任务结束，如果第一个任务返回的结果不为-1，则返回第一个任务的结果，否则返回第二个任务的结果
        int returnValue;
        returnValue = task1.join();
        if (returnValue != -1) {
            return returnValue;
        }
        returnValue = task2.join();
        return returnValue;
    }

    // 26.实现writeCancelMessage()方法，在控制台输入信息表示任务已经取消了
    public void writeCancelMessage() {
        System.out.printf("Task: Cancelled task from %d to %d\n", start, end);
    }
}