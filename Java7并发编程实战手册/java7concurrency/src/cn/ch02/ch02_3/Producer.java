package cn.ch02.ch02_3;

// 5.创建生产者类Producer并且实现Runnable接口，用以表示范例中的生产者
public class Producer implements Runnable {
    // 6.声明数据存储EventStorage对象，并且通过构造器对其进行初始化
    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    // 7.实现run(方法，用来循环调用100次set()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
