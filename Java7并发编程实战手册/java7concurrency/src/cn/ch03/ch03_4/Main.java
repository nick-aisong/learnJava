package cn.ch03.ch03_4;

import java.util.concurrent.CyclicBarrier;

// 在数字矩阵中寻找一个数字(使用分治编程技术)
// 这个矩阵会被分成几个子集，然后每个线程在一个子集中查找。一旦所有线程都完成查找，最终的任务将统一这些结果

// 32.实现范例的主类Main并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 33.声明和初始化5个常量。它们将作为这个应用程序的参数
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;
        // 34.创建矩阵类MatrixMock对象mock。它有10000行，每行有1000个数字，而这里要查找的是数字5
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);
        // 35.创建结果类Results对象results。它有10000个元素
        Results results = new Results(ROWS);
        // 36.创建Grouper对象grouper
        Grouper grouper = new Grouper(results);
        // 37.创建CyclicBarrier类对象barrier。这个对象将等待5个线程运行结束然后，它将执行创建的Grouper线程对象
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);
        // 38.创建5个查找类Searcher对象，将它们分别作为传入参数创建线程并启动
        Searcher[] searchers = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock,
                    results, SEARCH, barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}

// CyclicBarrier类还提供了另一种await()方法:
// await(long time, TimeUnit unit)
// 这个方法被调用后，线程将一直休眠到被中断，或者CyclicBarrier的内部计数器到达0，或者指定的时间已经过期
// 第二个参数是TimeUnit类型，它是一个常量枚举类型，它的值包含: DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

// CyclicBarrier类还提供了getNumberWaiting()方法和getParties()方法，前者将返回在await()上阻塞的线程的数目，后者返回被CyclicBarrier对象同步的任务数

// 重置CyclicBarrier对象
// 虽然CyclicBarrier类和CountDownLatch类有很多共性，但是它们也有一定的差异
// 其中最重要的不同是，CyclicBarrier对象可以被重置回初始状态，并把它的内部计数器重置成初始化时的值

// CyclicBarrier对象的重置，是通过CyclicBarrier类提供的reset()方法完成的
// 当重置发生后，在await()方法中等待的线程将收到一个BrokenBarrierException异常
// 本例是将这个异常打印出来，但是在更复杂的应用程序中，它可以用来执行其他的操作，比如重新执行或者将操作复原回被中断时的状态

// 损坏的CyclicBarrier对象
// CyclicBarrier对象有一种特殊的状态即损坏状态(Broken)。 当很多线程在await()方法上等待的时候，如果其中一个线程被中断，这个线程将抛出InterruptedException异常，其他的等待线程将抛出BrokenBarrierException异常，于是CyclicBarrier对象就处于损
// 坏状态了
// CyclicBarrier类提供了isBroken()方法，如果处于损坏状态就返回true，否则返回false

// Mock: There are 1000125 ocurrences of number 5 in generated data.
// Main: The main thread has finished.
// Thread-0: Processing lines from 0 to 2000.
// Thread-0: Lines processed.
// Thread-1: Processing lines from 2000 to 4000.
// Thread-2: Processing lines from 4000 to 6000.
// Thread-1: Lines processed.
// Thread-3: Processing lines from 6000 to 8000.
// Thread-2: Lines processed.
// Thread-4: Processing lines from 8000 to 10000.
// Thread-3: Lines processed.
// Thread-4: Lines processed.
// Grouper: Processing results...
// Grouper: Total result: 1000125.