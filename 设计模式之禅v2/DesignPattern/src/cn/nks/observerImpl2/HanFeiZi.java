package cn.nks.observerImpl2;

import java.util.ArrayList;


/**
 * Created by NKS on 2017/9/19.
 */
public class HanFeiZi implements Observable {

    private ArrayList<Observer> observersList = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        this.observersList.add(observer);
    }

    public void deleteObserver(Observer observer) {
        this.observersList.remove(observer);
    }

    public void notifyObservers(String context) {
        for (Observer observer : observersList) {
            observer.update(context);
        }
    }

    public void haveBreakfast() {
        System.out.println("韩非子:开始吃饭了...");
        this.notifyObservers("韩非子在吃饭");
    }

    public void haveFun() {
        System.out.println("韩非子:开始娱乐了...");
        this.notifyObservers("韩非子在娱乐");
    }

}
