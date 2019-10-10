package cn.nks.facade;

/**
 * Created by NKS on 2017/9/15.
 */
public class LetterProcessImpl implements LetterProcess {
    @Override
    public void writeContext(String context) {
        System.out.println("填写信的内容...." + context);
    }

    @Override
    public void fillEnvelope(String address) {
        System.out.println("填写收件人地址及姓名...." + address);
    }

    @Override
    public void letterInotoEnvelope() {
        System.out.println("把信放到信封中....");
    }

    @Override
    public void sendLetter() {
        System.out.println("邮递信件...");
    }
}
