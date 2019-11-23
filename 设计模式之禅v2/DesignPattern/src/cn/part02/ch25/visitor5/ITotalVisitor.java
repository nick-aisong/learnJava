package cn.part02.ch25.visitor5;

//代码清单25-22 汇总表接口
public interface ITotalVisitor extends IVisitor {
    //统计所有员工工资总和
    public void totalSalary();
}
