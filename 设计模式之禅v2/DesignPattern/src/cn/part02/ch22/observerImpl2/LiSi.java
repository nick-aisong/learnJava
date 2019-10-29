package cn.part02.ch22.observerImpl2;

/**
 * Created by NKS on 2017/9/19.
 */
public class LiSi implements Observer {

    @Override
    public void update(String str) {
        System.out.println("李斯：观察到李斯活动，开始向老板汇报了...");
        this.reportToQiShiHuang(str);
        System.out.println("李斯：汇报完毕，秦老板赏给他两个萝卜吃吃...\n");
    }

    private void reportToQiShiHuang(String reportContext) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了--->" + reportContext);
    }
}
