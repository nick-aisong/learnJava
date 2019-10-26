package cn.ch07.ch07_7;

import java.util.concurrent.ForkJoinPool;
// 在本例，我们将学习如何通过继承ForkJoinTask类来实现Fork/Join框架下的定制任务
// 这个任务实现计算自身的执行时间并在控制台输出，所以我们能够控制它的进一步演变
// 也可以实现自己的Fork/Join任务来输出日志信息，并获得任务中使用到的资源，或对任务结果做进一步的处理

// 15.创建名为Main的主类，并实现main()方法

// 定制运行在Fork / Join框架中的任务
public class Main {

    public static void main(String[] args) {
        // 16.创建一个有10,000元素的int数组
        int[] array = new int[10000];
        // 17.创建一个名为pool的ForkJoinPool对象
        ForkJoinPool pool = new ForkJoinPool();
        // 18.创建一个Task对象对数组所有元素值加1
        // 构造器参数Task作为任务名，array对象，0和10000来表明这个任务要处理整个数组
        Task task = new Task("Task", array, 0, array.length);
        // 19.使用invoke()方法传递任务到pool
        pool.invoke(task);
        // 20.使用shutdown()方法关闭pool
        pool.shutdown();
        // 21.在控制台输出一条消息表明程序结束
        System.out.printf("Main: End of the program.\n");
    }
}

// 在本节，我们继承ForkJoinTask类而实现了MyWorkerTask类
// 这是我们的任务基类，它能在ForkJoinPool中执行，能使用执行器提供的所有优点(如工作窃取算法)
// 这个类与RecursiveAction和RecursiveTask类相当
// 当继承ForkJoinTask类时，必须实现下列三个方法
// ·setRawResult()：被用来设置任务的结果。当任务不返回任何结果时，方法为空
// ·getRawResult()：被用来获取任务的结果。当任务不返回任何结果时，方法必须返回null值
// ·execO()： 实现任务的逻辑。本例将逻辑委托到了抽象方法compute()中(跟RecursiveAction和RecursiveTask类一样)
// 在exec()方法中测量这个方法执行的时间，并输出到控制台

// 最后，在本例的main主类中，创建了有10,000个元素的数组、一个ForkJoinPool执行器和一个Task对象，用来处理整个数组
// 运行程序，会发现不同的任务分别在控制台输出了各自的执行时间

