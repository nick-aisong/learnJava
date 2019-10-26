package cn.ch07.ch07_9;

// 15.实现一个名为Event的类，并实现Comparable接口，其泛型参数为Event类
public class Event implements Comparable<Event> {
    // 16.声明一个名为thread的私有String属性来存储创建事件的线程的名字
    private String thread;
    // 17.声明一个名为priority的私有int属性来存储事件的优先级
    private int priority;

    // 18.实现类构造器来初始化它的属性
    public Event(String thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    // 19.实现getThread()方法来返回线程thread属性的值
    public String getThread() {
        return thread;
    }

    // 20.实现getPriority()方法来返回优先级priority属性的值
    public int getPriority() {
        return priority;
    }

    // 21.实现compareTo()方法。该方法用来比较当前事件和传入事件
    // 如果当前事件比传入事件的优先级值大，则返回-1
    // 如果当前事件比传入事件的优先级值小，则返回1
    // 如果两个事件有相同的优先级值，则返回0
    // 如此便得到按优先级倒序排列的一个列表，有更高优先级的事件将被存储在队列的头部
    @Override
    public int compareTo(Event e) {
        if (this.priority > e.getPriority()) {
            return -1;
        } else if (this.priority < e.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}
