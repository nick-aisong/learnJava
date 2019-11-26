package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-19 抽象工厂
public interface CarFactory {
    //生产SUV
    public ICar createSuv();

    //生产商务车
    public ICar createVan();
}