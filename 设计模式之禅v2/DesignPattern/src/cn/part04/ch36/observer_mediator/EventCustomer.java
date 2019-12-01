package cn.part04.ch36.observer_mediator;

import java.util.Vector;

//代码清单36-8 抽象的事件处理者
public abstract class EventCustomer {
    //容纳每个消费者能够处理的级别
    private Vector<EventCustomType> customType = new Vector<EventCustomType>();

    //每个消费者都要声明自己处理哪一类别的事件
    public EventCustomer(EventCustomType _type) {
        addCustomType(_type);
    }

    //每个消费者可以消费多个事件
    public void addCustomType(EventCustomType _type) {
        customType.add(_type);
    }

    //得到自己的处理能力
    public Vector<EventCustomType> getCustomType() {
        return customType;
    }

    //每个事件都要对事件进行声明式消费
    public abstract void exec(ProductEvent event);
}

/*
EventCustomer抽象类负责定义事件处理者必须具有的行为，首先是每一个事件的处理者
都必须定义自己能够处理的级别，也就是通过构造函数来定义自己的处理能力，当然处理能
力可以是多值的，也就是说一个处理者可以处理多个事件；然后各个事件的处理者只要实现
exec方法就可以了，完成自己对事件的消费处理即可
*/