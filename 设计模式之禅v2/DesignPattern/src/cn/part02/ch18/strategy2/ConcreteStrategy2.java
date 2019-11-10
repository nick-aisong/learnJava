package cn.part02.ch18.strategy2;

//代码清单18-8 具体策略角色
public class ConcreteStrategy2 implements Strategy {
    public void doSomething() {
        System.out.println("具体策略2的运算法则");
    }
}