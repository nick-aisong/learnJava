package cn.part02.ch15.command2.invoker;

import cn.part02.ch15.command2.Command;

//代码清单15-10 负责人
public class Invoker {
    //什么命令
    private Command command;

    //客户发出命令
    public void setCommand(Command command) {
        this.command = command;
    }

    //执行客户的命令
    public void action() {
        this.command.execute();
    }
}
