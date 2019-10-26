package cn.ch07.ch07_10;
// 本例将学习如何继承一个原子对象和如何实现两个遵守原子对象机制保证所有操作在单步内结束的方法

// 16.创建名为Main的主类并添加main()方法

// 实现自己的原子对象
public class Main {

    public static void main(String[] args) throws Exception {
        // 17.创建一个名为counter的ParkingCounter对象
        ParkingCounter counter = new ParkingCounter(5);
        // 18.创建并执行一个Sensor1任务和一个Sensor2任务
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);

        Thread thread1 = new Thread(sensor1);
        Thread thread2 = new Thread(sensor2);

        thread1.start();
        thread2.start();
        // 19.等待两个任务执行结束
        thread1.join();
        thread2.join();
        // 20.在控制台输出实际的counter值
        System.out.printf("Main: Number of cars: %d\n", counter.get());
        // 21.在控制台输出一个消息表示程序结束
        System.out.printf("Main: End of the program.\n");
    }
}

// ParkingCounter类继承自AtomicInteger类，它带有两个原子操作carIn()和carOut()
// 本例模拟一个系统来控制停车场内的汽车数量。停车场能接纳的汽车数由maxNumber属性表示
// carIn()方法将停车场现有汽车数与最大停车数相比较
// 如果相等，则汽车不可以进入停车场，并返回false。否则，使用下面的原子操作指令
// 1.将原子对象的值赋给一个本地变量
// 2.将本地变量值增加1作为新值，并把这个新值赋给另一个不同的变量
// 3.使用compareAndSet()方法尝试使用新值替换旧值。如果返回true作为参数的旧值就是当前内部计数器的值，所以计数器值将发生改变
// 这个操作是以原子方式执行的，将返回true，如carIn()方法
// 如果compareAndSet()返回false，作为参数的旧值已不是当前内部计数器的值(另外一个线程已修改过它)，所以这个操作就不是以原子方式执行的
// 操作将重新开始直到它能够以原子方式完成

// carOut()方法与carIn()相似
// 我们还实现了两个Runnable对象，这两个对象使用carIn()和carOut()方法模拟停车场的活动
// 当运行这个程序时，能够看到停车场永不会超出设定的最大停车数

// ParkingCounter: A car has entered.
// ParkingCounter: A car has entered.
// ParkingCounter: A car has entered.
// ParkingCounter: A car has entered.
// ParkingCounter: A car has entered.
// ParkingCounter: A car has gone out.
// ParkingCounter: A car has gone out.
// ParkingCounter: A car has entered.
// ParkingCounter: A car has entered.
// ParkingCounter: A car has entered.
// ParkingCounter: A car has gone out.
// ParkingCounter: The parking lot is full.
// ParkingCounter: A car has entered.
// ParkingCounter: The parking lot is full.
// ParkingCounter: The parking lot is full.
// ParkingCounter: A car has gone out.
// ParkingCounter: A car has gone out.
// ParkingCounter: A car has entered.
// ParkingCounter: The parking lot is full.
// ParkingCounter: The parking lot is full.
// Main: Number of cars: 5
// Main: End of the program.