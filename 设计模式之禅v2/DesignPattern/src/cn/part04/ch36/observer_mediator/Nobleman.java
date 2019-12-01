package cn.part04.ch36.observer_mediator;

//代码清单36-12 贵族
public class Nobleman extends EventCustomer {
    //定义贵族能够处理的事件的级别
    public Nobleman() {
        super(EventCustomType.EDIT);
        super.addCustomType(EventCustomType.CLONE);
    }

    @Override
    public void exec(ProductEvent event) {
        //事件的源头
        Product p = event.getSource();
        //事件类型
        ProductEventType type = event.getEventType();
        if (type.getValue() == EventCustomType.CLONE.getValue()) {
            System.out.println("贵族处理事件:" + p.getName() + "克隆,事件类型=" + type);
        } else {
            System.out.println("贵族处理事件:" + p.getName() + "修改,事件类型=" + type);
        }
    }
}

/*
贵族稍有不同，它有两个处理能力，能够处理修改事件和克隆事件，同时在exec方法中
对这两类事件分别进行处理。此时，读者可能会想到另外一个处理模式：责任链模式。建立
一个链，然后两类事件分别在链上进行处理并反馈结果。读者可以参考一下Servlet的过滤器
（Filter）的设计，在框架平台的开发中可以采用该模式，它具有非常好的扩展性和稳定性
*/