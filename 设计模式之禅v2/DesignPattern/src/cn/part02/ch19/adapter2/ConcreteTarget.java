package cn.part02.ch19.adapter2;

//代码清单19-9 目标角色的实现类
public class ConcreteTarget implements Target {
    public void request() {
        System.out.println("if you need any help,pls call me!");
    }
}