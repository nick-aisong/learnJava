package cn.ch02.ch02_5;

// 8.创建读取类Reader，它实现了Runnable接口。这个类将读取价格信息PricesInfo类的属性值
public class Reader implements Runnable {
    // 9.声明价格信息PricesInfo对象，并且通过构造器为它赋值
    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    // 10.实现run()方法，它将循环读取两个价格值10次
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price 1:%f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.printf("%s: Price 2:%f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
