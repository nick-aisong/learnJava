package cn.part03.ch32.chainOfResponsibilityVSobserver.observer;

//代码清单32-38 上海DNS服务器
public class SHDnsServer extends DnsServer {
    protected void sign(Recorder recorder) {
        recorder.setOwner("上海DNS服务器");
    }

    //定义上海的DNS服务器能处理的级别
    protected boolean isLocal(Recorder recorder) {
        return recorder.getDomain().endsWith(".sh.cn");
    }
}