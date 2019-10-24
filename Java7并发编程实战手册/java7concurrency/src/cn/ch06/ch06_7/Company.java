package cn.ch06.ch06_7;

// 8.创建一个名为Company的类并实现Runnable接口。这个类模拟公司的付款
public class Company implements Runnable {
    // 9.声明一个私有Account属性account
    private Account account;

    // 10.实现构造器初始化属性
    public Company(Account account) {
        this.account = account;
    }

    // 11. 实现任务的run()法。使用account的addAmount()方法增加10次1,000到帐户余额上
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.addAmount(1000);
        }
    }
}
