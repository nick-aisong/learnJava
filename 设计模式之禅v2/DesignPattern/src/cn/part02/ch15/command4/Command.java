package cn.part02.ch15.command4;

//代码清单15-22 完美的Command类
public abstract class Command {
    //定义一个子类的全局共享变量
    protected final Receiver receiver;

    //实现类必须定义一个接收者
    public Command(Receiver _receiver) {
        this.receiver = _receiver;
    }

    //每个命令类都必须有一个执行命令的方法
    public abstract void execute();
}

//在Command父类中声明了一个接收者，通过构造函数约定每个具体命令都必须指定接收者，
//当然根据开发场景要求也可以有多个接收者，那就需要用集合类型