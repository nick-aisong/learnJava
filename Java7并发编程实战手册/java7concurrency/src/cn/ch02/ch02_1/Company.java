package cn.ch02.ch02_1;

// 8.实现公司模拟类Company。它使用addAmount()对账户的余额进行充值
// 这个类实现Runnable接口以作为线程运行
public class Company implements Runnable {
    // 9.为Company类增加账户类Account对象，用构造器初始化这个对象
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    // 10.实现run(方法。它将调用addAmount()方法对账户余额进行充值，并循环执行100次
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.addAmount(1000);
        }
    }
}
