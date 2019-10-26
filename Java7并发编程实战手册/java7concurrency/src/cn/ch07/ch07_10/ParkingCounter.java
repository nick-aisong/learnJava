package cn.ch07.ch07_10;

import java.util.concurrent.atomic.AtomicInteger;

// 1.创建一个名为ParkingCounter的类，继承AtomicInteger类
public class ParkingCounter extends AtomicInteger {
    private static final long serialVersionUID = 1L;
    // 2.声明一个名为maxNumber的私有int属性，用来存储停车场中可停放汽车的最大数量
    private int maxNumber;

    // 3.实现类构造器来初始化它的属性
    public ParkingCounter(int maxNumber) {
        set(0);
        this.maxNumber = maxNumber;
    }

    // 4.实现carln()方法。如果它的值小于指定的最大值，这个方法就增加车的数量
    // 构建一个无限循环并使用get()方法取得内部计数器的值
    public boolean carIn() {
        for (; ; ) {
            int value = get();
            // 5.如果内部计数器的值等于可停放汽车的最大数量maxNumber属性
            // 计数器不可以被继续增加(即停车场已满，无法再进入)，然后返回false
            if (value == maxNumber) {
                System.out.printf("ParkingCounter: The parking lot is full.\n");
                return false;
                // 6.否则，将内部计数器的值加1作为一个新值，并使用compareAndSet()方法将内部计数器设置为这个新值
                // 如果changed结果返回false，那么内部计数器不被增加，所以会再次循环
                // 如果changed结果返回true，意味着计数器已被更新，然后直接返回true
            } else {
                int newValue = value + 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed) {
                    System.out.printf("ParkingCounter: A car has entered.\n");
                    return true;
                }
            }
        }
    }

    // 7.实现carOut()方法。如果车的数量大于零，这个方法将减少车的数量
    // 构建一个无限循环并使用get()方法取得内部计数器的值
    public boolean carOut() {
        for (; ; ) {
            int value = get();
            if (value == 0) {
                System.out.printf("ParkingCounter: The parking lot is empty.\n");
                return false;
            } else {
                int newValue = value - 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed) {
                    System.out.printf("ParkingCounter: A car has gone out.\n");
                    return true;
                }
            }
        }
    }
}
