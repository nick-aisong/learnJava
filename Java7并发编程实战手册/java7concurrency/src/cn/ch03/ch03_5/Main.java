package cn.ch03.ch03_5;

import java.util.concurrent.Phaser;
// 使用Phaser类同步三个并发任务
// 这三个任务将在三个不同的文件夹及其子文件夹中查找过去24小时内修改过扩展名为.log的文件
// 这个任务分成以下三个步骤：
// 1.在指定的文件夹及其子文件夹中获得扩展名为.log的文件
// 2.对第一步的结果进行过滤，删除修改时间超过24小时的文件
// 3.将结果打印到控制台
// 在第一步和第二步结束的时候，都会检查所查找到的结果列表是不是有元素存在
// 如果结果列表是空的，对应的线程将结束执行，并且从phaser中删除

// 24.实现范例的主类Main并实现main()方法
public class Main {
    public static void main(String[] args) {
        // 25.创建一个Phaser对象，并指定参与阶段同步的线程是3个
        Phaser phaser = new Phaser(3);
        // 26.创建3个文件查找类FileSearch对象，为每一个对象指定不同的查找目录，并且指定查找的是扩展名为.log的文件
        FileSearch system = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files (x86)", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Program Files", "log", phaser);
        // 27.将第一个FileSearch文件查找类对象作为传入参数创建线程，并且启动它
        Thread systemThread = new Thread(system, "System");
        systemThread.start();
        // 28.将第二个FileSearch文件查找类对象作为传入参数创建线程，并且启动它
        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();
        // 29.将第三个FileSearch文件查找类对象作为传入参数创建线程，并且启动它
        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();
        // 30.等待三个线程执行结束
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 31.使用Phaser对象的isFinalized()方法，打印出Phaser对象是否已终止
        System.out.println("Terminated: " + phaser.isTerminated());
    }
}

