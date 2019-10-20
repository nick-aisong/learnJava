package cn.ch05.ch05_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

// 18. 创建名为LineTask的类，并继承RecursiveTask类，RecursiveTask类的泛型参数为Integer类型
// 这个RecursiveTask类实现了一个任务，用来计算所要查找的词在一行中出现的次数
public class LineTask extends RecursiveTask<Integer> {
    // 19.声明类的serialVersionUID属性。这个元素是必需的，因为RecursiveTask的父类ForkJoinTask实现了Serializable接口
    // 声明一个名为line的私有String数组属性和两个名为start和end的私有int属性
    // 最后，声明一个名为word的私有String属性
    private static final long serialVersionUID = 1L;

    private String line[];
    private int start, end;
    private String word;

    // 20.实现类的构造器，用来初始化它的属性
    public LineTask(String[] line, int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    // 21.实现compute()方法。如果end和start属性的差异小于100，那么任务将采用count()方法
    // 在由start与end属性所决定的行的片断中查找词
    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 100) {
            result = count(line, start, end, word);
        } else {
            // 22.如果end和start属性的差异不小于100，将这一组词拆分成两组，然后创建两个新的LineTask对象来处理这两个组
            // 调用invokeAll()方法在线程池中执行它们
            int mid = (start + end) / 2;
            LineTask task1 = new LineTask(line, start, mid, word);
            LineTask task2 = new LineTask(line, mid, end, word);
            invokeAll(task1, task2);
            // 23.调用groupResults()方法将两个任务返回的值相加。最后返回任务计算的结果
            try {
                result = groupResult(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 24.实现count()方法。它接收4个参数，一个完整行字符串line数组，start属性，end属性和任务将要查找的词word的属性
    private Integer count(String[] line, int start, int end, String word) {
        // 25.将存储在start和end属性值之间的词与任务正在查找的word属性相比较。如果相同，那么将计数器counter变量加1
        int counter;
        counter = 0;
        for (int i = start; i < end; i++) {
            if (line[i].equals(word)) {
                counter++;
            }
        }
        // 26.为了延缓范例的执行，将任务休眠10毫秒
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 27.返回计数器counter变量的值
        return counter;
    }

    // 28.实现groupResults()方法。计算两个数字之和并返回结果
    private Integer groupResult(Integer number1, Integer number2) {
        Integer result;
        result = number1 + number2;
        return result;
    }
}
