package cn.part02.ch22.observer4;

//代码清单22-18 具体观察者
public class ConcreteObserver implements Observer {
    //实现更新方法
    public void update() {
        System.out.println("接收到信息，并进行处理！");
    }
}
