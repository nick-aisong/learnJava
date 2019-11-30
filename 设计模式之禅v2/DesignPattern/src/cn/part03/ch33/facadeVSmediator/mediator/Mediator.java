package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-19 中介者
public class Mediator extends AbsMediator {
    //工资增加了
    public void up(ISalary _salary) {
        upSalary();
        upTax();
    }

    //职位提升了
    public void up(IPosition position) {
        upPosition();
        upSalary();
        upTax();
    }

    //税收增加了
    public void up(ITax tax) {
        upTax();
        downSalary();
    }

    public void down(ISalary _salary) {
        downSalary();
        downTax();
    }

    public void down(IPosition position) {
        downPosition();
        downSalary();
        downTax();
    }

    public void down(ITax tax) {
        downTax();
        upSalary();
    }

    //工资增加
    private void upSalary() {
        System.out.println("工资翻倍，乐翻天");
    }

    private void upTax() {
        System.out.println("税收上升，为国家做贡献");
    }

    private void upPosition() {
        System.out.println("职位上升一级，狂喜");
    }

    private void downSalary() {
        System.out.println("经济不景气，降低工资");
    }

    private void downTax() {
        System.out.println("税收减低，国家收入减少");
    }

    private void downPosition() {
        System.out.println("官降三级，比自杀还痛苦");
    }
}

//该类的方法较多，但是还是非常简单的，它的12个方法分为两大类型：一类是每个业务
//的独立流程，比如增加工资，仅仅实现单独增加工资的职能，而不关心职位、税收是如何变
//化的，该类型的方法是private私有类型，只能提供本类内访问；另一类是实现抽象中介者定
//义的方法，完成具体的每一个逻辑，比如职位上升，同时也引起了工资增加、税收增加