package cn.ch05.ch05_4;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

// 1.创建名为Task的类，并继承RecursiveTask类，RecursiveTask类的泛型参数为Integer类型
public class Task extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    // 2.声明一个名为array的私有int数组。用来模拟在这个范例中即将处理的数据数组
    private int[] array;
    // 3.声明两个分别名为start和end的私有int属性。这些属性将决定任务要处理的数组元素
    private int start, end;

    // 4.实现类的构造器，用来初始化类的属性
    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // 5.实现任务的compute()方法。由于指定了Integer类型作为RecursiveTask的泛型类型
    // 因此这个方法必须返回一个Integer对象。在控制台输出start和end属性
    @Override
    protected Integer compute() {
        System.out.printf("Task: Start from %d to %d\n", start, end);
        // 6.如果由start和end属性所决定的元素块规模小于10，那么直接检查元素
        // 当碰到元素块的第4个元素(索引位为3)时，就抛出RuntimeException异常。然后将任务休眠1秒钟
        if (end - start < 10) {
            if ((3 > start) && (3 < end)) {
                throw new RuntimeException("This task throws an Exception: Task from " + start + " to " + end);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 7.如果要处理的元素块规模大于或等于10，就拆分这个元素块为两部分，并创建两个Task对象来处理这两部分
            // 然后调用invokeAll()方法在线程池中执行这两个Task对象
        } else {
            int mid = (end + start) / 2;
            Task task1 = new Task(array, start, mid);
            Task task2 = new Task(array, mid, end);
            invokeAll(task1, task2);
        }
        // 8.在控制台输出信息，表示任务结束，并输出start和end属性值
        System.out.printf("Task: End from %d to %d\n", start, end);
        // 9.返回数字0作为任务的结果
        return 0;
    }
}
