package cn.part03.ch31.proxyVSdecorator.proxy;

import java.util.Random;

//代码清单31-3 代理人
public class RunnerAgent implements IRunner {
    private IRunner runner;

    public RunnerAgent(IRunner _runner) {
        this.runner = _runner;
    }

    //代理人是不会跑的
    public void run() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            System.out.println("代理人同意安排运动员跑步");
            runner.run();
        } else {
            System.out.println("代理人心情不好，不安排运动员跑步");
        }
    }
}