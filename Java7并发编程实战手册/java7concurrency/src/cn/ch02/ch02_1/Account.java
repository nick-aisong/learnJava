package cn.ch02.ch02_1;

// 1.创建名为Account的账号类，它是银行账户的模型，只有一个双精度浮点型属性balance
public class Account {
    private double balance;

    // 2.实现setBalance()和getBalance()方法来写入和读取余额balance的值
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // 3.实现addAmount()方法。它会将传入的数量加入到余额balance中，并且在同一时间只允许一个线程改变这个值
    // 所以我们使用synchronized关键字将这个方法标记成临界区
    public synchronized void addAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        balance = tmp;
    }

    // 4.实现subtractAmount()方法。它将传入的数量从余额中扣除，并且在同一时间只允许一个线程改变这个值
    // 所以我们使用synchronized关键字将这个方法标记成临界区
    public synchronized void substractAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= amount;
        balance = tmp;
    }
}
