package cn.ch03.ch03_3;

import java.util.concurrent.TimeUnit;

// 12.创建与会者类Participant，并且实现Runnable接口。这个类表示的是视频会议的与会者
public class Participant implements Runnable {

    // 13.声明Videoconference型私有属性conference
    private Videoconference conference;
    // 14.声明String型私有属性name
    private String name;

    // 15.实现构造器并初始化这两个属性
    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    // 16.实现run()方法
    @Override
    public void run() {
        // 17.将线程休眠一段随机时间
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 18.使用视频会议对象conference的arrive()方法来表明一个与会者的到来
        conference.arrive(name);
    }
}
