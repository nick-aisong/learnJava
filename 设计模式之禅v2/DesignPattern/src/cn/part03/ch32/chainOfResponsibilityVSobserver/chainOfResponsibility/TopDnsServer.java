package cn.part03.ch32.chainOfResponsibilityVSobserver.chainOfResponsibility;

//代码清单32-35 全球顶级DNS服务器
public class TopDnsServer extends DnsServer {
    protected Recorder echo(String domain) {
        Recorder recorder = super.echo(domain);
        recorder.setOwner("全球顶级DNS服务器");
        return recorder;
    }

    protected boolean isLocal(String domain) {
        //所有的域名最终的解析地点
        return true;
    }
}