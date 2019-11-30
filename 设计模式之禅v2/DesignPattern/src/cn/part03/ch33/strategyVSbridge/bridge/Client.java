package cn.part03.ch33.strategyVSbridge.bridge;

//代码清单33-10 场景类
public class Client {
    public static void main(String[] args) {
        //创建一封TEXT格式的邮件
        MailTemplate m = new HtmlMail("a@a.com", "b@b.com", "外星人攻击地球了", " 结局是外星人被地球人打败了！");
        //使用Postfix发送邮件
        MailServer mail = new Postfix(m);
        //发送邮件
        mail.sendMail();
    }
}