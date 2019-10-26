package cn.ch07.ch07_10;

// 12.创建一个名为Sensor2的类，实现Runnable接口
public class Sensor2 implements Runnable {
    // 13.声明一个名为counter的私有ParkineCounter属性
    private ParkingCounter counter;

    // 14.实现类构造器来初始化它的属性
    public Sensor2(ParkingCounter counter) {
        this.counter = counter;
    }

    // 15.实现run()方法。调用几次carIn()和carOut()方法
    @Override
    public void run() {
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
    }
}
