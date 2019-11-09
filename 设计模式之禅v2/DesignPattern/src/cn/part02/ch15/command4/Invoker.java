package cn.part02.ch15.command4;

//代码清单15-17 调用者Invoker类
public class Invoker {
    private Command command;

    //受气包，接受命令
    public void setCommand(Command _command) {
        this.command = _command;
    }

    //执行命令
    public void action() {
        this.command.execute();
    }
}