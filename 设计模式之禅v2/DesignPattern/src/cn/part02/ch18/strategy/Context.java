package cn.part02.ch18.strategy;

public class Context {

    // 构造函数，你要使用哪个妙计
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    // 使用计谋，看我出招了
    public void operate() {
        this.strategy.operate();
    }
}
