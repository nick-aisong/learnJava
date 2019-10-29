package cn.part02.ch22.observerImpl3;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by NKS on 2017/9/19.
 */
public class LiuSi implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("刘斯：观察到韩非子活动，开始动作了...");
        this.happy(arg.toString());
        System.out.println("刘斯：真被乐死了\n");
    }

    private void happy(String context) {
        System.out.println("刘斯：因为" + context + ",——所以我快乐呀！");
    }
}
