package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-21 奔驰车工厂
public class BenzFactory implements CarFactory {
    //生产SUV
    public ICar createSuv() {
        return new BenzSuv();
    }

    //生产商务车
    public ICar createVan() {
        return new BenzVan();
    }
}