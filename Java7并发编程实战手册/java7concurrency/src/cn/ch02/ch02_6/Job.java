package cn.ch02.ch02_6;

// 7.创建打印工作类Job并且实现Runnable接口
public class Job implements Runnable {

    // 8.声明打印队列PrintQueue对象，并且通过构造器对其进行初始化
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    // 9.实现run()方法。它使用打印队列PrintQueue对象发送一个打印工作
    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
