package cn.nks.observerImpl3;

import java.util.Observable;

public class HanFeiZi extends Observable {

    public void haveBreakfast() {
        super.setChanged();
        System.out.println("韩非子:开始吃饭了...");
        this.notifyObservers("韩非子在吃饭");
    }

    public void haveFun() {
        super.setChanged();
        System.out.println("韩非子:开始娱乐了...");
        this.notifyObservers("韩非子在娱乐");
    }

}
