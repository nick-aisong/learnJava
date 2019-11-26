package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-16 抽象奔驰车
public abstract class AbsBenz implements ICar {
    private final static String BENZ_BAND = "奔驰汽车";

    public String getBand() {
        return BENZ_BAND;
    }

    //具体型号由实现类完成
    public abstract String getModel();
}