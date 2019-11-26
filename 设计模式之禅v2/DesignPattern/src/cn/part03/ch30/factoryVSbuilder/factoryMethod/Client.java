package cn.part03.ch30.factoryVSbuilder.factoryMethod;

//代码清单30-5 场景类
public class Client {
    //模拟生产超人
    public static void main(String[] args) {
        //生产一个成年超人
        ISuperMan adultSuperMan = SuperManFactory.createSuperMan("adult");
        //展示一下超人的技能
        adultSuperMan.specialTalent();
    }
}

//超人力大无穷