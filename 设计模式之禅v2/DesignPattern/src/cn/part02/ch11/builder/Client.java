package cn.part02.ch11.builder;
//代码清单11-4 奔驰模型代码

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        /*
        * 客户告诉XX公司，我要这样一个模型，然后XX公司就告诉我老大
        * 说要这样一个模型，这样一个顺序，然后我就来制造
        */
        BenzModel benz = new BenzModel();
        //存放run的顺序
        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom"); //客户要求，run的时候先发动引擎
        sequence.add("start"); //启动起来
        sequence.add("stop"); //开了一段就停下来
        //我们把这个顺序赋予奔驰车
        benz.setSequence(sequence);
        benz.run();
    }
}

//奔驰车的引擎是这个声音的...
//奔驰车跑起来是这个样子的...
//奔驰车应该这样停车...