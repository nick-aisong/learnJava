package cn.part02.ch11.builder4;

//代码清单11-14 具体建造者
public class ConcreteProduct extends Builder {
    private Product product = new Product();

    //设置产品零件
    public void setPart() {
        /*
        * 产品类内的逻辑处理
        */
    }

    //组建一个产品
    public Product buildProduct() {
        return product;
    }
}