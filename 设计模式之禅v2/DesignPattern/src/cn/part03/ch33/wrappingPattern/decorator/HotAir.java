package cn.part03.ch33.wrappingPattern.decorator;

//代码清单33-36 吹大话
public class HotAir extends Decorator {
    public HotAir(IStar _star) {
        super(_star);
    }

    public void act() {
        System.out.println("演前：夸夸其谈，没有自己不能演的角色");
        super.act();
    }
}