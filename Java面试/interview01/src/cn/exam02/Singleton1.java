package cn.exam02;

/*
 * 饿汉式:
 * 在类初始化的时候直接创建实例对象，不管你是否需要这个对象都会创建
 *
 * (1)构造器私有化
 * (2)自行创建，并且用静态变量保存
 * (3)向外提供这个实例
 * (4)强调这是一个单例，我们可以用final修改
 *
 * JDK1.5
 */
public class Singleton1 {

    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){
    }

    // 如果外部只需要调用这个类的静态方法，也会new这个单例类，如果用不到，有些浪费资源
    public static void test(){

    }
}
