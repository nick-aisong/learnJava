package cn.part02.ch22.observer3;

//代码清单22-11 观察者接口
public interface Observer {
    //一发现别人有动静，自己也要行动起来
    public void update(String context);
}
