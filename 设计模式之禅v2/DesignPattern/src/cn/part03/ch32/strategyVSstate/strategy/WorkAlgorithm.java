package cn.part03.ch32.strategyVSstate.strategy;

//代码清单32-19 抽象工作算法
public abstract class WorkAlgorithm {
    //每个年龄段都必须完成的工作
    public abstract void work();
}