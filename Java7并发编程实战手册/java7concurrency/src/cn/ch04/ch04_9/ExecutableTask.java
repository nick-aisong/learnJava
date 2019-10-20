package cn.ch04.ch04_9;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// 1.创建名为ExecutableTask的类，并实现Callable接口，接口的泛型参数为String类型
public class ExecutableTask implements Callable<String> {
    // 2.声明一个名为name的私有String属性，用来存储任务的名称，用getName()方法来返回这个属性值
    private String name;

    public String getName() {
        return name;
    }

    // 3.实现类的构造器，并初始化任务的名称
    public ExecutableTask(String name) {
        this.name = name;
    }

    // 4.实现call(）方法。将任务休眠一段随机时间，并返回带有任务名称的消息
    @Override
    public String call() throws Exception {
        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, world. I'm " + name;
    }
}
