package cn.part03.ch31.decorator;

//代码清单31-9 抽象装饰类
public class Decorator implements Swan {
    private Swan swan;

    //修饰的是谁
    public Decorator(Swan _swan) {
        this.swan = _swan;
    }

    public void cry() {
        swan.cry();
    }

    public void desAppaearance() {
        swan.desAppaearance();
    }

    public void fly() {
        swan.fly();
    }
}