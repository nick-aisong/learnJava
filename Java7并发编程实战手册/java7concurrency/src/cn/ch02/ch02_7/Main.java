package cn.ch02.ch02_7;

// 20.实现范例的主程序Main，并为它添加main()方法
public class Main {
    public static void main(String[] args) {
        // 21.创建一个文件类FileMock对象
        FileMock mock = new FileMock(100, 10);

        // 22.创建一个缓冲区Buffer对象
        Buffer buffer = new Buffer(20);

        // 23.创建一个生产者对象Producer，并将它作为传入参数创建线程
        Producer producer = new Producer(mock, buffer);
        Thread threadProducer = new Thread(producer, "Producer");

        // 24.创建3个消费者对象Consumer，并将它作为传入参数创建线程
        Consumer consumers[] = new Consumer[3];
        Thread threadConsumers[] = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
        }

        // 25.启动生产者和消费者对象线程
        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }
    }
}

// 与锁绑定的所有条件对象都是通过Lock接口声明的newCondition()方法创建的
// 在使用条件的时候，必须获取这个条件绑定的锁，所以带条件的代码必须在调用Lock对象的lock()方法和unlock()方法之间
// 当线程调用条件的await()方法时，它将自动释放这个条件绑定的锁，其他某个线程才可以获取这个锁并且执行相同的操作，或者执行这个锁保护的另一个临界区代码

// 备注：当一个线程调用了条件对象的signal()或者signallAll()方法后，一个或者多个在该条件上挂起的线程将被唤醒，但这并不能保证让它们挂起的条件已经满足
// 所以必须在while循环中调用await()，在条件成立之前不能离开这个循环，如果条件不成立，将再次调用await()

// 必须小心使用await()和signal()方法。如果调用了一个条件的await()方法，却从不调用它的signal()方法，这个线程将永久休眠
// 因调用await()方法进入休眠的线程可能会被中断，所以必须处理InterruptedException异常

// Condition接口还提供了await()方法的其他形式
// await(long time, TimeUnit unit),直到发生以下情况之一之前，线程将一直处于休眠状态
// ·其他某个线程中断当前线程。
// ·其他某个线程调用了将当前线程挂起的条件的singal()或signalAll()方法
// ·指定的等待时间已经过去
// ·通过TimeUnit类的常量DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、ANOSECONDS和SECONDS指定的等待时间已经过去

// awaitUninterruptibly()：它是不可中断的
// 这个线程将休眠直到其他某个线程调用了将它挂起的条件的singal0或signalAll0方法

// awaitUntil(Date date) :直到发生以下情况之一之前，线程将一直处于休眠状态

// ·其他某个线程中断当前线程
// ·其他某个线程调用了将它挂起的条件的singal()或signalAll()方法
// ·指定的最后期限到了

// 也可以将条件与读写锁ReadLock和WriteLock一起使用

// Mock: 100
// Producer: Inserted Line: 1
// Mock: 99
// Consumer 0: Line Readed: 0
// Producer: Inserted Line: 1
// Mock: 98
// Consumer 1: Line Readed: 0
// Producer: Inserted Line: 1
// Mock: 97
// Consumer 2: Line Readed: 0
// Producer: Inserted Line: 1
// Mock: 96
// Producer: Inserted Line: 2
// Mock: 95
// Producer: Inserted Line: 3
// Mock: 94
// Producer: Inserted Line: 4
// Mock: 93
// Producer: Inserted Line: 5
// Mock: 92
// Producer: Inserted Line: 6
// Mock: 91
// Producer: Inserted Line: 7
// Mock: 90
// Producer: Inserted Line: 8
// Mock: 89
// Producer: Inserted Line: 9
// Mock: 88
// Producer: Inserted Line: 10
// Mock: 87
// Producer: Inserted Line: 11
// Mock: 86
// Producer: Inserted Line: 12
// Mock: 85
// Producer: Inserted Line: 13
// Mock: 84
// Producer: Inserted Line: 14
// Mock: 83
// Producer: Inserted Line: 15
// Mock: 82
// Producer: Inserted Line: 16
// Mock: 81
// Producer: Inserted Line: 17
// Mock: 80
// Producer: Inserted Line: 18
// Mock: 79
// Producer: Inserted Line: 19
// Mock: 78
// Producer: Inserted Line: 20
// Mock: 77
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 76
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 75
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 74
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 73
// Consumer 1: Line Readed: 19
// Consumer 0: Line Readed: 18
// Producer: Inserted Line: 19
// Mock: 72
// Producer: Inserted Line: 20
// Mock: 71
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 70
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 69
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 68
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 67
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 66
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 65
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 64
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 63
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 62
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 61
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 60
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 59
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 58
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 57
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 56
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 55
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 54
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 53
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 52
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 51
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 50
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 49
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 48
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 47
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 46
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 45
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 44
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 43
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 42
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 41
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 40
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 39
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 38
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 37
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 36
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 35
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 34
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 33
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 32
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 31
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 30
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 29
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 28
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 27
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 26
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 25
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 24
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 23
// Consumer 0: Line Readed: 19
// Consumer 2: Line Readed: 18
// Producer: Inserted Line: 19
// Mock: 22
// Producer: Inserted Line: 20
// Mock: 21
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 20
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 19
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 18
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 17
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 16
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 15
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 14
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 13
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 12
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 11
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 10
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 9
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 8
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 7
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 6
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 5
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 4
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 3
// Consumer 0: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 2
// Consumer 1: Line Readed: 19
// Producer: Inserted Line: 20
// Mock: 1
// Consumer 2: Line Readed: 19
// Producer: Inserted Line: 20
// Consumer 2: Line Readed: 19
// Consumer 0: Line Readed: 18
// Consumer 1: Line Readed: 17
// Consumer 2: Line Readed: 16
// Consumer 0: Line Readed: 15
// Consumer 2: Line Readed: 14
// Consumer 1: Line Readed: 13
// Consumer 2: Line Readed: 12
// Consumer 0: Line Readed: 11
// Consumer 2: Line Readed: 10
// Consumer 1: Line Readed: 9
// Consumer 1: Line Readed: 8
// Consumer 2: Line Readed: 7
// Consumer 1: Line Readed: 6
// Consumer 0: Line Readed: 5
// Consumer 1: Line Readed: 4
// Consumer 2: Line Readed: 3
// Consumer 2: Line Readed: 2
// Consumer 0: Line Readed: 1
// Consumer 1: Line Readed: 0