package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-12 汽车接口
public interface ICar {
    //汽车的生产商，也就是牌子
    public String getBand();

    //汽车的型号
    public String getModel();
}