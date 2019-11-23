package cn.part02.ch25.visitor3;

//代码清单25-13 抽象访问者
public interface IVisitor {
    //可以访问哪些对象
    public void visit(ConcreteElement1 el1);

    public void visit(ConcreteElement2 el2);
}