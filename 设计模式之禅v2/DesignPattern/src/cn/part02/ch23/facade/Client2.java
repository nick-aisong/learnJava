package cn.part02.ch23.facade;

/**
 * Created by NKS on 2017/9/15.
 */
public class Client2 {

    public static void main(String[] args) {

        ModenPostOffice hellRoadPostOffice = new ModenPostOffice();

        String address = "Happy Road No. 666,God Province,Heaven";
        String context = "Hello,It's me,do you know who I am? I'm your old lover. I'd like to....";
        hellRoadPostOffice.sendLetter(context, address);
    }
}
