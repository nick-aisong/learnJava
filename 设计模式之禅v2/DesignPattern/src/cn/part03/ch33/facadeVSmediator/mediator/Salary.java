package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-15 工资
public class Salary extends AbsColleague implements ISalary {
    public Salary(AbsMediator _mediator) {
        super(_mediator);
    }

    public void decreaseSalary() {
        super.mediator.down(this);
    }

    public void increaseSalary() {
        super.mediator.up(this);
    }
}
