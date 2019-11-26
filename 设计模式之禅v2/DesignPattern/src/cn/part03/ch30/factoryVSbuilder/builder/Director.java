package cn.part03.ch30.factoryVSbuilder.builder;

//代码清单30-10 导演类
public class Director {
    //两个建造者的应用
    private static Builder adultBuilder = new AdultSuperManBuilder();
    //未成年超人的建造者
    private static Builder childBuilder = new ChildSuperManBuilder();

    //建造一个成年、会飞行的超人
    public static SuperMan getAdultSuperMan() {
        return adultBuilder.getSuperMan();
    }

    //建造一个未成年、刀枪不入的超人
    public static SuperMan getChildSuperMan() {
        return childBuilder.getSuperMan();
    }
}