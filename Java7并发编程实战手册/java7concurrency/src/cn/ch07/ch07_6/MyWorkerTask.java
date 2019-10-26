package cn.ch07.ch07_6;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

// 1.创建一个名为MyWorkerThread的类，继承ForkJoinWorkerThread类
public abstract class MyWorkerTask extends ForkJoinTask<Void> {
    private static final long serialVersionUID = 1L;
    // 2.声明并创建一个私有ThreadLocal属性，命名为taskCounter, 并指定泛型参数是Integer
    private String name;

    protected abstract void compute();

    public MyWorkerTask(String name) {
        this.name = name;
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        Date startDate = new Date();
        compute();
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n", name, diff);
        return true;
    }
}
