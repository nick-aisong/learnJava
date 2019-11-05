package cn.part02.ch13.prototype5;

public class Client {
    public static void main(String[] args) {
        //产生一个对象
        Thing thing = new Thing();
        //设置一个值
        thing.setValue("张三");
        //拷贝一个对象
        Thing cloneThing = thing.clone();
        cloneThing.setValue("李四");
        System.out.println(thing.getValue());
    }
}

//[张三, 李四]