package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-14 df命令的抽象类
public abstract class AbstractDF extends CommandName {
    //默认参数
    public final static String DEFAULT_PARAM = "";
    //参数k
    public final static String K_PARAM = "k";
    //参数g
    public final static String G_PARAM = "g";
}