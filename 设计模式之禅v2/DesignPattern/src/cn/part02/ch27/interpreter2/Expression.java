package cn.part02.ch27.interpreter2;

//代码清单27-8 抽象表达
public abstract class Expression {
    //每个表达式必须有一个解析任务
    public abstract Object interpreter(Context ctx);
}