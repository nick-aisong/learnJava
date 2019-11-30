package cn.part03.ch33.strategyVSbridge.bridge;

//代码清单33-9 SendMail邮件服务器
public class SendMail extends MailServer {
    //传递一封邮件
    public SendMail(MailTemplate _m) {
        super(_m);
    }

    //修正邮件发送程序
    public void sendMail() {
        //增加邮件服务器信息
        super.m.add("Received: (sendmail); 7 Nov 2009 04:14:44 +0100");
        super.sendMail();
    }
}