package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-13 抽象宝马车
public abstract class AbsBMW implements ICar {
    private final static String BMW_BAND = "宝马汽车";

    //宝马车
    public String getBand() {
        return BMW_BAND;
    }

    //型号由具体的实现类实现
    public abstract String getModel();
}