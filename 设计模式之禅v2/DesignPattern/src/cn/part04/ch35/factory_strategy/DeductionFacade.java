package cn.part04.ch35.factory_strategy;

//代码清单35-9 扣款模块封装
public class DeductionFacade {
    //对外公布的扣款信息
    public static Card deduct(Card card, Trade trade) {
        //获得消费策略
        StrategyMan reg = getDeductionType(trade);
        //初始化一个消费策略对象
        IDeduction deduction = StrategyFactory.getDeduction(reg);
        //产生一个策略上下文
        DeductionContext context = new DeductionContext(deduction);
        //进行扣款处理
        context.exec(card, trade);
        //返回扣款处理完毕后的数据
        return card;
    }

    //获得对应的商户消费策略
    private static StrategyMan getDeductionType(Trade trade) {
        //模拟操作
        if (trade.getTradeNo().contains("abc")) {
            return StrategyMan.FreeDeduction;
        } else {
            return StrategyMan.SteadyDeduction;
        }
    }
}

//这次为什么要先展示代码而后写类图呢？那是因为这段代码比写类图更能让你理解。读
//者注意一下getDeductionType方法，这个方法在实际项目中是存在的，但是与上面的写法有
//天壤之别，因为在实际项目中，数据库中保存了策略代码与交易编码的对应关系，直接通过
//数据库的SQL语句就可以返回对应的扣款策略。这里我们采用大家最熟悉的条件转移来实
//现，也是比较清晰和容易理解的

//可能读者要问了，在门面模式中已经明确地说明，门面类中不允许有业务逻辑存在，但
//是你这里还是有了一个getDeductionType方法，它可代表的是一个判断逻辑呀，这是为什么
//呢？是的，该方法完全可以移到其他Hepler类中，由于我们是示例代码，暂没有明确的业务
//含义，故编写在此处，读者在实际应用中，请把该方法放置到其他类中