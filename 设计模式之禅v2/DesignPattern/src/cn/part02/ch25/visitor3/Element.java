package cn.part02.ch25.visitor3;

//代码清单25-11 抽象元素
public abstract class Element {
    //定义业务逻辑
    public abstract void doSomething();

    //允许谁来访问
    public abstract void accept(IVisitor visitor);
}