package cn.ch07.ch07_10;

// 8.创建一个名为Sensor1的类，实现Runnable接口
public class Sensor1 implements Runnable {
    // 9.声明一个名为counter的私有ParkineCounter属性
    private ParkingCounter counter;

    // 10.实现类构造器来初始化它的属性
    public Sensor1(ParkingCounter counter) {
        this.counter = counter;
    }

    // 11.实现run()方法。调用几次carIn()和carOut()方法
    @Override
    public void run() {
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carIn();
        counter.carIn();
        counter.carIn();
    }
}
