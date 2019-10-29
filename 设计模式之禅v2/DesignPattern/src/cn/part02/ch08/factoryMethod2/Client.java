package cn.part02.ch08.factoryMethod2;

public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        /*
        * 继续业务处理
        */
    }
}
