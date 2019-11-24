package cn.part02.ch29.bridge;

public abstract class Corp {


    protected abstract void produce();

    protected abstract void sell();

    public void makeMoney() {
        this.produce();
        this.sell();
    }
}
