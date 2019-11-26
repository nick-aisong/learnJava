package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-15 宝马SUV
public class BMWSuv extends AbsBMW {
    private final static String X_SEARIES = "X系列车型SUV";

    public String getModel() {
        return X_SEARIES;
    }
}