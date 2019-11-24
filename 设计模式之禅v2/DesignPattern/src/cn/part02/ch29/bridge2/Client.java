package cn.part02.ch29.bridge2;

import cn.part02.ch29.bridge2.abstraction.HouseCorp;
import cn.part02.ch29.bridge2.abstraction.ShanZhaiCorp;
import cn.part02.ch29.bridge2.implementor.House;
import cn.part02.ch29.bridge2.implementor.IPod;

public class Client {

    public static void main(String[] args) {
        House house = new House();
        System.out.println("-------房地产公司是这个样子运行的-------");
        HouseCorp houseCorp = new HouseCorp(house);
        houseCorp.makeMoney();
        System.out.println("\n");

        System.out.println("-------山寨公司是这样运行的-------");
        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new IPod());
        shanZhaiCorp.makeMoney();

    }
}
