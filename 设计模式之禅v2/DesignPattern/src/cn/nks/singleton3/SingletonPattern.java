package cn.nks.singleton3;

// 通用单例模式
// 解决线程安全问题
public class SingletonPattern {
    private static final SingletonPattern singletonPattern = new SingletonPattern();

    private SingletonPattern(){

    }

    public synchronized static SingletonPattern getInstance(){
        return singletonPattern;
    }
}
