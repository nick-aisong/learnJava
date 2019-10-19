package cn.ch03.ch03_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

// 使用Exchanger类来解决一对一的生产者-消费者问题

// 15.实现范例的主程序Main并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 16.创建两个buffer列表，一个用于生产者，另一个用于消费者
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        // 17.创建Exchanger对象，它用来同步生产者和消费者
        Exchanger<List<String>> exchanger = new Exchanger<>();
        // 18.创建生产者Producer对象和消费者Consumer对象
        Producer producer = new Producer(buffer1, exchanger);
        Consumer consumer = new Consumer(buffer2, exchanger);
        // 19.分别将生产者和消费者作为传入参数创建线程，并且启动线程
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }
}

// 消费者先创建一个空的缓存列表，然后通过调用Exchanger与生产者同步来获得可以消费的数据
// 生产者从一个空的缓存列表开始执行，它创建了10个字符串，然后存储在这个缓存中，并且使用exchanger对象与消费者同步
// 在这个同步点上，两个线程(生产者和消费者)都在Exchanger里，它们交换数据结构
// 当消费者从exchange()方 法返回的时候，它的缓存列表有10个字符串
// 当生产者从exchange()返回的时候，它的缓存列表是空的。这个操作将循环执行10次
// 运行这个范例，我们可以看到生产者和消费者是怎样并发工作的以及两个对象是怎样在每一步中交换数据的
// 如果使用其他的同步辅助类，第一个线程调用exchange()后会被置于休眠，直到其他的线程到达

// Exchanger类还提供了另外的exchange方法，即exchange(V data, long time, TimeUnit unit)方法
// 其中第一个传入参数的类型是V，即要交换的数据结构(本例中是List<String>)
// 这个方法被调用后，线程将休眠直到被中断，或者其他的线程到达，或者已耗费了指定的time值
// 第三个传入参数的类型是TimeUnit，它是枚举类型，其值包含以下常量:
// DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

