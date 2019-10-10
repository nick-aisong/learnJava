package cn.nks.factoryMethod;

/**
 * Created by NKS on 2017/9/13.
 */
public class YellowHuman implements Human {

    @Override
    public void laugh() {
        System.out.println("黄色人种大笑");
    }

    @Override
    public void cry() {
        System.out.println("黄色人种哭");
    }

    @Override
    public void talk() {
        System.out.println("黄色人种说话");
    }
}
