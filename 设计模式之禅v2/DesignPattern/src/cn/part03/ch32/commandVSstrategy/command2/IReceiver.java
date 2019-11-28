package cn.part03.ch32.commandVSstrategy.command2;

//代码清单32-14 依照职责设计的接收者接口
public interface IReceiver {
    //执行zip命令
    public boolean zipExec(String source, String to);

    //执行gzip命令
    public boolean gzipExec(String source, String to);
}

//接收者接口只是定义了每个接收者都必须完成zip和gzip相关的两个逻辑，有多少个职责就有多少个实现类
//我们这里只有两个职责：压缩和解压缩