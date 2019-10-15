package cn.ch02.ch02_7;

// 12.实现生产者类Producer，它实现了Runnable接口
public class Producer implements Runnable {
    // 3.声明两个属性：一个是文件类FileMock对象，另一个是缓冲区类Buffer对象
    private FileMock mock;
    private Buffer buffer;

    // 14.通过构造器为这两个属性设置值
    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    // 15.实现run()方法。这个方法用来读文件FileMock中所有的数据行，并且使用insert()方法将读到的数据行插入到缓冲区
    // 一旦读完 文件，将调用setPendingLines(方法来通知缓冲区停止生成更多的行
    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()) {
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
