package cn.part02.ch13.prototype;

import java.util.Random;

/**
 * Created by NKS on 2017/9/25.
 */
public class Client {

    private static int MAX_COUNT = 6;

    public static void main(String[] args) {

        int i = 0;

        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("XX银行版权所有");
        while (i < MAX_COUNT) {
            mail.setAppellation(getRandString(5) + " 先生（女士）");
            mail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");

            sendMail(mail);
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
