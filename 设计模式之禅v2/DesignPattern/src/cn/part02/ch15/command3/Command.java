package cn.part02.ch15.command3;

//代码清单15-15 抽象的Command类
public abstract class Command {
    //每个命令类都必须有一个执行命令的方法
    public abstract void execute();
}