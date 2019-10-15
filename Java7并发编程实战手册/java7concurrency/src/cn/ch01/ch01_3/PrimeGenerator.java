package cn.ch01.ch01_3;

// 1.创建一个名为PrimeGenerator的类，并继承Thread类
public class PrimeGenerator extends Thread {

    // 2.覆盖run()方法，并在方法体内包含一个无限循环。在每次循环中，我们将处理从1开始的连续数
    // 对每个数字，我们将计算它是不是一个质数，如果是的话就打印到控制台
    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            // 3.一个数字处理完后，调用isInterrupted()方法来检查线程是否被中断
            // 如果isInterrupted()返回值是true，就写一个信息并且结束线程的执行
            if (isInterrupted()) {
                System.out.printf("The Prime Generator has been Interrupted\n");
                return;
            }
            number++;
        }
    }

    // 4.实现isPrime()方法
    // isPrime()方法返回的 是一个布尔值，如果接收到的参数是一个质数就返回true，否则就返回false
    private boolean isPrime(long number) {
        if (number < 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }
}
