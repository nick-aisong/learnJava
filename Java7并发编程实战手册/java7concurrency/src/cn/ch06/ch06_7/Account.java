package cn.ch06.ch06_7;

import java.util.concurrent.atomic.AtomicLong;

// 1.创建名为Account的类来模拟银行帐户
public class Account {
    // 2.声明一个私有AtomicLong属性balance来存放帐户余额
    private AtomicLong balance;

    // 3.实现类构建器来初始化属性
    public Account() {
        this.balance = new AtomicLong();
    }

    // 4.实现一个名为getBalance()的方法，返回balance属性的值
    public long getBalance() {
        return balance.get();
    }

    // 5.实现一个名为setBalance()的方法来设置balance属性的值
    public void setBalance(long amount) {
        this.balance.set(amount);
    }

    // 6.实现一个名为addAmount()的方法来增加balance属性的值
    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
    }

    // 7.实现一个名为substractAmount()的方法来减少balance属性的值
    public void substractAmount(long amount) {
        this.balance.getAndAdd(-amount);
    }
}
