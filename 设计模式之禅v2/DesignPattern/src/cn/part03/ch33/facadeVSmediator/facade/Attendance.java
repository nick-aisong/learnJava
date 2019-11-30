package cn.part03.ch33.facadeVSmediator.facade;

import java.util.Random;

//代码清单33-21 考勤情况
public class Attendance {
    //得到出勤天数
    public int getWorkDays() {
        return (new Random()).nextInt(30);
    }
}