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

// 在这个范例中，我们实现了两个不同的任务
// ·DocumentTask类：这个类的任务需要处理由start和end属性决定的文档行
// 如果这些行数小于10，那么，就每行创建一个LineTask对象，然后在任务执行结束后，合计返回的结果，并返回总数
// 如果任务要处理的行数大于10，那么，将任务拆分成两组，并创建两个DocumentTask对象来处理这两组对象
// 当这些任务执行结束后，同样合计返回的结果，并返回总数
// ·LineTask类：这个类的任务需要处理文档中一行的某一组词
// 如果一组词的个数小100，那么任务将直接在这一组词里搜索特定词，然后返回查找词在这一组词中出现的次数
// 否则，任务将拆分这些词为两组，并创建两个LineTask对象来处理这两组词
// 当这些任务执行完成后，合计返回的结果，并返回总数

// 在Main主类中，我们通过默认的构造器创建了ForkJoinPool对象，然后执行DocumentTask类，来处理一个共有100行，每行1,000字的文档
// 这个任务将问题拆分成DocumentTask对象和LineTask对象，然后当所有的任务执行完成后，使用原始的任务来获取整个文档中所要查找的词出现的次数
// 由于任务继承了RecursiveTask 类，因此能够返回结果
// 调用get()方法来获得Task返回的结果。这个方法声明在Future接口里，并由RecursiveTask类实现
// 执行程序时，在控制台上，我们可以比较第一行与最后一行的输出信息
// 第一行是文档生成时被查找的词出现的次数，最后一行则是通过Fork/Join任务计算而来的被查找的词出现的次数，而且这两个数字相同

// ForkJoinTask类提供了另一个complete()方法来结束任务的执行并返回结果
// 这个方法接收一个对象，对象的类型就是RecursiveTask类的泛型参数，然后在任务调用join()方法后返回这个对象作为结果
// 这一过程采用了推荐的异步任务来返回任务的结果
// 由于RecursiveTask类实现了Future接口，因此还有get()方法调用的其他版本：
// ·get(long timeout, TimeUnit unit)：这个版本中，如果任务的结果未准备好，将等待指定的时间
// 如果等待时间超出，而结果仍未准备好，那方法就会返回null值
// TimeUnit是一个枚举类，有如下的常量： DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

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
