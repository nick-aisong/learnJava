package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-13 gzip接收者
public class GzipReceiver implements IReceiver {
    //gzip的压缩算法
    public boolean compress(String source, String to) {
        System.out.println(source + " --> " + to + " GZIP压缩成功!");
        return true;
    }

    //gzip解压缩算法
    public boolean uncompress(String source, String to) {
        System.out.println(source + " --> " + to + " GZIP解压缩成功!");
        return true;
    }
}