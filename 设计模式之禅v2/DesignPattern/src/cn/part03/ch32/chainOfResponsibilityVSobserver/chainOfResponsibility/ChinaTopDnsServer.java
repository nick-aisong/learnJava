package cn.part03.ch32.chainOfResponsibilityVSobserver.chainOfResponsibility;

//代码清单32-34 中国顶级DNS服务器
public class ChinaTopDnsServer extends DnsServer {
    protected Recorder echo(String domain) {
        Recorder recorder = super.echo(domain);
        recorder.setOwner("中国顶级DNS服务器");
        return recorder;
    }

    protected boolean isLocal(String domain) {
        return domain.endsWith(".cn");
    }
}