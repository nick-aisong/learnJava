package cn.ch06.ch06_2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
// 将要实现的范例包括以下两个不同的任务：
// ·添加大量的数据到一个列表中
// ·从同一个列表中移除大量的数据

// 5.创建范例的主类Main，并添加main()方法

// 使用阻塞式线程安全列表
public class Main {

    public static void main(String[] args) throws Exception {
        // 6.声明并创建LinkedBlockingDeque属性list, 并指定它的泛型参数是String型的
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);
        // 7.将client作为传入参数创建线程Thread并启动
        Client client = new Client(list);
        Thread thread = new Thread(client);
        thread.start();
        // 8.使用list对象的take()方法, 每300毫秒从列表中取出3个字符串对象，重复5次。在控制台输出字符串
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String request = list.take();
                System.out.printf("Main: Request: %s at %s. Size: %d\n", request, new Date(), list.size());
            }
            TimeUnit.MILLISECONDS.sleep(300);
        }
        // 9.输出一条表示程序结束的消息
        System.out.printf("Main: End of the program.\n");
    }
}

// 本节使用的泛型参数是String的LinkedBlockingDeque对象，用来实现一个阻塞式并发数据列表
// Client类使用put()方法将字符串插入到列表中
// 如果列表已满(列表生成时指定了固定的容量)，调用这个方法的线程将被阻塞直到列表中有了可用的空间
// Main类使用take()方法从列表中取字符串。如果列表为空，调用这个方法的线程将被，阻塞直到列表不为空(即有可用的元素)
// 这个例子中使用了LinkedBlockingDeque对象的两个方法，调用它们的线程可能会被阻塞
// 在阻塞时如果线程被中断，方法会抛出InterruptedException异常，所以必须捕获和处理这个异常

// LinkedBlockingDeque类也提供了其他存取元素的方法，这些方法不会引起阻塞，而是抛出异常或返回null
// ·takeFirst()和takeLast()：分别返回列表中第一个和最后一个元素，返回的元素会从列表中移除。如果列表为空，调用方法的线程将被阻塞直到列表中有可用的元素出现
// ·getFirst()和getLast()：分别返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，则抛出NoSuchElementExcpetinon异常
// ·peek()、 peekFirst()和peekLast()：分别返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，返回null
// ·poll()、 pollFirst()和 pollLast()：分别返回列表中第一个和最后一个元素，返回的元素将会从列表中移除。如果列表为空，返回null
// ·add()、addFirst()和addLast()：分别将元素添加到列表中第一位和最后一位。如果列表已满(列表生成时指定了固定的容量)，这些方法将抛出IllegalStateException异常

// Main: Request: 0:0 at Wed Oct 23 23:04:25 CST 2019. Size: 0
// Client: 0:0 at Wed Oct 23 23:04:25 CST 2019. Size: 0
// Client: 0:1 at Wed Oct 23 23:04:25 CST 2019. Size: 1
// Main: Request: 0:1 at Wed Oct 23 23:04:25 CST 2019. Size: 0
// Client: 0:2 at Wed Oct 23 23:04:25 CST 2019. Size: 1
// Main: Request: 0:2 at Wed Oct 23 23:04:25 CST 2019. Size: 0
// Client: 0:3 at Wed Oct 23 23:04:25 CST 2019. Size: 1
// Client: 0:4 at Wed Oct 23 23:04:25 CST 2019. Size: 2
// Main: Request: 0:3 at Wed Oct 23 23:04:25 CST 2019. Size: 1
// Main: Request: 0:4 at Wed Oct 23 23:04:25 CST 2019. Size: 0
// Client: 1:0 at Wed Oct 23 23:04:27 CST 2019. Size: 1
// Client: 1:1 at Wed Oct 23 23:04:27 CST 2019. Size: 1
// Client: 1:2 at Wed Oct 23 23:04:27 CST 2019. Size: 2
// Client: 1:3 at Wed Oct 23 23:04:27 CST 2019. Size: 3
// Main: Request: 1:0 at Wed Oct 23 23:04:27 CST 2019. Size: 0
// Main: Request: 1:1 at Wed Oct 23 23:04:27 CST 2019. Size: 2
// Main: Request: 1:2 at Wed Oct 23 23:04:27 CST 2019. Size: 2
// Client: 1:4 at Wed Oct 23 23:04:27 CST 2019. Size: 3
// Main: Request: 1:3 at Wed Oct 23 23:04:27 CST 2019. Size: 1
// Main: Request: 1:4 at Wed Oct 23 23:04:28 CST 2019. Size: 0
// Client: 2:0 at Wed Oct 23 23:04:29 CST 2019. Size: 1
// Main: Request: 2:0 at Wed Oct 23 23:04:29 CST 2019. Size: 0
// Main: Request: 2:1 at Wed Oct 23 23:04:29 CST 2019. Size: 0
// Client: 2:1 at Wed Oct 23 23:04:29 CST 2019. Size: 1
// Client: 2:2 at Wed Oct 23 23:04:29 CST 2019. Size: 1
// Client: 2:3 at Wed Oct 23 23:04:29 CST 2019. Size: 2
// Client: 2:4 at Wed Oct 23 23:04:29 CST 2019. Size: 3
// Main: Request: 2:2 at Wed Oct 23 23:04:30 CST 2019. Size: 2
// Main: Request: 2:3 at Wed Oct 23 23:04:30 CST 2019. Size: 1
// Main: Request: 2:4 at Wed Oct 23 23:04:30 CST 2019. Size: 0
// Main: End of the program.
// Client: End.





