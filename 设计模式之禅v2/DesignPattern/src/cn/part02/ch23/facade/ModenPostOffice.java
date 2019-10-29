package cn.part02.ch23.facade;

/**
 * Created by NKS on 2017/9/15.
 */
public class ModenPostOffice {

    private LetterProcess letterProcess = new LetterProcessImpl();
    private Police letterPolice = new Police();

    public void sendLetter(String context, String address){
        letterProcess.writeContext(context);
        letterProcess.fillEnvelope(address);

        letterPolice.checkLetter(letterProcess);

        letterProcess.letterInotoEnvelope();
        letterProcess.sendLetter();
    }
}
