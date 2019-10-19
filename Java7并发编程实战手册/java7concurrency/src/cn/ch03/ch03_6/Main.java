package cn.ch03.ch03_6;

// 这个范例实现了自己的Phaser类，并且覆盖onAdvance()方法在每个阶段改变的时候执行一些操作
// 这个范例将模拟考试，考生必须做三道试题，只有当所有学生都完成一道试题的时候，才能继续下一个

// 17.实现范例的主类Main，并实现main()方法
public class Main {

    public static void main(String[] args) {
        // 18.创建MyPhaser对象
        MyPhaser phaser = new MyPhaser();

        // 19.创建5个学生类Student对象，并且通过register()方法将他们注册到phaser对象中
        Student students[] = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(phaser);
            phaser.register();
        }
        // 20.将创建的Student对象逐个作为传入参数创建线程，并且启动它们
        Thread threads[] = new Thread[students.length];
        for (int i = 0; i < students.length; i++) {
            threads[i] = new Thread(students[i], "Student " + i);
            threads[i].start();
        }
        // 21.等待5个线程的完成
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 22.通过调用isTerminated()方法，将phaser是否处于终止态的信息打印到控制台
        System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
    }
}

// 这个范例模拟了有三道试题的考试过程
// 所有的学生必须做完第-道题才可以开始做第二道
// 为了实现同步，我们使用了Phaser类,并且通过继承Phaser类和覆盖onAdvance()方法，实现了自己的phaser

// phaser对象进行阶段切换的时候，在所有在arriveAndAwaitAdvance()方法里休眠的
// 线程被唤醒之前，onAdvance()方法将被自动调用这个方法的传入参数是当前阶段序号
// 其中0表示第一个阶段，另一个传入参数是注册的参与者其中当前阶段序号最有用
// 如果要根据阶段序号执行不同的操作，那么就必须使用一个可选择的结构(if/else或者switch)来选择要执行的操作

// 在本范例中，我们使用switch 结构为每个阶段来选择不同的方法

// onAdvance()方法返回布尔值以表明phaser终止与否，false 表示没有终止，因而线程可以继续执行其他的阶段
// 如果返回值是true,则phaser仍然唤醒等待的线程，但是状态已经改变成终止状态
// 所以继续调用phaser的方法将立即返回，并且isTerminated()方法也将返回true
// 在主类中，创建MyPhaser对象时，并没有指定phaser的参与者数目，但是每个学生对象都调用了phaser的register()方法，这将在phaser中注册
// 这个调用并没有建立学生对象或者它对应的执行线程与phaser之间的关联
// 实际上, phaser 中的参与者数目只是一个数字，phaser与参与者不存在任何关联

// Student 1: Has arrived to do the exam. Sat Oct 19 12:36:49 CST 2019
// Student 3: Has arrived to do the exam. Sat Oct 19 12:36:49 CST 2019
// Student 4: Has arrived to do the exam. Sat Oct 19 12:36:49 CST 2019
// Student 2: Has arrived to do the exam. Sat Oct 19 12:36:49 CST 2019
// Student 0: Has arrived to do the exam. Sat Oct 19 12:36:49 CST 2019
// Phaser: The exam are going to start. The students are ready.
// Phaser: We have 5 students.
// Student 0: Is going to do the first exercise. Sat Oct 19 12:36:49 CST 2019
// Student 1: Is going to do the first exercise. Sat Oct 19 12:36:49 CST 2019
// Student 4: Is going to do the first exercise. Sat Oct 19 12:36:49 CST 2019
// Student 3: Is going to do the first exercise. Sat Oct 19 12:36:49 CST 2019
// Student 2: Is going to do the first exercise. Sat Oct 19 12:36:49 CST 2019
// Student 2: Has done the first exercise. Sat Oct 19 12:36:49 CST 2019
// Student 1: Has done the first exercise. Sat Oct 19 12:36:50 CST 2019
// Student 3: Has done the first exercise. Sat Oct 19 12:36:50 CST 2019
// Student 0: Has done the first exercise. Sat Oct 19 12:36:56 CST 2019
// Student 4: Has done the first exercise. Sat Oct 19 12:36:58 CST 2019
// Phaser: All the students have finished the first exercise.
// Phaser: It's time for the second one.
// Student 0: Is going to do the second exercise. Sat Oct 19 12:36:58 CST 2019
// Student 2: Is going to do the second exercise. Sat Oct 19 12:36:58 CST 2019
// Student 4: Is going to do the second exercise. Sat Oct 19 12:36:58 CST 2019
// Student 3: Is going to do the second exercise. Sat Oct 19 12:36:58 CST 2019
// Student 1: Is going to do the second exercise. Sat Oct 19 12:36:58 CST 2019
// STUDENT 0: Has done the second exercise. Sat Oct 19 12:36:58 CST 2019
// STUDENT 1: Has done the second exercise. Sat Oct 19 12:36:59 CST 2019
// STUDENT 3: Has done the second exercise. Sat Oct 19 12:37:00 CST 2019
// STUDENT 2: Has done the second exercise. Sat Oct 19 12:37:01 CST 2019
// STUDENT 4: Has done the second exercise. Sat Oct 19 12:37:07 CST 2019
// Phaser: Al1 the students have finished the second exercise.
// Phaser: It's time for the third one.
// STUDENT 1: Is going to do the third exercise. Sat Oct 19 12:37:07 CST 2019
// STUDENT 4: Is going to do the third exercise. Sat Oct 19 12:37:07 CST 2019
// STUDENT 0: Is going to do the third exercise. Sat Oct 19 12:37:07 CST 2019
// STUDENT 2: Is going to do the third exercise. Sat Oct 19 12:37:07 CST 2019
// STUDENT 3: Is going to do the third exercise. Sat Oct 19 12:37:07 CST 2019
// Student 4: Has finished the exam. Sat Oct 19 12:37:08 CST 2019
// Student 1: Has finished the exam. Sat Oct 19 12:37:10 CST 2019
// Student 3: Has finished the exam. Sat Oct 19 12:37:11 CST 2019
// Student 0: Has finished the exam. Sat Oct 19 12:37:14 CST 2019
// Student 2: Has finished the exam. Sat Oct 19 12:37:15 CST 2019
// Phaser: All the students have finished the exam.
// Phaser: Thank you for your time.
// Main: The phaser has finished: true.
