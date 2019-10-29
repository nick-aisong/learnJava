package cn.part02.ch11.builder;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client2 {
    public static void main(String[] args){
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");

        BenzBuilder benzBuilder = new BenzBuilder();
        benzBuilder.setSequence(sequence);
        BenzModel benz = (BenzModel)benzBuilder.getCarModel();
        benz.run();

        BMWBuilder bmwBuilder = new BMWBuilder();
        bmwBuilder.setSequence(sequence);
        BMWModel bmw = (BMWModel) bmwBuilder.getCarModel();
        bmw.run();
    }
}
