package cn.part03.ch33.strategyVSbridge.bridge;

//代码清单33-3 超文本邮件
public class HtmlMail extends MailTemplate {
    public HtmlMail(String _from, String _to, String _subject, String _context) {
        super(_from, _to, _subject, _context);
    }

    public String getContext() {
        //超文本类型设置邮件的格式为：multipart/mixed
        String context = "\nContent-Type: multipart/mixed; charset= GB2312\n" + super.getContext();
        //同时对邮件进行HTML检查，是否有类似未关闭的标签
        context = context + "\n邮件格式为：超文本格式";
        return context;
    }
}