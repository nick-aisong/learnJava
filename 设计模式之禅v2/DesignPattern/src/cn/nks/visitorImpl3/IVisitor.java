package cn.nks.visitorImpl3;

/**
 * Created by NKS on 2017/9/24.
 */
public interface IVisitor {

    public void visit(CommonEmployee commonEmployee);

    public void visit(Manager manager);
}
