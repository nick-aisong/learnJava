package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-6 抽象压缩命令
public abstract class AbstractCmd {
    //对接收者的引用
    protected IReceiver zip = new ZipReceiver();
    protected IReceiver gzip = new GzipReceiver();

    //抽象方法，命令的具体单元
    public abstract boolean execute(String source, String to);
}