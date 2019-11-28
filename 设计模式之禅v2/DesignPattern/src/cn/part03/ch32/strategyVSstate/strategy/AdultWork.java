package cn.part03.ch32.strategyVSstate.strategy;

//代码清单32-21 成年人工作
public class AdultWork extends WorkAlgorithm {
    //成年人的工作
    public void work() {
        System.out.println("成年人的工作就是先养活自己，然后为社会做贡献！");
    }
}