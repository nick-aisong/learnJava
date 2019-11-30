package cn.part03.ch33.wrappingPattern.decorator;

//代码清单33-35 抽象装饰类
public abstract class Decorator implements IStar {
    //粉饰的是谁
    private IStar star;

    public Decorator(IStar _star) {
        this.star = _star;
    }

    public void act() {
        this.star.act();
    }
}