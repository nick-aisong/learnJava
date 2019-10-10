package cn.nks.bridgeImpl;

import cn.nks.bridgeImpl.abstraction.HouseCorp;
import cn.nks.bridgeImpl.abstraction.ShanZhaiCorp;
import cn.nks.bridgeImpl.implementor.House;
import cn.nks.bridgeImpl.implementor.IPod;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client {

    public static void main(String[] args){
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