// MyWorkerTask: Task1111111 : 51 Milliseconds to complete.
// MyWorkerTask: Task2211111 : 51 Milliseconds to complete.
// MyWorkerTask: Task1211111 : 51 Milliseconds to complete.
// MyWorkerTask: Task2111111 : 51 Milliseconds to complete.
// MyWorkerTask: Task2111112 : 56 Milliseconds to complete.
// MyWorkerTask: Task1211112 : 56 Milliseconds to complete.
// MyWorkerTask: Task2211112 : 57 Milliseconds to complete.
// MyWorkerTask: Task1111112 : 57 Milliseconds to complete.
// MyWorkerTask: Task211111 : 194 Milliseconds to complete.
// MyWorkerTask: Task121111 : 194 Milliseconds to complete.
// MyWorkerTask: Task221111 : 194 Milliseconds to complete.
// MyWorkerTask: Task111111 : 194 Milliseconds to complete.
// MyWorkerTask: Task2111121 : 53 Milliseconds to complete.
// MyWorkerTask: Task1211121 : 53 Milliseconds to complete.
// MyWorkerTask: Task2211121 : 53 Milliseconds to complete.
// MyWorkerTask: Task1111121 : 56 Milliseconds to complete.
// MyWorkerTask: Task2111122 : 51 Milliseconds to complete.
// MyWorkerTask: Task1211122 : 51 Milliseconds to complete.
// MyWorkerTask: Task2211122 : 51 Milliseconds to complete.
// MyWorkerTask: Task1111122 : 51 Milliseconds to complete.
// MyWorkerTask: Task121112 : 154 Milliseconds to complete.
// MyWorkerTask: Task211112 : 154 Milliseconds to complete.
// MyWorkerTask: Task221112 : 157 Milliseconds to complete.
// MyWorkerTask: Task111112 : 157 Milliseconds to complete.
// MyWorkerTask: Task21111 : 399 Milliseconds to complete.
// MyWorkerTask: Task12111 : 399 Milliseconds to complete.
// MyWorkerTask: Task11111 : 404 Milliseconds to complete.
// MyWorkerTask: Task22111 : 404 Milliseconds to complete.
// MyWorkerTask: Task1211211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2111211 : 51 Milliseconds to complete.
// MyWorkerTask: Task1111211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2211211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2111212 : 51 Milliseconds to complete.
// MyWorkerTask: Task1211212 : 51 Milliseconds to complete.
// MyWorkerTask: Task1111212 : 51 Milliseconds to complete.
// MyWorkerTask: Task2211212 : 51 Milliseconds to complete.
// MyWorkerTask: Task121121 : 153 Milliseconds to complete.
// MyWorkerTask: Task211121 : 153 Milliseconds to complete.
// MyWorkerTask: Task111121 : 154 Milliseconds to complete.
// MyWorkerTask: Task221121 : 154 Milliseconds to complete.
// MyWorkerTask: Task2111221 : 52 Milliseconds to complete.
// MyWorkerTask: Task1211221 : 52 Milliseconds to complete.
// MyWorkerTask: Task1111221 : 51 Milliseconds to complete.
// MyWorkerTask: Task2211221 : 51 Milliseconds to complete.
// MyWorkerTask: Task1211222 : 51 Milliseconds to complete.
// MyWorkerTask: Task2111222 : 51 Milliseconds to complete.
// MyWorkerTask: Task1111222 : 51 Milliseconds to complete.
// MyWorkerTask: Task2211222 : 51 Milliseconds to complete.
// MyWorkerTask: Task211122 : 155 Milliseconds to complete.
// MyWorkerTask: Task121122 : 156 Milliseconds to complete.
// MyWorkerTask: Task111122 : 152 Milliseconds to complete.
// MyWorkerTask: Task221122 : 152 Milliseconds to complete.
// MyWorkerTask: Task12112 : 360 Milliseconds to complete.
// MyWorkerTask: Task21112 : 360 Milliseconds to complete.
// MyWorkerTask: Task11112 : 357 Milliseconds to complete.
// MyWorkerTask: Task22112 : 357 Milliseconds to complete.
// MyWorkerTask: Task1211 : 811 Milliseconds to complete.
// MyWorkerTask: Task2111 : 811 Milliseconds to complete.
// MyWorkerTask: Task1111 : 812 Milliseconds to complete.
// MyWorkerTask: Task2211 : 813 Milliseconds to complete.
// MyWorkerTask: Task2112111 : 51 Milliseconds to complete.
// MyWorkerTask: Task1212111 : 51 Milliseconds to complete.
// MyWorkerTask: Task1112111 : 51 Milliseconds to complete.
// MyWorkerTask: Task2212111 : 51 Milliseconds to complete.
// MyWorkerTask: Task1212112 : 51 Milliseconds to complete.
// MyWorkerTask: Task2112112 : 51 Milliseconds to complete.
// MyWorkerTask: Task1112112 : 52 Milliseconds to complete.
// MyWorkerTask: Task2212112 : 51 Milliseconds to complete.
// MyWorkerTask: Task211211 : 153 Milliseconds to complete.
// MyWorkerTask: Task121211 : 159 Milliseconds to complete.
// MyWorkerTask: Task221211 : 158 Milliseconds to complete.
// MyWorkerTask: Task111211 : 159 Milliseconds to complete.
// MyWorkerTask: Task1212121 : 52 Milliseconds to complete.
// MyWorkerTask: Task1112121 : 52 Milliseconds to complete.
// MyWorkerTask: Task2212121 : 52 Milliseconds to complete.
// MyWorkerTask: Task2112121 : 51 Milliseconds to complete.
// MyWorkerTask: Task2212122 : 50 Milliseconds to complete.
// MyWorkerTask: Task1112122 : 54 Milliseconds to complete.
// MyWorkerTask: Task1212122 : 55 Milliseconds to complete.
// MyWorkerTask: Task2112122 : 54 Milliseconds to complete.
// MyWorkerTask: Task221212 : 218 Milliseconds to complete.
// MyWorkerTask: Task211212 : 216 Milliseconds to complete.
// MyWorkerTask: Task121212 : 219 Milliseconds to complete.
// MyWorkerTask: Task111212 : 218 Milliseconds to complete.
// MyWorkerTask: Task11121 : 431 Milliseconds to complete.
// MyWorkerTask: Task12121 : 432 Milliseconds to complete.
// MyWorkerTask: Task21121 : 432 Milliseconds to complete.
// MyWorkerTask: Task22121 : 430 Milliseconds to complete.
// MyWorkerTask: Task1212211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2212211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2112211 : 51 Milliseconds to complete.
// MyWorkerTask: Task1112211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2212212 : 51 Milliseconds to complete.
// MyWorkerTask: Task1212212 : 51 Milliseconds to complete.
// MyWorkerTask: Task1112212 : 51 Milliseconds to complete.
// MyWorkerTask: Task2112212 : 51 Milliseconds to complete.
// MyWorkerTask: Task211221 : 158 Milliseconds to complete.
// MyWorkerTask: Task111221 : 158 Milliseconds to complete.
// MyWorkerTask: Task121221 : 158 Milliseconds to complete.
// MyWorkerTask: Task221221 : 161 Milliseconds to complete.
// MyWorkerTask: Task2112221 : 58 Milliseconds to complete.
// MyWorkerTask: Task1112221 : 59 Milliseconds to complete.
// MyWorkerTask: Task1212221 : 59 Milliseconds to complete.
// MyWorkerTask: Task2212221 : 64 Milliseconds to complete.
// MyWorkerTask: Task1112222 : 51 Milliseconds to complete.
// MyWorkerTask: Task2112222 : 51 Milliseconds to complete.
// MyWorkerTask: Task1212222 : 51 Milliseconds to complete.
// MyWorkerTask: Task2212222 : 51 Milliseconds to complete.
// MyWorkerTask: Task111222 : 161 Milliseconds to complete.
// MyWorkerTask: Task211222 : 161 Milliseconds to complete.
// MyWorkerTask: Task121222 : 169 Milliseconds to complete.
// MyWorkerTask: Task221222 : 167 Milliseconds to complete.
// MyWorkerTask: Task21122 : 371 Milliseconds to complete.
// MyWorkerTask: Task11122 : 371 Milliseconds to complete.
// MyWorkerTask: Task12122 : 378 Milliseconds to complete.
// MyWorkerTask: Task22122 : 379 Milliseconds to complete.
// MyWorkerTask: Task2112 : 854 Milliseconds to complete.
// MyWorkerTask: Task1112 : 853 Milliseconds to complete.
// MyWorkerTask: Task1212 : 861 Milliseconds to complete.
// MyWorkerTask: Task2212 : 860 Milliseconds to complete.
// MyWorkerTask: Task211 : 1716 Milliseconds to complete.
// MyWorkerTask: Task111 : 1716 Milliseconds to complete.
// MyWorkerTask: Task121 : 1723 Milliseconds to complete.
// MyWorkerTask: Task221 : 1724 Milliseconds to complete.
// MyWorkerTask: Task2121111 : 51 Milliseconds to complete.
// MyWorkerTask: Task1121111 : 51 Milliseconds to complete.
// MyWorkerTask: Task1221111 : 51 Milliseconds to complete.
// MyWorkerTask: Task2221111 : 51 Milliseconds to complete.
// MyWorkerTask: Task2121112 : 50 Milliseconds to complete.
// MyWorkerTask: Task1121112 : 51 Milliseconds to complete.
// MyWorkerTask: Task1221112 : 50 Milliseconds to complete.
// MyWorkerTask: Task2221112 : 50 Milliseconds to complete.
// MyWorkerTask: Task112111 : 153 Milliseconds to complete.
// MyWorkerTask: Task212111 : 153 Milliseconds to complete.
// MyWorkerTask: Task122111 : 152 Milliseconds to complete.
// MyWorkerTask: Task222111 : 152 Milliseconds to complete.
// MyWorkerTask: Task2121121 : 51 Milliseconds to complete.
// MyWorkerTask: Task1121121 : 51 Milliseconds to complete.
// MyWorkerTask: Task1221121 : 51 Milliseconds to complete.
// MyWorkerTask: Task2221121 : 51 Milliseconds to complete.
// MyWorkerTask: Task1121122 : 51 Milliseconds to complete.
// MyWorkerTask: Task2121122 : 51 Milliseconds to complete.
// MyWorkerTask: Task1221122 : 51 Milliseconds to complete.
// MyWorkerTask: Task2221122 : 51 Milliseconds to complete.
// MyWorkerTask: Task212112 : 153 Milliseconds to complete.
// MyWorkerTask: Task112112 : 153 Milliseconds to complete.
// MyWorkerTask: Task122112 : 171 Milliseconds to complete.
// MyWorkerTask: Task222112 : 170 Milliseconds to complete.
// MyWorkerTask: Task11211 : 357 Milliseconds to complete.
// MyWorkerTask: Task21211 : 357 Milliseconds to complete.
// MyWorkerTask: Task22211 : 373 Milliseconds to complete.
// MyWorkerTask: Task12211 : 374 Milliseconds to complete.
// MyWorkerTask: Task2121211 : 51 Milliseconds to complete.
// MyWorkerTask: Task1121211 : 51 Milliseconds to complete.
// MyWorkerTask: Task1221211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2221211 : 51 Milliseconds to complete.
// MyWorkerTask: Task1121212 : 51 Milliseconds to complete.
// MyWorkerTask: Task2121212 : 51 Milliseconds to complete.
// MyWorkerTask: Task1221212 : 50 Milliseconds to complete.
// MyWorkerTask: Task2221212 : 50 Milliseconds to complete.
// MyWorkerTask: Task212121 : 152 Milliseconds to complete.
// MyWorkerTask: Task112121 : 152 Milliseconds to complete.
// MyWorkerTask: Task122121 : 152 Milliseconds to complete.
// MyWorkerTask: Task222121 : 152 Milliseconds to complete.
// MyWorkerTask: Task2121221 : 51 Milliseconds to complete.
// MyWorkerTask: Task1121221 : 51 Milliseconds to complete.
// MyWorkerTask: Task1221221 : 50 Milliseconds to complete.
// MyWorkerTask: Task2221221 : 50 Milliseconds to complete.
// MyWorkerTask: Task2121222 : 50 Milliseconds to complete.
// MyWorkerTask: Task1121222 : 50 Milliseconds to complete.
// MyWorkerTask: Task1221222 : 51 Milliseconds to complete.
// MyWorkerTask: Task2221222 : 51 Milliseconds to complete.
// MyWorkerTask: Task112122 : 152 Milliseconds to complete.
// MyWorkerTask: Task212122 : 152 Milliseconds to complete.
// MyWorkerTask: Task222122 : 152 Milliseconds to complete.
// MyWorkerTask: Task122122 : 152 Milliseconds to complete.
// MyWorkerTask: Task21212 : 354 Milliseconds to complete.
// MyWorkerTask: Task11212 : 354 Milliseconds to complete.
// MyWorkerTask: Task22212 : 354 Milliseconds to complete.
// MyWorkerTask: Task12212 : 354 Milliseconds to complete.
// MyWorkerTask: Task2121 : 762 Milliseconds to complete.
// MyWorkerTask: Task1121 : 762 Milliseconds to complete.
// MyWorkerTask: Task1221 : 779 Milliseconds to complete.
// MyWorkerTask: Task2221 : 778 Milliseconds to complete.
// MyWorkerTask: Task2122111 : 50 Milliseconds to complete.
// MyWorkerTask: Task1122111 : 50 Milliseconds to complete.
// MyWorkerTask: Task1222111 : 50 Milliseconds to complete.
// MyWorkerTask: Task2222111 : 50 Milliseconds to complete.
// MyWorkerTask: Task2122112 : 51 Milliseconds to complete.
// MyWorkerTask: Task1122112 : 51 Milliseconds to complete.
// MyWorkerTask: Task2222112 : 51 Milliseconds to complete.
// MyWorkerTask: Task1222112 : 51 Milliseconds to complete.
// MyWorkerTask: Task112211 : 152 Milliseconds to complete.
// MyWorkerTask: Task212211 : 152 Milliseconds to complete.
// MyWorkerTask: Task122211 : 160 Milliseconds to complete.
// MyWorkerTask: Task222211 : 160 Milliseconds to complete.
// MyWorkerTask: Task1122121 : 51 Milliseconds to complete.
// MyWorkerTask: Task2122121 : 51 Milliseconds to complete.
// MyWorkerTask: Task1222121 : 51 Milliseconds to complete.
// MyWorkerTask: Task2222121 : 51 Milliseconds to complete.
// MyWorkerTask: Task1122122 : 51 Milliseconds to complete.
// MyWorkerTask: Task2122122 : 51 Milliseconds to complete.
// MyWorkerTask: Task2222122 : 51 Milliseconds to complete.
// MyWorkerTask: Task1222122 : 51 Milliseconds to complete.
// MyWorkerTask: Task112212 : 152 Milliseconds to complete.
// MyWorkerTask: Task212212 : 152 Milliseconds to complete.
// MyWorkerTask: Task222212 : 152 Milliseconds to complete.
// MyWorkerTask: Task122212 : 152 Milliseconds to complete.
// MyWorkerTask: Task21221 : 355 Milliseconds to complete.
// MyWorkerTask: Task11221 : 355 Milliseconds to complete.
// MyWorkerTask: Task22221 : 362 Milliseconds to complete.
// MyWorkerTask: Task12221 : 362 Milliseconds to complete.
// MyWorkerTask: Task1122211 : 50 Milliseconds to complete.
// MyWorkerTask: Task2122211 : 50 Milliseconds to complete.
// MyWorkerTask: Task2222211 : 51 Milliseconds to complete.
// MyWorkerTask: Task1222211 : 51 Milliseconds to complete.
// MyWorkerTask: Task2122212 : 51 Milliseconds to complete.
// MyWorkerTask: Task1122212 : 51 Milliseconds to complete.
// MyWorkerTask: Task1222212 : 50 Milliseconds to complete.
// MyWorkerTask: Task2222212 : 50 Milliseconds to complete.
// MyWorkerTask: Task112221 : 151 Milliseconds to complete.
// MyWorkerTask: Task212221 : 151 Milliseconds to complete.
// MyWorkerTask: Task222221 : 152 Milliseconds to complete.
// MyWorkerTask: Task122221 : 152 Milliseconds to complete.
// MyWorkerTask: Task2122221 : 51 Milliseconds to complete.
// MyWorkerTask: Task1122221 : 51 Milliseconds to complete.
// MyWorkerTask: Task2222221 : 50 Milliseconds to complete.
// MyWorkerTask: Task1222221 : 50 Milliseconds to complete.
// MyWorkerTask: Task1122222 : 50 Milliseconds to complete.
// MyWorkerTask: Task2122222 : 50 Milliseconds to complete.
// MyWorkerTask: Task1222222 : 51 Milliseconds to complete.
// MyWorkerTask: Task2222222 : 51 Milliseconds to complete.
// MyWorkerTask: Task112222 : 152 Milliseconds to complete.
// MyWorkerTask: Task212222 : 152 Milliseconds to complete.
// MyWorkerTask: Task122222 : 152 Milliseconds to complete.
// MyWorkerTask: Task222222 : 152 Milliseconds to complete.
// MyWorkerTask: Task21222 : 354 Milliseconds to complete.
// MyWorkerTask: Task11222 : 354 Milliseconds to complete.
// MyWorkerTask: Task12222 : 354 Milliseconds to complete.
// MyWorkerTask: Task22222 : 354 Milliseconds to complete.
// MyWorkerTask: Task1122 : 759 Milliseconds to complete.
// MyWorkerTask: Task2122 : 759 Milliseconds to complete.
// MyWorkerTask: Task1222 : 767 Milliseconds to complete.
// MyWorkerTask: Task2222 : 767 Milliseconds to complete.
// MyWorkerTask: Task112 : 1571 Milliseconds to complete.
// MyWorkerTask: Task212 : 1571 Milliseconds to complete.
// MyWorkerTask: Task222 : 1595 Milliseconds to complete.
// MyWorkerTask: Task122 : 1596 Milliseconds to complete.
// MyWorkerTask: Task11 : 3338 Milliseconds to complete.
// MyWorkerTask: Task21 : 3338 Milliseconds to complete.
// MyWorkerTask: Task12 : 3370 Milliseconds to complete.
// MyWorkerTask: Task22 : 3370 Milliseconds to complete.
// MyWorkerTask: Task2 : 3421 Milliseconds to complete.
// MyWorkerTask: Task1 : 3421 Milliseconds to complete.
// MyWorkerTask: Task : 3472 Milliseconds to complete.
// Main: End of the program.

