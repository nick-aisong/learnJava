package cn.part04.ch36.observer_mediator;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

//代码清单36-5 事件的观察者
public class EventDispatch implements Observer {
    //单例模式
    private final static EventDispatch dispatch = new EventDispatch();
    //事件消费者
    private Vector<EventCustomer> customer = new Vector<EventCustomer>();

    //不允许生成新的实例
    private EventDispatch() {
    }

    //获得单例对象
    public static EventDispatch getEventDispatch() {
        return dispatch;
    }

    //事件触发
    public void update(Observable o, Object arg) {
        //事件的源头
        Product product = (Product) arg;
        //事件
        ProductEvent event = (ProductEvent) o;
        //处理者处理，这里是中介者模式的核心，可以是很复杂的业务逻辑
        for (EventCustomer e : customer) {
            //处理能力是否匹配
            for (EventCustomType t : e.getCustomType()) {
                if (t.getValue() == event.getEventType().getValue()) {
                    e.exec(event);
                }
            }
        }
    }

    //注册事件处理者
    public void registerCustomer(EventCustomer _customer) {
        customer.add(_customer);
    }
}

/*
刚刚我们说完了产品和事件的关系处理，现在回到我们事件的观察者，它承担着非常重
要的职责。我们知道它要处理事件，但是现在还没有想好怎么实现它处理事件的update方
法，暂时保持为空
*/

/*
我们在这里使用Vector来存储所有的事件处理者，在update方法中使用了两个简单的for
循环来完成业务逻辑的判断，只要事件的处理者级别和事件的类型相匹配，就调用事件处理
者的exec方法来处理事件，该逻辑是整个事件触发架构的关键点，但不是难点。请读者注
意，在设计这样的框架前，一定要定义好消费者与生产者之间的搭配问题，一般的做法是通
过xml文件类或者IoC容器配置规则，然后在框架启动时加载并驻留内存
*/