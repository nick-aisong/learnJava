package cn.part02.ch17.decorator2;

//代码清单17-1 抽象成绩单
public abstract class SchoolReport {
    //成绩单主要展示的就是你的成绩情况
    public abstract void report();

    //成绩单要家长签字，这个是最要命的
    public abstract void sign(String name);
}
