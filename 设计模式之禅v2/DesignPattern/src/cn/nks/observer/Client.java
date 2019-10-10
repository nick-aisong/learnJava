package cn.nks.observer;

/**
 * Created by NKS on 2017/9/19.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        LiSi liSi = new LiSi();
        HanFeiZi hanFeiZi = new HanFeiZi();

        Watch watchBreakfast = new Watch(hanFeiZi, liSi, "breakfast");
        watchBreakfast.start();

        Watch watchFun = new Watch(hanFeiZi, liSi, "haveFun");
        watchFun.start();

        Thread.sleep(1000);
        hanFeiZi.haveBreakfast();

        Thread.sleep(1000);
        hanFeiZi.haveFun();
    }
}
