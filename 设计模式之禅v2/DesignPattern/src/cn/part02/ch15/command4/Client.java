package cn.part02.ch15.command4;

//代码清单15-18 场景类
public class Client {
    public static void main(String[] args) {
        //首先声明调用者Invoker
        Invoker invoker = new Invoker();
        //定义一个发送给接收者的命令
        Command command = new ConcreteCommand1();
        //把命令交给调用者去执行
        invoker.setCommand(command);
        invoker.action();
    }
}