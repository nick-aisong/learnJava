package cn.ch03.ch03_3;

// 19.实现范例的主类Main，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 20.创建视频会议对象conference，它要等待10个与会者到齐
        Videoconference conference = new Videoconference(10);
        // 21.将视频会议对象作为传入参数创建线程，并且启动
        Thread threadConference = new Thread(conference);
        threadConference.start();

        // 22.创建10个与会者对象，分别将每一个作为传入参数创建线程，并且启动
        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference, "Participant " + i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}

// CountDownLatch类有三个基本元素:
// ·一个初始值，即定义必须等待的先行完成的操作的数目
// ·await()方法，需要等待其他事件先完成的线程调用
// ·countDown()方法，每个被等待的事件在完成的时候调用

// 当创建CountDownLatch对象时，使用构造器来初始化内部计数器
// 当countDown()方法被调用后，计数器将减1当计数器到达0的时候，CountDownLatch对象将唤起所有在await()方法上等待的线程
// CountDownLatch对象的内部计数器被初始化之后就不能被再次初始化或者修改
// 一旦计数器被初始化后，唯一能改变参数值的方法是countDown()方法
// 当计数器到达0时，所有因调用await()方法而等待的线程立刻被唤醒，再执行countDown()将不起任何作用

// 和其他同步方法相比，CountDownLatch 机制有下述不同
// ·CountDownLatch机制不是用来保护共享资源或者临界区的，它是用来同步执行多个任务的一个或者多个线程
// ·CountDownLatch只准许进入一次
// 如同刚刚解释的，一旦CountDownLatch的内部计数器到达0，再调用这个方法将不起作用
// 如果要做类似的同步，就必须创建一个新的CountDownLatch对象

// CountDownLatch类提供了另外一种await0方法，即await(long time, TimeUnit unit)
// 这个方法被调用后，线程将休眠直到被中断，或者CountDownLatch的内部计数器达到0，或者指定的时间已经过期
// 第二个参数是TimeUnit类型，TimeUnit类是以下常量的枚举:
// DAYS、HOURS、MICROSECONDS、MIILLISECONDS、MINUTES、NANOSECONDS和SECONDS

// VideoConference: Initialization: 10 participants.
// Participant 1 has arrived.VideoConference: Waiting for 9 participants.
// Participant 3 has arrived.VideoConference: Waiting for 8 participants.
// Participant 7 has arrived.VideoConference: Waiting for 7 participants.
// Participant 8 has arrived.Participant 5 has arrived.VideoConference: Waiting for 5 participants.
// VideoConference: Waiting for 6 participants.
// Participant 9 has arrived.VideoConference: Waiting for 4 participants.
// Participant 4 has arrived.VideoConference: Waiting for 3 participants.
// Participant 2 has arrived.VideoConference: Waiting for 2 participants.
// Participant 6 has arrived.VideoConference: Waiting for 1 participants.
// Participant 0 has arrived.VideoConference: Waiting for 0 participants.
// VideoConference: All the participants have come
// VideoConference: Let's start...