package cn.part02.ch22.observer2;

//代码清单22-4 韩非子
public class LiSi implements ILiSi {
    //首先李斯是个观察者，一旦韩非子有活动，他就知道，他就要向老板汇报
    public void update(String str) {
        System.out.println("李斯: 观察到韩非子活动，开始向老板汇报了...");
        this.reportToQinShiHuang(str);
        System.out.println("李斯：汇报完毕...\n");
        // 如果不调用flush()，System.out会有缓存，后面缓存可能被覆盖掉，导致console输出出现信息丢失
        System.out.flush();
    }

    //汇报给秦始皇
    private void reportToQinShiHuang(String reportContext) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了--->" + reportContext);
    }
}
