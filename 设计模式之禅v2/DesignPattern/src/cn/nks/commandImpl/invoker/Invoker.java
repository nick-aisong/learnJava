package cn.nks.commandImpl.invoker;

import cn.nks.commandImpl.Command;

/**
 * Created by NKS on 2017/9/16.
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void action(){
        this.command.execute();
    }
}
