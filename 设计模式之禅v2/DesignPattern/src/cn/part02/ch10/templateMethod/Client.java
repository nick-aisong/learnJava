package cn.part02.ch10.templateMethod;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client {

    public static void main(String[] agrs){
        HummerModel h1 = new HummerH1Model();
        h1.run();

        HummerModel h2 = new HummerH2Model();
        h2.run();
    }
}
