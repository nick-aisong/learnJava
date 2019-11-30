package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-11 抽象同事类
public abstract class AbsColleague {
    //每个同事类都对中介者非常了解
    protected AbsMediator mediator;

    public AbsColleague(AbsMediator _mediator) {
        this.mediator = _mediator;
    }
}

//在抽象同事类中定义了每个同事类对中介者都非常了解，如此才能把请求委托给中介者
//完成。三个同事类都具有相同的设计，即定义一个业务接口以及每个对象必须实现的职责，
//同时既然是同事类就都继承AbsColleague。抽象同事类只是一个标志性父类，并没有限制子
//类的业务逻辑，因此每一个同事类并没有违背单一职责原则