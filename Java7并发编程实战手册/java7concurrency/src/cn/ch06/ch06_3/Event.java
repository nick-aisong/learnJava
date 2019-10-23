package cn.ch06.ch06_3;

// 1.创建名为Event的类并实现Comparable接口，指定Comparable接口的泛型参数是Event类
public class Event implements Comparable<Event> {
    // 2.声明一个私有int属性，名为thread，来存放创建了event的线程数
    private int thread;
    // 3.声明一个私有int属性，名为priority, 来存放event的优先级
    private int priority;

    // 4.实现类的构造器来初始化属性
    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    // 5.实现getThread()方法，返回thread属性
    public int getThread() {
        return thread;
    }

    // 6.实现getPriority()方法，返回priority属性
    public int getPriority() {
        return priority;
    }

    // 7.实现compareTo()方法。它接收Event作为参数，然后比较当前event与作为参数接收的event的优先级
    // 如果当前event优先级更大，它将返回-1
    // 如果优先级相等，它将返回0
    // 如果当前event优先级更小，它将返回1
    // 注意：这里的实现与大多数Comparator.compareTo()实现相反
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
