package cn.part02.ch16.chainOfResponsibility;

//代码清单16-5 丈夫类
public class Husband implements IHandler {
    //妻子向丈夫请示
    public void HandleMessage(IWomen women) {
        System.out.println("妻子的请示是：" + women.getRequest());
        System.out.println("丈夫的答复是：同意");
    }
}
