package cn.part02.ch24.memento6;

//代码清单24-19 场景类
public class Client {
    public static void main(String[] args) {
        //定义出发起人
        Originator ori = new Originator();
        //定义出备忘录管理员
        Caretaker caretaker = new Caretaker();
        //初始化
        ori.setState1("中国");
        ori.setState2("强盛");
        ori.setState3("繁荣");
        System.out.println("===初始化状态===\n" + ori);
        //创建一个备忘录
        caretaker.setMemento(ori.createMemento());
                //修改状态值
        ori.setState1("软件");
        ori.setState2("架构");
        ori.setState3("优秀");
        System.out.println("\n===修改后状态===\n" + ori);
        //恢复一个备忘录
        ori.restoreMemento(caretaker.getMemento());
        System.out.println("\n===恢复后状态===\n" + ori);
    }
}

//===初始化状态===
//state1=中国
//stat2=强盛
//state3=繁荣
//
//===修改后状态===
//state1=软件
//stat2=架构
//state3=优秀
//
//===恢复后状态===
//state1=中国
//stat2=强盛
//state3=繁荣