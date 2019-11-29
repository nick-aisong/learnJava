package cn.part03.ch32.chainOfResponsibilityVSobserver.observer;

//代码清单32-39 中国顶级DNS服务器
public class ChinaTopDnsServer extends DnsServer {
    protected void sign(Recorder recorder) {
        recorder.setOwner("中国顶级DNS服务器");
    }

    protected boolean isLocal(Recorder recorder) {
        return recorder.getDomain().endsWith(".cn");
    }
}