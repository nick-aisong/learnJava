package cn.part04.ch35.factory_strategy;

//代码清单35-2 交易类
public class Trade {
    //交易编号
    private String tradeNo = "";
    //交易金额
    private int amount = 0;

    //getter/setter方法
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String postNo) {
        this.tradeNo = postNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

//交易信息Trade类，负责记录每一笔交易，它是由监听程序监听MQ队列而产生的，有两
//个属性：交易编号和交易金额，其中的交易编号对整个交易非常重要，18位字符（在银行的
//交易系统中，这里可不是字符串，一般是十进制数字或二进制数字，要考虑系统的性能，数
//字运算可比字符运算快得多），包括POS机编号、商户编号、校验码等，我们这里暂时用不
//到，就不多做介绍，我们只要知道它是一个非常有用的编码就成。交易金额为整数类型，实
//际金额放大100倍即可