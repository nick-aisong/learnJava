package cn.part02.ch25.visitor2;

//代码清单25-8 普通员工
public class CommonEmployee extends Employee {
    //工作内容，这非常重要，以后的职业规划就是靠它了
    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    //我允许访问者访问
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
