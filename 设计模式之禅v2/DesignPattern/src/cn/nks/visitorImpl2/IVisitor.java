package cn.nks.visitorImpl2;


/**
 * Created by NKS on 2017/9/24.
 */
public interface IVisitor {

    public void visit(CommonEmployee commonEmployee);

    public void visit(Manager manager);

    public int getTotalSalary();

}
