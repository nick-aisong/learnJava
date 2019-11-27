package cn.part03.ch31.proxyVSdecorator.proxy;

//代码清单31-2 运动员跑步
public class Runner implements IRunner {
    public void run() {
        System.out.println("运动员跑步：动作很潇洒");
    }
}