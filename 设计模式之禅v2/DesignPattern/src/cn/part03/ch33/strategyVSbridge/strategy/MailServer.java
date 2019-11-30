package cn.part03.ch33.strategyVSbridge.strategy;

//代码清单33-4 邮件服务器
public class MailServer {
    //发送的是哪封邮件
    private MailTemplate m;

    public MailServer(MailTemplate _m) {
        this.m = _m;
    }

    //发送邮件
    public void sendMail() {
        System.out.println("====正在发送的邮件信息====");
        //发件人
        System.out.println("发件人：" + m.getFrom());
        //收件人
        System.out.println("收件人：" + m.getTo());
        //标题
        System.out.println("邮件标题：" + m.getSubject());
        //邮件内容
        System.out.println("邮件内容：" + m.getContext());
    }
}

//很简单，邮件服务器接收了一封邮件，然后调用自己的发送程序进行发送。可能读者要
//问了，为什么不把sendMail方法移植到邮件模板类中呢？这也是邮件模板类的一个行为，邮
//件可以被发送。是的，这确实是邮件的一个行为，完全可以这样做，两者没有什么区别，只
//是从不同的角度看待该方法而已