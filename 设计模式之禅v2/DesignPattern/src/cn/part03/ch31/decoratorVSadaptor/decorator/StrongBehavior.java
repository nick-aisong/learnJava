package cn.part03.ch31.decoratorVSadaptor.decorator;

//代码清单31-11 强化行为
public class StrongBehavior extends Decorator {
    //强化谁
    public StrongBehavior(Swan _swan) {
        super(_swan);
    }

    //会飞行了
    public void fly() {
        System.out.println("会飞行了！");
    }
}