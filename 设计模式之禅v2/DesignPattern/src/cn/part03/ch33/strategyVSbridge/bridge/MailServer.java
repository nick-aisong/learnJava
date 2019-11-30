package cn.part03.ch33.strategyVSbridge.bridge;

//代码清单33-7 邮件服务器
public abstract class MailServer {
    //发送的是哪封邮件
    protected final MailTemplate m;

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

//该类相对于策略模式的环境角色有两个改变：

//修改为抽象类。为什么要修改成抽象类？因为我们在设计一个架构，邮件服务器是一
//个具体的、可实例化的对象吗？“给我一台邮件服务器”能实现吗？不能，只能说“给我一台
//Postfix邮件服务器”，这才能实现，必须有一个明确的可指向对象

//变量m修改为Protected访问权限，方便子类调用