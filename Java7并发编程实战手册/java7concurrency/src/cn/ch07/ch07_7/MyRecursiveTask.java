package cn.ch07.ch07_7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

// 8.创建一个名为MyRecursiveTask的类，它继承泛型参数Integer的RecursiveTask类
public class MyRecursiveTask extends RecursiveTask<Integer> {
    // 9.声明一个名为array的私有int数组
    private int array[];
    // 10.声明两个私有int属性，名字分别为start和end
    private int start, end;

    // 11.实现类构造器来初始化属性
    public MyRecursiveTask(int array[], int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // 12.实现compute()方法，用于对数组中从起始位到指定位的所有元素求和
    // 首先，它将正在执行任务的线程转换为MyWorkerThread类型，然后使用addTask()方法增加线程属性taskCounter的值
    @Override
    protected Integer compute() {
        Integer ret;
        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
        thread.addTask();
        if (end - start > 100) {
            int mid = (start + end) / 2;
            MyRecursiveTask task1 = new MyRecursiveTask(array, start, mid);
            MyRecursiveTask task2 = new MyRecursiveTask(array, mid, end);
            invokeAll(task1, task2);
            ret = addResults(task1, task2);
        } else {
            int add = 0;
            for (int i = start; i < end; i++) {
                add += array[i];
            }
            ret = new Integer(add);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ret;
    }

    // 13.实现addResults()方法。它计算并返回通过参数传入的两个任务的结果之和
    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
        int value;
        try {
            value = task1.get().intValue() + task2.get().intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            value = 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }
        // 14.使线程休眠10毫秒，并返回任务的结果
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }
}