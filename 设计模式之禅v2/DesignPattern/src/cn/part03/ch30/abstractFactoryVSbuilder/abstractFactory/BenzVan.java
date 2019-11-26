package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-17 奔驰商务车
public class BenzVan extends AbsBenz {
    private final static String R_SERIES = "R系列商务车";

    public String getModel() {
        return R_SERIES;
    }
}