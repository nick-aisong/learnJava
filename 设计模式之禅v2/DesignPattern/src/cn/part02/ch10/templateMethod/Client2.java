package cn.part02.ch10.templateMethod;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client2 {

    public static void main(String[] args){
        HummerH1Model h1 = new HummerH1Model();
        h1.setAlarm(true);
        h1.run();
    }
}
