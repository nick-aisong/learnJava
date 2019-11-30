package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-14 工资接口
public interface ISalary {
    //加薪
    public void increaseSalary();

    //降薪
    public void decreaseSalary();
}