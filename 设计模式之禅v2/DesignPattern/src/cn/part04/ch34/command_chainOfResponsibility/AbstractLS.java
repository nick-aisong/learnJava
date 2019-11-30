package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-2 抽象ls命令
public abstract class AbstractLS extends CommandName {
    //默认参数
    public final static String DEFAULT_PARAM = "";
    //参数a
    public final static String A_PARAM = "a";
    //参数l
    public final static String L_PARAM = "l";
}

//很惊讶，是吗？怎么是个空的抽象类？是的，确实是一个空类，只定义了3个参数名称，它有两个职责：

//标记ls命令族
//个性化处理

//因为现在还没有思考清楚ls有什么个性（可以把命令的选项也认为是其个性化数据），
//所以先写个空类放在这里，以后想清楚了再填写上去，留下一些可扩展的类也许会给未来带
//来不可估量的优点