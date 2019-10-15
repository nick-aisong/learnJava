package cn.ch02.ch02_7;

import java.util.Random;

// 16.实现消费者类Consumer，它实现了Runnable接口
public class Consumer implements Runnable {
    // 17.声明缓冲区类Buffer对象，并通过构造器对它进行初始化
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    // 18.实现run()方法，如果缓冲区有数据行，它将获取一行并且进行处理
    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    // 19.实现辅助方法processLine()。该方法仅用于休眠10毫秒以模拟对数据行的处理
    private void processLine(String line) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
