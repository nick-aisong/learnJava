package cn.ch02.ch02_1;

// 我们有一个银行账号和两个线程，一个线程将转钱到账户中，另一线程将从账户中取钱
// 如果方法不同步，账户钱数可能不正确。而同步机制则能确保账户的最终余额是正确的

// 11.实现范例的主程序，创建一个带有main()方法的主类Main
public class Main {

    public static void main(String[] args) {
        //12.创建一个账户类Account对象并设置初始值为1000
        Account account = new Account();
        account.setBalance(1000);

        //13.创建一个公司类Company对象，并用它作为传入参数创建线程
        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        //14.创建一个ATM模拟类Bank对象，并用它作为传入参数创建线程
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        // 15.将初始余额打印到控制台，并启动这两个线程。
        System.out.printf("Account : Initial Balance: %f\n", account.getBalance());
        companyThread.start();
        bankThread.start();

        // 16.使用join()方法等待这两个线程运行完成，然后打印账户的最终余额到控制台
        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// synchronized关键字的使用，保证了在并发程序中对共享数据的正确访问

// 一个对象的方法采用synchronized关键字进行声明，只能被一个线程访问
// 如果线程A正在执行一个同步方法syncMethodA()， 线程B要执行这个对象
// 的其他同步方法syncMethodB()，线程B将被阻塞直到线程A访问完
// 但如果线程B访问的是同一个类的不同对象，那么两个线程都不会被阻塞

// synchronized关键字会降低应用程序的性能，因此只能在并发情景中需要修改共享数据的方法上使用它
// 如果多个线程访问同一个synchronized方法，则只有一个线程可以访问，其他线程将等待
// 如果方法声明没有使用synchronized关键字，所有的线程都能在同一时间执行这个方法，因而总运行时间将降低
// 如果已知一个方法不会被一个以上线程调用，则无需使用synchronized关键字声明之

// 可以递归调用被synchronized声明的方法

// 我们可以通过synchronized关键字来保护代码块(而不是整个方法)的访问
// 应该这样利用synchronized关键字:方法的其余部分保持在synchronized代码块之外，以获取更好的性能
// 临界区（即同一时间只能被一个线程访问的代码块）的访问应该尽可能的短

// 例如在获取一幢楼人数的操作中，我们只使用synchronized关键字来保护对人数更新的指令，并让其他操作不使用共享数据
// 当这样使用synchronized关键字时，必须把对象引用作为传入参数。同一时间只有一个线程被允许访问这个synchronized代码
// 通常来说，我们使用this关键字来引用正在执行的方法所属的对象
// synchronized(this) {
// // Java code
// }

// Account : Initial Balance: 1000.000000
// Account : Final Balance: 1000.000000