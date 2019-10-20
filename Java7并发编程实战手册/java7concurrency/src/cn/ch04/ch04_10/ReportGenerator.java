package cn.ch04.ch04_10;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// 1.创建名为ReportGenerator的类，并实现Callable接口，接口的泛型参数为String类型
public class ReportGenerator implements Callable<String> {
    // 2.声明两个私有的String属性，分别命名为sender和title，将用来表示报告的数据
    private String sender;
    private String title;

    // 3.实现类的构造器，用来初始化这两个属性
    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    // 4.实现call()方法。让线程休眠一段随机时间
    @Override
    public String call() throws Exception {
        long duration = (long) (Math.random() * 10);
        System.out.printf("%s_%s: ReportGenerator: Generating a report during %s seconds\n", this.sender, this.title,
                duration);
        TimeUnit.SECONDS.sleep(duration);
        // 5.生成包含了sender和title属性的字符串并返回该字符串
        String ret = sender + ": " + title;
        return ret;
    }
}
