package cn.part02.ch14.mediator3;

//代码清单14-14 抽象同事类
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator _mediator) {
        this.mediator = _mediator;
    }
}