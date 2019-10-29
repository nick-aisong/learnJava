package cn.part02.ch16.chainOfResponsibility;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by NKS on 2017/9/20.
 */
public class Client {

    public static void main(String[] args) {

        Random rand = new Random();
        ArrayList<IWomen> arrayList = new ArrayList<IWomen>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Women(rand.nextInt(4), "我要出去逛街"));
        }

        IHandler father = new Father();
        IHandler husband = new Husband();
        IHandler son = new Son();

        for (IWomen women : arrayList) {
            if (women.getType() == 1) {
                System.out.println("\n--------女儿向父亲请示-------");
                father.HandleMessage(women);
            } else if (women.getType() == 2) {
                System.out.println("\n--------妻子向丈夫请示-------");
                husband.HandleMessage(women);
            } else if (women.getType() == 3) {
                System.out.println("\n--------母亲向儿子请示-------");
                son.HandleMessage(women);
            } else {
                //暂时啥也不做
            }
        }

    }
}
