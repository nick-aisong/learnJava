package cn.ch06.ch06_4;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

// 本例中，将会学习如何使用DelayQueue类来存放具有不同激活日期的event

// 12.创建范例的主类Main，并添加main()方法

// 使用带有延迟元素的线程安全列表
public class Main {

    public static void main(String[] args) throws Exception {
        // 13.创建DelayQueue对象，指定其泛型参数为Event类
        DelayQueue<Event> queue = new DelayQueue<>();
        // 14.创建一个可以存放5个Thread对象的线程数组
        Thread[] threads = new Thread[5];
        // 15.创建5个Task对象，它们有各不相同的编号，并分别作为传入参数创建线程
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i + 1, queue);
            threads[i] = new Thread(task);
        }
        // 16.执行刚创建的5个线程对象
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        // 17.使用join()方法等待所有线程结束
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        // 18.把存放在队列中的event对象输出到控制台。当队列长度大于0时，使用poll()方法获得一个Event类
        // 如果返回null，则使当前线程休眠500毫秒以等待更多event对象被激活
        do {
            int counter = 0;
            Event event;
            do {
                event = queue.poll();
                if (event != null)
                    counter++;
            } while (event != null);
            System.out.printf("At %s you have read %d events\n", new Date(), counter);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }
}

// 在本节中我们实现了Event类。它只有一个属性，即对象的激活日期，因为继承了Delayed接口，所以Event对象可以存放到DelayQueue队列中

// getDelay()方法用来计算激活日期和实际日期之间的纳秒数
// 这两个日期都是Date类的对象，并使用日期对象的getTime()方法将日期转化为毫秒数后进行比较
// 然后通过getDelay()方法的传入参数TimeUnit的convert()方法，将时间间隔转化为event激活时间的剩余纳秒数
// DelayQueue类本身是使用纳秒工作的，但是对于使用者来讲，是透明的

// 如果当前对象的延迟值小于参数对象的值，compareTo()方法将返回一个小于0的值
// 如果当前对象的延迟值大于参数对象的值，则返回一个大于0的值;如果两者的延迟值相等则返回0

// Task类已被实现，这个类有一个名为id的整型属性
// 当Task对象执行时，它添加与taskid相同数量的秒数到实际日期，作为DelayQueue类中当前task存放的event的激活日期
// 每个Task对象使用add()方法在队列中存放100个event

// 最后，在Main类的main()方法中，创建5个Task对象，并在对应线程中执行
// 当执行完时，使用poll()方法输出所有event到控制台
// poll()方法提取并移除队列中的第一个元素，如果队列中没有活动的元素，此方法返回null值
// 每调用一次poll()方法，如果返回一个 Event对象，计数器加1
// 当调用 poll()返回 null值时，输出计数器中的值到控制台，使线程休眠半秒钟以等待更多活动的event
// 当队列中存放了500个event时，程序执行完毕

// DelayQueue类还提供了其他一些方法
// ·clear()：移除队列中的所有元素
// ·offer(E e)：E是DelayQueue的泛型参数，表示传入参数的类型。这个方法把参数对应的元素插入到队列中
// ·peek()：返回队列中的第一个元素，但不将其移除
// ·take()：返回队列中的第一个元素，并将其移除。如果队列为空，线程将被阻塞直到队列中有可用的元素

// Thread 1: Thu Oct 24 00:42:49 CST 2019
// Thread 5: Thu Oct 24 00:42:53 CST 2019
// Thread 4: Thu Oct 24 00:42:52 CST 2019
// Thread 3: Thu Oct 24 00:42:51 CST 2019
// Thread 2: Thu Oct 24 00:42:50 CST 2019
// At Thu Oct 24 00:42:48 CST 2019 you have read 0 events
// At Thu Oct 24 00:42:48 CST 2019 you have read 0 events
// At Thu Oct 24 00:42:49 CST 2019 you have read 100 events
// At Thu Oct 24 00:42:49 CST 2019 you have read 0 events
// At Thu Oct 24 00:42:50 CST 2019 you have read 100 events
// At Thu Oct 24 00:42:50 CST 2019 you have read 0 events
// At Thu Oct 24 00:42:51 CST 2019 you have read 100 events
// At Thu Oct 24 00:42:51 CST 2019 you have read 0 events
// At Thu Oct 24 00:42:52 CST 2019 you have read 100 events
// At Thu Oct 24 00:42:52 CST 2019 you have read 0 events
// At Thu Oct 24 00:42:53 CST 2019 you have read 100 events