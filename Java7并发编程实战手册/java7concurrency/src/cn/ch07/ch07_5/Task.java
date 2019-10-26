package cn.ch07.ch07_5;

import java.util.concurrent.TimeUnit;

// 17.创建名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 18.实现run()方法。在任务开始时输出相应消息
    // 然后使当前线程休眠2秒，在任务结束时输出结束消息
    @Override
    public void run() {
        System.out.printf("Task: Begin.\n");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task: End.\n");
    }
}
