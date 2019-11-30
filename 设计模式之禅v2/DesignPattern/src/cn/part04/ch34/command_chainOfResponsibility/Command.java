package cn.part04.ch34.command_chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

//代码清单34-7 抽象命令
public abstract class Command {
    public abstract String execute(CommandVO vo);

    //建立链表
    protected final List<? extends CommandName> buildChain(Class<? extends CommandName> abstractClass) {
        //取出所有的命令名下的子类
        List<Class> classes = ClassUtils.getSonClass(abstractClass);
        //存放命令的实例，并建立链表关系
        List<CommandName> commandNameList = new ArrayList<>();
        for (Class c : classes) {
            CommandName commandName = null;
            try {
                //产生实例
                commandName = (CommandName) Class.forName(c.getName()).newInstance();
            } catch (Exception e) {
                // TODO 异常处理
            }
            //建立链表
            if (commandNameList.size() > 0) {
                commandNameList.get(commandNameList.size() - 1).setNext(commandName);
            }
            commandNameList.add(commandName);
        }
        return commandNameList;
    }
}

//Command抽象类有两个作用：一是定义命令的执行方法，二是负责命令族（责任链）的
//建立。其中buildChain方法负责建立一个责任链，它通过接收一个抽象的命令族类就可建
//立一条命令解析链，如传递AbstarctLS类就可以建立一条解析ls命令族的责任链，请读者注意如下这句代码：
//commandName = (CommandName)Class.forName(c.getName()).newInstance();
//
//在一个遍历中，类中的每个元素都是一个类名，然后根据类名产生一个实例，它会抛出
//异常，例如类文件不存在、初始化失败等，读者在设计时要实现该部分的异常。我们再来想一下，每个实现类的类名是如何取得的呢？看下面这句代码：
//List<Class> classes = ClassUtils.getSonClass(abstractClass);
