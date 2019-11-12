package cn.part02.ch20.iterator3;

import java.util.Vector;

//代码清单20-12 具体容器
public class ConcreteAggregate implements Aggregate {
    //容纳对象的容器
    private Vector vector = new Vector();

    //增加一个元素
    public void add(Object object) {
        this.vector.add(object);
    }

    //返回迭代器对象
    public Iterator iterator() {
        return new ConcreteIterator(this.vector);
    }

    //删除一个元素
    public void remove(Object object) {
        this.remove(object);
    }
}