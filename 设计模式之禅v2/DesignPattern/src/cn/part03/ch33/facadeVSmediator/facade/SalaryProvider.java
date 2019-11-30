package cn.part03.ch33.facadeVSmediator.facade;

//代码清单33-26 总工资计算
public class SalaryProvider {
    //基本工资
    private BasicSalary basicSalary = new BasicSalary();
    //奖金
    private Bonus bonus = new Bonus();
    //绩效
    private Performance perf = new Performance();
    //税收
    private Tax tax = new Tax();

    //获得用户的总收入
    public int totalSalary() {
        return basicSalary.getBasicSalary() + bonus.getBonus() + perf.getPerformanceValue() - tax.getTax();
    }
}

//这里只是对前面的元素值做了一个加减法计算，这是对实际HR系统的简化处理，如果
//把这个类暴露给外系统，那么被修改的风险是非常大的，因为它的方法totalSalary是一个具
//体的业务逻辑。我们采用门面模式的目的是要求门面是无逻辑的，与业务无关，只是一个子
//系统的访问入口