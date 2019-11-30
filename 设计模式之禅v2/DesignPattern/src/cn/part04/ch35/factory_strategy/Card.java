package cn.part04.ch35.factory_strategy;

//代码清单35-1 IC卡类
public class Card {
    //IC卡号码
    private String cardNo = "";
    //卡内的固定交易金额
    private int steadyMoney = 0;
    //卡内自由交易金额
    private int freeMoney = 0;

    //getter/setter方法
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getSteadyMoney() {
        return steadyMoney;
    }

    public void setSteadyMoney(int steadyMoney) {
        this.steadyMoney = steadyMoney;
    }

    public int getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(int freeMoney) {
        this.freeMoney = freeMoney;
    }
}

//细心的读者可能注意到，金额怎么都是整数类型呀，应该是double类型或者BigDecimal
//类型呀。是，一般非银行的交易系统，比如超市的收银系统，系统内都是存放的int类型，在
//显示的时候才转换为货币类型