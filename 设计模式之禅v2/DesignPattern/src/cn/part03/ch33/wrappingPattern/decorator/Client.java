package cn.part03.ch33.wrappingPattern.decorator;

//代码清单33-38 场景类
public class Client {
    public static void main(String[] args) {
        //定义出所谓的明星
        IStar freakStar = new FreakStar();
        //看看他是怎么粉饰自己的
       //演前吹嘘自己无所不能
        freakStar = new HotAir(freakStar);
        //演完后，死不承认自己演的不好
        freakStar = new Deny(freakStar);
        System.out.println("====看看一些虚假明星的形象====");
        freakStar.act();
    }
}