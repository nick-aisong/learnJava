package cn.part04.ch35.factory_strategy;

//代码清单35-7 策略枚举
public enum StrategyMan {
    SteadyDeduction("cn.part04.ch35.factory_strategy.SteadyDeduction"),
    FreeDeduction("cn.part04.ch35.factory_strategy.FreeDeduction");
    String value = "";

    private StrategyMan(String _value) {
        this.value = _value;
    }

    public String getValue() {
        return this.value;
    }
}
