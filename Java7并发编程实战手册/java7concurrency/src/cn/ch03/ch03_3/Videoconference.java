package cn.ch03.ch03_3;

import java.util.concurrent.CountDownLatch;

// 使用CountDownLatch类实现视频会议系统
// 这个视频会议系统将等待所有的参会者都到齐才开始

// 1.创建视频会议类Videoconference,并且实现Runnable接口。这个类实现的是一个视频会议系统
public class Videoconference implements Runnable {

    // 2.声明CountDownLatch对象controller
    private final CountDownLatch controller;

    // 3.实现构造器，并且初始化controller属性
    public Videoconference(int number) {
        controller = new CountDownLatch(number);
    }

    // 8.实现视频会议系统的run()方法，这是Runnable对象必须实现的
    @Override
    public void run() {
        // 9.使用getCount()方法打印出这次视频会议的人数
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
        try {
            // 10.使用await()方法等待所有的与会者到达。由于这个方法会抛出InterruptedException异常，所以必须捕获并处理这个异常
            controller.await();
            // 11.当所有的与会者都到齐后，打印出与会者到齐会议开始的信息
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 4.实现arrive()方法。每一个 与会者进入视频会议的时候，这个方法将被调用。它的传入参数是String型变量name
    public void arrive(String name) {
        // 5.打印出与会者到达的信息
        System.out.printf("%s has arrived.", name);
        // 6.调用controller的countDown()方法
        controller.countDown();
        // 7.打印出还没有到达的与会者的数目，通过CountDownLatch对象的getCount()方法实现
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }
}
