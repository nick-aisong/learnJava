package cn.part04.ch35.factory_strategy;

//代码清单35-6 扣款策略的封装
public class DeductionContext {
    //扣款策略
    private IDeduction deduction = null;

    //构造函数传递策略
    public DeductionContext(IDeduction _deduction) {
        this.deduction = _deduction;
    }

    //执行扣款
    public boolean exec(Card card, Trade trade) {
        return this.deduction.exec(card, trade);
    }
}

//典型的策略上下文角色。扣款模块的策略已经定义完毕了，然后需要想办法解决策略模
//式的缺陷：它把所有的策略类都暴露出去，暴露得越多以后的修改风险也就越大。怎么修改
//呢？增加一个映射配置文件，实现策略类的隐藏。我们使用枚举担当此任，对策略类进行映
//射处理，避免高层模块直接访问策略类，同时由工厂方法模式根据映射产生策略对象