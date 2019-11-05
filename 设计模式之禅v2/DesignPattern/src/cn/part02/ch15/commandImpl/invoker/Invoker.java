package cn.part02.ch15.commandImpl.invoker;

import cn.part02.ch15.commandImpl.Command;

/**
 * Created by NKS on 2017/9/16.
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        this.command.execute();
    }
}
