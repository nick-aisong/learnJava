package cn.ch08.ch08_5;

import java.util.logging.Level;
import java.util.logging.Logger;
// 在本例，我们将学习如何使用java.util.logging包提供的类来将一个日志系统增加到并发应用程序中

// 15.实现范例的主类，创建Main主类，并实现main()方法

// 输出高效的日志信息
public class Main {

    public static void main(String[] args) {
        // 16. 声明一个名为logger的Logger对象
        // 调用MyLogger类的getLogger()方法，以字符串Core作为参数来初始化Logger对象
        Logger logger = MyLogger.getLogger("Core");
        // 17.使用entering()方法输出一条日志消息表示主程序开始执行
        logger.entering("Core", "main", args);
        // 18.创建一个Thread数组存储5个线程
        Thread[] threads = new Thread[5];
        // 19.创建5个Task对象和5个线程来执行它们。输出日志消息表明将启动一个新线程且线程已创建
        for (int i = 0; i < threads.length; i++) {
            logger.log(Level.INFO, "Launching thread: " + i);
            Task task = new Task();
            threads[i] = new Thread(task);
            logger.log(Level.INFO, "Thread created: " + threads[i].getName());
            threads[i].start();
        }
        // 20.输出一条日志消息表明已创建了线程
        logger.log(Level.INFO, "Ten Threads created. Waiting for its finalization");
        // 21.调用join()方法等待5个线程执行结束。在每个线程结束后，输出一条日志消息
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
                logger.log(Level.INFO, "Thread has finished its execution", threads[i]);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Exception", e);
            }
        }
        // 22.调用exiting()方法输出一条日志消息表示主程序执行结束
        logger.entering("Core", "main");
    }
}

// 在本节，我们使用了来自Java日志API的Logger类在并发应用程序中输出日志消息
// 首先，实现MyFormatter类，为日志消息指定格式
// 这个类继承了Formatter类，它声明了抽象方法format()
// 这个方法接收一个LogRecord对象，用来格式化给定的日志记录，并返回格式化后的字符串
// 在这个实现类中，已使用LogRecord类的下列方法来获取日志消息的信息
// ·getLevel()：返回消息级别
// ·getMillis()：返回发送消息到Logger对象时的时间(从1970开始计算的毫秒数)
// ·getSourceClassName()：返回发送消息到Logger的类的名称
// ·getSourceMessageName()：返回发送消息到Logger的方法的名称

// getMessageO方法返回日志消息。MyLogger类实现了静态方法getLogger()，
// 它创建一个Logger对象，并指定一个Handler对象来使用MyFormatter格式器输出应用程序的日志消息到recipe8.log文件中

// 本范例创建了带有静态方法getLogger()的Logger对象
// 这个方法根据所传递的参数返回不同的对象
// 因以仅创建了一个Handler对象，所以所有的Logger对象都将日志消息输出到同一个文件中
// 已配置logger对象输出所有的日志消息，因此不需要考虑它的日志级别
// 最后，实现了一个Task对象和一个主程序，输出不同的日志消息到日志文件中使用了下列方法
// ·entering()：输出FINER级别的消息表示方法开始执行
// ·exiting()：输出FINER级别的消息表示方法停止执行
// ·log()：输出带有指定级别的消息

// 当使用一个日志系统时，需要重点考虑下列两点
// ·输出必需的信息：如果输出的日志信息太少，日志器将徒劳无功
// 如果输出的日志信息太多，将产生过大的日志文件，不仅不易于管理还难于从中获取必需的信息
// ·为消息设定恰当的级别：如果使用过高级别或过低级别消息，将会让阅读日志文件的人感到迷惑
// 在一个错误情况中很难知道发生了什么问题，或者得到了引起错误的过多信息，而无法快速找到主要原因

// 也有其他类库比java.util.logging包提供了更完全的日志系统，如Log4j或slf4j类库
// 但是java.util.logging包是JavaAPI的一部分，并且它的所有方法都是安全的，所以用在并发应用程序中没有问题

// Log in console: (只设置了FileHandler，还是会把日志输出到console)

// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Launching thread: 0
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Thread created: Thread-1
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Launching thread: 1
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Thread created: Thread-2
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Launching thread: 2
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Thread created: Thread-3
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Launching thread: 3
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Thread created: Thread-4
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Launching thread: 4
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Thread created: Thread-5
// 十月 27, 2019 2:07:02 上午 cn.ch08.ch08_5.Main main
// 信息: Ten Threads created. Waiting for its finalization
// 十月 27, 2019 2:07:04 上午 cn.ch08.ch08_5.Main main
// 信息: Thread has finished its execution
// 十月 27, 2019 2:07:04 上午 cn.ch08.ch08_5.Main main
// 信息: Thread has finished its execution
// 十月 27, 2019 2:07:04 上午 cn.ch08.ch08_5.Main main
// 信息: Thread has finished its execution
// 十月 27, 2019 2:07:04 上午 cn.ch08.ch08_5.Main main
// 信息: Thread has finished its execution
// 十月 27, 2019 2:07:04 上午 cn.ch08.ch08_5.Main main
// 信息: Thread has finished its execution

// Log in file

// [FINER] - Sun Oct 27 02:07:02 CST 2019 : Core.main : ENTRY
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Launching thread: 0
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Thread created: Thread-1
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Launching thread: 1
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Thread created: Thread-2
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Launching thread: 2
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Thread created: Thread-3
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Launching thread: 3
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Thread created: Thread-4
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Launching thread: 4
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Thread created: Thread-5
// [INFO] - Sun Oct 27 02:07:02 CST 2019 : cn.ch08.ch08_5.Main.main : Ten Threads created. Waiting for its finalization
// [FINER] - Sun Oct 27 02:07:02 CST 2019 : Thread-3.run() : ENTRY
// [FINER] - Sun Oct 27 02:07:02 CST 2019 : Thread-2.run() : ENTRY
// [FINER] - Sun Oct 27 02:07:02 CST 2019 : Thread-1.run() : ENTRY
// [FINER] - Sun Oct 27 02:07:02 CST 2019 : Thread-4.run() : ENTRY
// [FINER] - Sun Oct 27 02:07:02 CST 2019 : Thread-5.run() : ENTRY
// [FINER] - Sun Oct 27 02:07:04 CST 2019 : Thread-2.run() : RETURN {0}
// [FINER] - Sun Oct 27 02:07:04 CST 2019 : Thread-3.run() : RETURN {0}
// [FINER] - Sun Oct 27 02:07:04 CST 2019 : Thread-1.run() : RETURN {0}
// [INFO] - Sun Oct 27 02:07:04 CST 2019 : cn.ch08.ch08_5.Main.main : Thread has finished its execution
// [INFO] - Sun Oct 27 02:07:04 CST 2019 : cn.ch08.ch08_5.Main.main : Thread has finished its execution
// [INFO] - Sun Oct 27 02:07:04 CST 2019 : cn.ch08.ch08_5.Main.main : Thread has finished its execution
// [FINER] - Sun Oct 27 02:07:04 CST 2019 : Thread-4.run() : RETURN {0}
// [INFO] - Sun Oct 27 02:07:04 CST 2019 : cn.ch08.ch08_5.Main.main : Thread has finished its execution
// [FINER] - Sun Oct 27 02:07:04 CST 2019 : Thread-5.run() : RETURN {0}
// [INFO] - Sun Oct 27 02:07:04 CST 2019 : cn.ch08.ch08_5.Main.main : Thread has finished its execution
// [FINER] - Sun Oct 27 02:07:04 CST 2019 : Core.main : ENTRY
