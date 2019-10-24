package cn.ch06.ch06_7;
// 本节将要学习如何使用原子变量实现一个银行帐号和两个不同的任务：
// 一个加钱到帐号上，另一个从帐号上取钱。在例子的实现中使用了AtomicLong类

// 16.创建名为Main的类，并添加main()方法来实现主类

// 使用原子变量
public class Main {

    public static void main(String[] args) {
        // 17.创建一个Account对象，设置它的账户余额为1000
        Account account = new Account();
        account.setBalance(1000);
        // 18.创建一个新的Company任务和一个执行它的线程。再创建一个新的Bank任务和一个执行它的线程
        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);
        // 19.在控制台上输出账户的初始化余额
        System.out.printf("Account : Initial Balance: %d\n", account.getBalance());
        // 20.开始执行线程
        companyThread.start();
        bankThread.start();
        // 21.使用join()方法等待所有线程执行完成，将帐号的最后余额打印到控制台
        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %d\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 本例的核心在Account类中。该类声明了名为balance的AtomicLong变量来存放帐户余额，然后实现对余额的存和取操作
// 为了获得余额，我们实现了getBalance()方法，它使用了AtomicLong类的get()方法
// 为了设置余额，我们实现了setBalance()方法，它使用了AtomicLong类的set()方法
// 为了增加余额，我们实现了addAmount()方法，它使用了AtomicLong类的getAndAdd()方法，这个方法返回增加指定参数值后的余额值
// 最后，为了减少余额，我们实现了subtractAmount()方法，它也使用了AtomicLong类的getAndAdd()方法

// 接下来，实现两个不同的任务类
// ·Company类模拟一家公司，它将增加账户余额。这个类将执行10次，每次将余额增加1,000
// ·Bank类模拟一家银行,它将从账户中取钱。这个类执行10次,每次将余额减少1,000

// 在Main类中，创建Account对象余额为1,000。然后执行一个 bank线程和一个company线程，执行完成后，最终账户余额与初始余额应该相同

// 在简介中提到过，Java还提供了其他原子类，AtomicBoolean、AtomicInteger和AtomicReference是原子类的其他实现类

// Account : Initial Balance: 1000
// Account : Final Balance: 1000