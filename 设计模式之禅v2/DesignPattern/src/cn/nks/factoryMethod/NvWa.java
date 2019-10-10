package cn.nks.factoryMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by NKS on 2017/9/13.
 */
public class NvWa {

    public static void main(String[] agrs) {

        //女娲第一次造人，试验性质，少造点，火候不足，缺陷产品
        System.out.println("------------造出的第一批人是这样的：白人-----------------");
        Human whiteHhuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHhuman.cry();
        whiteHhuman.laugh();
        whiteHhuman.talk();

        //女娲第二次造人，火候加足点，然后又出了个次品，黑人
        System.out.println("\n\n------------造出的第二批人是这样的：黑人 -----------------");
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.laugh();
        blackHuman.talk();

        //第三批人了，这次火候掌握的正好，黄色人种（不写黄人，免得引起歧义），备注：RB人不属于此列
        System.out.println("\n\n------------造出的第三批人是这样的：黄色人种-----------------");
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.cry();
        yellowHuman.laugh();
        yellowHuman.talk();

        for (int i = 0; i < 100; i++) {
            System.out.println("\n\n------------随机产生人种了-----------------" + i);
            Human human = HumanFactory.createHuman();
            human.cry();
            human.laugh();
            human.talk();
        }

        List<Class> list = new ArrayList<Class>();
        list.add(YellowHuman.class);
        list.add(BlackHuman.class);
        list.add(WhiteHuman.class);
        Random random = new Random();

        for(int i=0; i<100; i++){
            int rand = random.nextInt(list.size());
            Class c = list.get(rand);
            Human human2 = HumanFactory.createHuman2(c);
        }
    }

}
