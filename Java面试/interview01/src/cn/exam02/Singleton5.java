package cn.exam02;

public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {
    }

    // 线程不安全，对于单一线程是可以的
    public static Singleton5 getInstance() {
        if (instance == null) { // 为了提高性能，省的每次进入synchronized
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
