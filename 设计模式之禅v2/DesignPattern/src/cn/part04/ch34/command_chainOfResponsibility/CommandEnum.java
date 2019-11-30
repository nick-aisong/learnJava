package cn.part04.ch34.command_chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

//代码清单34-12 命令配置对象
public enum CommandEnum {
    ls("cn.part04.ch34.command_chainOfResponsibility.LSCommand"),
    df("cn.part04.ch34.command_chainOfResponsibility.DFCommand");
    
    private String value = "";

    //定义构造函数，目的是Data(value)类型的相匹配
    private CommandEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    //返回所有的enum对象
    public static List<String> getNames() {
        CommandEnum[] commandEnum = CommandEnum.values();
        List<String> names = new ArrayList<>();
        for (CommandEnum c : commandEnum) {
            names.add(c.name());
        }
        return names;
    }
}

//为什么要用枚举类型？用一个接口来管理也是很容易实现的。注意CommandEnum中的构
//造函数CommandEnum(String value)和getValue类，没有新建一个Enum对象，但是可以直接使
//用CommandEnum.ls.getValue方法获得值，这就是Enum类型的独特地方。再看下面：
//ls("cn.part04.ch34.command_chainOfResponsibility.LSCommand");
//是不是很特别？是的，枚举的基本功能就是定义默认可选值，但是Java中的枚举功能又
//增强了很多，可以添加方法和属性，基本上就是一个特殊的类。若要详细了解Enum，读者
//可以翻阅一下相关语法书