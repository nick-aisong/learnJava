package cn.ch05.ch05_1;

// 1.创建一个名为Product的类，用来存储产品的名称和价格
public class Product {
    // 2.声明一个名为name的私有String属性，一个名为price的私有double属性
    private String name;
    private double price;

    // 3.实现两个属性各自的设值与取值方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
