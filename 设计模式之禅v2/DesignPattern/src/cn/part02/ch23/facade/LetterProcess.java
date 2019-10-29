package cn.part02.ch23.facade;

/**
 * Created by NKS on 2017/9/15.
 */
public interface LetterProcess {

    public void writeContext(String context);

    public void fillEnvelope(String address);

    public void letterInotoEnvelope();

    public void sendLetter();
}
