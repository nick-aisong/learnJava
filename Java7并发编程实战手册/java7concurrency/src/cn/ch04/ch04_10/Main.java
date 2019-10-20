package cn.ch04.ch04_10;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 18.实现范例的主类，创建Main主类，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 19.调用Executors工厂类的newCachedThreadPool()方法创建ThreadPoolExecutor执行器对象
        ExecutorService executor = Executors.newCachedThreadPool();
        // 20.创建CompletionService对象，并将上一步创建的executor对象作为构造器的参数
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        // 21.创建两个ReportRequest对象，然后创建两个线程Thread对象分别来执行它
        ReportRequest faceRequest = new ReportRequest("Face", service);
        ReportRequest onlineRequest = new ReportRequest("Online", service);
        Thread faceThread = new Thread(faceRequest);
        Thread onlineThread = new Thread(onlineRequest);
        // 22.创建1个ReportProcessor对象，然后创建1个线程Thread对象来执行它
        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);
        // 23.启动这3个线程
        System.out.printf("Main: Starting the Threads\n");
        faceThread.start();
        onlineThread.start();
        senderThread.start();
        // 24.等待ReportRequest线程的结束
        try {
            System.out.printf("Main: Waiting for the report generators.\n");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 25.调用shutdown()方法结束执行器，然后调用awaitTermination()方法等待所有的任务执行结束
        System.out.printf("Main: Shutting down the executor.\n");
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 26.调用setEnd()方法，设置end属性为true, 来结束ReportSender的执行
        processor.setEnd(true);
        System.out.println("Main: Ends");
    }
}

// 在范例的主类中，我们调用Executors工厂类的newCachedThreadPool()方法创建了ThreadPoolExecutor执行器对象
// 然后，使用这个对象初始化了CompletionService对象,因为完成服务(Completion Service)使用执行器来执行任务
// 然后,调用ReportRequest类中的submit()方法，利用“完成服务”来执行任务
// 当“完成服务”任务结束，这些任务中一个任务就执行结束了，“完成服务”中存储着Future对象，用来控制它在队列中的执行
// 调用poll()方法访问这个队列，查看是否有任务已经完成，如果有，则返回队列中的第一个元素(即一个任务执行完成后的Future对象)
// 当poll()方法返回Future对象后，它将从队列中删除这个Future对象
// 在这个示例中，我们在调用poll()方法时传递了两个参数，表示当队列里完成任务的结果为空时，想要等待任务执行结束的时间
// 一旦创建了CompletionService对象,还要创建两个ReportRequest对象,用来执行在CompletionService中的两个ReportGenerator任务
// ReportProcessor 任务则将处理两个被发送到执行器里的ReportRequest任务所产生的结果

// CompletionService类可以执行Callable或Runnable类型的任务
// 在这个范例中，使用的是Callable类型的任务，但是，也可以发送Runnable对象给它
// 由于Runnable对象不能产生结果，因此CompletionService的基本原则不适用于此
// CompletionService类提供了其他两种方法来获取任务已经完成的Future对象
// 这些方法如下：
// ·poll():无参数的poll()方法用于检查队列中是否有Future对象
// 如果队列为空，则立即返回null。否则，它将返回队列中的第一个元素，并移除这个元素
// ·take():这个方法也没有参数，它检查队列中是否有Future对象
// 如果队列为空，它将阻塞线程直到队列中有可用的元素。如果队列中有元素，它将返回队列中的第一个元素，并移除这个元素

// Main: Starting the Threads
// Main: Waiting for the report generators.
// Main: Shutting down the executor.
// Face_Report: ReportGenerator: Generating a report during 8 seconds
// Online_Report: ReportGenerator: Generating a report during 5 seconds
// ReportReceiver: Report received: Online: Report
// ReportReceiver: Report received: Face: Report
// ReportSender: End
// Main: End