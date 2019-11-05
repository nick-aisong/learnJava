package cn.part02.ch29.bridgeImpl;

import cn.part02.ch29.bridgeImpl.abstraction.HouseCorp;
import cn.part02.ch29.bridgeImpl.abstraction.ShanZhaiCorp;
import cn.part02.ch29.bridgeImpl.implementor.House;
import cn.part02.ch29.bridgeImpl.implementor.IPod;

/**
 * Created by NKS on 2017/9/16.
 */
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
