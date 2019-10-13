package cn.ch02.ch02_3;

// 8.创建消费者类Consumer并且实现Runnable接口，用以表示范例中的消费者
public class Consumer implements Runnable {
    // 9.声明数据存储EventStorage对象，并且通过构造器对其进行初始化
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    // 10.实现run()方法，用以循环调用100次get()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.get();
        }
    }
}
