package cn.part02.ch11.builder4;

//代码清单11-13 抽象建造者
public abstract class Builder {
    //设置产品的不同部分，以获得不同的产品
    public abstract void setPart();

    //建造产品
    public abstract Product buildProduct();
}