package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-13 职位
public class Position extends AbsColleague implements IPosition {
    public Position(AbsMediator _mediator) {
        super(_mediator);
    }

    public void demote() {
        super.mediator.down(this);
    }

    public void promote() {
        super.mediator.up(this);
    }
}