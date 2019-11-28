package cn.part03.ch32.commandVSstrategy.strategy;

//代码清单32-1 抽象压缩算法
public interface Algorithm {
    //压缩算法
    public boolean compress(String source, String to);

    //解压缩算法
    public boolean uncompress(String source, String to);
}