package cn.nks.singleton2;


// 通用单例模式
// 有线程安全问题
public class SingletonPattern {

    private static SingletonPattern singletonPattern = null;

    private SingletonPattern() {

    }

    public SingletonPattern getInstance() {
        if (this.singletonPattern == null) {
            this.singletonPattern = new SingletonPattern();
        }
        return this.singletonPattern;
    }
}
