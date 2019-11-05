package cn.part02.ch13.prototype4;

//代码清单13-8 简单的可拷贝对象
public class Thing implements Cloneable {
    public Thing() {
        System.out.println("构造函数被执行了...");
    }

    @Override
    public Thing clone() {
        Thing thing = null;
        try {
            thing = (Thing) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }
}
