package cn.part02.ch09.abstractFactory;
//在接口中，我们看到八卦炉是可以生产出不同肤色人种的（当然了，女娲的失误嘛）
//那它有多少个八卦炉呢？两个，分别生产女性和男性

//代码清单9-7 八卦炉定义
public interface HumanFactory {
    //制造一个黄色人种
    public Human createYellowHuman();

    //制造一个白色人种
    public Human createWhiteHuman();

    //制造一个黑色人种
    public Human createBlackHuman();
}