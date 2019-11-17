package cn.part02.ch22.observer3;

//代码清单22-14 场景类
public class Client {
    public static void main(String[] args) {
        //三个观察者产生出来
        Observer liSi = new LiSi();
        Observer wangSi = new WangSi();
        Observer liuSi = new LiuSi();
        //定义出韩非子
        HanFeiZi hanFeiZi = new HanFeiZi();
        //我们后人根据历史，描述这个场景，有三个人在观察韩非子
        hanFeiZi.addObserver(liSi);
        hanFeiZi.addObserver(wangSi);
        hanFeiZi.addObserver(liuSi);
        //然后这里我们看看韩非子在干什么
        hanFeiZi.haveBreakfast();
    }
}

//韩非子:开始吃饭了...
//李斯：观察到韩非子活动，开始向老板汇报了...
//李斯：报告，秦老板！韩非子有活动了-->韩非子在吃饭
//李斯：汇报完毕...
//
//王斯：观察到韩非子活动，自己也开始活动了...
//王斯：因为韩非子在吃饭，--所以我悲伤呀！
//王斯：哭死了...
//
//刘斯：观察到韩非子活动，开始动作了...
//刘斯：因为韩非子在吃饭,--所以我快乐呀！
//刘斯：乐死了