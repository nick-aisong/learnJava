package cn.nks.facade;

/**
 * Created by NKS on 2017/9/15.
 */
public class Client {

    public static void main(String[] args){
        LetterProcess letterProcess = new LetterProcessImpl();
        letterProcess.writeContext("Hello,It's me,do you know who I am? I'm your old lover. I'd like to....");
        letterProcess.fillEnvelope("Happy Road No. 666,God Province,Heaven");
        letterProcess.letterInotoEnvelope();
        letterProcess.sendLetter();
    }
}
