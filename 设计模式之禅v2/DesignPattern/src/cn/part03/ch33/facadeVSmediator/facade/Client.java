package cn.part03.ch33.facadeVSmediator.facade;

import java.util.Date;

//代码清单33-28 场景类
public class Client {
    public static void main(String[] args) {
        //定义门面
        HRFacade facade = new HRFacade();
        System.out.println("===外系统查询总收入===");
        int salary = facade.querySalary("张三", new Date(System.currentTimeMillis()));
        System.out.println("张三 11月 总收入为：" + salary);
        //再查询出勤天数
        System.out.println("\n===外系统查询出勤天数===");
        int workDays = facade.queryWorkDays("李四");
        System.out.println("李四 本月出勤：" + workDays);
    }
}

//在该例中，我们使用了门面模式对薪水计算子系统进行封装，避免子系统内部复杂逻辑
//外泄，确保子系统的业务逻辑的单纯性，即使业务流程需要变更，影响的也是子系统内部功
//能，比如奖金需要与基本工资挂钩，这样的修改对外系统来说是透明的，只需要子系统内部/**/
//变更即可