// Producer: Cycle 1
// Consumer: Cycle 1
// Producer: Event 0
// Producer: Event 1
// Producer: Event 2
// Producer: Event 3
// Producer: Event 4
// Producer: Event 5
// Producer: Event 6
// Producer: Event 7
// Producer: Event 8
// Producer: Event 9
// Producer: 0
// Producer: Cycle 2
// Consumer: 10
// Producer: Event 10
// Consumer: Event 0
// Consumer: Event 1
// Producer: Event 11
// Producer: Event 12
// Producer: Event 13
// Producer: Event 14
// Producer: Event 15
// Producer: Event 16
// Producer: Event 17
// Producer: Event 18
// Consumer: Event 2
// Producer: Event 19
// Consumer: Event 3
// Consumer: Event 4
// Consumer: Event 5
// Consumer: Event 6
// Consumer: Event 7
// Consumer: Event 8
// Consumer: Event 9
// Consumer: Cycle 2
// Consumer: 10
// Consumer: Event 10
// Producer: 0
// Producer: Cycle 3
// Consumer: Event 11
// Producer: Event 20
// Consumer: Event 12
// Producer: Event 21
// Consumer: Event 13
// Producer: Event 22
// Consumer: Event 14
// Producer: Event 23
// Consumer: Event 15
// Producer: Event 24
// Producer: Event 25
// Producer: Event 26
// Producer: Event 27
// Producer: Event 28
// Producer: Event 29
// Consumer: Event 16
// Consumer: Event 17
// Consumer: Event 18
// Consumer: Event 19
// Consumer: Cycle 3
// Consumer: 10
// Producer: 0
// Producer: Cycle 4
// Consumer: Event 20
// Producer: Event 30
// Consumer: Event 21
// Producer: Event 31
// Consumer: Event 22
// Consumer: Event 23
// Producer: Event 32
// Consumer: Event 24
// Producer: Event 33
// Consumer: Event 25
// Producer: Event 34
// Consumer: Event 26
// Producer: Event 35
// Consumer: Event 27
// Producer: Event 36
// Consumer: Event 28
// Producer: Event 37
// Consumer: Event 29
// Consumer: Cycle 4
// Producer: Event 38
// Producer: Event 39
// Producer: 0
// Producer: Cycle 5
// Producer: Event 40
// Producer: Event 41
// Consumer: 10
// Producer: Event 42
// Consumer: Event 30
// Producer: Event 43
// Consumer: Event 31
// Producer: Event 44
// Consumer: Event 32
// Producer: Event 45
// Producer: Event 46
// Producer: Event 47
// Consumer: Event 33
// Consumer: Event 34
// Consumer: Event 35
// Producer: Event 48
// Consumer: Event 36
// Producer: Event 49
// Consumer: Event 37
// Consumer: Event 38
// Consumer: Event 39
// Consumer: Cycle 5
// Consumer: 10
// Producer: 0
// Producer: Cycle 6
// Consumer: Event 40
// Producer: Event 50
// Consumer: Event 41
// Producer: Event 51
// Consumer: Event 42
// Producer: Event 52
// Consumer: Event 43
// Producer: Event 53
// Consumer: Event 44
// Producer: Event 54
// Consumer: Event 45
// Producer: Event 55
// Consumer: Event 46
// Producer: Event 56
// Consumer: Event 47
// Producer: Event 57
// Consumer: Event 48
// Producer: Event 58
// Consumer: Event 49
// Consumer: Cycle 6
// Producer: Event 59
// Producer: 0
// Producer: Cycle 7
// Producer: Event 60
// Producer: Event 61
// Producer: Event 62
// Producer: Event 63
// Producer: Event 64
// Producer: Event 65
// Consumer: 10
// Consumer: Event 50
// Producer: Event 66
// Consumer: Event 51
// Consumer: Event 52
// Consumer: Event 53
// Consumer: Event 54
// Consumer: Event 55
// Consumer: Event 56
// Consumer: Event 57
// Consumer: Event 58
// Consumer: Event 59
// Consumer: Cycle 7
// Producer: Event 67
// Producer: Event 68
// Producer: Event 69
// Producer: 0
// Producer: Cycle 8
// Consumer: 10
// Consumer: Event 60
// Producer: Event 70
// Consumer: Event 61
// Producer: Event 71
// Consumer: Event 62
// Producer: Event 72
// Consumer: Event 63
// Producer: Event 73
// Consumer: Event 64
// Producer: Event 74
// Consumer: Event 65
// Producer: Event 75
// Consumer: Event 66
// Producer: Event 76
// Consumer: Event 67
// Producer: Event 77
// Consumer: Event 68
// Producer: Event 78
// Consumer: Event 69
// Producer: Event 79
// Consumer: Cycle 8
// Consumer: 10
// Producer: 0
// Producer: Cycle 9
// Producer: Event 80
// Producer: Event 81
// Consumer: Event 70
// Producer: Event 82
// Consumer: Event 71
// Producer: Event 83
// Consumer: Event 72
// Producer: Event 84
// Consumer: Event 73
// Producer: Event 85
// Consumer: Event 74
// Producer: Event 86
// Consumer: Event 75
// Producer: Event 87
// Consumer: Event 76
// Producer: Event 88
// Consumer: Event 77
// Producer: Event 89
// Consumer: Event 78
// Consumer: Event 79
// Consumer: Cycle 9
// Consumer: 10
// Consumer: Event 80
// Consumer: Event 81
// Consumer: Event 82
// Consumer: Event 83
// Consumer: Event 84
// Consumer: Event 85
// Consumer: Event 86
// Consumer: Event 87
// Consumer: Event 88
// Consumer: Event 89
// Consumer: Cycle 10
// Producer: 0
// Producer: Cycle 10
// Producer: Event 90
// Producer: Event 91
// Producer: Event 92
// Producer: Event 93
// Producer: Event 94
// Producer: Event 95
// Producer: Event 96
// Producer: Event 97
// Producer: Event 98
// Producer: Event 99
// Producer: 0
// Consumer: 10
// Consumer: Event 90
// Consumer: Event 91
// Consumer: Event 92
// Consumer: Event 93
// Consumer: Event 94
// Consumer: Event 95
// Consumer: Event 96
// Consumer: Event 97
// Consumer: Event 98
// Consumer: Event 99