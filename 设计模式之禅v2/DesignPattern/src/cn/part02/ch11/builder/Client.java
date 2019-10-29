package cn.part02.ch11.builder;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client {

    public static void main(String[] args){
        BenzModel benz = new BenzModel();
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");

        benz.setSequence(sequence);
        benz.run();
    }
}
