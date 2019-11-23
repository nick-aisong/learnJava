package cn.part02.ch25.visitor5;

public interface IVisitor {

    public void visit(CommonEmployee commonEmployee);

    public void visit(Manager manager);
}
