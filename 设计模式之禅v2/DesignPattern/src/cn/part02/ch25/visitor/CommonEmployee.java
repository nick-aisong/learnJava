package cn.part02.ch25.visitor;

/**
 * Created by NKS on 2017/9/21.
 */
public class CommonEmployee extends Employee {

    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    protected String getOtherInfo() {
        return "工作：" + this.job + "\t";
    }
}
