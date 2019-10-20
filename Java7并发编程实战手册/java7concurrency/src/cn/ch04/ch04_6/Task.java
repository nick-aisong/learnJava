package cn.ch04.ch04_6;

import java.util.Date;
import java.util.concurrent.Callable;

// 1.创建一个名为Task的类，并实现Callable接口，接口的泛型参数为String类型
public class Task implements Callable<String> {
    // 2.声明一个名为name的私有String属性，用来存储任务的名称
    private String name;

    // 3.实现类的构造器，用来初始化类的属性
    public Task(String name) {
        this.name = name;
    }

    // 4.实现call()方法。在控制台输出实际的时间：并返回一个文本信息，比如“Hello, world”
    @Override
    public String call() throws Exception {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
        return "Hello, world";
    }
}
