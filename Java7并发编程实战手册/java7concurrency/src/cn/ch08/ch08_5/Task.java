package cn.ch08.ch08_5;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Test;

// 10.创建一个名为Task的类，实现Runnable接口。它用来测试Logger对象的任务
public class Task implements Runnable {
    // 11.实现run()方法
    @Override
    public void run() {
        // 12.声明一个名为logger的Logger对象。调用MyLogger类的getLogger()方法，传递自身的类名作为参数来初始化Logger对象
        Logger logger = MyLogger.getLogger(this.getClass().getName());
        // 13.使用entering()方法输出一条日志消息表示方法开始执行，然后使线程休眠2秒钟
        // entering 记录在文件里
        logger.entering(Thread.currentThread().getName(), "run()");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 14.使用exiting()方法输出一条日志消息表示方法执行结束
        logger.exiting(Thread.currentThread().getName(), "run()", Thread.currentThread());
    }

    @Test
    public void test() {
        System.out.println(this.getClass());
        System.out.println(this.getClass().getName());
        Logger logger = Logger.getLogger(this.getClass().getName());

        // entering 记录在文件里
        logger.entering(Thread.currentThread().getName(), "test()");
    }
}
