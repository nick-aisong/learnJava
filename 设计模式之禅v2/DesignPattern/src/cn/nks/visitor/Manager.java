package cn.nks.visitor;

/**
 * Created by NKS on 2017/9/21.
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
    protected String getOtherInfo() {
        return "业绩：" + this.performance + "\t";
    }
}
