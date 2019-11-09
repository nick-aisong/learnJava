package cn.part02.ch14.mediator2;

//代码清单14-7 抽象同事类
public class AbstractColleague {
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator _mediator) {
        this.mediator = _mediator;
    }
}
