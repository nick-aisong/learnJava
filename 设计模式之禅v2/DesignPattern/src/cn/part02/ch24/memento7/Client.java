package cn.part02.ch24.memento7;

//代码清单24-21 场景类
public class Client {
    public static void main(String[] args) {
        //定义出发起人
        Originator originator = new Originator();
        //定义出备忘录管理员
        Caretaker caretaker = new Caretaker();
        //创建两个备忘录
        caretaker.setMemento("001", originator.createMemento());
        caretaker.setMemento("002", originator.createMemento());
        //恢复一个指定标记的备忘录
        originator.restoreMemento(caretaker.getMemento("001"));
    }
}