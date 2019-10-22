package cn.ch06.ch06_1;

import java.util.concurrent.ConcurrentLinkedDeque;

// 将要实现的范例包括以下两个不同的任务：
// ·添加大量的数据到一个列表中
// ·从同一个列表中移除大量的数据

// 使用非阻塞式线程安全列表

// 9.创建范例的主类Main，并添加main()方法
public class Main {

    public static void main(String[] args) {
        // 10.创建ConcurrentLinkedDeque对象，并指定它的泛型参数是String型的
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        // 11.创建线程数组threads，它包含100个线程
        Thread[] threads = new Thread[100];
        // 12.创建100个AddTask对象及其对应的运行线程。将每个线程存放到上一步创建的数组中，然后启动线程
        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);
        // 13.使用join()方法等待线程完成
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 14.将列表的元素数量打印到控制台
        System.out.printf("Main: Size of the List: %d\n", list.size());
        // 15.创建100个PollTask对象及其对应的运行线程。将每个线程存放到上一步创建的数组中，然后启动线程
        for (int i = 0; i < threads.length; i++) {
            PollTask task = new PollTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);
        // 16.使用join()方法等待线程完成
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 17.将列表的元素数量打印到控制台
        System.out.printf("Main: Size of the List: %d\n", list.size());
    }
}

// 首先，执行100个AddTask任务将元素添加到ConcurrentLinkedDeque对象list中
// 每个任务使用add()方法向这个列表中插入10,000个元素。add()方法将新元素添加到列表尾部
// 当所有任务运行完毕，列表中的元素数量将被打印到控制台
// 在这一刻，列表中有1,000,000个元素
// 接下来，执行100个PollTask任务将元素从列表中移除
// 每个任务使用pollFirst()和pollLast()方法从列表中移除10,000个元素
// polFirst()方法返回并移除列表中的第一个元素，pollLast()方法返回并移除列表中的最后一个元素
// 如果列表为空，这些方法返回null
// 当所有任务运行完毕，列表中的元素数量将被打印到控制台
// 在这一刻，列表中有0个元素

// 使用size()方法输出列表中的元素数量。需要注意的是，这个方法返回的值可能不是真实的
// 尤其当有线程在添加数据或移除数据时，这个方法需要遍历整个列表来计算元素数量，而遍历过的数据可能已经改变
// 仅当没有任何线程修改列表时，才能保证返回的结果是准确的

// ConcurrentLinkedDeque类提供了其他从列表中读取数据的方法
// ·getFirst()和getLast()：分别返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，这两个方法抛出NoSuchElementExcpetion异常
// ·peek()、 peekFirst()和peekLast()：分别返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，这些方法返回null
// ·remove()、removeFirst()和removeLastO：分别返回列表中第一个和最后一个元素，返回的元素将会从列表中移除。如果列表为空，这些方法抛出NoSuchElementExcpetion异常

// Main: 100 AddTask threads have been launched
// Main: Size of the List: 1000000
// Main: 100 PollTask threads have been launched
// Main: Size of the List: 0