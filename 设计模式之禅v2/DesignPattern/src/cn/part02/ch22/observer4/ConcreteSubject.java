package cn.part02.ch22.observer4;

//代码清单22-16 具体被观察者
public class ConcreteSubject extends Subject {
    //具体的业务
    public void doSomething() {
        /*
        * do something
        */
        super.notifyObservers();
    }
}