package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-17 税收
public class Tax extends AbsColleague implements ITax {
    //注入中介者
    public Tax(AbsMediator _mediator) {
        super(_mediator);
    }

    public void drop() {
        super.mediator.down(this);
    }

    public void raise() {
        super.mediator.up(this);
    }
}