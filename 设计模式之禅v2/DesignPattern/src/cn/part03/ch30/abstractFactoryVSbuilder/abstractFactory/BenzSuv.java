package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-18 奔驰SUV
public class BenzSuv extends AbsBenz {
    private final static String G_SERIES = "G系列SUV";

    public String getModel() {
        return G_SERIES;
    }
}