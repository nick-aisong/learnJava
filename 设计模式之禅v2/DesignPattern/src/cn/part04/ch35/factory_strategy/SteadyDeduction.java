package cn.part04.ch35.factory_strategy;

//代码清单35-4 扣款策略一
public class SteadyDeduction implements IDeduction {
    //固定性交易扣款
    public boolean exec(Card card, Trade trade) {
        //固定金额和自由金额各扣除50%
        int halfMoney = (int) Math.rint(trade.getAmount() / 2.0);
        card.setFreeMoney(card.getFreeMoney() - halfMoney);
        card.setSteadyMoney(card.getSteadyMoney() - halfMoney);
        return true;
    }
}

//这个具体策略也非常简单，就是两个金额各自减去交易额的一半（注意除数是2.0，可
//不是2），然后再四舍五入，算法确实简单。该逻辑没有考虑账户余额不足的情况，也没有
//考虑异常情况，比如并发情况，读者可以想想看，一张卡有两笔消费同时发生时，是不是就
//发生错误了？一张卡同时有两笔消费会出现这种情况吗？会的，网络阻塞的情况，MQ多通
//道发送，在网络繁忙的情况下是有可能出现该问题，这里就不多介绍，有兴趣的读者可以看
//看MQ的资料。我们在这里的讲解实现的是一个快乐路径，认为所有的交易都是在安全可靠
//的环境中发生的，并且所有的系统环境都满足我们的要求