package cn.part03.ch33.strategyVSbridge.strategy;

//代码清单33-1 抽象邮件
public abstract class MailTemplate {
    //邮件发件人
    private String from;
    //收件人
    private String to;
    //邮件标题
    private String subject;
    //邮件内容
    private String context;

    //通过构造函数传递邮件信息
    public MailTemplate(String _from, String _to, String _subject, String _context) {
        this.from = _from;
        this.to = _to;
        this.subject = _subject;
        this.context = _context;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContext(String context) {
        this.context = context;
    }

    //邮件都有内容
    public String getContext() {
        return context;
    }
}

//很奇怪，是吗？抽象类没有抽象的方法，设置为抽象类还有什么意义呢？有意义，在这
//里我们定义了一个这样的抽象类：它具有邮件的所有属性，但不是一个具体可以被实例化的
//对象。例如，你对邮件服务器说“给我制造一封邮件”，邮件服务器肯定拒绝，为什么？你要
//产生什么邮件？什么格式的？邮件对邮件服务器来说是一个抽象表示，是一个可描述但不可
//形象化的事物。你可以这样说：“我要一封标题为XX，发件人是XXX的文本格式的邮件”，
//这就是一个可实例化的对象，因此我们的设计就产生了两个子类以具体化邮件，而且每种邮
//件格式对邮件的内容都有不同的处理