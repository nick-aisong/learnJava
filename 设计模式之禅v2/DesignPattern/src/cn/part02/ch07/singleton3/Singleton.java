package cn.part02.ch07.singleton3;

//懒汉式单例
//延迟加载，但是每次要获得锁，性能开销比较大
public class Singleton {
    private static Singleton singleton = null;

    //限制产生多个对象
    private Singleton() {
    }

    //通过该方法获得实例对象
    public static synchronized Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}