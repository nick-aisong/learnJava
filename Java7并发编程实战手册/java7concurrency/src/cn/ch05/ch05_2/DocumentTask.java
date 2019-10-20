package cn.ch05.ch05_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

// 7.创建名为DocumentTask的类，并继承RecursiveTask类，RecursiveTask类的泛型参数为Integer类型
// 这个DocumentTask类将实现一个任务，用来计算所要查找的词在行中出现的次数
public class DocumentTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    // 8.声明一个名为document的私有String矩阵，以及两个名为start和end的私有int属性，并声明一个名为word的私有String属性
    private String document[][];
    private int start, end;
    private String word;

    // 9.实现类的构造器，用来初始化类的所有属性
    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }
    // 10.实现compute()方法。如果end和start的差异小于10，则调用processLines()方法，来计算这两个位置之间要查找的词出现的次数
    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 10) {
            result = processLines(document, start, end, word);
            // 11.否则，拆分这些行成为两个对象，并创建两个新的DocumentTask对象来处理这两个对象
            // 然后调用invokeAll()方法在线程池里执行它们
        } else {
            int mid = (start + end) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            invokeAll(task1, task2);
            // 12.采用groupResults()方法将这两个任务返回的值相加。最后，返回任务计算的结果
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    // 13.实现processLines()方法
    // 接收4个参数，一个字符串document矩阵，start属性end属性和任务将要查找的词word的属性
    private Integer processLines(String[][] document, int start, int end, String word) {
        // 14.为任务所要处理的每一行， 创建一个LineTask对象，然后存储在任务列表里
        List<LineTask> tasks = new ArrayList<>();
        for (int i = start; i < end; i++) {
            LineTask task = new LineTask(document[i], 0, document[i].length, word);
            tasks.add(task);
        }
        // 15.调用invokeAll()方法执行列表中所有的任务
        invokeAll(tasks);
        // 16.合计这些任务返回的值，并返回结
        int result = 0;
        for (int i = 0; i < tasks.size(); i++) {
            LineTask task = tasks.get(i);
            try {
                result = result + task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return new Integer(result);
    }
    // 17.实现groupResults()方法。它将两个数字相加并返回结
    private Integer groupResults(Integer number1, Integer number2) {
        Integer result;
        result = number1 + number2;
        return result;
    }
}
