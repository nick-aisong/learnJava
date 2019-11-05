package cn.part02.ch13.prototype3;

//代码清单13-7 原型模式通用源码
public class PrototypeClass implements Cloneable {
    //覆写父类Object方法
    @Override
    public PrototypeClass clone() {
        PrototypeClass prototypeClass = null;
        try {
            prototypeClass = (PrototypeClass) super.clone();
        } catch (CloneNotSupportedException e) {
            //异常处理
        }
        return prototypeClass;
    }
}

//实现一个接口，然后重写clone方法，就完成了原型模式