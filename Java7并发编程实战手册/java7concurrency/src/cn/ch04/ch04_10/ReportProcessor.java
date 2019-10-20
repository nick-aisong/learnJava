package cn.ch04.ch04_10;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 11. 创建名为ReportProcessor的类，并实现Runnable接口。这个类将获取到ReportGenerator任务的结果
public class ReportProcessor implements Runnable {
    // 12.声明一个名为service的私有CompletionService属性
    // 由于CompletionService接口是一个泛型接口，在这个示例中，我们采用String类作为泛型参数
    private CompletionService<String> service;
    // 13.声明一个名为end的私有boolean属性
    private boolean end;

    // 14.实现类的构造器，并初始化这两个属性
    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    // 15.实现run()方法。如果end属性值为false，则调用CompletionService接口的poll()方法，来获取下一个已经完成任务的Future对象
    // 当然，这个任务是采用CompletionService来完成的
    @Override
    public void run() {
        while (!end) {
            try {
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                // 16.通过调用Future对象的get()方法来获取任务的结果，并在控制台输出这些结果
                if (result != null) {
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report received: %s\n", report);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("ReportSender: End\n");
    }

    // 17.实现setEnd()设置方法，修改end的属性值
    public void setEnd(boolean end) {
        this.end = end;
    }
}
