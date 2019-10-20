package cn.ch05.ch05_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// 在文档中查找一个词。我们将实现以下两种任务：
// ·一个文档任务，它将遍历文档中的每一行来查找这个词
// ·一个行任务，它将在文档的一部分当中查找这个词
// 所有这些任务将返回文档或行中所出现这个词的次数

// 29.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 30.创建Document对象，包含100行，每行1,000个词
        Document documentMock = new Document();
        String[][] document = documentMock.generateDocument(100, 1000, "the");
        // 31.创建一个DocumentTask对象，用来更新整个文档。传递数字0给参数start，以及数字100给参数end
        DocumentTask task = new DocumentTask(document, 0, 100, "the");
        // 32.采用无参的构造器创建一个ForkJoinPool对象，然后调用execute()方法在线程池里执行这个任务
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        // 33.实现代码块，显示线程池的进展信息，每秒钟在控制台输出线程池的一些参数，直到任务执行结束
        do {
            System.out.printf("*************************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("*************************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        // 34.调用shutdown()方法关闭线程池
        pool.shutdown();
        try {
            // 35.调用awaitTermination()等待任务执行结束
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 36.在控制台输出文档中出现要查找的词的次数。检验这个数字与DocumentMock类输出的数字是否一致
        try {
            System.out.printf("Main: The word appears %d in the document", task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

// DocumentMock: The word appears 33521 times in the document
// *************************************************
// Main: Parallelism: 4
// Main: Active Threads: 4
// Main: Task Count: 40
// Main: Steal Count: 0
// *************************************************
// *************************************************
// Main: Parallelism: 4
// Main: Active Threads: 4
// Main: Task Count: 13
// Main: Steal Count: 0
// *************************************************
// *************************************************
// Main: Parallelism: 4
// Main: Active Threads: 4
// Main: Task Count: 16
// Main: Steal Count: 0
// *************************************************
// *************************************************
// Main: Parallelism: 4
// Main: Active Threads: 4
// Main: Task Count: 10
// Main: Steal Count: 0
// *************************************************
// *************************************************
// Main: Parallelism: 4
// Main: Active Threads: 4
// Main: Task Count: 17
// Main: Steal Count: 0
// *************************************************
// Main: The word appears 33521 in the document
