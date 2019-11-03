package cn.part02.ch11.builder4;

//代码清单11-15 导演类
public class Director {
    private Builder builder = new ConcreteProduct();

    //构建不同的产品
    public Product getAProduct() {
        builder.setPart();
        /*
        * 设置不同的零件，产生不同的产品
        */
        return builder.buildProduct();
    }
}