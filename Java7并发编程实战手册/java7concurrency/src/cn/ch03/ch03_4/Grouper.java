package cn.ch03.ch03_4;

// 25.实现一个类来计算在矩阵中查找到的总次数。计算是基于Results对象的，后者存放了矩阵中每行查找到的次数
// 创建Grouper类并指定它实现Runnable接口
public class Grouper implements Runnable {

    // 26.声明私有结果类Results的results对象
    private Results results;

    // 27.实现构造器并初始化结果类对象
    public Grouper(Results results) {
        this.results = results;
    }

    // 28.实现run()方法，用来计算在结果类数组中查找的次数
    @Override
    public void run() {
        // 29.声明int变量finalResult，并将开始统计的信息打印到控制台
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        // 30.使用results对象的getData()方法获得存放每行发生次数的数组。然后，处理这个数组并将结果累加到finalResult变量
        int[] data = results.getData();
        for (int number : data) {
            finalResult += number;
        }
        // 31.将结果打印到控制台
        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
