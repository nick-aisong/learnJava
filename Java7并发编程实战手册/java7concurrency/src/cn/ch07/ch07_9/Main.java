package cn.ch07.ch07_9;

import java.util.concurrent.TimeUnit;
// 范例用来解决数据结构中的元素是按优先级排序的生产者/消费者问题，优先级高的先被处理

// 30.创建名为Main的主类，并实现main()方法

// 实现基于优先级的传输队列
public class Main {

    public static void main(String[] args) throws Exception {
        // 31.创建一个名为buffer的MyPriorityTransferQueue对象
        MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<Event>();
        // 32.创建一个Producer任务，并运行10个线程来执行这些任务
        Producer producer = new Producer(buffer);
        Thread[] producerThreads = new Thread[10];
        for (int i = 0; i < producerThreads.length; i++) {
            producerThreads[i] = new Thread(producer);
            producerThreads[i].start();
        }
        // 33.创建并运行一个Consumer任务
        Consumer consumer = new Consumer(buffer);
        Thread consumerTread = new Thread(consumer);
        consumerTread.start();
        // 34.在控制台输出实际的消费者数
        System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
        // 35.使用transfer()方法把一个事件传递给消费者
        Event myEvent = new Event("Core Event", 0);
        buffer.transfer(myEvent);
        System.out.printf("Main: My Event has been transfered.\n");
        // 36.使用join()方法等待生产者执行结束
        for (int i = 0; i < producerThreads.length; i++) {
            producerThreads[i].join();
        }
        // 37.线程休眠1秒钟
        TimeUnit.SECONDS.sleep(1);
        // 38.在控制台输出实际的消费者数
        System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
        // 39.使用transfer()方法传递另一个事件
        myEvent = new Event("Core Event 2", 0);
        buffer.transfer(myEvent);
        // 40.使用join()方法等待消费者线程执行结束
        consumerTread.join();
        // 41.在控制台输出一条消息表示程序结束
        System.out.printf("Main: End of the program\n");
    }
}

// 本节实现了MyPriorityTransferQueue数据结构
// 该结构用在生产者-消费者问题中，但是里面的元素按优先级进行排序而不是按到达的先后顺序排序
// 因为Java不允许多继承，所以第一考虑是使用基类
// MyPriorityTransferQueue类继承了PriorityBlockingQueue类，并实现了把元素按优先级插入到结构中的操作
// MyPriorityTransferQueue类也实现了TransferQueue接口，用来增加与生产者-消费者相关的操作方法

// MyPriorityTransferQueue类有下面3个属性
// ·一个名为counter的AtomicInteger属性：用来存储等待从数据结构中读取元素的消费者的数量
// 当消费者调用take()方法从数据结构中取元素时，counter属性增加1
// 当消费者完成take()方法的执行，counter属性减1
// counter用在hasWaitingConsumer()和getWaitingConsumerCount()方法的实现中
// ·一个名为lock的ReentrantLock属性：用来控制访问上述实现的生产者-消费者的操作，实现仅有一个线程可操作数据结构的目标
// ·一个LinkedBlockingQueue列表：存储已传递(尚未被消费)的元素
// 我们在MyPriorityTransferQueue类中实现了几个方法，这些方法都在TransferQueue接口中进行声明的
// 而take()方法在所继承的PriorityBlockingQueue类中则有默认实现

// 这些方法中的两个已在之前部分描述过，下面是其他方法的介绍
// ·tryTransfer(E e)：这个方法尝试直接发送一个元素到消费者
// 如果有消费者正在等待，这个方法将把元素存入到可被立即消费的优先级队列中，然后返回true
// 如果没有消费者在等待，则返回false
// ·transfer(E e)：这个方法直接传递一个元素到一个消费者。如果有消费者正在等待，这个方法将把元素存入到可被立即消费的优先级队列中
// 否则，元素被存储在已转移(尚未被消费)的元素列表中，线程将被阻塞直至元素被消费
// 当线程休眠时，必须释放锁，否则队列被阻塞
// ·tryTransfer(E e， long timeout， TimeUnit unit)：这个方法与transfer()方法相似，但是线程阻塞的时间由参数决定
// 当线程休眠时，必须释放锁，否则队列被阻塞
// ·take()： 这个方法返回下一个要被消费的元素。如果在转移列表transfered中有元素，那么就从这个列表中获取将要被消费的元素
// 否则，就从优先队列中获取

// 在这个数据结构之后，我们实现了Event类，它是存储在数据结构中的元素类
// Event类有两个属性存储生产者ID(线程的名称)和事件的优先级priority，它实现了Comparable接口，这是数据结构的一个需求
// 接下来，实现了生产者Producer和消费者Consumer类
// 在本例中，有10个生产者和消费者，它们共享相同的缓冲区
// 每个生产者生成100个带有递增优先级的事件，所以有更高优先级的事件最后被生成
// 本例的main主类创建了MyPriorityTransferQueue对象、10个生产者和1个消费者，并且使用MyPriorityTransferQueue类对象buffer的transfer()方法传递两个事件到缓冲区

