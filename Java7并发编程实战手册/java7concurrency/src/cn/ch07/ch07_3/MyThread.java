package cn.ch07.ch07_3;

import java.util.Date;

// 1.创建一个类名为MyThread的类，并继承Thread类
public class MyThread extends Thread {
    // 2.声明三个私有Date属性，分别命名为creationDate、startDate和finishDate
    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    // 3.实现类构造器。它接收将要被执行的Runnable类型对象target作为参数。然后保存线程的创建时间
    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }

    // 4.实现run() 方法。保存线程的开始时间，然后调用父类的run() 方法，保存执行的结束时间
    @Override
    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    // 5.实现一个方法来设置creationDate属性的值
    private void setCreationDate() {
        creationDate = new Date();
    }

    // 6.实现一个方法来设置startDate属性的值
    private void setStartDate() {
        startDate = new Date();
    }

    // 7.实现一个方法来设置finishDate属性的值
    private void setFinishDate() {
        finishDate = new Date();
    }

    // 8.实现一个名为getExecutionTime()的方法，用来计算线程开始和结束的时间差
    public long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }

    // 9.覆盖toString()方法，返回线程的创建时间和执行时间
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getName());
        buffer.append(": ");
        buffer.append(" Creation Date: ");
        buffer.append(creationDate);
        buffer.append(" : Running time: ");
        buffer.append(getExecutionTime());
        buffer.append(" Milliseconds. ");
        return buffer.toString();
    }
}
