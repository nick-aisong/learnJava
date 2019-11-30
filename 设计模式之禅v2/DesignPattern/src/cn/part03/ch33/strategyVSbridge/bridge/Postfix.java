package cn.part03.ch33.strategyVSbridge.bridge;

//代码清单33-8 Postfix邮件服务器
public class Postfix extends MailServer {
    public Postfix(MailTemplate _m) {
        super(_m);
    }

    //修正邮件发送程序
    public void sendMail() {
        //增加邮件服务器信息
        String context = "Received: from XXXX (unknown [xxx.xxx.xxx.xxx]) by aaa.aaa.com (Postfix) with ESMTP id 8DBCD172B8\n";
        super.m.add(context);
        super.sendMail();
    }
}

//为什么要覆写sendMail程序呢？这是因为每个邮件服务器在发送邮件时都会在邮件内容
//上留下自己的标志，一是广告作用，二是为了互联网上统计需要，三是方便同质软件的共振