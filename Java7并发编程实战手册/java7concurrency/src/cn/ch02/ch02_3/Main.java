package cn.ch02.ch02_3;

// 这个范例中将使用synchronized关键字和wait()、notify()及notifyAll()方法
// 11.实现范例的主程序，创建一个带有main()方法的主类Main
public class Main {
    public static void main(String[] args) {
        // 12.创建一个数据存储EventStorage对象
        EventStorage storage = new EventStorage();

        // 13.创建一个生产者Producer对象，并用它作为传入参数创建一个线程
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);

        // 14.创建一个消费者Consumer对象，并用它作为传入参数创建一个线程
        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        // 15.启动这两个线程
        thread2.start();
        thread1.start();
    }
}

// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
// Set: 1
// Set: 2
// Set: 3
// Set: 4
// Set: 5
// Set: 6
// Set: 7
// Set: 8
// Set: 9
// Set: 10
// Get:10: Mon Oct 14 01:09:18 CST 2019
// Get:9: Mon Oct 14 01:09:18 CST 2019
// Get:8: Mon Oct 14 01:09:18 CST 2019
// Get:7: Mon Oct 14 01:09:18 CST 2019
// Get:6: Mon Oct 14 01:09:18 CST 2019
// Get:5: Mon Oct 14 01:09:18 CST 2019
// Get:4: Mon Oct 14 01:09:18 CST 2019
// Get:3: Mon Oct 14 01:09:18 CST 2019
// Get:2: Mon Oct 14 01:09:18 CST 2019
// Get:1: Mon Oct 14 01:09:18 CST 2019
