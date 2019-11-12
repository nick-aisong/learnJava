package cn.part02.ch19.adapter2;

//代码清单19-11 适配器角色
public class Adapter extends Adaptee implements Target {
    public void request() {
        super.doSomething();
    }
}