package cn.part02.ch22.observer2;

//代码清单22-8 通过聚集方式的场景类
public class Client {
    public static void main(String[] args) {
        //定义出韩非子
        HanFeiZi hanFeiZi = new HanFeiZi();
        //然后我们看看韩非子在干什么
        hanFeiZi.haveBreakfast();
        //韩非子娱乐了
        hanFeiZi.haveFun();
    }
}
