package cn.nks.abstractFactory;


public abstract class AbstractWhiteHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("白色人种大笑");
    }

    @Override
    public void cry() {
        System.out.println("白色人种哭");
    }

    @Override
    public void talk() {
        System.out.println("白色人种说话");
    }

}
