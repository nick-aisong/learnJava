package cn.nks.abstractFactory;

public abstract class AbstractYellowHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黄色人种会笑");
    }

    @Override
    public void cry() {
        System.out.println("黄色人种会哭");
    }

    @Override
    public void talk() {
        System.out.println("黄色人种会说话");
    }

}
