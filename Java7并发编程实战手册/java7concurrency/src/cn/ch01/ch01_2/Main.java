package cn.ch01.ch01_2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

// 4.编写范例的主类。创建一个名为Main的类，创建的时候同时生成main()方法
public class Main {

    public static void main(String[] args) {
        // 5.创建一个容量为10的线程数组，以用来存储线程;
        // 创建一个容量为10的Thread.State数组，以用来存放这10个线程运行时的状态
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];

        // 6.创建10个Calculator对象，为每个对象都设置不同的数字，然后使用它们作为Thread构造器的参数来创建10个线程对象
        // 并且将其中5个线程的优先级设置为最高，另外5个线程的优先级设置为最低
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread " + i);
        }
        // 7.创建一个PrintWriter对象，用来把线程的状态演变写入到文件中
        try (FileWriter file = new FileWriter(".\\src\\cn\\ch01\\ch01_2\\log.txt");
             PrintWriter pw = new PrintWriter(file);) {
            // 8.把这10个线程的状态写入文件中。现在线程的状态是NEW
            for (int i = 0; i < 10; i++) {
                pw.println("Main: Status of Thread " + i + " : " + threads[i].getState());
                states[i] = threads[i].getState();
            }
            pw.flush();

            // 9.开始执行10个线程
            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            // 10.直到10个线程都运行完成，我们就可以查看他们的状态
            // 所有任何一个线程的状态发生了变化，我们就会将它写入到文件中
            boolean finish = false;

            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != states[i]) {
                        writeThreadInfo(pw, threads[i], states[i]);
                        states[i] = threads[i].getState();
                    }
                }
                finish = true;
                for (int i = 0; i < 10; i++) {
                    finish = finish && (threads[i].getState() == State.TERMINATED);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 11.编写writeThreadInfo()方法，用来写下线程的ID、名称、优先级、旧的状态和新的状态
    private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
        pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority: %d\n", thread.getPriority());
        pw.printf("Main : Old State : %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : ***********************************\n");
        pw.flush();
    }
}

// 可能因为我的电脑CPU核数较多（4），导致优先级高的（10）没有明显比优先级低的（1）线程提早结束

// Thread类的属性存储了线程的所有信息。JVM使用线程的priority 属性来决定某-刻
// 由哪个线程来使用CPU，并且根据线程的情景为它们设置实际状态
// 如果没有为线程指定一个名字，JVM将自动给它分配-一个名字，格式是Thread-XX
// 其中XX是一组数字。线程的ID和状态是不允许被修改的，线程类没有提供setIld()和setStatus(方法来修改它们

// Thread 0: 0 * 1 = 0
// Thread 0: 0 * 2 = 0
// Thread 0: 0 * 3 = 0
// Thread 0: 0 * 4 = 0
// Thread 0: 0 * 5 = 0
// Thread 0: 0 * 6 = 0
// Thread 0: 0 * 7 = 0
// Thread 0: 0 * 8 = 0
// Thread 0: 0 * 9 = 0
// Thread 0: 0 * 10 = 0
// Thread 1: 1 * 1 = 1
// Thread 1: 1 * 2 = 2
// Thread 1: 1 * 3 = 3
// Thread 1: 1 * 4 = 4
// Thread 1: 1 * 5 = 5
// Thread 1: 1 * 6 = 6
// Thread 1: 1 * 7 = 7
// Thread 1: 1 * 8 = 8
// Thread 1: 1 * 9 = 9
// Thread 1: 1 * 10 = 10
// Thread 9: 9 * 1 = 9
// Thread 9: 9 * 2 = 18
// Thread 9: 9 * 3 = 27
// Thread 9: 9 * 4 = 36
// Thread 9: 9 * 5 = 45
// Thread 9: 9 * 6 = 54
// Thread 9: 9 * 7 = 63
// Thread 9: 9 * 8 = 72
// Thread 9: 9 * 9 = 81
// Thread 9: 9 * 10 = 90
// Thread 7: 7 * 1 = 7
// Thread 7: 7 * 2 = 14
// Thread 7: 7 * 3 = 21
// Thread 7: 7 * 4 = 28
// Thread 7: 7 * 5 = 35
// Thread 7: 7 * 6 = 42
// Thread 7: 7 * 7 = 49
// Thread 7: 7 * 8 = 56
// Thread 7: 7 * 9 = 63
// Thread 7: 7 * 10 = 70
// Thread 5: 5 * 1 = 5
// Thread 5: 5 * 2 = 10
// Thread 5: 5 * 3 = 15
// Thread 5: 5 * 4 = 20
// Thread 5: 5 * 5 = 25
// Thread 5: 5 * 6 = 30
// Thread 5: 5 * 7 = 35
// Thread 5: 5 * 8 = 40
// Thread 5: 5 * 9 = 45
// Thread 5: 5 * 10 = 50
// Thread 3: 3 * 1 = 3
// Thread 8: 8 * 1 = 8
// Thread 8: 8 * 2 = 16
// Thread 8: 8 * 3 = 24
// Thread 8: 8 * 4 = 32
// Thread 8: 8 * 5 = 40
// Thread 8: 8 * 6 = 48
// Thread 8: 8 * 7 = 56
// Thread 8: 8 * 8 = 64
// Thread 8: 8 * 9 = 72
// Thread 8: 8 * 10 = 80
// Thread 6: 6 * 1 = 6
// Thread 6: 6 * 2 = 12
// Thread 6: 6 * 3 = 18
// Thread 6: 6 * 4 = 24
// Thread 6: 6 * 5 = 30
// Thread 6: 6 * 6 = 36
// Thread 6: 6 * 7 = 42
// Thread 6: 6 * 8 = 48
// Thread 6: 6 * 9 = 54
// Thread 6: 6 * 10 = 60
// Thread 4: 4 * 1 = 4
// Thread 4: 4 * 2 = 8
// Thread 4: 4 * 3 = 12
// Thread 4: 4 * 4 = 16
// Thread 4: 4 * 5 = 20
// Thread 4: 4 * 6 = 24
// Thread 4: 4 * 7 = 28
// Thread 4: 4 * 8 = 32
// Thread 4: 4 * 9 = 36
// Thread 4: 4 * 10 = 40
// Thread 2: 2 * 1 = 2
// Thread 2: 2 * 2 = 4
// Thread 2: 2 * 3 = 6
// Thread 2: 2 * 4 = 8
// Thread 2: 2 * 5 = 10
// Thread 2: 2 * 6 = 12
// Thread 2: 2 * 7 = 14
// Thread 2: 2 * 8 = 16
// Thread 2: 2 * 9 = 18
// Thread 2: 2 * 10 = 20
// Thread 3: 3 * 2 = 6
// Thread 3: 3 * 3 = 9
// Thread 3: 3 * 4 = 12
// Thread 3: 3 * 5 = 15
// Thread 3: 3 * 6 = 18
// Thread 3: 3 * 7 = 21
// Thread 3: 3 * 8 = 24
// Thread 3: 3 * 9 = 27
// Thread 3: 3 * 10 = 30