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

// 本范例开始的时候创建了Phaser对象，用于在每个阶段结束时对线程同步进行控制
// Phaser构造器传入了参与阶段同步的线程的个数。在这个例子中，Phaser有三个参与线程
// 这个数字通知Phaser在唤醒所有休眠线程以进行下一个阶段之前，必须执行arriveAndAwaitAdvance()方法的线程数
// 在Phaser创建后，我们使用三个不同的文件查找对象创建了三个线程并且启动了它们

// 在文件查找类FileSearch的run()方法中，第一个指令是调用Phaser对象的arriveAndAwaitAdvance()方法
// 如前所述，Phaser知道我们要同步的线程数，当一个线程调用这个方法时，Phaser 对象将减1，并且把这个线程置于休眠状态，直到所有其他线程完成这个阶段
// 在run()方法的开头调用这个方法可以保障在所有线程创建好之前没有线程开始执行任务

// 在第一阶段和第二阶段结束的时候，检查在这个阶段中是不是生成了结果集以及结果集中是不是有元素
// 在第一个阶段，checkResults()方法调用了arriveAndAwaitAdvance()方法
// 在第二个阶段，如果结果集是空的，对应的线程没有理由继续执行，所以返回
// 但是必须通知phaser对象参与同步的线程少了一个
// 为了达到这个目地，我们使用了arriveAndDeregister()方法
// 这就实现了对phaser对象的通知，即这个线程已经完成了当前语句，并且不会在下一个阶段中参与，因而phaser对象在开始下一个阶段时不会等待这个线程了
// 在第三阶段结束的时候，在showInfo()方法中调用了phaser对象的arriveAndAwaitAdvance()方法
// 通过这个调用，确保三个线程都已完成
// 当showInfo()方法执行完成之后，还调用了phaser对象的arriveAndDeregister()方法
// 通过这个调用，撤销了phaser中线程的注册，所以当所有线程运行结束的时候，phaser对象就没有参与同步的线程了
// 最后，main()方法等待所有三个线程完成后，调用了phaser对象的isTerminated()方法
// 当phaser对象不存在参与同步的线程时，phaser是终止状态的，isTerminated()方法将返回true
// 当取消所有线程的注册时，phaser对象会变成终止状态，所以，这个调用将打印到控制台的是true信息

// 一个Phaser对象有两种状态：
// ·活跃态(Active): 当存在参与同步的线程的时候，Phaser就是活跃的，并且在每个阶段结束的时候进行同步
// 在这种状态中，Phaser的执行如前文所述。但在Java并发API中并没有提到这种状态
// ·终止态(Termination):当所有参与同步的线程都取消注册的时候，Phaser 就处于终止状态，在这种状态下，Phaser 没有任何参与者
// 更具体地说，当Phaser 对象的onAdvance()方法返回true的时候，Phaser对象就处于了终止态
// 通过覆盖这个方法可以改变默认的行为。当Phaser是终止态的时候，同步方法arriveAndAwaitAdvance()会立即返回，而且不会做任何同步的操作
// Phaser类的一个重大特性就是不必对它的方法进行异常处理。不像其他的同步辅助类，被Phaser类置于休眠的线程不会响应中断事件，也不会抛出InterruptedException异常

// Phaser类提供了一些其他改变Phaser对象的方法，这些方法如下
// ·arrive(): 这个方法通知phaser对象一个参与者已经完成了当前阶段，但是它不应该等待其他参与者都完成当前阶段
// 必须小心使用这个方法，因为它不会与其他线程同步
// ·awaitAdvance(int phase):如果传入的阶段参数与当前阶段一致,这个方法会将当
// 前线程置于休眠，直到这个阶段的所有参与者都运行完成
// 如果传入的阶段参数与当前阶段不一致，这个方法将立即返回
// ·awaitAdvanceInterruptibly(int phaser): 这个方法跟awaitAdvance(int phase)一
// 样，不同之处是，如果在这个方法中休眠的线程被中断，它将抛出InterruptedException异常

// 将参与者注册到Phaser中
// 创建一个Phaser对象时，需要指出有多少个参与者。Phaser类提供了两种方法增加注册者的数量，这些方法如下
// ·register():这个方法将一个新的参与者注册到Phaser中，这个新的参与者将被当成没有执完本阶段的线程
// ·bulkRegister(int Parties):这个方法将指定数目的参与者注册到Phaser中，所有这些新的参与者都将被当成没有执完本阶段的线程
// Phaser类只提供了一种方法减少注册者的数目，即arriveAndDeregister()方法
// 它通知phaser对象对应的线程已经完成了当前阶段，并且它不会参与到下一个阶段的操作中

// 强制终止Phaser
// 当一个phaser对象没有参与线程的时候，它就处于终止状态
// Phaser类提供了forceTermination()方法来强制phaser进入终止态，这个方法不管phaser中是否存在注册的参与线程
// 当一个参与线程产生错误的时候，强制phaser终止是很有意义的
// 当一个phaser处于终止态的时候，awaitAdvance()和arriveAndAwaitAdvance()方法立即返回一个负数，而不再是一个正值了
// 如果知道phaser可能会被终止，就需要验证这些方法的返回值，以确定phaser是不是被终止了

// Documents: Starting.
// Apps: Starting.
// System: Starting.
// System: Phase 1: 48 results.
// Documents: Phase 1: 48 results.
// Apps: Phase 1: 40 results.
// Apps: Phase 2: 0 results.
// Apps: Phase 2: End.
// Documents: Phase 2: 0 results.
// Documents: Phase 2: End.
// System: Phase 2: 0 results.
// System: Phase 2: End.
// Terminated: true