package cn.part02.ch29.bridgeImpl.abstraction;

import cn.part02.ch29.bridgeImpl.implementor.House;

/**
 * Created by NKS on 2017/9/16.
 */
public class HouseCorp extends Corp {
    public HouseCorp(House house) {
        super(house);
    }

    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚大钱了...");
    }
}
