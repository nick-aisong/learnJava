package cn.ch02.ch02_1;

// 5.实现一个ATM模拟类Bank它使用subtractAmount()方法对账户余额进行扣除
// 这个类实现Runnable接口以作为线程执行
public class Bank implements Runnable {
    // 6.为这个类增加账户类Account对象，用构造器初始化这个对象
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    // 7.实现run()方法。它将调用subtractAmount()方法对账户余额进行扣除，并循环执行100次
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.substractAmount(1000);
        }
    }
}
