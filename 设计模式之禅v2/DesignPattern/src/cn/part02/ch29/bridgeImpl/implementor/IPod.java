package cn.part02.ch29.bridgeImpl.implementor;

/**
 * Created by NKS on 2017/9/16.
 */
public class IPod extends Product {
    @Override
    public void beProducted() {
        System.out.println("生产出的iPod是这个样子的...");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出的iPod卖出去了...");
    }
}
