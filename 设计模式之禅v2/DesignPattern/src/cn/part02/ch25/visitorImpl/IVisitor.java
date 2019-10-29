package cn.part02.ch25.visitorImpl;

/**
 * Created by NKS on 2017/9/24.
 */
public interface IVisitor {

    public void visit(CommonEmployee commonEmployee);

    public void visit(Manager manager);

}
