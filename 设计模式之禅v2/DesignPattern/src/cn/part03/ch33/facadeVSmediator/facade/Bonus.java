package cn.part03.ch33.facadeVSmediator.facade;

//代码清单33-22 奖金计算
public class Bonus {
    //考勤情况
    private Attendance atte = new Attendance();

    //奖金
    public int getBonus() {
        //获得出勤情况
        int workDays = atte.getWorkDays();
        //奖金计算模型
        int bonus = workDays * 1800 / 30;
        return bonus;
    }
}