package cn.ch03.ch03_4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 13.到此为止我们实现了辅助类，现在来实现线程。首先，实现查找类Searcher
// 它在随机数矩阵指定的行中查找某个数。创建Searcher类并实现Runnable接口
public class Searcher implements Runnable {

    // 14.声明两个名为firstRow和lastRow的私有int属性。这两个属性将决定查找的子集范围
    private int firstRow;
    private int lastRow;
    // 15.声明私有MatrixMock属性mock
    private MatrixMock mock;
    // 16.声明私有Results属性results
    private Results results;
    // 17.声明私有int属性number，用于存放要查找的数字
    private int number;
    // 18.声明CyclicBarrier类对象barrier
    private final CyclicBarrier barrier;

    // 19.实现构造器，初始化刚刚声明的属性
    public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
        this.barrier = barrier;
    }

    // 20.实现run()方法，它将查找数字。它使用内部变量counter来存放每行查找到的次数
    @Override
    public void run() {
        int counter;
        // 21.将查找的范围打印到控制台
        System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
        // 22.在要查找的所有的行中进行查找。对每一行查找指定的数字，并将查找到的次数保存到对应的results对象的相应位置
        for (int i = firstRow; i < lastRow; i++) {
            int[] row = mock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number) {
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        // 23.将线程查找完成的信息打印到控制台
        System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());
        // 24.调用CyclicBarrier对象的await()方法，并捕获及处理方法抛出的异常InterruptedException和BrokenBarrierException
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
