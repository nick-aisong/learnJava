package cn.ch04.ch04_8;

import java.util.concurrent.Callable;

// 1.创建一个名为Task的类，并实现Callable接口，接口的泛型参数为String类型
// 接着实现call()方法，构造一个无限循环，先在控制台输出信息，然后休眠100毫秒
public class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        while (true) {
            System.out.printf("Task: Test\n");
            Thread.sleep(100);
        }
    }
}
