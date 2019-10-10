package cn.nks.factoryMethod;

import cn.nks.factoryMethod.Human;

/**
 * Created by NKS on 2017/9/13.
 */
public class BlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑色人种大笑");
    }

    @Override
    public void cry() {
        System.out.println("黑色人种哭");
    }

    @Override
    public void talk() {
        System.out.println("黑色人种说话");
    }
}
