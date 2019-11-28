package cn.part03.ch31.decoratorVSadaptor.adaptor;

//代码清单31-17 场景类
public class Client {
    public static void main(String[] args) {
        //鸭妈妈有5个孩子，其中4个都是一个模样
        System.out.println("===妈妈有五个孩子，其中四个模样是这样的：===");
        Duck duck = new Duckling();
        duck.cry(); //小鸭子的叫声
        duck.desAppearance(); //小鸭子的外形
        duck.desBehavior(); //小鸭子的其他行为
        System.out.println("\n===一只独特的小鸭子，模样是这样的：===");
        Duck uglyDuckling = new UglyDuckling();
        uglyDuckling.cry(); //丑小鸭的叫声
        uglyDuckling.desAppearance(); //丑小鸭的外形
        uglyDuckling.desBehavior(); //丑小鸭的其他行为
    }
}

//===妈妈有五个孩子，其中四个模样是这样的：===
//叫声是嘎——嘎——嘎
//外形是黄白相间，嘴长
//会游泳
//
//===一只独特的小鸭子，模样是这样的：===
//叫声是克噜——克噜——克噜
//外形是纯白色，惹人喜爱
//会游泳
//能够飞行