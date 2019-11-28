package cn.part03.ch32.commandVSstrategy.command2;

//代码清单32-15 压缩接收者
public class CompressReceiver implements IReceiver {
    //执行gzip压缩命令
    public boolean gzipExec(String source, String to) {
        System.out.println(source + " --> " + to + " GZIP压缩成功!");
        return true;
    }

    //执行zip压缩命令
    public boolean zipExec(String source, String to) {
        System.out.println(source + " --> " + to + " ZIP压缩成功!");
        return true;
    }
}