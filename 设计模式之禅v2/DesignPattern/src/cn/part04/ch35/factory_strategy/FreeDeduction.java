package cn.part04.ch35.factory_strategy;

//代码清单35-5 扣款策略二
public class FreeDeduction implements IDeduction {
    //自由扣款
    public boolean exec(Card card, Trade trade) {
        //直接从自由余额中扣除
        card.setFreeMoney(card.getFreeMoney() - trade.getAmount());
        return true;
    }
}

//卡内的自由金额减去交易金额再修改卡内自由金额就完事了，异常情况不考虑。这两个
//具体的策略与我们的交易类型没有任何关系，也不应该有关系，策略模式就是提供两个可以
//相互替换的策略，至于在什么时候使用什么策略，则不是由策略模式来决定的