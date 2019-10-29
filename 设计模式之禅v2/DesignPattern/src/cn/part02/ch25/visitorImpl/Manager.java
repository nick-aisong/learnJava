package cn.part02.ch25.visitorImpl;

/**
 * Created by NKS on 2017/9/24.
 */
public class Manager extends Employee {

    private String performance;

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
