package cn.part02.ch17.decorator2;

//代码清单17-6 修饰的抽象类
public abstract class Decorator extends SchoolReport {
    //首先我要知道是哪个成绩单
    private SchoolReport sr;

    //构造函数，传递成绩单过来
    public Decorator(SchoolReport sr) {
        this.sr = sr;
    }

    //成绩单还是要被看到的
    public void report() {
        this.sr.report();
    }

    //看完还是要签名的
    public void sign(String name) {
        this.sr.sign(name);
    }
}
