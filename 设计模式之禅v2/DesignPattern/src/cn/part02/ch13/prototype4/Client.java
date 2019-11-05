package cn.part02.ch13.prototype4;

//代码清单13-9 简单的场景类
public class Client {
    public static void main(String[] args) {
        //产生一个对象
        Thing thing = new Thing();
        //拷贝一个对象
        Thing cloneThing = thing.clone();
    }
}

//构造函数被执行了...