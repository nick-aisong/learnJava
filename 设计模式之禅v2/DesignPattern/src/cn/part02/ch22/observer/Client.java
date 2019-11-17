package cn.part02.ch22.observer;

//代码清单22-6 场景类
public class Client {
    public static void main(String[] args) throws InterruptedException {
        //定义出韩非子和李斯
        LiSi liSi = new LiSi();
        HanFeiZi hanFeiZi = new HanFeiZi();
        //观察早餐
        Spy watchBreakfast = new Spy(hanFeiZi,liSi,"breakfast");
        //开始启动线程，监控
        watchBreakfast.start();
        //观察娱乐情况
        Spy watchFun = new Spy(hanFeiZi,liSi,"fun");
        watchFun.start();
        //然后我们看看韩非子在干什么
        Thread.sleep(1000); //主线程等待1秒后后再往下执行
        hanFeiZi.haveBreakfast();
        //韩非子娱乐了
        Thread.sleep(1000);
        hanFeiZi.haveFun();
    }
}

//韩非子:开始吃饭了...
//李斯: 观察到韩非子活动，开始向老板汇报了...
//李斯：报告，秦老板！韩非子有活动了--->韩非子在吃饭
//李斯：汇报完毕...
//
//韩非子:开始娱乐了...
//李斯: 观察到韩非子活动，开始向老板汇报了...
//李斯：报告，秦老板！韩非子有活动了--->韩非子在娱乐
//李斯：汇报完毕...