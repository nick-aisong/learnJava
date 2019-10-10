package cn.nks.visitorImpl3;

/**
 * Created by NKS on 2017/9/24.
 */
public class TotalVisitor implements ITotalVisitor {

    private final static int MANAGER_COEFFICIENT = 5;

    private final static int COMMONEMPLOYEE_COEFFICIENT = 2;

    private int commonTotalSalary = 0;

    private int managerTotalSalary = 0;


    @Override
    public void visit(CommonEmployee commonEmployee) {
        this.commonTotalSalary = this.commonTotalSalary +
                commonEmployee.getSalary() * COMMONEMPLOYEE_COEFFICIENT;
    }

    @Override
    public void visit(Manager manager) {
        this.managerTotalSalary = this.managerTotalSalary + manager.getSalary()
                * MANAGER_COEFFICIENT;
    }

    @Override
    public void totalSalary() {
        System.out.println(" 本公司的月工资总额是 " + (this.commonTotalSalary +
                this.managerTotalSalary));
    }
}
