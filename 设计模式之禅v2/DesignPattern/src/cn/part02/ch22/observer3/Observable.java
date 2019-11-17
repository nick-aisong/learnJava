package cn.part02.ch22.observer3;

//代码清单22-9 被观察者接口
public interface Observable {
    //增加一个观察者
    public void addObserver(Observer observer);

    //删除一个观察者
    public void deleteObserver(Observer observer);

    //既然要观察，我发生改变了他也应该有所动作，通知观察者
    public void notifyObservers(String context);
}
