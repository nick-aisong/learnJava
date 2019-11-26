package cn.part03.ch30.abstractFactoryVSbuilder.builder;

//代码清单30-23 车辆产品描述
public interface ICar {
    //汽车车轮
    public String getWheel();

    //汽车引擎
    public String getEngine();
}