package cn.part04.ch35.factory_strategy;

//代码清单35-8 策略工厂
public class StrategyFactory {
    //策略工厂
    public static IDeduction getDeduction(StrategyMan strategy) {
        IDeduction deduction = null;
        try {
            deduction = (IDeduction) Class.forName(strategy.getValue()).newInstance();
        } catch (Exception e) {
            // 异常处理
        }
        return deduction;
    }
}

//一个简单的工厂，根据策略管理类的枚举项创建一个策略对象，简单而实用，策略模式
//的缺陷也弥补成功。那这么复杂的系统怎么让高层模块访问？（你看不出复杂？那是因为我
//们写的都是快乐路径，太多情况都没有考虑，在实际项目中仅就并发处理和事务管理这两部
//分就够你头疼了。）既然系统很复杂，是不是需要封装一下。我们请出门面模式进行封装