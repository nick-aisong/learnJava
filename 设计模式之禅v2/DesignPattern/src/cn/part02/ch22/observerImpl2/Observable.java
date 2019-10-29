package cn.part02.ch22.observerImpl2;

/**
 * Created by NKS on 2017/9/19.
 */
public interface Observable {

    public void addObserver(Observer observer);

    public void deleteObserver(Observer observer);

    public void notifyObservers(String context);

}
