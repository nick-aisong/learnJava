package cn.part03.ch32.chainOfResponsibilityVSobserver.chainOfResponsibility;

//代码清单32-33 上海DNS服务器
public class SHDnsServer extends DnsServer {
    protected Recorder echo(String domain) {
        Recorder recorder = super.echo(domain);
        recorder.setOwner("上海DNS服务器");
        return recorder;
    }

    //定义上海的DNS服务器能处理的级别
    protected boolean isLocal(String domain) {
        return domain.endsWith(".sh.cn");
    }
}

//为什么要覆写echo方法？各具体的DNS服务器实现自己的解析过程，属于个性化处理，
//它代表的是每个DNS服务器的不同处理逻辑。还要注意一下，我们在这里做了一个简化处
//理，所有以".sh.cn"结尾的域名都由上海DNS服务器解析。其他的中国顶级DNS和全球顶级
//DNS实现过程类似