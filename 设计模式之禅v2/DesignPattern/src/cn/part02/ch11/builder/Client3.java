package cn.part02.ch11.builder;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client3 {

    public static void main(String[] args) {

        Director director = new Director();

        for (int i = 0; i < 10; i++) {
            director.getABenzModel().run();
        }

        for (int i = 0; i < 10; i++) {
            director.getBBenzModel().run();
        }

        for (int i = 0; i < 10; i++) {
            director.getCBMWModel().run();
        }
    }
}
