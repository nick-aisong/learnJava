package cn.part03.ch31.decoratorVSadaptor.adaptor;

//代码清单31-16 把白天鹅当做小鸭子看待
public class UglyDuckling extends WhiteSwan implements Duck {
    //丑小鸭的叫声
    public void cry() {
        super.cry();
    }

    //丑小鸭的外形
    public void desAppearance() {
        super.desAppaearance();
    }

    //丑小鸭的其他行为
    public void desBehavior() {
        //丑小鸭不仅会游泳
        System.out.println("会游泳");
        //还会飞行
        super.fly();
    }
}