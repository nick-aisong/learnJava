package cn.part03.ch30.abstractFactoryVSbuilder.abstractFactory;

//代码清单30-20 宝马车工厂
public class BMWFactory implements CarFactory {
    //生产SUV
    public ICar createSuv() {
        return new BMWSuv();
    }

    //生产商务车
    public ICar createVan() {
        return new BMWVan();
    }
}