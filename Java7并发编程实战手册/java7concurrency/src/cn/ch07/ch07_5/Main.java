package cn.ch07.ch07_5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

// 本里将学习如何实现RunnableScheduledFuture接口来执行延迟任务和周期性任务

// 19.创建名为Main的主类,并实现main()方法

//定制运行在定时线程池中的任务
public class Main {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        // 20.创建MyScheduledThreadPolExecutor对象，命名为executor
        // 创建时使用了2作为参数，这样线程池中将有两个线程
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
        // 21.创建一个Task类型的对象并命名为task。在控制台输出当前时间
        Task task = new Task();
        System.out.printf("Main: %s\n", LocalDateTime.now().format(dtf));
        // 22.使用schedule()方法发送一个延迟任务到执行器。这个任务将延迟1秒后被执行
        executor.schedule(task, 1, TimeUnit.SECONDS);
        // 23.使主线程休眠3秒
        TimeUnit.SECONDS.sleep(3);
        // 24.创建另一个Task对象。在控制台再次输出当前时间
        task = new Task();
        System.out.printf("Main: %s\n", LocalDateTime.now().format(dtf));
        // 25.使用scheduleAtFixedRate()方法发送一个周期性任务到执行器
        // 这个任务将延迟1秒后被执行，然后每3秒执行一次
        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
        // 26.使主线程休眠10秒
        TimeUnit.SECONDS.sleep(10);
        // 27.使用shutdown()方法关闭执行器。使用awaitTermination()方法等待执行器结束
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        // 28.在控制台输出一条消息表示程序结束
        System.out.printf("Main: End of the program.\n");
    }
}

// 在本节中，我们实现了定制的任务类MyScheduledTask，它能在ScheduledThreadPoolExecutor对象里执行
// 该类继承了FutureTask类并实现了RunnableScheduledFuture接口
// 之所以实现RunnableScheduledFuture接口是因为所有在定时执行器中执行的任务都必须实现这个接口
// 之所以继承FutureTask类是因为该类提供了RunnableScheduledFuture接口中声明的方法的有效实现

// 所有上面提到的接口和类都使用了泛型参数化，泛型参数是任务返回的数据类型
// 为了在定时执行器中使用MyScheduledTask任务，我们必须在MyScheduledThreadPoollExecutor类中覆盖decorateTask()方法
// MyScheduledThreadPoolExecutor类继承了ScheduledThreadPoolExecutor
// decorateTask()方法默认返回的是ScheduledThreadPoolExecutor实现的缺省定时任务
// 覆盖这个方法会将缺省定时任务替换为MyScheduledTask任务
// 所以，当实现自定义的定时任务时，必须实现自定义的定时执行器
// decorateTask()方法只是使用传入参数来创建MyScheduledTask对象
// 传入的Runnable对象将在任务中执行，执行结果也将被任务返回

// 在本例中，任务不会返回一个结果，所以结果参数使用了null作为返回值
// 这个方法默认返回的任务将执行Runnable对象
// 覆盖后新返回的对象将在池中替换默认的任务对象，并传入参数执行器用来执行这个任务

// 在本例中，使用关键字this来引用创建任务的执行器
// MyScheduledTask类既可以执行延迟任务也可以执行周期性任务
// 我们已经实现了getDelay()和run()方法，它们具有执行这两种任务的所有必需逻辑
// 对于getDelayO方法，定时执行器调用它来确定是否必须执行一个任务
// 这个方法在延迟任务和周期性任务中表现不一样
// 像之前提到的，MyScheduledTask类构造器接收的是ScheduledRunnableFuture默认实现的对象
// 用来执行传入的Runnable 对象，并把它保存为MyScheduledTask类的属性，以供MyScheduledTask类的其他方法和数据进行访问

// 如果执行的是一个延迟任务，getDelay()方法返回传入任务的延迟值，如果执行的是周期性任务，getDelay()方 法返回startDate属性与当前时间的时间差
// run()方法，即执行任务的方法。周期性任务的一个特殊性是，它必须添加到执行器的队列中作为新任务，才能被再次执行
// 所以，如果正在执行一个周期性任务，需用当前时间与任务执行周期的和重置startDate属性值，并将这个任务再次加入到执行器队列中
// startDate属性存放任务下一次执行的时间

// 接下来，使用FutureTask类提供的runAndReset()方法来执行方法
// 在延迟任务中，不需要再次把任务放入执行器队列，因为它仅执行一次

// 备注:必须考虑执行器已关闭时的情况。在这种情况下，周期性任务不需要再加入到执行器队列中

// 最后，在MyScheduledThreadPoolExecutor类中，我们覆盖了scheduleAtFixedRate()方法
// 如前所述，对于周期性任务，startDate属性必须被重置，而这需要用到任务的周期，此时我们并没有初始化它
// 所以我们必须覆盖scheduleAtFixedRate()方法，因为它接收任务的周期值，并传入到MyScheduledTask类里
// Task类使得范例更完整，它实现了Runnable接口，也是在定时执行器中执行的任务
// Main主类创建了一个MyScheduledThreadPoolExecutor执行器对象并发送给它下面的两个任务
// ·一个延迟任务，在当前时间1秒后执行
// ·一个周期性任务，第一次在当前时间1秒后执行，接下来每3秒执行一次

// ScheduledThreadPoolExecutor类提供了另外一种decorateTask()方法，它接收一个Callable对象(而不是Runnable对象)作为参数

// Main: 2019-10-26 15:07:48
// Pre-MyScheduledTask: 2019-10-26 15:07:49
// MyScheduleTask: Is Periodic: false
// Task: Begin.
// Task: End.
// Post-MyScheduledTask: 2019-10-26 15:07:51
// Main: 2019-10-26 15:07:51
// Pre-MyScheduledTask: 2019-10-26 15:07:52
// MyScheduleTask: Is Periodic: true
// Task: Begin.
// Task: End.
// Post-MyScheduledTask: 2019-10-26 15:07:54
// Pre-MyScheduledTask: 2019-10-26 15:07:55
// MyScheduleTask: Is Periodic: true
// Task: Begin.
// Task: End.
// Post-MyScheduledTask: 2019-10-26 15:07:57
// Pre-MyScheduledTask: 2019-10-26 15:07:58
// MyScheduleTask: Is Periodic: true
// Task: Begin.
// Task: End.
// Post-MyScheduledTask: 2019-10-26 15:08:00
// Main: End of the program.