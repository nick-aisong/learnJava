package cn.part02.ch22.observer5;

import java.util.Observable;

public class HanFeiZi extends Observable implements IHanFeiZi {
    //韩非子要吃饭了
    public void haveBreakfast() {
        System.out.println("韩非子：开始吃饭了...");
        //通知所有的观察者
        super.setChanged();
        super.notifyObservers("韩非子在吃饭");
    }

    //韩非子开始娱乐了
    public void haveFun() {
        System.out.println("韩非子：开始娱乐了...");
        super.setChanged();
        this.notifyObservers("韩非子在娱乐");
    }
}
