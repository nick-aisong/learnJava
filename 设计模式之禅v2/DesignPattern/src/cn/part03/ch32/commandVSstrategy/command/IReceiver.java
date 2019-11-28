package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-11 抽象接收者
public interface IReceiver {
    //压缩
    public boolean compress(String source, String to);

    //解压缩
    public boolean uncompress(String source, String to);
}