// Main: Buffer: Consumer count: 0
// Consumer: Thread-0: 99
// Consumer: Core Event: 0
// Consumer: Thread-2: 99
// Consumer: Thread-6: 99
// Consumer: Thread-8: 99
// Main: My Event has been transfered.
// Consumer: Thread-1: 99
// Consumer: Thread-3: 99
// Consumer: Thread-9: 99
// Consumer: Thread-4: 99
// Consumer: Thread-5: 99
// Consumer: Thread-7: 99
// Consumer: Thread-2: 98
// Consumer: Thread-6: 98
// Consumer: Thread-8: 98
// Consumer: Thread-3: 98
// Consumer: Thread-9: 98
// Consumer: Thread-0: 98
// Consumer: Thread-1: 98
// Consumer: Thread-5: 98
// Consumer: Thread-4: 98
// Consumer: Thread-7: 98
// Consumer: Thread-2: 97
// Consumer: Thread-6: 97
// Consumer: Thread-8: 97
// Consumer: Thread-1: 97
// Consumer: Thread-3: 97
// Consumer: Thread-9: 97
// Consumer: Thread-0: 97
// Consumer: Thread-5: 97
// Consumer: Thread-4: 97
// Consumer: Thread-7: 97
// Consumer: Thread-2: 96
// Consumer: Thread-6: 96
// Consumer: Thread-7: 96
// Consumer: Thread-8: 96
// Consumer: Thread-3: 96
// Consumer: Thread-9: 96
// Consumer: Thread-1: 96
// Consumer: Thread-0: 96
// Consumer: Thread-5: 96
// Consumer: Thread-4: 96
// Consumer: Thread-2: 95
// Consumer: Thread-6: 95
// Consumer: Thread-8: 95
// Consumer: Thread-3: 95
// Consumer: Thread-9: 95
// Consumer: Thread-1: 95
// Consumer: Thread-0: 95
// Consumer: Thread-5: 95
// Consumer: Thread-7: 95
// Consumer: Thread-4: 95
// Consumer: Thread-6: 94
// Consumer: Thread-2: 94
// Consumer: Thread-8: 94
// Consumer: Thread-1: 94
// Consumer: Thread-0: 94
// Consumer: Thread-3: 94
// Consumer: Thread-9: 94
// Consumer: Thread-5: 94
// Consumer: Thread-4: 94
// Consumer: Thread-7: 94
// Consumer: Thread-0: 93
// Consumer: Thread-6: 93
// Consumer: Thread-2: 93
// Consumer: Thread-8: 93
// Consumer: Thread-1: 93
// Consumer: Thread-3: 93
// Consumer: Thread-9: 93
// Consumer: Thread-4: 93
// Consumer: Thread-5: 93
// Consumer: Thread-7: 93
// Consumer: Thread-6: 92
// Consumer: Thread-2: 92
// Consumer: Thread-0: 92
// Consumer: Thread-8: 92
// Consumer: Thread-1: 92
// Consumer: Thread-3: 92
// Consumer: Thread-9: 92
// Consumer: Thread-5: 92
// Consumer: Thread-4: 92
// Consumer: Thread-7: 92
// Consumer: Thread-2: 91
// Consumer: Thread-6: 91
// Consumer: Thread-8: 91
// Consumer: Thread-0: 91
// Consumer: Thread-1: 91
// Consumer: Thread-3: 91
// Consumer: Thread-9: 91
// Consumer: Thread-5: 91
// Consumer: Thread-4: 91
// Consumer: Thread-7: 91
// Consumer: Thread-2: 90
// Consumer: Thread-6: 90
// Consumer: Thread-8: 90
// Consumer: Thread-0: 90
// Consumer: Thread-1: 90
// Consumer: Thread-3: 90
// Consumer: Thread-9: 90
// Consumer: Thread-5: 90
// Consumer: Thread-4: 90
// Consumer: Thread-7: 90
// Consumer: Thread-2: 89
// Consumer: Thread-6: 89
// Consumer: Thread-4: 89
// Consumer: Thread-8: 89
// Consumer: Thread-0: 89
// Consumer: Thread-1: 89
// Consumer: Thread-3: 89
// Consumer: Thread-9: 89
// Consumer: Thread-5: 89
// Consumer: Thread-7: 89
// Consumer: Thread-6: 88
// Consumer: Thread-2: 88
// Consumer: Thread-7: 88
// Consumer: Thread-8: 88
// Consumer: Thread-4: 88
// Consumer: Thread-1: 88
// Consumer: Thread-0: 88
// Consumer: Thread-3: 88
// Consumer: Thread-9: 88
// Consumer: Thread-5: 88
// Consumer: Thread-2: 87
// Consumer: Thread-6: 87
// Consumer: Thread-8: 87
// Consumer: Thread-0: 87
// Consumer: Thread-1: 87
// Consumer: Thread-3: 87
// Consumer: Thread-9: 87
// Consumer: Thread-5: 87
// Consumer: Thread-4: 87
// Consumer: Thread-7: 87
// Consumer: Thread-2: 86
// Consumer: Thread-6: 86
// Consumer: Thread-8: 86
// Consumer: Thread-0: 86
// Consumer: Thread-1: 86
// Consumer: Thread-3: 86
// Consumer: Thread-9: 86
// Consumer: Thread-7: 86
// Consumer: Thread-5: 86
// Consumer: Thread-4: 86
// Consumer: Thread-2: 85
// Consumer: Thread-6: 85
// Consumer: Thread-8: 85
// Consumer: Thread-0: 85
// Consumer: Thread-1: 85
// Consumer: Thread-3: 85
// Consumer: Thread-9: 85
// Consumer: Thread-5: 85
// Consumer: Thread-4: 85
// Consumer: Thread-7: 85
// Consumer: Thread-2: 84
// Consumer: Thread-6: 84
// Consumer: Thread-8: 84
// Consumer: Thread-0: 84
// Consumer: Thread-1: 84
// Consumer: Thread-3: 84
// Consumer: Thread-9: 84
// Consumer: Thread-5: 84
// Consumer: Thread-4: 84
// Consumer: Thread-7: 84
// Consumer: Thread-2: 83
// Consumer: Thread-6: 83
// Consumer: Thread-0: 83
// Consumer: Thread-8: 83
// Consumer: Thread-1: 83
// Consumer: Thread-3: 83
// Consumer: Thread-9: 83
// Consumer: Thread-5: 83
// Consumer: Thread-7: 83
// Consumer: Thread-4: 83
// Consumer: Thread-2: 82
// Consumer: Thread-6: 82
// Consumer: Thread-0: 82
// Consumer: Thread-8: 82
// Consumer: Thread-1: 82
// Consumer: Thread-3: 82
// Consumer: Thread-9: 82
// Consumer: Thread-7: 82
// Consumer: Thread-5: 82
// Consumer: Thread-4: 82
// Consumer: Thread-2: 81
// Consumer: Thread-6: 81
// Consumer: Thread-8: 81
// Consumer: Thread-0: 81
// Consumer: Thread-1: 81
// Consumer: Thread-5: 81
// Consumer: Thread-3: 81
// Consumer: Thread-9: 81
// Consumer: Thread-4: 81
// Consumer: Thread-7: 81
// Consumer: Thread-2: 80
// Consumer: Thread-6: 80
// Consumer: Thread-7: 80
// Consumer: Thread-8: 80
// Consumer: Thread-0: 80
// Consumer: Thread-1: 80
// Consumer: Thread-3: 80
// Consumer: Thread-9: 80
// Consumer: Thread-5: 80
// Consumer: Thread-4: 80
// Consumer: Thread-2: 79
// Consumer: Thread-6: 79
// Consumer: Thread-0: 79
// Consumer: Thread-8: 79
// Consumer: Thread-1: 79
// Consumer: Thread-3: 79
// Consumer: Thread-9: 79
// Consumer: Thread-5: 79
// Consumer: Thread-4: 79
// Consumer: Thread-7: 79
// Consumer: Thread-2: 78
// Consumer: Thread-6: 78
// Consumer: Thread-0: 78
// Consumer: Thread-8: 78
// Consumer: Thread-1: 78
// Consumer: Thread-3: 78
// Consumer: Thread-9: 78
// Consumer: Thread-5: 78
// Consumer: Thread-4: 78
// Consumer: Thread-7: 78
// Consumer: Thread-2: 77
// Consumer: Thread-6: 77
// Consumer: Thread-7: 77
// Consumer: Thread-0: 77
// Consumer: Thread-8: 77
// Consumer: Thread-1: 77
// Consumer: Thread-3: 77
// Consumer: Thread-9: 77
// Consumer: Thread-5: 77
// Consumer: Thread-4: 77
// Consumer: Thread-2: 76
// Consumer: Thread-0: 76
// Consumer: Thread-6: 76
// Consumer: Thread-8: 76
// Consumer: Thread-1: 76
// Consumer: Thread-3: 76
// Consumer: Thread-9: 76
// Consumer: Thread-5: 76
// Consumer: Thread-4: 76
// Consumer: Thread-7: 76
// Consumer: Thread-2: 75
// Consumer: Thread-6: 75
// Consumer: Thread-0: 75
// Consumer: Thread-8: 75
// Consumer: Thread-1: 75
// Consumer: Thread-3: 75
// Consumer: Thread-9: 75
// Consumer: Thread-5: 75
// Consumer: Thread-4: 75
// Consumer: Thread-7: 75
// Consumer: Thread-2: 74
// Consumer: Thread-6: 74
// Consumer: Thread-0: 74
// Consumer: Thread-8: 74
// Consumer: Thread-1: 74
// Consumer: Thread-3: 74
// Consumer: Thread-9: 74
// Consumer: Thread-5: 74
// Consumer: Thread-4: 74
// Consumer: Thread-7: 74
// Consumer: Thread-2: 73
// Consumer: Thread-6: 73
// Consumer: Thread-0: 73
// Consumer: Thread-8: 73
// Consumer: Thread-1: 73
// Consumer: Thread-5: 73
// Consumer: Thread-3: 73
// Consumer: Thread-9: 73
// Consumer: Thread-4: 73
// Consumer: Thread-7: 73
// Consumer: Thread-2: 72
// Consumer: Thread-6: 72
// Consumer: Thread-0: 72
// Consumer: Thread-8: 72
// Consumer: Thread-1: 72
// Consumer: Thread-3: 72
// Consumer: Thread-9: 72
// Consumer: Thread-5: 72
// Consumer: Thread-7: 72
// Consumer: Thread-4: 72
// Consumer: Thread-2: 71
// Consumer: Thread-6: 71
// Consumer: Thread-0: 71
// Consumer: Thread-8: 71
// Consumer: Thread-1: 71
// Consumer: Thread-3: 71
// Consumer: Thread-9: 71
// Consumer: Thread-5: 71
// Consumer: Thread-4: 71
// Consumer: Thread-7: 71
// Consumer: Thread-2: 70
// Consumer: Thread-6: 70
// Consumer: Thread-0: 70
// Consumer: Thread-7: 70
// Consumer: Thread-8: 70
// Consumer: Thread-1: 70
// Consumer: Thread-3: 70
// Consumer: Thread-9: 70
// Consumer: Thread-5: 70
// Consumer: Thread-4: 70
// Consumer: Thread-2: 69
// Consumer: Thread-6: 69
// Consumer: Thread-0: 69
// Consumer: Thread-8: 69
// Consumer: Thread-1: 69
// Consumer: Thread-3: 69
// Consumer: Thread-9: 69
// Consumer: Thread-5: 69
// Consumer: Thread-4: 69
// Consumer: Thread-7: 69
// Consumer: Thread-2: 68
// Consumer: Thread-6: 68
// Consumer: Thread-0: 68
// Consumer: Thread-8: 68
// Consumer: Thread-1: 68
// Consumer: Thread-7: 68
// Consumer: Thread-3: 68
// Consumer: Thread-9: 68
// Consumer: Thread-5: 68
// Consumer: Thread-4: 68
// Consumer: Thread-0: 67
// Consumer: Thread-2: 67
// Consumer: Thread-6: 67
// Consumer: Thread-8: 67
// Consumer: Thread-1: 67
// Consumer: Thread-3: 67
// Consumer: Thread-9: 67
// Consumer: Thread-5: 67
// Consumer: Thread-4: 67
// Consumer: Thread-7: 67
// Consumer: Thread-2: 66
// Consumer: Thread-0: 66
// Consumer: Thread-6: 66
// Consumer: Thread-8: 66
// Consumer: Thread-1: 66
// Consumer: Thread-3: 66
// Consumer: Thread-9: 66
// Consumer: Thread-5: 66
// Consumer: Thread-4: 66
// Consumer: Thread-7: 66
// Consumer: Thread-2: 65
// Consumer: Thread-0: 65
// Consumer: Thread-6: 65
// Consumer: Thread-5: 65
// Consumer: Thread-8: 65
// Consumer: Thread-1: 65
// Consumer: Thread-3: 65
// Consumer: Thread-9: 65
// Consumer: Thread-7: 65
// Consumer: Thread-4: 65
// Consumer: Thread-2: 64
// Consumer: Thread-6: 64
// Consumer: Thread-0: 64
// Consumer: Thread-1: 64
// Consumer: Thread-8: 64
// Consumer: Thread-3: 64
// Consumer: Thread-9: 64
// Consumer: Thread-5: 64
// Consumer: Thread-4: 64
// Consumer: Thread-7: 64
// Consumer: Thread-2: 63
// Consumer: Thread-0: 63
// Consumer: Thread-6: 63
// Consumer: Thread-1: 63
// Consumer: Thread-8: 63
// Consumer: Thread-3: 63
// Consumer: Thread-5: 63
// Consumer: Thread-4: 63
// Consumer: Thread-7: 63
// Consumer: Thread-9: 63
// Consumer: Thread-2: 62
// Consumer: Thread-0: 62
// Consumer: Thread-6: 62
// Consumer: Thread-1: 62
// Consumer: Thread-8: 62
// Consumer: Thread-3: 62
// Consumer: Thread-9: 62
// Consumer: Thread-5: 62
// Consumer: Thread-4: 62
// Consumer: Thread-7: 62
// Consumer: Thread-2: 61
// Consumer: Thread-6: 61
// Consumer: Thread-1: 61
// Consumer: Thread-8: 61
// Consumer: Thread-3: 61
// Consumer: Thread-9: 61
// Consumer: Thread-0: 61
// Consumer: Thread-5: 61
// Consumer: Thread-4: 61
// Consumer: Thread-7: 61
// Consumer: Thread-2: 60
// Consumer: Thread-6: 60
// Consumer: Thread-1: 60
// Consumer: Thread-8: 60
// Consumer: Thread-3: 60
// Consumer: Thread-9: 60
// Consumer: Thread-5: 60
// Consumer: Thread-0: 60
// Consumer: Thread-4: 60
// Consumer: Thread-7: 60
// Consumer: Thread-2: 59
// Consumer: Thread-9: 59
// Consumer: Thread-6: 59
// Consumer: Thread-8: 59
// Consumer: Thread-7: 59
// Consumer: Thread-1: 59
// Consumer: Thread-3: 59
// Consumer: Thread-0: 59
// Consumer: Thread-5: 59
// Consumer: Thread-4: 59
// Consumer: Thread-2: 58
// Consumer: Thread-6: 58
// Consumer: Thread-0: 58
// Consumer: Thread-1: 58
// Consumer: Thread-8: 58
// Consumer: Thread-3: 58
// Consumer: Thread-9: 58
// Consumer: Thread-7: 58
// Consumer: Thread-5: 58
// Consumer: Thread-4: 58
// Consumer: Thread-2: 57
// Consumer: Thread-6: 57
// Consumer: Thread-1: 57
// Consumer: Thread-8: 57
// Consumer: Thread-3: 57
// Consumer: Thread-9: 57
// Consumer: Thread-4: 57
// Consumer: Thread-7: 57
// Consumer: Thread-5: 57
// Consumer: Thread-0: 57
// Consumer: Thread-2: 56
// Consumer: Thread-6: 56
// Consumer: Thread-8: 56
// Consumer: Thread-1: 56
// Consumer: Thread-3: 56
// Consumer: Thread-9: 56
// Consumer: Thread-7: 56
// Consumer: Thread-0: 56
// Consumer: Thread-5: 56
// Consumer: Thread-4: 56
// Consumer: Thread-2: 55
// Consumer: Thread-6: 55
// Consumer: Thread-9: 55
// Consumer: Thread-1: 55
// Consumer: Thread-8: 55
// Consumer: Thread-3: 55
// Consumer: Thread-5: 55
// Consumer: Thread-0: 55
// Consumer: Thread-4: 55
// Consumer: Thread-7: 55
// Consumer: Thread-6: 54
// Consumer: Thread-1: 54
// Consumer: Thread-8: 54
// Consumer: Thread-3: 54
// Consumer: Thread-9: 54
// Consumer: Thread-7: 54
// Consumer: Thread-5: 54
// Consumer: Thread-4: 54
// Consumer: Thread-0: 54
// Consumer: Thread-2: 54
// Consumer: Thread-6: 53
// Consumer: Thread-1: 53
// Consumer: Thread-8: 53
// Consumer: Thread-3: 53
// Consumer: Thread-5: 53
// Consumer: Thread-9: 53
// Consumer: Thread-4: 53
// Consumer: Thread-0: 53
// Consumer: Thread-7: 53
// Consumer: Thread-2: 53
// Consumer: Thread-6: 52
// Consumer: Thread-1: 52
// Consumer: Thread-8: 52
// Consumer: Thread-3: 52
// Consumer: Thread-9: 52
// Consumer: Thread-5: 52
// Consumer: Thread-0: 52
// Consumer: Thread-4: 52
// Consumer: Thread-7: 52
// Consumer: Thread-2: 52
// Consumer: Thread-6: 51
// Consumer: Thread-7: 51
// Consumer: Thread-1: 51
// Consumer: Thread-3: 51
// Consumer: Thread-0: 51
// Consumer: Thread-5: 51
// Consumer: Thread-4: 51
// Consumer: Thread-8: 51
// Consumer: Thread-9: 51
// Consumer: Thread-2: 51
// Consumer: Thread-6: 50
// Consumer: Thread-1: 50
// Consumer: Thread-8: 50
// Consumer: Thread-7: 50
// Consumer: Thread-3: 50
// Consumer: Thread-9: 50
// Consumer: Thread-5: 50
// Consumer: Thread-0: 50
// Consumer: Thread-2: 50
// Consumer: Thread-4: 50
// Consumer: Thread-6: 49
// Consumer: Thread-0: 49
// Consumer: Thread-1: 49
// Consumer: Thread-8: 49
// Consumer: Thread-3: 49
// Consumer: Thread-9: 49
// Consumer: Thread-5: 49
// Consumer: Thread-4: 49
// Consumer: Thread-7: 49
// Consumer: Thread-2: 49
// Consumer: Thread-8: 48
// Consumer: Thread-6: 48
// Consumer: Thread-7: 48
// Consumer: Thread-1: 48
// Consumer: Thread-3: 48
// Consumer: Thread-9: 48
// Consumer: Thread-0: 48
// Consumer: Thread-5: 48
// Consumer: Thread-4: 48
// Consumer: Thread-2: 48
// Consumer: Thread-6: 47
// Consumer: Thread-1: 47
// Consumer: Thread-8: 47
// Consumer: Thread-3: 47
// Consumer: Thread-9: 47
// Consumer: Thread-0: 47
// Consumer: Thread-5: 47
// Consumer: Thread-4: 47
// Consumer: Thread-7: 47
// Consumer: Thread-2: 47
// Consumer: Thread-6: 46
// Consumer: Thread-1: 46
// Consumer: Thread-8: 46
// Consumer: Thread-3: 46
// Consumer: Thread-9: 46
// Consumer: Thread-2: 46
// Consumer: Thread-7: 46
// Consumer: Thread-0: 46
// Consumer: Thread-5: 46
// Consumer: Thread-4: 46
// Consumer: Thread-0: 45
// Consumer: Thread-6: 45
// Consumer: Thread-1: 45
// Consumer: Thread-8: 45
// Consumer: Thread-3: 45
// Consumer: Thread-9: 45
// Consumer: Thread-2: 45
// Consumer: Thread-5: 45
// Consumer: Thread-4: 45
// Consumer: Thread-7: 45
// Consumer: Thread-6: 44
// Consumer: Thread-1: 44
// Consumer: Thread-0: 44
// Consumer: Thread-3: 44
// Consumer: Thread-8: 44
// Consumer: Thread-9: 44
// Consumer: Thread-2: 44
// Consumer: Thread-5: 44
// Consumer: Thread-4: 44
// Consumer: Thread-7: 44
// Consumer: Thread-1: 43
// Consumer: Thread-5: 43
// Consumer: Thread-8: 43
// Consumer: Thread-3: 43
// Consumer: Thread-0: 43
// Consumer: Thread-9: 43
// Consumer: Thread-7: 43
// Consumer: Thread-2: 43
// Consumer: Thread-4: 43
// Consumer: Thread-6: 43
// Consumer: Thread-6: 42
// Consumer: Thread-1: 42
// Consumer: Thread-2: 42
// Consumer: Thread-8: 42
// Consumer: Thread-3: 42
// Consumer: Thread-9: 42
// Consumer: Thread-7: 42
// Consumer: Thread-5: 42
// Consumer: Thread-4: 42
// Consumer: Thread-0: 42
// Consumer: Thread-6: 41
// Consumer: Thread-1: 41
// Consumer: Thread-4: 41
// Consumer: Thread-8: 41
// Consumer: Thread-3: 41
// Consumer: Thread-2: 41
// Consumer: Thread-9: 41
// Consumer: Thread-5: 41
// Consumer: Thread-0: 41
// Consumer: Thread-7: 41
// Consumer: Thread-6: 40
// Consumer: Thread-1: 40
// Consumer: Thread-7: 40
// Consumer: Thread-3: 40
// Consumer: Thread-4: 40
// Consumer: Thread-0: 40
// Consumer: Thread-9: 40
// Consumer: Thread-2: 40
// Consumer: Thread-5: 40
// Consumer: Thread-8: 40
// Consumer: Thread-6: 39
// Consumer: Thread-9: 39
// Consumer: Thread-0: 39
// Consumer: Thread-1: 39
// Consumer: Thread-4: 39
// Consumer: Thread-8: 39
// Consumer: Thread-3: 39
// Consumer: Thread-2: 39
// Consumer: Thread-5: 39
// Consumer: Thread-7: 39
// Consumer: Thread-6: 38
// Consumer: Thread-1: 38
// Consumer: Thread-4: 38
// Consumer: Thread-8: 38
// Consumer: Thread-3: 38
// Consumer: Thread-0: 38
// Consumer: Thread-9: 38
// Consumer: Thread-7: 38
// Consumer: Thread-5: 38
// Consumer: Thread-2: 38
// Consumer: Thread-6: 37
// Consumer: Thread-1: 37
// Consumer: Thread-8: 37
// Consumer: Thread-0: 37
// Consumer: Thread-3: 37
// Consumer: Thread-2: 37
// Consumer: Thread-9: 37
// Consumer: Thread-5: 37
// Consumer: Thread-4: 37
// Consumer: Thread-7: 37
// Consumer: Thread-6: 36
// Consumer: Thread-7: 36
// Consumer: Thread-4: 36
// Consumer: Thread-8: 36
// Consumer: Thread-3: 36
// Consumer: Thread-0: 36
// Consumer: Thread-9: 36
// Consumer: Thread-2: 36
// Consumer: Thread-5: 36
// Consumer: Thread-1: 36
// Consumer: Thread-0: 35
// Consumer: Thread-6: 35
// Consumer: Thread-1: 35
// Consumer: Thread-8: 35
// Consumer: Thread-3: 35
// Consumer: Thread-4: 35
// Consumer: Thread-5: 35
// Consumer: Thread-9: 35
// Consumer: Thread-2: 35
// Consumer: Thread-7: 35
// Consumer: Thread-6: 34
// Consumer: Thread-7: 34
// Consumer: Thread-0: 34
// Consumer: Thread-8: 34
// Consumer: Thread-3: 34
// Consumer: Thread-5: 34
// Consumer: Thread-1: 34
// Consumer: Thread-9: 34
// Consumer: Thread-2: 34
// Consumer: Thread-4: 34
// Consumer: Thread-6: 33
// Consumer: Thread-1: 33
// Consumer: Thread-0: 33
// Consumer: Thread-8: 33
// Consumer: Thread-2: 33
// Consumer: Thread-3: 33
// Consumer: Thread-5: 33
// Consumer: Thread-4: 33
// Consumer: Thread-7: 33
// Consumer: Thread-9: 33
// Consumer: Thread-1: 32
// Consumer: Thread-6: 32
// Consumer: Thread-2: 32
// Consumer: Thread-8: 32
// Consumer: Thread-3: 32
// Consumer: Thread-5: 32
// Consumer: Thread-4: 32
// Consumer: Thread-9: 32
// Consumer: Thread-0: 32
// Consumer: Thread-7: 32
// Consumer: Thread-0: 31
// Consumer: Thread-7: 31
// Consumer: Thread-5: 31
// Consumer: Thread-6: 31
// Consumer: Thread-9: 31
// Consumer: Thread-4: 31
// Consumer: Thread-1: 31
// Consumer: Thread-2: 31
// Consumer: Thread-8: 31
// Consumer: Thread-3: 31
// Consumer: Thread-1: 30
// Consumer: Thread-6: 30
// Consumer: Thread-0: 30
// Consumer: Thread-4: 30
// Consumer: Thread-8: 30
// Consumer: Thread-9: 30
// Consumer: Thread-7: 30
// Consumer: Thread-5: 30
// Consumer: Thread-3: 30
// Consumer: Thread-2: 30
// Consumer: Thread-8: 29
// Consumer: Thread-1: 29
// Consumer: Thread-3: 29
// Consumer: Thread-9: 29
// Consumer: Thread-6: 29
// Consumer: Thread-0: 29
// Consumer: Thread-5: 29
// Consumer: Thread-7: 29
// Consumer: Thread-4: 29
// Consumer: Thread-2: 29
// Consumer: Thread-1: 28
// Consumer: Thread-6: 28
// Consumer: Thread-5: 28
// Consumer: Thread-4: 28
// Consumer: Thread-8: 28
// Consumer: Thread-9: 28
// Consumer: Thread-3: 28
// Consumer: Thread-0: 28
// Consumer: Thread-2: 28
// Consumer: Thread-7: 28
// Consumer: Thread-1: 27
// Consumer: Thread-8: 27
// Consumer: Thread-3: 27
// Consumer: Thread-6: 27
// Consumer: Thread-9: 27
// Consumer: Thread-0: 27
// Consumer: Thread-4: 27
// Consumer: Thread-5: 27
// Consumer: Thread-7: 27
// Consumer: Thread-2: 27
// Consumer: Thread-6: 26
// Consumer: Thread-2: 26
// Consumer: Thread-3: 26
// Consumer: Thread-0: 26
// Consumer: Thread-8: 26
// Consumer: Thread-4: 26
// Consumer: Thread-9: 26
// Consumer: Thread-5: 26
// Consumer: Thread-7: 26
// Consumer: Thread-1: 26
// Consumer: Thread-9: 25
// Consumer: Thread-8: 25
// Consumer: Thread-7: 25
// Consumer: Thread-4: 25
// Consumer: Thread-6: 25
// Consumer: Thread-3: 25
// Consumer: Thread-5: 25
// Consumer: Thread-2: 25
// Consumer: Thread-0: 25
// Consumer: Thread-1: 25
// Consumer: Thread-6: 24
// Consumer: Thread-5: 24
// Consumer: Thread-8: 24
// Consumer: Thread-3: 24
// Consumer: Thread-9: 24
// Consumer: Thread-0: 24
// Consumer: Thread-2: 24
// Consumer: Thread-7: 24
// Consumer: Thread-4: 24
// Consumer: Thread-1: 24
// Consumer: Thread-6: 23
// Consumer: Thread-3: 23
// Consumer: Thread-2: 23
// Consumer: Thread-4: 23
// Consumer: Thread-1: 23
// Consumer: Thread-5: 23
// Consumer: Thread-9: 23
// Consumer: Thread-0: 23
// Consumer: Thread-7: 23
// Consumer: Thread-8: 23
// Consumer: Thread-6: 22
// Consumer: Thread-4: 22
// Consumer: Thread-8: 22
// Consumer: Thread-3: 22
// Consumer: Thread-7: 22
// Consumer: Thread-5: 22
// Consumer: Thread-0: 22
// Consumer: Thread-2: 22
// Consumer: Thread-9: 22
// Consumer: Thread-1: 22
// Consumer: Thread-5: 21
// Consumer: Thread-0: 21
// Consumer: Thread-8: 21
// Consumer: Thread-9: 21
// Consumer: Thread-2: 21
// Consumer: Thread-4: 21
// Consumer: Thread-6: 21
// Consumer: Thread-7: 21
// Consumer: Thread-3: 21
// Consumer: Thread-1: 21
// Consumer: Thread-8: 20
// Consumer: Thread-6: 20
// Consumer: Thread-1: 20
// Consumer: Thread-7: 20
// Consumer: Thread-9: 20
// Consumer: Thread-4: 20
// Consumer: Thread-2: 20
// Consumer: Thread-0: 20
// Consumer: Thread-3: 20
// Consumer: Thread-5: 20
// Consumer: Thread-9: 19
// Consumer: Thread-1: 19
// Consumer: Thread-8: 19
// Consumer: Thread-5: 19
// Consumer: Thread-6: 19
// Consumer: Thread-0: 19
// Consumer: Thread-4: 19
// Consumer: Thread-2: 19
// Consumer: Thread-3: 19
// Consumer: Thread-7: 19
// Consumer: Thread-6: 18
// Consumer: Thread-7: 18
// Consumer: Thread-8: 18
// Consumer: Thread-2: 18
// Consumer: Thread-3: 18
// Consumer: Thread-1: 18
// Consumer: Thread-9: 18
// Consumer: Thread-5: 18
// Consumer: Thread-0: 18
// Consumer: Thread-4: 18
// Consumer: Thread-4: 17
// Consumer: Thread-3: 17
// Consumer: Thread-6: 17
// Consumer: Thread-5: 17
// Consumer: Thread-7: 17
// Consumer: Thread-9: 17
// Consumer: Thread-8: 17
// Consumer: Thread-0: 17
// Consumer: Thread-2: 17
// Consumer: Thread-1: 17
// Consumer: Thread-0: 16
// Consumer: Thread-4: 16
// Consumer: Thread-8: 16
// Consumer: Thread-1: 16
// Consumer: Thread-3: 16
// Consumer: Thread-5: 16
// Consumer: Thread-9: 16
// Consumer: Thread-6: 16
// Consumer: Thread-7: 16
// Consumer: Thread-2: 16
// Consumer: Thread-9: 15
// Consumer: Thread-0: 15
// Consumer: Thread-8: 15
// Consumer: Thread-1: 15
// Consumer: Thread-3: 15
// Consumer: Thread-2: 15
// Consumer: Thread-7: 15
// Consumer: Thread-5: 15
// Consumer: Thread-4: 15
// Consumer: Thread-6: 15
// Consumer: Thread-0: 14
// Consumer: Thread-8: 14
// Consumer: Thread-2: 14
// Consumer: Thread-9: 14
// Consumer: Thread-1: 14
// Consumer: Thread-3: 14
// Consumer: Thread-6: 14
// Consumer: Thread-5: 14
// Consumer: Thread-4: 14
// Consumer: Thread-7: 14
// Consumer: Thread-3: 13
// Consumer: Thread-5: 13
// Consumer: Thread-6: 13
// Consumer: Thread-0: 13
// Consumer: Thread-2: 13
// Consumer: Thread-9: 13
// Consumer: Thread-8: 13
// Consumer: Thread-7: 13
// Consumer: Thread-1: 13
// Consumer: Thread-4: 13
// Consumer: Thread-0: 12
// Consumer: Thread-1: 12
// Consumer: Thread-7: 12
// Consumer: Thread-2: 12
// Consumer: Thread-5: 12
// Consumer: Thread-9: 12
// Consumer: Thread-8: 12
// Consumer: Thread-6: 12
// Consumer: Thread-3: 12
// Consumer: Thread-4: 12
// Consumer: Thread-9: 11
// Consumer: Thread-6: 11
// Consumer: Thread-8: 11
// Consumer: Thread-7: 11
// Consumer: Thread-0: 11
// Consumer: Thread-5: 11
// Consumer: Thread-2: 11
// Consumer: Thread-4: 11
// Consumer: Thread-3: 11
// Consumer: Thread-1: 11
// Consumer: Thread-6: 10
// Consumer: Thread-4: 10
// Consumer: Thread-8: 10
// Consumer: Thread-7: 10
// Consumer: Thread-9: 10
// Consumer: Thread-3: 10
// Consumer: Thread-0: 10
// Consumer: Thread-5: 10
// Consumer: Thread-1: 10
// Consumer: Thread-2: 10
// Consumer: Thread-1: 9
// Consumer: Thread-3: 9
// Consumer: Thread-7: 9
// Consumer: Thread-8: 9
// Consumer: Thread-6: 9
// Consumer: Thread-2: 9
// Consumer: Thread-9: 9
// Consumer: Thread-4: 9
// Consumer: Thread-0: 9
// Consumer: Thread-5: 9
// Consumer: Thread-5: 8
// Consumer: Thread-6: 8
// Consumer: Thread-7: 8
// Consumer: Thread-2: 8
// Consumer: Thread-3: 8
// Consumer: Thread-8: 8
// Consumer: Thread-1: 8
// Consumer: Thread-4: 8
// Consumer: Thread-0: 8
// Consumer: Thread-9: 8
// Consumer: Thread-6: 7
// Consumer: Thread-1: 7
// Consumer: Thread-5: 7
// Consumer: Thread-0: 7
// Consumer: Thread-7: 7
// Consumer: Thread-9: 7
// Consumer: Thread-4: 7
// Consumer: Thread-2: 7
// Consumer: Thread-8: 7
// Consumer: Thread-3: 7
// Consumer: Thread-7: 6
// Consumer: Thread-2: 6
// Consumer: Thread-6: 6
// Consumer: Thread-8: 6
// Consumer: Thread-0: 6
// Consumer: Thread-9: 6
// Consumer: Thread-1: 6
// Consumer: Thread-4: 6
// Consumer: Thread-5: 6
// Consumer: Thread-3: 6
// Consumer: Thread-2: 5
// Consumer: Thread-6: 5
// Consumer: Thread-4: 5
// Consumer: Thread-9: 5
// Consumer: Thread-5: 5
// Consumer: Thread-7: 5
// Consumer: Thread-0: 5
// Consumer: Thread-3: 5
// Consumer: Thread-1: 5
// Consumer: Thread-8: 5
// Consumer: Thread-7: 4
// Consumer: Thread-1: 4
// Consumer: Thread-2: 4
// Consumer: Thread-6: 4
// Consumer: Thread-0: 4
// Consumer: Thread-8: 4
// Consumer: Thread-5: 4
// Consumer: Thread-3: 4
// Consumer: Thread-4: 4
// Consumer: Thread-9: 4
// Consumer: Thread-9: 3
// Consumer: Thread-5: 3
// Consumer: Thread-7: 3
// Consumer: Thread-6: 3
// Consumer: Thread-1: 3
// Consumer: Thread-0: 3
// Consumer: Thread-3: 3
// Consumer: Thread-4: 3
// Consumer: Thread-2: 3
// Consumer: Thread-8: 3
// Consumer: Thread-3: 2
// Consumer: Thread-9: 2
// Consumer: Thread-2: 2
// Consumer: Thread-5: 2
// Consumer: Thread-4: 2
// Consumer: Thread-6: 2
// Consumer: Thread-8: 2
// Consumer: Thread-0: 2
// Consumer: Thread-7: 2
// Consumer: Thread-1: 2
// Consumer: Thread-5: 1
// Consumer: Thread-9: 1
// Consumer: Thread-0: 1
// Consumer: Thread-3: 1
// Consumer: Thread-8: 1
// Consumer: Thread-1: 1
// Consumer: Thread-2: 1
// Consumer: Thread-4: 1
// Consumer: Thread-7: 1
// Consumer: Thread-6: 1
// Consumer: Thread-4: 0
// Consumer: Thread-0: 0
// Consumer: Thread-1: 0
// Consumer: Thread-2: 0
// Consumer: Thread-6: 0
// Consumer: Thread-8: 0
// Consumer: Thread-5: 0
// Consumer: Thread-9: 0
// Consumer: Thread-7: 0
// Consumer: Thread-3: 0
// Main: Buffer: Consumer count: 1
// Consumer: Core Event 2: 0
// Main: End of the program

