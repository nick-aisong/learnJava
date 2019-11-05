package cn.part02.ch22.observerImpl2;

/**
 * Created by NKS on 2017/9/19.
 */
public class Client {

    public static void main(String[] args) {
        Observer liSi = new LiSi();
        Observer wangSi = new WangSi();
        Observer liuSi = new LiuSi();

        HanFeiZi hanFeiZi = new HanFeiZi();

        hanFeiZi.addObserver(liSi);
        hanFeiZi.addObserver(wangSi);
        hanFeiZi.addObserver(liuSi);

        hanFeiZi.haveBreakfast();

    }

}
