package cn.part02.ch12.proxy9;

//代码清单12-25 真实主题
public class RealSubject implements Subject {
    //业务操作
    public void doSomething(String str) {
        System.out.println("do something!---->" + str);
    }
}