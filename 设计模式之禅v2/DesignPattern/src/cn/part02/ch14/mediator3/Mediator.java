package cn.part02.ch14.mediator3;

//代码清单14-12 通用抽象中介者
public abstract class Mediator {
    //定义同事类
    protected ConcreteColleague1 c1;
    protected ConcreteColleague2 c2;

    //通过getter/setter方法把同事类注入进来
    public ConcreteColleague1 getC1() {
        return c1;
    }

    public void setC1(ConcreteColleague1 c1) {
        this.c1 = c1;
    }

    public ConcreteColleague2 getC2() {
        return c2;
    }

    public void setC2(ConcreteColleague2 c2) {
        this.c2 = c2;
    }

    //中介者模式的业务逻辑
    public abstract void doSomething1();

    public abstract void doSomething2();
}

// 在Mediator抽象类中我们只定义了同事类的注入，为什么使用同事实现类注入而不使用
// 抽象类注入呢？那是因为同事类虽然有抽象，但是没有每个同事类必须要完成的业务方法，
// 当然如果每个同事类都有相同的方法，比如execute、handler等，那当然注入抽象类，做到依赖倒置