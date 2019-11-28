package cn.part03.ch32.strategyVSstate.state;

//代码清单32-30 场景类
public class Client {
    public static void main(String[] args) {
        //定义一个普通的人
        Human human = new Human();
        //设置一个人的初始状态
        human.setState(new ChildState());
        System.out.println("====儿童的主要工作=====");
        human.work();
        System.out.println("\n====成年人的主要工作=====");
        human.work();
        System.out.println("\n====老年人的主要工作=====");
        human.work();
    }
}

//====儿童的主要工作=====
//儿童的工作是玩耍！
//
//====成年人的主要工作=====
//成年人的工作就是先养活自己，然后为社会做贡献！
//
//====老年人的主要工作=====
//老年人的工作就是享受天伦之乐！