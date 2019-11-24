package cn.part02.ch29.bridge3.implementor;

//代码清单29-5 iPod山寨公司
public class IPod extends Product {
    public void beProducted() {
        System.out.println("生产出的iPod是这样的...");
    }

    public void beSelled() {
        System.out.println("生产出的iPod卖出去了...");
    }
}
