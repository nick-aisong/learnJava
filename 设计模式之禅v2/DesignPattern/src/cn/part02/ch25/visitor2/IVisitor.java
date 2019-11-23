package cn.part02.ch25.visitor2;

//代码清单25-5 访问者接口
public interface IVisitor {
    //首先，定义我可以访问普通员工
    public void visit(CommonEmployee commonEmployee);

    //其次，定义我还可以访问部门经理
    public void visit(Manager manager);
}
