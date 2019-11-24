package cn.part02.ch29.bridge3;

import cn.part02.ch29.bridge3.abstraction.HouseCorp;
import cn.part02.ch29.bridge3.abstraction.ShanZhaiCorp;
import cn.part02.ch29.bridge3.implementor.Clothes;
import cn.part02.ch29.bridge3.implementor.House;
import cn.part02.ch29.bridge3.implementor.IPod;

public class Client {
    public static void main(String[] args) {
        House house = new House();
        System.out.println("-------房地产公司是这样运行的-------");
        //先找到房地产公司
        HouseCorp houseCorp = new HouseCorp(house);
        //看我怎么挣钱
        houseCorp.makeMoney();
        System.out.println("\n");
        //山寨公司生产的产品很多，不过我只要指定产品就成了
        System.out.println("-------山寨公司是这样运行的-------");
        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new IPod());
        shanZhaiCorp.makeMoney();

        //山寨公司生产的产品很多，不过我只要指定产品就成了
        System.out.println("-------山寨公司是这样运行的-------");
        ShanZhaiCorp shanZhaiCorp2 = new ShanZhaiCorp(new Clothes());
        shanZhaiCorp2.makeMoney();
    }
}

//-------房地产公司是这样运行的-------
//生产出的房子是这样的...
//生产出的房子卖出去了...
//房地产公司赚大钱了...
//
//
//-------山寨公司是这样运行的-------
//生产出的iPod是这样的...
//生产出的iPod卖出去了...
//我赚钱呀...
//-------山寨公司是这样运行的-------
//生产出的衣服是这样的...
//生产出的衣服卖出去了...
//我赚钱呀...