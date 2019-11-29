package cn.part03.ch32.chainOfResponsibilityVSobserver.observer;

//代码清单32-40 全球顶级DNS服务器
public class TopDnsServer extends DnsServer {
    protected void sign(Recorder recorder) {
        recorder.setOwner("全球顶级DNS服务器");
    }

    protected boolean isLocal(Recorder recorder) {
        //所有的域名最终的解析地点
        return true;
    }
}