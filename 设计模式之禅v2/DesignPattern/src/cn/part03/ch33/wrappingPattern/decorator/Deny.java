package cn.part03.ch33.wrappingPattern.decorator;

//代码清单33-37 抵赖
public class Deny extends Decorator {
    public Deny(IStar _star) {
        super(_star);
    }

    public void act() {
        super.act();
        System.out.println("演后：百般抵赖，死不承认");
    }
}
