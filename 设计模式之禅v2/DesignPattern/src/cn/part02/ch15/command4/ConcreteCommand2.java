package cn.part02.ch15.command4;

//代码清单15-23 具体的命令
public class ConcreteCommand2 extends Command {
    //声明自己的默认接收者
    public ConcreteCommand2() {
        super(new ConcreteReciver2());
    }

    //设置新的接收者
    public ConcreteCommand2(Receiver _receiver) {
        super(_receiver);
    }

    //每个具体的命令都必须实现一个命令
    public void execute() {
        //业务处理
        super.receiver.doSomething();
    }
}
