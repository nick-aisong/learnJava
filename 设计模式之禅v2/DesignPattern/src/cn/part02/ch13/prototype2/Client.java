package cn.part02.ch13.prototype2;

import java.util.Random;

//代码清单13-5 修改后的场景类
public class Client {
    //发送账单的数量，这个值是从数据库中获得
    private static int MAX_COUNT = 6;

    public static void main(String[] args) {
        //模拟发送邮件
        int i = 0;
        //把模板定义出来，这个是从数据中获得
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("XX银行版权所有");
        while (i < MAX_COUNT) {
            //以下是每封邮件不同的地方
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation(getRandString(5) + " 先生（女士）");
            cloneMail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
            //然后发送邮件
            sendMail(cloneMail);
            i++;
        }
    }

    public static void sendMail(Mail mail) {
        System.out.println(("标题：" + mail.getSubject() + "\t收件人： " + mail.getReceiver() + "\t....发送成功！"));
    }

    public static String getRandString(int maxLength) {

        String source = "abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < maxLength; i++) {
            sb.append(source.charAt(rand.nextInt(source.length())));
        }
        return sb.toString();
    }
}

//标题：XX银行国庆信用卡抽奖活动	收件人： bKiJO@cOuImdxY.com	....发送成功！
//标题：XX银行国庆信用卡抽奖活动	收件人： Tqwor@kNOfYNIq.com	....发送成功！
//标题：XX银行国庆信用卡抽奖活动	收件人： oigHM@dkugEDpT.com	....发送成功！
//标题：XX银行国庆信用卡抽奖活动	收件人： LjXhZ@lAYyuggP.com	....发送成功！
//标题：XX银行国庆信用卡抽奖活动	收件人： LljoM@FEgSVqDT.com	....发送成功！
//标题：XX银行国庆信用卡抽奖活动	收件人： xUhAv@hLoPkgsy.com	....发送成功！
