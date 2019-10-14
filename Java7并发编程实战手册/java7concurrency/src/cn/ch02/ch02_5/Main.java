package cn.ch02.ch02_5;

// 14.创建范例的主类Main，它包含main()方法
public class Main {
    public static void main(String[] args) {
        // 15.创建一个价格信息PricesInfo对象
        PricesInfo pricesInfo = new PricesInfo();

        // 16.创建5个读取类Reader对象，并把每一个对象作为传入参数创建线程
        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        // 17. 创建一个写入类Writer对象，并把它作为传入参数创建线程
        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        // 18. 启动这6个线程
        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
        }
        threadWriter.start();
    }
}

// 像我们之前提到的，ReentrantReadWriteLock类有两种锁：一种是读操作锁，另一种是写操作锁
// 读操作锁是通过ReadWriteLock接口的readLock()方法获取的，这个锁实现了Lock接口，所以我们可以使用lock(),unlock()和tryLock()方法
// 写操作锁是通过ReadWriteLock接口的writeLock()方法获取的，这个锁同样也实现了Lock接口，所以我们也可以使用lock()、unlock()和tryLock()方法
// 编程人员应该确保正确地使用这些锁，使用它们的时候应该符合这些锁的设计初衷。当你获取Lock接口的读锁时，不可以进行修改操作，否则将引起数据不一致的错误

// Thread-0: Price 1:1.000000
// Thread-0: Price 2:2.000000
// Thread-4: Price 1:1.000000
// Thread-1: Price 1:1.000000
// Thread-3: Price 1:1.000000
// Thread-2: Price 1:1.000000
// Writer: Attempt to modify the prices.
// Thread-2: Price 2:2.000000
// Thread-3: Price 2:2.000000
// Thread-1: Price 2:2.000000
// Thread-4: Price 2:2.000000
// Thread-0: Price 1:1.000000
// Thread-0: Price 2:2.834658
// Thread-4: Price 1:6.185608
// Writer: Prices have been modified.
// Thread-1: Price 1:1.000000
// Thread-3: Price 1:1.000000
// Thread-2: Price 1:1.000000
// Thread-3: Price 2:2.834658
// Thread-1: Price 2:2.834658
// Thread-4: Price 2:2.834658
// Thread-0: Price 1:6.185608
// Thread-4: Price 1:6.185608
// Thread-1: Price 1:6.185608
// Thread-3: Price 1:6.185608
// Thread-2: Price 2:2.834658
// Thread-3: Price 2:2.834658
// Thread-1: Price 2:2.834658
// Thread-4: Price 2:2.834658
// Thread-0: Price 2:2.834658
// Thread-4: Price 1:6.185608
// Thread-1: Price 1:6.185608
// Thread-3: Price 1:6.185608
// Thread-2: Price 1:6.185608
// Thread-3: Price 2:2.834658
// Thread-1: Price 2:2.834658
// Thread-4: Price 2:2.834658
// Thread-4: Price 1:6.185608
// Writer: Attempt to modify the prices.
// Thread-0: Price 1:6.185608
// Writer: Prices have been modified.
// Thread-4: Price 2:2.834658
// Thread-1: Price 1:6.185608
// Thread-3: Price 1:6.185608
// Thread-2: Price 2:2.834658
// Thread-3: Price 2:4.720921
// Thread-1: Price 2:4.720921
// Thread-4: Price 1:7.913131
// Thread-0: Price 2:4.720921
// Thread-0: Price 1:7.913131
// Thread-4: Price 2:4.720921
// Thread-1: Price 1:7.913131
// Thread-3: Price 1:7.913131
// Thread-2: Price 1:7.913131
// Thread-3: Price 2:4.720921
// Thread-1: Price 2:4.720921
// Thread-4: Price 1:7.913131
// Thread-0: Price 2:4.720921
// Thread-4: Price 2:4.720921
// Thread-1: Price 1:7.913131
// Thread-3: Price 1:7.913131
// Thread-2: Price 2:4.720921
// Thread-3: Price 2:4.720921
// Thread-1: Price 2:4.720921
// Thread-4: Price 1:7.913131
// Writer: Attempt to modify the prices.
// Thread-0: Price 1:7.913131
// Writer: Prices have been modified.
// Thread-4: Price 2:4.720921
// Thread-1: Price 1:7.913131
// Thread-3: Price 1:7.913131
// Thread-2: Price 1:7.913131
// Thread-3: Price 2:4.271891
// Thread-1: Price 2:4.271891
// Thread-4: Price 1:3.833965
// Thread-0: Price 2:4.271891
// Thread-4: Price 2:4.271891
// Thread-1: Price 1:3.833965
// Thread-3: Price 1:3.833965
// Thread-2: Price 2:4.271891
// Thread-3: Price 2:4.271891
// Thread-3: Price 1:3.833965
// Thread-1: Price 2:4.271891
// Thread-4: Price 1:3.833965
// Thread-0: Price 1:3.833965
// Thread-4: Price 2:4.271891
// Thread-1: Price 1:3.833965
// Thread-3: Price 2:4.271891
// Thread-2: Price 1:3.833965
// Thread-1: Price 2:4.271891
// Thread-0: Price 2:4.271891
// Thread-2: Price 2:4.271891
// Thread-0: Price 1:3.833965
// Thread-2: Price 1:3.833965
// Thread-0: Price 2:4.271891
// Thread-2: Price 2:4.271891
// Thread-0: Price 1:3.833965
// Thread-2: Price 1:3.833965
// Thread-0: Price 2:4.271891
// Thread-2: Price 2:4.271891
// Thread-0: Price 1:3.833965
// Thread-2: Price 1:3.833965
// Thread-0: Price 2:4.271891
// Thread-2: Price 2:4.271891
// Thread-2: Price 1:3.833965
// Thread-2: Price 2:4.271891