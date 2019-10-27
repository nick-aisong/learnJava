package cn.ch08.ch08_6;

import java.util.concurrent.locks.ReentrantLock;
// 5.实现范例的主类，创建Main主类，并实现main()方法

// 使用FindBugs分析并发代码
public class Main {

    public static void main(String[] args) {
        // 6.声明并创建一个名为lock的ReentrantLock对象
        ReentrantLock lock = new ReentrantLock();
        // 7.创建10个Task对象和10个用来执行这些任务的线程。调用run()方法来启动线程
        for (int i = 0; i < 10; i++) {
            Task task = new Task(lock);
            Thread thread = new Thread(task);
            thread.run();
        }
    }
}
// 8.将项目导出为一个.jar文件，命名为recipe8.jar。使用集成开发工具的菜单项或者用javac和jar命令来编译压缩应用程序
// 9.在Windows平台上通过运行findbugs.bat命令来启动FindBugs独立程序，在Linux平台上则通过执行findbugs.sh命令来启动
// 10.使用File菜单中的NewProject选项来创建一个新项目
// 11.FindBugs应用程序将显示一个窗口来配置项目。在ProjectName文本框中输入文本Recipe08
// 在Classpath for analysis文本框中加入jar文件，在Source directories文本框中添加例子源代码的目录
// 12.单击Analyze按钮创建新项目并分析它的代码
// 13.FindBugs应用程序显示了代码分析的结果。在本例中，它发现了两个Bug
// 14.单击一个Bug，将在右边栏区域看到Bug对应的源代码和屏幕底部对Bug的描述

// 分析检测到在程序中有下列两个潜在Bug
// ·一个在Task类的run()方法中。抛出InterruptedExeption异常时，任务不会释放锁，因为它执行不到unlock()方法。这可能会引起应用程序死锁
// ·另一个是Main类的main()方法。因为已经直接调用了一个线程的run()方法，但是没有调用start()方法开始执行线程
// 双击一个Bug，将看到Bug的详细信息
// 像已在项目配置中包含的源代码参考一样，将看到检测到的Bug对应的源代码

// 注意：FindBugs只能检测出一些问题(与并发代码相关或不相关)
// 例如，在Task类的run()方法中删除调用unlock()方法的代码，然后重新分析，FindBugs将不会警告已在任务中获取到锁但永远不会释放它
// 使用这个静态代码分析工具有助于提高代码的质量，但是不要期望它检测出代码中的所有Bug