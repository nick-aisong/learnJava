package cn.part02.ch28.flyweight;

public class Client {
    public static void main(String[] args) {
        //从工厂中获得一个对象
        SignInfo signInfo = SignInfoFactory.getSignInfo();
        //进行其他业务处理
    }
}
