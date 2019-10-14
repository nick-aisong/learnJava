package cn.ch02.ch02_5;

// 11. 创建写入类Writer, 它实现了Runnable接口。这个类将修改价格信息PricesInfo类的属性值
public class Writer implements Runnable {
    // 12.声明价格信息PricesInfo对象，并且通过构造器为它赋值
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    // 13.实现run()方法，它将循环修改两个价格3次，每次修改后线程将休眠2秒钟
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("Writer: Attempt to modify the prices.\n");
            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
