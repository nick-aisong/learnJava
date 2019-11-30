package cn.part03.ch33.facadeVSmediator.facade;

import java.util.Date;

//代码清单33-27 HR门面
public class HRFacade {
    //总工资情况
    private SalaryProvider salaryProvider = new SalaryProvider();
    //考勤情况
    private Attendance attendance = new Attendance();

    //查询一个人的总收入
    public int querySalary(String name, Date date) {
        return salaryProvider.totalSalary();
    }

    //查询一个员工一个月工作了多少天
    public int queryWorkDays(String name) {
        return attendance.getWorkDays();
    }
}