package cn.part05.ch37.mvc;

import java.util.Observable;

//代码清单37-9 文件监听者
public class FileWatcher extends Observable implements Watchable {
    //是否要重新加载XML文件
    private boolean isReload = false;

    //启动监视
    public void watch() {
        //启动一个线程，每隔15秒扫描一下文件，发现文件日期被修改，立刻通知观察者
        super.addObserver(new Checker());
        super.setChanged();
        super.notifyObservers(isReload);
    }
}

/*
由于框架是在操作系统之上运行的，文件变化时操作系统是不会通知应用系统的，因此
我们能做的就是启动一个线程监视一批文件，发现文件改变了，立刻通知相关的处理者，它
虽然有时间延迟，但对于一个应用框架来说是非常有必要的，避免了重启应用才能使配置生
效的情况

读者可能很疑惑，这种死循环的监控方式会不会对性能产生影响，答案是不会！为什么呢？

检查一个文件的时间一般是毫秒级的，相对于我们设置的运行周期（比如15秒执行一
次）是一个非常微小的运行时间，对应用不会产生任何影响。大家都在使用Log4j进行日志
处理，它有一个线程是每5秒检查一次日志是否满，大家觉得性能受影响了吗？基本上性能
影响可以忽略不计
*/