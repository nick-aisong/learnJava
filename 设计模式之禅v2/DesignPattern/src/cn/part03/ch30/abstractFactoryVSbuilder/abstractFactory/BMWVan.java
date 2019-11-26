package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-14 宝马商务车
public class BMWVan extends AbsBMW {
    private final static String SEVENT_SEARIES = "7系列车型商务车";

    public String getModel() {
        return SEVENT_SEARIES;
    }
}