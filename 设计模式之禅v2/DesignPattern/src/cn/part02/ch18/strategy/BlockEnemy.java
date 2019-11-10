package cn.part02.ch18.strategy;

//代码清单18-4 孙夫人断后
public class BlockEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}