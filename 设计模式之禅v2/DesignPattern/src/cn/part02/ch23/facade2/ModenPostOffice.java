package cn.part02.ch23.facade2;

//代码清单23-4 现代化邮局
public class ModenPostOffice {
    private ILetterProcess letterProcess = new LetterProcessImpl();

    //写信，封装，投递，一体化
    public void sendLetter(String context, String address) {
        //帮你写信
        letterProcess.writeContext(context);
        //写好信封
        letterProcess.fillEnvelope(address);
        //把信放到信封中
        letterProcess.letterInotoEnvelope();
        //邮递信件
        letterProcess.sendLetter();
    }
}
