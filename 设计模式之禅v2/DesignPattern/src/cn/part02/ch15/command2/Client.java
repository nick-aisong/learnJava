package cn.part02.ch15.command2;

import cn.part02.ch15.command2.invoker.Invoker;

//代码清单15-11 增加一项需求
public class Client {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        System.out.println("-------------客户要求增加一项需求-----------------");
        Command command = new AddRequirementCommand();
        invoker.setCommand(command);
        invoker.action();

        System.out.println("-------------客户要求删除一个页面-----------------");
        Command command1 = new DeletePageCommand();
        invoker.setCommand(command1);
        invoker.action();
    }
}
