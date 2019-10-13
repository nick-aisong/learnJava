package cn.ch01.ch01_2;

// 1.创建一个名为Calculator的类，它实现了Runnable接口
public class Calculator implements Runnable {

    // 2.声明一个名为number的私有(private) int 属性。编写这个类的一个构造器，用来为属性number设置值
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    // 3.编写run()方法。这个方法用来执行我们创建的线程的指令，本范例中它将对指定的数字进行乘法表运算
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            // 在run()方法里调用Thread类的静态方法currentThread()获取当前线程的信息
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
        }
    }
}
