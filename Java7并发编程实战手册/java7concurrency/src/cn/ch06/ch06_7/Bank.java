package cn.ch06.ch06_7;

// 12.创建名为Bank的类并实现Runnable接口。这个类模拟从帐户中取钱
public class Bank implements Runnable {
    // 13.声明一个私有Account属性account
    private Account account;

    // 14.实现构造器，初始化属性
    public Bank(Account account) {
        this.account = account;
    }

    // 15.执行任务的run()方法。使用account的subtractAmount()方法执行10 次从账户上减少1000
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.substractAmount(1000);
        }
    }
}
