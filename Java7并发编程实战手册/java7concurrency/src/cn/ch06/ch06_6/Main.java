package cn.ch06.ch06_6;

// 4.创建本范例的主类Main，并实现main()方法

// 生成并发随机数
public class Main {

    public static void main(String[] args) {
        // 5.创建一个长度为3的线程数组
        Thread[] threads = new Thread[3];
        // 6.创建3个TaskLocalRandom对象task，并作为传入参数生成线程。将生成的线程存放到上一步创建的线程数组中
        for (int i = 0; i < 3; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }
    }
}

// 本例的核心是TaskLocalRandom类
// 在类的构造器中，调用了TaskLocalRandom类的current()方法
// current()方法是一个静态方法，返回与当前线程关联的TaskLocalRandom对象，所以可以使用这个对象生成随机数
// 如果调用这个方法的线程还没有关联随机数对象，就会生成一个新的

// 在本例中，使用这个方法初始化与本任务关联的随机数生成器，所以它将在下一次调用这个方法时被创建
// 在TaskLocalRandom类的run()方法中，调用current()获取与本线程关联的随机数生成器，同时也调用了nextInt()方法并以数字10作为传入参数
// 这个方法返回一个介于0到10之间的伪随机数。每个任务生成10个随机数

// TaskLocalRandom类也提供了方法来生成long、float和double数字和Boolean值
// 还可以为方法指定一个数字作为输入参数，来生成介于0与该数字之间的随机数
// 还可以为方法指定两个数字作为输入参数，来生成介于两个参数之间的随机数

// Thread-0: 9
// Thread-1: 0
// Thread-0: 5
// Thread-1: 3
// Thread-0: 1
// Thread-1: 2
// Thread-0: 9
// Thread-1: 3
// Thread-0: 1
// Thread-0: 1
// Thread-0: 8
// Thread-1: 6
// Thread-0: 0
// Thread-1: 7
// Thread-0: 4
// Thread-1: 1
// Thread-0: 6
// Thread-1: 7
// Thread-1: 0
// Thread-1: 3
// Thread-2: 8
// Thread-2: 0
// Thread-2: 3
// Thread-2: 8
// Thread-2: 1
// Thread-2: 0
// Thread-2: 7
// Thread-2: 9
// Thread-2: 6
// Thread-2: 1