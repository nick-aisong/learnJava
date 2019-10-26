package cn.ch07.ch07_3;

import java.util.concurrent.TimeUnit;

// 15.创建MyTask类实现Runnable接口。然后实现run()方法，使当前线程休眠2s
public class MyTask implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
