package cn.part03.ch30.factoryVSbuilder.builder;

//代码清单30-11 场景类
public class Client {
    public static void main(String[] args) {
        //建造一个成年超人
        SuperMan adultSuperMan = Director.getAdultSuperMan();
        //展示一下超人的信息
        adultSuperMan.getSpecialTalent();
    }
}