package cn.part02.ch29.bridge;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class Corp {


    protected abstract void produce();

    protected abstract void sell();

    public void makeMoney() {
        this.produce();
        this.sell();
    }
}
