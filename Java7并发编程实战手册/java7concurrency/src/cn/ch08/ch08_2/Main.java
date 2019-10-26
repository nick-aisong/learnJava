package cn.ch08.ch08_2;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

// 8.实现范例的主类，创建Main主类，并实现main()方法
// 监控Phaser类
public class Main {

    public static void main(String[] args) throws Exception {
        // 9.创建一个名为phaser的新Phaser对象，有3个参与者
        Phaser phaser = new Phaser(3);
        // 10.创建并启动3个线程来执行3个任务对象
        for (int i = 0; i < 3; i++) {
            Task task = new Task(i + 1, phaser);
            Thread thread = new Thread(task);
            thread.start();
        }
        // 12.输出关于已注册方的信息、phaser 的阶段、已到达方和未到达方
        for (int i = 0; i < 10; i++) {
            System.out.printf("*********************************\n");
            System.out.printf("Main: Phaser Log\n");
            System.out.printf("Main: Phaser : Phase: %d\n", phaser.getPhase());
            System.out.printf("Main: Phaser : Registered Parties: %d\n", phaser.getRegisteredParties());
            System.out.printf("Main: Phaser : Arrived Parties: %d\n", phaser.getArrivedParties());
            System.out.printf("Main: Phaser : Unarrived Parties: %d\n", phaser.getUnarrivedParties());
            System.out.printf("*********************************\n");
            // 13. 让线程休眠1秒钟
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

// 本节范例在Task类中实现了一个阶段任务(Phase Task)
// 该任务有3个阶段并使用一个Phaser接口与其他Task对象进行同步
// 主类启动3个任务并且当这些任务都在执行各自阶段时，主类在控制台输出关于phaser 对象状态的信息

// 使用下列方法得到phaser对象的状态
// ·getPhase()：这个方法返回phaser对象的当前阶段
// ·getRegisteredParties()：这个方法返回使用phaser对象作为同步机制的任务数
// ·getArrivedParties()：这个方法返回在一个阶段结束时已到达的任务数
// ·getUnarrivedParties()：这个方法返回在一个阶段结束时未到达的任务数

// *********************************
// Main: Phaser Log
// Thread-2: Entering phase 1.
// Thread-1: Entering phase 1.
// Thread-0: Entering phase 1.
// Main: Phaser : Phase: 1
// Main: Phaser : Registered Parties: 3
// Main: Phaser : Arrived Parties: 0
// Main: Phaser : Unarrived Parties: 3
// *********************************
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 1
// Main: Phaser : Registered Parties: 3
// Main: Phaser : Arrived Parties: 0
// Thread-0: Finishing phase 1.
// Main: Phaser : Unarrived Parties: 3
// *********************************
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 1
// Main: Phaser : Registered Parties: 3
// Main: Phaser : Arrived Parties: 1
// Main: Phaser : Unarrived Parties: 2
// *********************************
// Thread-1: Finishing phase 1.
// Thread-2: Finishing phase 1.
// Thread-2: Entering phase 2.
// Thread-0: Entering phase 2.
// Thread-1: Entering phase 2.
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 2
// Main: Phaser : Registered Parties: 3
// Main: Phaser : Arrived Parties: 0
// Main: Phaser : Unarrived Parties: 3
// *********************************
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 2
// Main: Phaser : Registered Parties: 3
// Main: Phaser : Arrived Parties: 0
// Main: Phaser : Unarrived Parties: 3
// *********************************
// Thread-0: Finishing phase 2.
// Thread-1: Finishing phase 2.
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 2
// Main: Phaser : Registered Parties: 3
// Main: Phaser : Arrived Parties: 2
// Main: Phaser : Unarrived Parties: 1
// *********************************
// Thread-2: Finishing phase 2.
// Thread-1: Entering phase 3.
// Thread-0: Entering phase 3.
// Thread-2: Entering phase 3.
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 3
// Main: Phaser : Registered Parties: 3
// Main: Phaser : Arrived Parties: 0
// Main: Phaser : Unarrived Parties: 3
// *********************************
// Thread-0: Finishing phase 3.
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 3
// Main: Phaser : Registered Parties: 2
// Main: Phaser : Arrived Parties: 0
// Main: Phaser : Unarrived Parties: 2
// *********************************
// Thread-1: Finishing phase 3.
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: 3
// Main: Phaser : Registered Parties: 1
// Main: Phaser : Arrived Parties: 0
// Main: Phaser : Unarrived Parties: 1
// *********************************
// Thread-2: Finishing phase 3.
// *********************************
// Main: Phaser Log
// Main: Phaser : Phase: -2147483644
// Main: Phaser : Registered Parties: 0
// Main: Phaser : Arrived Parties: 0
// Main: Phaser : Unarrived Parties: 0
// *********************************