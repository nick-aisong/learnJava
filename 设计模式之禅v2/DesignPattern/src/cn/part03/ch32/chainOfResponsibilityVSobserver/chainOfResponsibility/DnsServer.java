package cn.part03.ch32.chainOfResponsibilityVSobserver.chainOfResponsibility;

import java.util.Random;

//代码清单32-32 抽象域名服务器
public abstract class DnsServer {
    //上级DNS是谁
    private DnsServer upperServer;

    //解析域名
    public final Recorder resolve(String domain) {
        Recorder recorder = null;
        if (isLocal(domain)) {//是本服务器能解析的域名
            recorder = echo(domain);
        } else {//本服务器不能解析
            //提交上级DNS进行解析
            recorder = upperServer.resolve(domain);
        }
        return recorder;
    }

    //指向上级DNS
    public void setUpperServer(DnsServer _upperServer) {
        this.upperServer = _upperServer;
    }

    //每个DNS都有一个数据处理区（ZONE）,检查域名是否在本区中
    protected abstract boolean isLocal(String domain);

    //每个DNS服务器都必须实现解析任务
    protected Recorder echo(String domain) {
        Recorder recorder = new Recorder();
        //获得IP地址
        recorder.setIp(genIpAddress());
        recorder.setDomain(domain);
        return recorder;
    }

    //随机产生一个IP地址，工具类
    private String genIpAddress() {
        Random rand = new Random();
        String address = rand.nextInt(255) + "." + rand.nextInt(255) + "." + rand.nextInt(255) + "." + rand.nextInt(255);
        return address;
    }
}

//在该类中有一个方法——genIpAddress方法——没有在类图中展现出来，它用于实现随
//机生成IP地址，这是我们为模拟DNS解析场景而建立的一个虚拟方法，在实际的应用中是不
//可能出现的
