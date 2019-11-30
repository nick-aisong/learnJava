package cn.part03.ch33.strategyVSbridge.strategy;

//代码清单33-5 场景类
public class Client {
    public static void main(String[] args) {
        //创建一封TEXT格式的邮件
        MailTemplate m = new HtmlMail("a@a.com", "b@b.com", "外星人攻击地球了", "结局是外星人被地球人打败了！");
        //创建一个Mail发送程序
        MailServer mail = new MailServer(m);
        //发送邮件
        mail.sendMail();
    }
}

//当然，如果想产生一封文本格式的邮件，只要稍稍修改一下场景类就可以了：new
//HtmlMail修改为new TextMail，读者可以自行实现，非常简单。在该场景中，我们使用策略
//模式实现两种算法的自由切换，它提供了这样的保证：封装邮件的两种行为是可选择的，至
//于选择哪个算法是由上层模块决定的。策略模式要完成的任务就是提供两种可以替换的算法