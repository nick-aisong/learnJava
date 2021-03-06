##### 本书覆盖Java 7并发API中大部分重要而有用的机制

- 线程同步机制
- 通过执行器创建和管理线程
- 通过Fork/Join框架提高应用程序的性能
- 并发编程的数据结构
- 根据需要调整一些并发类的默认行为
- 测试Java并发应用程序

#### ch01 线程管理
#### ch02 线程同步基础
#### ch03 线程同步辅助类
#### ch04 线程执行器
#### ch05 Fork/Join框架
#### ch06 并发集合
#### ch07 定制并发类
#### ch08 测试并发应用程序

线程管理
========

- 线程的创建和运行  
- 线程信息的获取和设置  
- 线程的中断  
- 线程中断的控制  
- 线程的休眠和恢复  
- 等待线程的终止  
- 守护线程的创建和运行  
- 线程中不可控异常的处理  
- 线程局部变量的使用  
- 线程的分组  
- 线程组中不可控异常的处理  
- 使用工厂类创建线程  

### 1 简介
在计算机领域中，我们说的并发(Concurrency) 是指一系列任务的同时运行。如果一
台电脑有多个处理器或者有一个多核处理器，这个同时性(Simultaneity) 是真正意义的并
发;但是一台电脑只有一个单核处理器，这个同时性并不是真正的并发  

同时打开不同程序是进程级（Process-Level)并发  
一个进程内也可以同时进行多个任务，进程内并发任务成为线程（Thread）

与并发相关的另-个概念是并行(Parallelism)  

一些学者认为并发是在单核处理器中使用多线程执行应用，与此同时你看到
的程序执行只是表面的；相应的，他们认为并行是在多核处理器中使用多线程执行应用，
这里的多核处理器可以是一个多核处理器，也可以是同一台电脑上的多个处理器  

另一些学者认为并发执行应用的线程是非顺序执行的，相应的，他们认为并行是使用很多线程去
简化问题，这些线程是按预定顺序执行的  


### 2 线程的创建和运行
1. 继承Thread类，并且覆盖run(方法  
2. 创建一个实现Runnable接口的类。使用带参数的Thread构造器来创建Thread对象。这个参数就是实现Runnable接口的类的一个对象

### 3 线程信息的获取和设置
Thread类有一些保存信息的属性，比如可以用来：   
1. 标识线程  
2. 显示线程的状态  
3. 控制线程的优先级   
  
- ID: 保存了线程的唯一标示符  
- Name: 保存了线程名称  
- Priority: 保存了线程对象的优先级。线程的优先级是从1到10,其中1是最低优先级;
10是最高优先级  
- Status: 保存了线程的状态。在Java中，线程的状态有6种: new、 runnable、 blocked、
waiting、time waiting或者terminated  

### 4 线程的中断
Java提供了中断机制，我们可以使用它来结束一个线程。这种机制要求线程检查它是
否被中断了，然后决定是不是响应这个中断请求。线程允许忽略中断请求并且继续执行

### 5 线程中断的控制
如果线程实现了复杂的算法并且分布在几个方法中，或者线程里有递归调用的方法，我们
就得使用一个更好的机制来控制线程的中断。为了达到这个目的，Java 提供了
InterruptedException异常。当检查到线程中断的时候，就抛出这个异常，然后在run()中捕
获并处理这个异常

### 6 线程的休眠和恢复
有些时候，需要在某一个预期的时间中断线程的执行。例如，程序的一个线程每隔一分钟检查一次传感器状态，其余时间什么都不做。
在这段空闲时间，线程不占用计算机的任何资源。当它继续执行的CPU时钟来临时，
JVM会选中它继续执行。可以通过线程的sleep()方 法来达到这个目标  

sleep()方法接受整型数值作为参数，以表明线程挂起执行的毫秒数。当线程休眠的时间结束了，
JVM会分给它CPU时钟，线程将继续执行它的指令  

sleep()方法的另一种使用方式是通过TimeUnit枚举类元素进行调用。这个方法也使
用Thread类的sleep()方法来使当前线程休眠，但是它接收的参数单位是秒，最后会被转化成毫秒

### 7 等待线程的终止
在一些情形下，我们必须等待线程的终止。例如，我们的程序在执行其他的任务时，必须先初始化一些必须的资源。
可以使用线程来完成这些初始化任务，等待线程终止，再执行程序的其他任务。
为了达到这个目的，我们使用Thread类的join()方法。当一个线程对象的join()方法被
调用时，调用它的线程将被挂起，直到这个线程对象完成它的任务  
相当于当前线程，让别的线程加入到它之前去执行，让别的线程执行完，再轮到自己执行

### 8 守护线程的创建和运行
Java里有一种特殊的线程叫做守护(Daemon)线程。这种线程的优先级很低，通常来
说，当同一个应用程序里没有其他的线程运行的时候，守护线程才运行。当守护线程是程;
序中唯一运行的线程时，守护线程执行结束后，JVM也就结束了这个程序  

因为这种特性，守护线程通常被用来做为同一程序中普通线程(也称为用户线程)的
服务提供者。它们通常是无限循环的，以等待服务请求或者执行线程的任务。它们不能做
重要的工作，因为我们不可能知道守护线程什么时候能够获取CPU时钟，并且，在没有其
他线程运行的时候，守护线程随时可能结束。一个典型的守护线程是Java的垃圾回收器
(Garbage Collector)

### 9 线程中不可控异常的处理
在Java中有两种异常  
- 非运行时异常( Checked Exception):  
    这种异常必须在方法声明的throws语句指定，或者在方法体内捕获。例如: IOException 和ClassNotFoundException  
- 运行时异常(Unchecked Exception):  
    这种异常不必在方法声明中指定，也不需要在方法体中捕获。例如: NumberFormatException  

因为run()方法不支持throws语句，所以当线程对象的run()方法抛出非运行异常时，
我们必须捕获并且处理它们。当运行时异常从run()方法中抛出时，默认行为是在控制台输
出堆栈记录并且退出程序

### 10 线程局部变量的使用
共享数据是并发程序最核心的问题之一，对于继承了Thread类或者实现了Runnable接口的对象来说尤其重要

如果创建的对象是实现了Runnable接口的类的实例，用它作为传入参数创建多个线程
对象并启动这些线程，那么所有的线程将共享相同的属性。也就是说，如果你在一个线程
中改变了一个属性，所有线程都会被这个改变影响。在某种情况下，这个对象的属性不需要被所有线程共享

Java并发API提供了一个干净的机制，即线程局部变量(Thread-LocalVariable)，其具有很好的性能。

### 11 线程的分组
Java并发API提供了一个有趣的功能，它能够把线程分组。这允许我们把一个组的线
程当成一个单一的单元，对组内线程对象进行访问并操作它们。例如，对于一些执行同样
任务的线程，你想控制它们，不管多少线程在运行，只需要一个单一的调用， 所有这些线
程的运行都会被中断

Java提供ThreadGroup类表示一组线程。线程组可以包含线程对象，也可以包含其他
的线程组对象，它是一个树形结构

### 12 线程中不可控异常的处理
提供应用程序中对错误情景的管理，是编程语言很重要的一面。和几乎所有的现代编
程语言一样，Java 语言也实现了通过异常管理机制来处理错误情景，它提供了很多类来表
示不同的错误。当错误情景发生时，Java 类将抛出这些异常。你可以使用这些异常，或者
实现自己的异常，来管理类中的错误

Java也提供了捕获和处理这些异常的机制。有的异常必须被捕获，或者必须使用方法
的throws声明再次抛出，这类异常叫做非运行时异常。还有一类异常叫做运行时异常，
它们不需要被捕获或者声明抛出

另一种可行的做法是，建立一个方法来捕获线程组中的任何线程对象抛出的非捕获异常

### 13 使用工厂类创建线程
工厂模式是面向对象编程中最常使用的模式之一。它是一个创建者模式，使用一个类
为其他的一个或者多个类创建对象。当我们要为这些类创建对象时，不需再使用new构造
器，而使用工厂类

使用工厂类，可以将对象的创建集中化，这样做有以下的好处:
- 更容易修改类， 或者改变创建对象的方式
- 更容易为有限资源限制创建对象的数目。例如，我们可以限制一个类型的对象不多于n个
- 更容易为创建的对象生成统计数据

Java提供了ThreadFactory接口，这个接口实现了线程对象工厂  
Java并发API的高级工具类也使用了线程工厂创建线程

线程同步基础
========

- 使用synchronized实现同步方法
- 使用非依赖属性实现同步
- 在同步代码块中使用条件
- 使用锁实现同步
- 使用读写锁同步数据访问
- 修改锁的公平性
- 在锁中使用多条件

### 1 简介
多个执行线程共享一个资源的情景，是最常见的并发编程情景之一。在并发应用中常
常遇到这样的情景：多个线程读或者写相同的数据，或者访问相同的文件或数据库连接。
为了防止这些共享资源可能出现的错误或数据不一致，我们必须实现一些机制来防止这些错误的发生

为了解决这些问题，人们引入了临界区（Critical Section）概念。临界区是一个用以访
问共享资源的代码块，这个代码块在同一时间内只允许一个线程执行

为了帮助编程人员实现这个临界区，Java（以及大多数编程语言）提供了同步机制。
当一个线程试图访问一个临界区时，它将使用一种同步机制来查看是不是已经有其他线程
进入临界区。如果没有其他线程进入临界区，它就可以进入临界区；如果已经有线程进入
了临界区，它就被同步机制挂起，直到进入的线程离开这个临界区。如果在等待进入临界
区的线程不止一个，JVM会选择其中的一个，其余的将继续等待

Java语言提供的两种基本同步机制：
- synchronized 关键字机制
- Lock接口及其实现机制

### 2 使用synchronized实现同步方法
如果一个对象已用synchronized关键字声明，那么只有一
个执行线程被允许访问它。如果其他某个线程试图访问这个对象的其他方法,它将被挂起，
直到第一个线程执行完正在运行的方法。

换句话说，每一个用synchronized关键字声明的方法都是临界区。在Java中，同一个
对象的临界区，在同一时间只有一个允许被访问。

静态方法则有不同的行为。用synchronized关键字声明的静态方法，同时只能够被一
个执行线程访问，但是其他线程可以访问这个对象的非静态方法。必须非常谨慎这一点，
因为两个线程可以同时访问一个对象的两个不同的synchronized方法，即其中一个是静态
方法，另一个是非静态方法。如果两个方法都改变了相同的数据，将会出现数据不一致的错误

### 3 使用非依赖属性实现同步
当使用synchronized关键字来保护代码块时，必须把对象引用作为传入参数。通常情
况下，使用this关键字来引用执行方法所属的对象，也可以使用其他的对象对其进行引用。
一般来说，这些对象就是为这个目的而创建的。例如，在类中有两个非依赖属性，它们被
多个线程共享，你必须同步每一个变量的访问，但是同一时刻只允许一个线程访问一个属
性变量，其他某个线程访问另一个属性变量

### 4 在同步代码中使用条件
在并发编程中一个典型的问题是生产者-消费者(Producer-Consumer)问题

我们有一个数据缓冲区，一个或者多个数据生产者将把数据存入这个缓冲区，一个或者多个数据消费者将数据从缓冲区中取走

这个缓冲区是一个共享数据结构，必须使用同步机制控制对它的访问，例如使用
synchronized关键字，但是会受到更多的限制。如果缓冲区是满的，生产者就不能再放入数据，
如果缓冲区是空的，消费者就不能读取数据

对于这些场景，Java在Object类中提供了wait()、 notify()和 notifyAll()方法
线程可以在同步代码块中调用wait()方法。 如果在同步代码块之外调用wait()方法，JVM将抛出
IllegalMonitorStateException异常。当一线程调用wait()方法时，JVM将这个线程置入
休眠，并且释放控制这个同步代码块的对象，同时允许其他线程执行这个对象控制的其他
同步代码块。为了唤醒这个线程，必须在这个对象控制的某个同步代码块中调用notify()或者notifyAll()方法

### 5 使用锁实现同步
Java提供了同步代码块的另一种机制，它是一种比synchronized关键字更强大也更灵活的机制。
这种机制基于Lock接口及其实现类(例如 ReentrantLock),提供了更多的好处
- 支持更灵活的同步代码块结构  
使用synchronized关键字时，只能在同一个synchronized块结构中获取和释放控制Lock接口允许实现更复杂的临界区结构
- 相比 synchronized关键字，Lock接口提供了更多的功能  
其中一个新功能是tryLock()方法的实现。这个方法试图获取锁，如果锁已被其他线程获取，它将返回false并继续往下执行代码。
使用synchronized关键字时，如果线程A试图执行一个同步代码块，而线程B已在执行这个同步代码块,则线程A就会被挂起直到线程B运行完这个同步代码块。
使用锁的tryLock()方法，通过返回值将得知是否有其他线程正在使用这个锁保护的代码块

- Lock接口允许分离读和写操作，允许多个读线程和只有一个写线程
- 相比synchronized关键字，Lock接口具有更好的性能

### 6 使用读写锁实现同步数据访问
锁机制最大的改进之一就是ReadWriteLock接口和它的唯一实现类ReentrantReadWriteLock。
这个类有两个锁，一个是读操作锁，另一个是写操作锁。
使用读操作锁时可以允许多个线程同时访问，但是使用写操作锁时只允许一个线程进行。
在一个线程执行写操作时，其他线程不能够执行读操作

### 7 修改锁的公平性
ReentrantLock和ReentrantReadWriteLock类的构造器都含有一个布尔参数fair，它允许你控制这两个类的行为。
默认fair值是false，它称为非公平模式(Non-Fair Mode)。
在非公平模式下，当有很多线程在等待锁(ReentrantLock和ReentrantReadWriteLock)时,锁将选择它们中的一个来访问临界区,这个选择是没有任何约束的。
如果fair值是true，则称为公平模式(Fair Mode)。
在公平模式下，当有很多线程在等待锁(ReentrantLock和ReentrantReadWriteLock)时，锁将选择它们中的一个来访问临界区，而且选择的是等待时间最长的。
这两种模式只适用于lock()和unlock()方法。而Lock接口的tryLock()方法没有将线程置于休眠，fair属性并不影响这个方法

### 8 在锁中使用多条件（Multiple Condition）
一个锁可能关联一个或者多个条件，这些条件通过Condition接口声明。
目的是允许线程获取锁并且查看等待的某一个条件是否满足，如果不满足就挂起直到某个线程唤醒它们。
Condition接口提供了挂起线程和唤起线程的机制。

并发编程中的一个典型问题是生产者-消费者(Producer Consumer)问题。
我们使用一个数据缓冲区，一个或者多个数据生产者(Producer)将数据保存到缓冲区，一个或者多个数据消费者(Consumer)将数据从缓冲区中取走

线程同步辅助类
========

- 资源的并发访问控制
- 资源的多副本的并发访问控制
- 等待多个并发事件的完成
- 在集合点的同步
- 并发阶段任务的运行
- 并发阶段任务中的阶段切换
- 并发任务间的数据交换

### 1 简介
使用高级的同步机制来实现多线程间的同步  
- 信号量(Semaphore):是一种计数器，用来保护一个或者多个共享资源的访问。它是并发编程的一种基础工具，大多数编程语言都提供了这个机制
- CountDownLatch:是Java语言提供的同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许线程一直等待
- CyclicBarrier:也是Java语言提供的同步辅助类，它允许多个线程在某个集合点(common point)处进行相互等待
- Phaser:也是Java语言提供的同步辅助类。它把并发任务分成多个阶段运行，在开始下一阶段之前，当前阶段中的所有线程都必须执行完成，这是Java7 API中的新特性
- Exchanger:也是Java语言提供的同步辅助类。它提供了两个线程之间的数据交换点

在应用程序中，任何时候都可以使用Semaphore来保护临界区，因为它是一个基础的同步机制。
而其他的同步机制，则需要根据各自的上述特性来对其选择使用。所以我们需要根据应用程序的特性来选择合适的同步机制

### 2 资源的并发访问控制
Java语言提供了信号量(Semaphore)机制。信号量是一种计数器，用来保护一个或者多个共享资源的访问。
如果线程要访问一个共享资源，它必须先获得信号量。如果信号量的内部计数器大于0，信号量将减1，然后允许访问这个共享资源。
计数器大于0意味着有可以使用的资源，因此线程将被允许使用其中一个资源。
否则，如果信号量的计数器等于0，信号量将会把线程置入休眠直至计数器大于0。
计数器等于0的时候意味着所有的共享资源已经被其他线程使用了，所以需要访问这个共享资源的线程必须等待。
当线程使用完某个共享资源时，信号量必须被释放，以便其他线程能够访问共享资源。
释放操作将使信号量的内部计数器增加1

### 3 资源的多副本的并发访问控制
Semaphore信号量可以保护对单一共享资源，或者单一临界区的访问，从而使得保护的资源在同一个时间内只能被一个线程访问。
然而，信号量也可以用来保护一个资源的多个副本，或者被多个线程同时执行的临界区

### 4 等待多个并发事件的完成
Java并发API提供了CountDownLatch类，它是一个同步辅助类。
在完成一组正在其他线程中执行的操作之前，它允许线程一直等待。
这个类使用一个整数进行初始化，这个整数就是线程要等待完成的操作的数目。
当一个线程要等待某些操作先执行完时，需要调用await()方法，这个方法让线程进入休眠直到等待的所有操作都完成。
当某一个操作完成后，它将调用countDown()方法将CountDownLatch类的内部计数器减1。
当计数器变成0 的时候，CountDownLatch类将唤醒所有调用await()方法而进入休眠的线程。

### 5 在集合点的同步
Java并发API提供了CyclicBarrier类，它也是一个同步辅助类。它允许两个或者多个线程在某个点上进行同步。
这个类与，上一节所讲述的CountDownLatch类类似，但也有不同之处，使之成为更强大的类。
CyclicBarrier类使用一个整型数进行初始化,这个数是需要在某个点上同步的线程数

当一个线程到达指定的点后，它将调用await()方 法等待其他的线程。当线程调用await()
方法后，CyclicBarrier类将阻塞这个线程并使之休眠直到所有其他线程到达。当最后一个
线程调用CyclicBarrier类的await()方法时，CyclicBarrier对象将唤醒所有在等待的线程，
然后这些线程将继续执行

CyclicBarrier类有一个很有意义的改进，即它可以传入另一个Runnable 对象作为初
始化参数。当所有的线程都到达集合点后，CyclicBarrier类将这个Runnable对象作为线
程执行。这个特性使得这个类在并行任务上可以媲美分治编程技术(DivideandConquer
Programming Technique)

### 6 并发阶段任务的运行
Java并发API还提供了一个更复杂、更强大的同步辅助类，即Phaser，它允许执行并发多阶段任务。
当我们有并发任务并且需要分解成几步执行时，这种机制就非常适用。
Phaser类机制是在每一步结束的位置对线程进行同步，当所有的线程都完成了这一步，才允许执行下一步。
跟其他同步工具一样，必须对Phaser类中参与同步操作的任务数进行初始化，不同的是，我们可以动态地增加或者减少任务数。

### 7 并发阶段任务中的阶段切换
Phaser类提供了onAdvance()方法，它在phaser阶段改变的时候会被自动执行。
onAdvance()方法需要两个int型的传入参数：当前的阶段数以及注册的参与者数量
它返回的是boolean值，如果返回false表示phaser在继续执行，返回true表示phaser已经完成执行并且进入了终止态。
这个方法默认实现如下：如果注册的参与者数量是0就返回true,否则就返回false。
但是我们可以通过继承Phaser类覆盖这个方法。一般来说，当必须在从一个阶段到另一个阶段过渡的时候执行一些操作，那么我们就得这么做

### 8 并发任务间的数据交换
Java并发API还提供了一个同步辅助类，它就是Exchanger，它允许在并发任务之间交换数据。
具体来说，Exchanger类允许在两个线程之间定义同步点(SynchronizationPoint)。
当两个线程都到达同步点时，它们交换数据结构，因此第一个线程的数据结构进入到第二个线程中，同时第二个线程的数据结构进入到第一个线程中。
Exchanger类在生产者消费者问题情境中很有用。这是一个经典的并发场景，包含一个数据缓冲区，一个或者多个数据生产者，一个或者多个数据消费者。
Exchanger 类只能同步两个线程，如果有类似的只有一个生产者和消费者的问题，就可以使用Exchanger类

线程执行器
========

- 创建线程执行器
- 创建固定大小的线程执行器
- 在执行器中执行任务并返回结果
- 运行多个任务并处理第一个结果
- 运行多个任务并处理所有结果
- 在执行器中延时执行任务
- 在执行器中周期性执行任务
- 在执行器中取消任务
- 在执行器中控制任务的完成
- 在执行器中分离任务的启动与结果的处理
- 处理在执行器中被拒绝的任务

### 1 简介
通常，使用Java来开发一个简单的并发应用程序时，会创建一些Runnable对象，然后创建对应的Thread对象来执行它们。
但是，如果需要开发一个程序来运行大量的并发任务，这个方法将突显以下劣势:
- 必须实现所有与Thread对象管理相关的代码，比如线程的创建、结束以及结果获取
- 需要为每一个任务创建一个Thread对象。如果需要执行大量的任务，这将大大地影响应用程序的处理能力
- 计算机的资源需要高效地进行控制和管理，如果创建过多的线程，将会导致系统负荷过重

自从Java5开始，Java并发API提供了一套意在解决这些问题的机制。
这套机制称之为执行器框架(ExecutorFramework)，围绕着Executor接口和它的子接口ExecutorService，以及实现这两个接口的ThreadPoolExecutor类展开。

这套机制分离了任务的创建和执行。通过使用执行器，仅需要实现Runnable接口的对象，然后将这些对象发送给执行器即可。
执行器通过创建所需的线程，来负责这些Runnable对象的创建、实例化以及运行。但是执行器功能不限于此，它使用了线程池来提高应用程序的性能。
当发送一个任务给执行器时，执行器会尝试使用线程池中的线程来执行这个任务，避免了不断地创建和销毁线程而导致系统性能下降。
执行器框架另一个重要的优势是Callable接口。它类似于Runnable接口，但是却提供了两方面的增强。
这个接口的主方法名称为call() ，可以返回结果。
当发送一个Callable对象给执行器时，将获得一个实现了Future接口的对象。
可以使用这个对象来控制Callable对象的状态和结果

### 2 创建线程执行器
使用执行器框架(Executor Framework)的第一步是创建ThreadPoolExecutor对象。
可以ThreadPoolExecutor类提供的四个构造器或者使用Executors工厂类来创建ThreadPoolExecutor对象。
一旦有了执行器，就可以将Runnable或Callable对象发送给它去执行了

### 3 创建固定大小的线程执行器
当使用Executors类的newCachedThreadPool()方法创建基本的ThreadPoolExecutor时，执行器运行过程中将碰到线程数量的问题。
如果线程池里没有空闲的线程可用，那么执行器将为接收到的每一个任务创建一个新线程，当发送大量的任务给执行器并且任务需要持续较长的时间时，系统将会超负荷，应用程序也将随之性能不佳。
为了避免这个问题，Executors工厂类提供了一个方法来创建一个固定大小的线程执行器。这个执行器有一个线程数的最大值，如果发送超过这个最大值的任务给执行器，执行器将不再创建额外的线程，剩下的任务将被阻塞直到执行器有空闲的线程可用。
这个特性可以保证执行器不会给应用程序带来性能不佳的问题

### 4 在执行器中执行任务并返回结果
执行器框架(Executor Framework)的优势之一是，可以运行并发任务并返回结果。
Java并发API通过以下两个接口来实现这个功能。
Callable:这个接口声明了call()方法。可以在这个方法里实现任务的具体逻辑操作。
Callable接口是一个泛型接口，这就意味着必须声明call()方法返回的数据类型。
Future:这个接口声明了一些方法来获取由Callable对象产生的结果，并管理它们的状态

### 5 运行多个任务并处理第一个结果
并发编程比较常见的一个问题是，当采用多个并发任务来解决一个问题时，往往只关心这些任务中的第-一个结果。
比如，对一个数组进行排序有很多种算法，可以并发启动所有算法，但是对于一个给定的数组，第一个得到排序结果的算法就是最快的排序算法

### 6 运行多个任务并处理所有结果
执行器框架(Executor Framework)允许执行并发任务而不需要去考虑线程创建和执行。
它还提供了可以用来控制在执行器中执行任务的状态和获取任务运行结果的Future类。

如果想要等待任务结束，可以使用如下两种方法：
- 如果任务执行结束，那么Future接口的isDone()方法将返回true
- 在调用shutdown()方法后，ThreadPoolExecutor类的awaitTermination()方法会将线程休眠，直到所有的任务执行结束

这两个方法有一些缺点：
第一个方法，仅可以控制任务的完成与否
第二个方法，必须关闭执行器来等待一个线程，否则调用这个方法线程将立即返回

ThreadPoolExecutor类还提供一个方法，它允许发送一个任务列表给执行器，并等待列表中所有任务执行完成

### 7 在执行器中延时执行任务
执行器框架(Executor Framework)提供了ThreadPoolExecutor类并采用线程池来执行Callable和Runnable类型的任务，采用线程池可以避免所有线程的创建操作而提高应用程序的性能。
当发送一个任务给执行器时，根据执行器的相应配置，任务将尽可能快地被执行。
但是，如果并不想让任务马上被执行，而是想让任务在过一段时间后才被执行，或者任务能够被周期性地执行。
为了达到这个目的，执行器框架提供了ScheduledThreadPoolExecutor类

### 8 在执行器中周期性执行任务
执行器框架(Executor Framework)提供了ThreadPoolExecutor类，通过线程池来执行并发任务从而避免了所有线程的创建操作。
当发送一个任务给执行器后，根据执行器的配置，它将尽快地执行这个任务。
当任务执行结束后，这个任务就会从执行器中删除；如果想再次执行这个任务，则需要再次发送这个任务到执行器
但是，执行器框架提供了ScheduledThreadPoolExecutor类来执行周期性的任务

### 9 在执行器中取消任务
使用执行器时，不需要管理线程，只需要实现Runnable或Callable任务并发送任务给执行器即可。
执行器负责创建线程，管理线程池中的线程，当线程不再需要时就销毁它们。
有时候，我们可能需要取消已经发送给执行器的任务。在这种情况下，可以使用Future接口的cancel()方法来执行取消操作

### 10 在执行器中控制任务的完成
FutureTask类提供了一个名为done()的方法，允许在执行器中的任务执行结束之后，还可以执行一些代码。
这个方法可以被用来执行一些后期处理操作，比如：产生报表，通过邮件发送结果或释放一些系统资源。
当任务执行完成是受FutureTask类控制时，这个方法在内部被FutureTask类调用。
在任务结果设置后以及任务的状态已改变为isDone之后，无论任务是否被取消或者正常结束，done()方法才被调用。
默认情况下，done()方法的实现为空，即没有任何具体的代码实现。
我们可以覆盖FutureTask类并实现done()方法来改变这种行为

### 11 在执行器中分离任务的启动与结果的处理
通常情况下，使用执行器来执行并发任务时，将Runnable或Callable任务发送给执行器，并获得Future对象来控制任务。
此外，还会碰到如下情形，需要在一个对象里发送任务给执行器，然后在另一个对象里处理结果。
对于这种情况，Java 并发API提供了CompletionService类。
CompletionService类有一个方法用来发送任务给执行器，还有一个方法为下一个已经执行结束的任务获取Future 对象。
从内部实现机制来看，CompletionService类使用Executor对象来执行任务。
这个行为的优势是可以共享CompletionService对象，并发送任务到执行器，然后其他的对象可以处理任务的结果。
第二个方法有一个不足之处，它只能为已经执行结束的任务获取Future对象，因此，这些Future对象只能被用来获取任务的结果

### 12 处理在执行器中被拒绝的任务
当我们想结束执行器的执行时，调用shutdown()方法来表示执行器应当结束。
但是，执行器只有等待正在运行的任务或者等待执行的任务结束后，才能真正结束。
如果在shutdown()方法与执行器结束之间发送一个任务给执行器，这个任务会被拒绝，因为这个时间段执行器已不再接受任务了。
ThreadPoolExecutor类提供了一套机制，当任务被拒绝时调用这套机制来处理它们

Fork/Join框架
========

- 创建Fork/Join线程池
- 合并任务的结果
- 异步运行任务
- 在任务中抛出异常
- 取消任务

### 1 简介
通常，使用Java来开发一个简单的并发应用程序时，会创建一些Runnable对象，然
后创建对应的Thread对象来控制程序中这些线程的创建、执行以及线程的状态。
自从Java 5开始引入了Executor和ExecutorService接口以及实现这两个接口的类(比如ThreadPoolExecutor)之后，使得Java在并发支持上得到了进一步的提升。
执行器框架(Executor Framework)将任务的创建和执行进行了分离，通过这个框架，
只需要实现Runnable接口的对象和使用Executor对象，然后将Runnable对象发送给执行器。
执行器再负责运行这些任务所需要的线程，包括线程的创建，线程的管理以及线程的结束。
Java 7则又更进了一步，它包括了ExecutorService接口的另一种实现，用来解决特殊类型的问题，它就是Fork/Join框架，有时也称分解/合并框架。

Fork/Join框架是用来解决能够通过分治技术(Divide and Conquer Technique)将问题拆分成小任务的问题。
在一个任务中，先检查将要解决的问题的大小，如果大于一个设定的大小，那就将问题拆分成可以通过框架来执行的小任务。
如果问题的大小比设定的大小要小，就可以直接在任务里解决这个问题，然后，根据需要返回任务的结果。

没有固定的公式来决定问题的参考大小(Reference Size)，从而决定一个任务是需要进行拆分或不需要拆分，拆分与否仍是依赖于任务本身的特性。
可以使用在任务中将要处理的元素的数目和任务执行所需要的时间来决定参考大小。
测试不同的参考大小来决定解决问题最好的一个方案，将ForkJoinPool类看作一个特殊的Executor 执行器类型。
这个框架基于以下两种操作。 

- 分解(Fork)操作：当需要将一个任务拆分成更小的多个任务时，在框架中执行这些任务
- 合并(Join)操作：当一个主任务等待其创建的多个子任务的完成执行

Fork/Join框架和执行器框架(Executor Framework)主要的区别在于工作窃取算法(Work-Stealing Algorithm)。
与执行器框架不同，使用Join操作让一个主任务等待它所创建的子任务的完成，执行这个任务的线程称之为工作者线程(Worker Thread)。
工作者线程寻找其他仍未被执行的任务，然后开始执行。
通过这种方式，这些线程在运行时拥有所有的优点，进而提升应用程序的性能

为了达到这个目标，通过Fork/Join框架执行的任务有以下限制
- 任务只能使用fork()和join()操作当作同步机制。如果使用其他的同步机制，工作者线程就不能执行其他任务，当然这些任务是在同步操作里时。
比如，如果在Fork/Join框架中将一个任务休眠，正在执行这个任务的工作者线程在休眠期内不能执行另一个任务
- 任务不能执行I/O操作，比如文件数据的读取与写入
- 任务不能抛出非运行时异常(CheckedException)，必须在代码中处理掉这些异常

Fork/Join框架的核心是由下列两个类组成的

- ForkJoinPool：这个类实现了ExecutorService接口和工作窃取算法(Work-StealingAlgorithm)
它管理工作者线程，并提供任务的状态信息，以及任务的执行信息
- ForkJoinTask：这个类是一个将在ForkJoinPool中执行的任务的基类

Fork/Join框架提供了在一个任务里执行fork()和join()操作的机制和控制任务状态的方法。通常，为了实现Fork/Join任务，需要实现一个以下两个类之一的子类
- RecursiveAction：用于任务没有返回结果的场景
- RecursiveTask：用于任务有返回结果的场景

### 2 创建Fork/Join线程池
Fork/Join框架的基本元素包括
- 创建用来执行任务的ForkJoinPool对象
- 创建即将在线程池中被执行的任务ForkJoinTask子类

本范例中即将使用的Fork/Join框架的主要特性如下
- 采用默认的构造 器创建ForkJoinPool对象
- 在任务中将使用JavaAPI文档推荐的结构  
```java
if (problem size > default size) {
	tasks = divide(task);
	execute(tasks);
} else {
	resolve problem using another algorithm;
}
```
- 我们将以同步的方式执行任务。当一个主任务执行两个或更多的子任务时，这个主任务将等待子任务的完成。
用这种方法，执行主任务的线程，称之为工作者线程(WorkerThread)，它将寻找其他的子任务来执行，并在子任务执行的时间里利用所有的线程优势
- 如果将要实现的任务没有返回任何结果，那么，采用RecursiveAction类作为实现任务的基类

### 3 合并任务的结果
Fork/Join框架提供了执行任务并返回结果的能力。这些类型的任务都是通过RecursiveTask类来实现的。
RecursiveTask类继承了ForkJoinTask类，并且实现了由执行器框架(Executor Framework)提供的Future接口。

在任务中，必须使用Java API文档推荐的如下结构:
```java
if (problem size > size) {
	tasks = Divide(task);
	execute(tasks);
	groupResults()
	return result;
} else {
	resolve problem;
	return result;
}
```
如果任务需要解决的问题大于预先定义的大小，那么就要将这个问题拆分成多个子任务，并使用Fork/Join框架来执行这些子任务。
执行完成后，原始任务获取到由所有这些子任务产生的结果，合并这些结果，返回最终的结果。
当原始任务在线程池中执行结束后，将高效地获取到整个问题的最终结果

### 4 异步运行任务
在ForkJoinPool中执行ForkJoinTask时，可以采用同步或异步方式。
当采用同步方式执行时，发送任务给Fork/Join线程池的方法直到任务执行完成后才会返回结果。
而采用异步方式执行时，发送任务给执行器的方法将立即返回结果，但是任务仍能够继续执行

需要明白这两种方式在执行任务时的一个很大的区别。
当采用同步方式，调用这些方法(比如，invokeAll()方法)时，任务被挂起，直到任务被发送到Fork/Join 线程池中执行完成。
这种方式允许ForkJoinPool类采用工作窃取算法(Work-Stealing Algorithm)来分配一个新任务给在执行休眠任务的工作者线程(WorkerThread)。
相反，当采用异步方法(比如，fork()方法)时，任务将继续执行，因此ForkJoinPool类无法使用工作窃取算法来提升应用程序的性能。
在这个示例中，只有调用join()或get()方法来等待任务的结束时，ForkJoinPool类才可以使用工作窃取算法

### 5 在任务中抛出异常
Java有两种类型的异常
- 非运行时异常(Checked Exception)：这些异常必须在方法上通过throws子句抛出，或者在方法体内通过try{...}catch{...} 方式进行捕捉处理。比如IOException或ClassNotFoundException异常
- 运行时异常(Unchecked Exception)：这些异常不需要在方法上通过throws子句抛出，也不需要在方法体内通过try{...}catch{...}方式进行捕捉处理。比如NumberFormatException异常

不能在ForkJoinTask类的compute()方法中抛出任务非运行时异常，因为这个方法的实现没有包含任何throws声明。
因此，需要包含必需的代码来处理相关的异常。另一方面，compute()方法可以抛出运行时异常(它可以是任何方法或者方法内的对象抛出的异常)

ForkJoinTask类和ForkJoinPool类的行为与我们期待的可能不同。在控制台上，程序没有结束执行，不能看到任务异常信息。
如果异常不被抛出，那么它只是简单地将异常吞噬掉。
然而，我们能够利用ForkJoinTask类的一些方法来获知任务是否有异常抛出，以及抛出哪一种类型的异常

### 6 取消任务
在ForkJoinPool类中执行ForkJoinTask对象时，在任务开始执行前可以取消它。
ForkJoinTask类提供了cancel()方法来达到取消任务的目的。在取消一个任务时必须要注意以下两点：
- ForkJoinPool类不提供任何方法来取消线程池中正在运行或者等待运行的所有任务
- 取消任务时，不能取消已经被执行的任务

并发集合
========

- 使用非阻塞式线程安全列表
- 使用阻塞式线程安全列表
- 使用按优先级排序的阻塞式线程安全列表
- 使用带有延迟元素的线程安全列表
- 使用线程安全可遍历映射
- 生成并发随机数
- 使用原子变量
- 使用原子数组

### 1 简介
数据结构(Data Structure)是编程中的基本元素，几乎每个程序都使用一种或多种数据结构来存储和管理数据。
Java API提供了包含接口、类和算法的Java集合框架(Java Collection Framework)，它实现了可用在程序中的大量数据结构。
当需要在并发程序中使用数据集合时，必须要谨慎地选择相应的实现方式。
大多数集合类不能直接用于并发应用，因为它们没有对本身数据的并发访问进行控制。
如果一些并发任务共享了一个不适用于并发任务的数据结构，将会遇到数据不一致的错误，并将影响程序的准确运行。
这类数据结构的一个例子是ArrayList类。

Java提供了一些可以用于并发程序中的数据集合，它们不会引起任何问题。
一般来说，Java提供了两类适用于并发应用的集合
- 阻塞式集合(Blocking Collection)：这类集合包括添加和移除数据的方法。
当集合已满或为空时，被调用的添加或者移除方法就不能立即被执行，那么调用这个方法的线程将被阻塞，一直到该方法可以被成功执行
- 非阻塞式集合(Non-Blocking Collection):这类集合也包括添加和移除数据的方法。
如果方法不能立即被执行，则返回null或抛出异常，但是调用这个方法的线程不会被阻塞

可以在并发应用中使用一些Java集合
- 非阻塞式列表对应的实现类: ConcurrentLinkedDeque类
- 阻塞式列表对应的实现类: LinkedBlockingDeque类
- 用于数据生成或消费的阻塞式列表对应的实现类: LinkedTransferQueue类
- 按优先级排序列表元素的阻塞式列表对应的实现类: PriorityBlockingQueue类
- 带有延迟列表元素的阻塞式列表对应的实现类: DelayQueue类
- 非阻塞式可遍历映射对应的实现类: ConcurrentSkipListMap类
- 随机数字对应的实现类: ThreadLocalRandom类
- 原子变量对应的实现类: AtomicLong和AtomicIntegerArray类

### 2 使用非阻塞式线程安全列表
最基本的集合类型是列表(List)。
一个列表包含的元素数量不定，可以在任何位置添加、读取或移除元素。
并发列表允许不同的线程在同一时间添加或移除列表中的元素，而不会造成数据不一致。
在本节，将会学到如何在并发程序中使用非阻塞式列表。
非阻塞式列表提供了一些操作，如果被执行的操作不能够立即运行(例如，在列表为空时，从列表取出一个元素)，方法会抛出异常或返回null。
Java 7引入了ConcurrentLinkedDeque类来实现非阻塞式并发列表

### 3 使用阻塞式线程安全列表
最基本的集合类型是列表。一个列表包含的元素数量不定，可以在任何位置添加、读取或移除元素。
并发列表允许不同的线程在同一时间添加或移除列表中的元素，而不会造成数据不一致。
阻塞式列表与非阻塞式列表的主要差别是：阻塞式列表在插入和删除操作时，如果列表已满或为空，操作不会被立即执行，而是将调用这个操作的线程阻塞队列直到操作可以执行成功。
Java引入了LinkedBlockingDeque类来实现阻塞式列表

### 4 使用按优先级排序的阻塞式线程安全列表
数据结构应用中的一个经典需求是实现一个有序列表。
Java引入了PriorityBlockingQueue类来满足这类需求。
所有添加进PriorityBlockingQueue的元素必须实现Comparable接口。
这个接口提供了compareTo()方法，它的传入参数是一个同类型的对象。
这样就有了两个同类型的对象并且相互比较：其中一个是执行这个方法的对象，另一个是参数传入的对象。
这个方法必须返回一个数字值，如果当前对象小于参数传入的对象，那么返回一个小于0的值；
如果当前对象大于参数传入的对象，那么返回一个大于0的值；如果两个对象相等就返回0。
当插入元素时，PriorityBlockingQueue使用compareTo()方法来决定插入元素的位置。元素越大越靠后。

PriorityBlockingQueue的另一个重要的特性是：它是阻塞式数据结构(BlockingDataStructure)。
当它的方法被调用并且不能立即执行时，调用这个方法的线程将被阻塞直到方法执行成功

### 5 使用带有延迟元素的线程安全列表
JavaAPI提供了一种用于并发应用的有趣的数据结构，即DelayQueue类。
这个类可以存放带有激活日期的元素。当调用方法从队列中返回或提取元素时，未来的元素日期将被忽略。
这些元素对于这些方法是不可见的。
为了具有调用行为，存放到DelayQueue类中的元素必须继承Delayed接口。
Delayed接口使对象成为延迟对象，它使存放在DelayQueue类中的对象具有了激活日期，即到激活日期的时间

该接口强制执行下列两个方法
- compareTo(Delayed o)：Delayed接口继承了Comparable接口，因此有了这个方法。
如果当前对象的延迟值小于参数对象的值，将返回一个小于0的值；如果当前对象的延迟值大于参数对象的值，将返回一个大于0的值；如果两者的延迟值相等则返回0
- getDelay(TimeUnit unit):这个方法返回到激活日期的剩余时间，单位由单位参数指定

TimeUnit类是一个由下列常量组成的枚举类型: DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS

### 6 使用线程安全可遍历映射
Java API还提供了一种用于并发应用程序中的有趣数据结构，即ConcurrentNavigableMap接口及其实现类。
实现这个接口的类以如下两部分存放元素：
- 一个键值(Key)，它是元素的标识并且是唯一的
- 元素其他部分数据

每一个组成部分都必须在不同的类中实现。
Java API也提供了一个实现ConcurrentSkipListMap接口的类，ConcurrentSkipListMap接口实现了与ConcurrentNavigableMap接口有相同行为的一个非阻塞式列表。
从内部实现机制来讲，它使用了一个SkipList来存放数据。
SkipList是基于并发列表的数据结构，效率与二叉树相近。有了它，就有了一个数据结构，比有序列表在添加、搜索或删除元素时耗费更少的访问时间。

备注: Skip List由William Pugh在1990 年引入，详见```http://www.cs.umd.edu/~pugh```

当你插入元素到映射中时，ConcurrentSkipListMap接口类使用键值来排序所有元素。
除了提供返回一个具体元素的方法之外，这个类也提供获取子映射的方法

### 7 生成并发随机数
Java并发API提供了一个特殊类用以在并发程序中生成伪随机数(Pseudo-RandomNumber)，即Java 7新引入的ThreadLocalRandom类。
它是线程本地变量。每个生成随机数的线程都有一个不同的生成器，但是都在同一个类中被管理，对程序员来讲是透明的。
相比于使用共享的Random对象为所有线程生成随机数，这种机制具有更好的性能

### 8 使用原子变量
原子变量(Atomic Variable)是从Java 5开始引入的，它提供了单个变量上的原子操作。
在编译程序时，Java代码中的每个变量、每个操作都将被转换成机器可以理解的指令。
例如，当给一个变量赋值时，在Java代码中只使用一个指令，但是编译这个程序时，指令被转换成JVM语言中的不同指令。
当多个线程共享同一个变量时，就会发生数据不一致的错误

为了避免这类错误，Java 引入了原子变量。当一个线程在对原子变量操作时，如果其他线程也试图对同一原子变量执行操作，原子变量的实现类提供了一套机制来检查操作是否在一步内完成。
一般来说，这个操作先获取变量值，然后在本地改变变量的值，然后试图用这个改变的值去替换之前的值。
如果之前的值没有被其他线程改变，就可以执行这个替换操作。
否则，方法将再执行这个操作。这种操作称为CAS原子操作(Compare and Set)

原子变量不使用锁或其他同步机制来保护对其值的并发访问。
所有操作都是基于CAS原子操作的。它保证了多线程在同一时间操作一个原子变量而不会产生数据不一致的错误，并且它的性能优于使用同步机制保护的普通变量

### 9 使用原子数组
当实现一个并发应用时，将不可避免地会有多线程共享一个或多个对象的现象，为了避免数据不一致错误，需要使用同步机制(如锁或synchronized关键字)来保护对这些共享属性的访问

但是，这些同步机制存在下列问题  
- 死锁：一个线程被阻塞，并且试图获得的锁正被其他线程使用，但其他线程永远不会释放这个锁。这种情况使得应用不会继续执行，并且永远不会结束
- 即使只有一个线程访问共享对象，它仍然需要执行必须的代码来获取和释放锁

针对这种情况，为了提供更优的性能，Java于是引入了比较和交换操作(Compare-and-Swap Operation)

这个操作使用以下三步修改变量的值  
- 1.取得变量值，即变量的旧值  
- 2.在一个临时变量中修改变量值，即变量的新值  
- 3.如果上面获得的变量旧值与当前变量值相等，就用新值替换旧值  

如果已有其他线程修改了这个变量的值，上面获得的变量的旧值就可能与当前变量值不同。
采用比较和交换机制不需要使用同步机制，不仅可以避免死锁并且性能更好。
Java在原子变量(Atomic Variable)中实现了这种机制。
这些变量提供了实现比较和交换操作的compareAndSet()方法，其他方法也基于它展开。
Java也引入了原子数组(Atomic Array)提供对integer或long数字数组的原子操作

定制并发类
========

- 定制ThreadPoolExecutor类
- 实现基于优先级的Executor类
- 实现ThreadFactory接口生成定制线程
- 在Executor对象中使用ThreadFactory
- 定制运行在定时线程池中的任务
- 通过实现ThreadFactory接口为Fork/Join框架生成定制线程
- 定制运行在Fork/Join框架中的任务
- 实现定制Lock类
- 实现基于优先级的传输队列
- 实现自己的原子对象

### 1 简介
Java并发API提供了大量接口和类来实现并发应用程序。
这些接口和类既包含了底层机制，如Thread类、Runnable接口或Callable接口、synchronized关键字，
也包含了高层机制，如在Java 7中增加的Executor框架和Fork/Join框架。
尽管如此，在开发应用程序时，仍会发现已有的Java类无法满足需求。
这时，我们就需要基于Java提供类和接口来实现自己的定制并发工具。

一般来说，我们可以：
- 实现一个接口以拥有接口定义的功能，例如，ThreadFactory接口
- 覆盖类的一些方法，改变这些方法的行为，来满足需求，例如，覆盖Thread类的
run()方法，它默认什么都不做，可以被用来覆盖以提供预期的功能

### 2 定制ThreadPoolExecutor类
Executor框架是一种将线程的创建和执行分离的机制。
它基于Executor和ExecutorService接口，及这两个接口的实现类ThreadPoolExecutor展开。
Executor有一个内部线程池，并提供了将任务传递到池中线程以获得执行的方法

可传递的任务有如下两种:
- 通过Runnable接口实现的任务，它不返回结果
- 通过Callable接口实现的任务，它返回结果

在这两种情况下，只需要传递任务到执行器，执行器即可使用线程池中的线程或新创建的线程来执行任务。执行器也决定了任务执行的时间

### 3 实现基于优先级的Executor类
在Java并发API的第一个版本中，我们必须创建并运行应用程序中的所有线程。
在Java5中，伴随Executor框架的出现，引入了一种新的并发任务机制。
使用Executor框架，只需要实现任务并将它们传递到执行器中，然后执行器将负责创建执行任务的线程，并执行这些线程。
执行器内部使用一个阻塞式队列存放等待执行的任务，并按任务到达执行器时的顺序进行存放。
另一个可行的替代方案是使用优先级队列存放新的任务，这样，如果有高优先级的新任务到达执行器，它将在其他正在等待的低优先级的线程之前被执行

### 4 实现ThreadFactory接口生成定制线程
工厂模式(Factory Pattern)在面向对象编程中是一个应用广泛的设计模式。
它是一种创建模式(Creational Pattern)，目标是创建一个类并通过这个类创建一个或多一个类的对象时，使用工厂类而不是new操作符

通过工厂模式，我们能够将对象创建集中化，这样做的好处是：改变对象的创建方式将会变得很容易，并且针对限定资源还可以限制创建对象的数量。
例如，通过工厂模式生成了一个类型的N个对象，就很容易获得创建这些对象的统计数据

Java提供了ThreadFactory接口来实现Thread对象工厂。
Java 并发API的一些高级辅助类，像Executor框架或Fork/Join框架，都使用了线程工厂来创建线程。
线程工厂在Java并发API中的另一个应用是Executors类。它提供了大量方法来创建不同类型的Executor对象

### 5 在Executor对象中使用ThreadFactory
在上一节“实现ThreadFactory接口生成定制线程”中，我们引入了工厂模式并提供了实现一个线程工厂的实例，该线程工厂实现了ThreadFactory接口。
Executor框架是一种分离线程的创建和执行的机制。
它是基于Executor和ExecutorService接口，以及这两个接口的实现类ThreadPoolExecutor 展开的。
它有一个内部线程池，并提供了将任务传递到池中线程以获得执行的方法

可传递的任务有如下两种：
- 通过Runnable接口实现的任务，它不返回结果
- 通过Callable接口实现的任务，它返回结果

Executor框架内部使用了ThreadFactory接口来生成新的线程

### 6 定制运行在定时线程池中的任务
定时线程池(Scheduled Thread Pool)是Executor框架基本线程池的扩展，允许在一段时间后定时执行任务。
ScheduledThreadPoolExecutor类不仅实现了这个功能，还允许执行下列两类任务

- 延迟任务(Delayed Task)：这类任务在一段时间后仅执行一次
- 周期性任务(PeriodicTask)：这类任务在一段延迟时间后周期性地执行

延迟任务能够执行实现Callable和Runnable接口的两类对象，周期性任务仅能执行实现Runnable接口的对象。
所有由定时线程池执行的任务都必须实现RunnableScheduledFuture接口

### 7 通过实现ThreadFactory接口为Fock/Join框架生成定制线程
Java 7中最有趣特的性之一是Fork/Join框架。
它是Executor和ExecutorService接口的实现，这两个接口能够允许我们执行Callable和Runnable任务，而不需要去关注执行这些任务的具体线程。

这个执行器用于执行可以分拆成更小任务体的任务，它的主要组件如下
- 一种特殊类型任务，由ForkJoinTask类来实现
- 两种操作，其中通过fork操作将一个任务分拆为多个子任务，而通过join操作等待这些子任务结束
- 工作窃取算法(Work-Stealing Algorithm)，用来对线程池的使用进行优化。
当一个任务等待它的子任务时，执行这个任务的线程可以被用来执行其他任务

Fork/Join框架的主类是ForkJoinPool类。
从内部实现来说，它有下面两个元素：
- 一个任务队列，存放的是等待被执行的任务
- 一个执行这些任务的线程池

### 8 定制运行在Fork/Join框架中的任务
Executor框架分离了任务的创建和执行。
在这个框架下，只需要实现Runnable对象和Executor对象，并将Runnable对象发送给执行器即可，
这样，执行器将创建执行这些任务的线程，并对其进行管理直到线程终止。

Java7在Fork/Join框架中提供了一个特殊形式的执行器。
这个框架旨在通过分拆和合并技术，把任务分拆为更小的任务。
在一个任务中，我们需要检查待解决问题的规模。
如果问题的规模比制定的规模大，则需要把问题分拆为两个或多个任务，并使用Fork/Join框架执行这些任务。
如果问题的规模小于已制定的规模，直接在任务里解决问题即可。
另外，可以选择是否返回结果。Fork/Join框架通过工作窃取算法(WorkStealingAlgorithm)，
提升了这类问题的整体性能。

Fork/Join框架的主类是ForkJoinPool类。从内部来讲，它有下面两个元素
- 一个任务队列， 存放的是等待被执行的任务
- 一个执行这 些任务的线程池

默认情况下，ForkJoinPool类执行的任务是ForkJoinTask类的对象。
我们也可以传递Runnable和Callable对象到ForkJoinPool类中，但是它们不会利用Fork/Join框架的优势

一般来说，我们将ForkJoinTask的两种子类传递给ForkJoinPool对象
- RecursiveAction：用于任务不返回结果的情况
- RecursiveTask：用于任务返回结果的情况

### 9 实现定制Lock类
锁是Java并发API提供的最基本的同步机制之一。
程序员用锁来保护代码的临界区(Critical Section)，所以同一时间只有一个线程能执行临界区代码

它提供了下列两种操作：
- lock()：当要访问临界区代码时调用这个操作。如果另一个线程正在运行临界区代码，其他线程将被阻塞直到被访问临界区的锁唤醒
- unlock()：在临界区代码结尾调用这个操作，以允许其他线程来访问这部分临界区代码

在Java并发API中，锁是使用Lock接口来声明的，并且有一些类实现了Lock接口，如ReentrantLock类

### 10 实现基于优先级的传输队列
Java 7 API提供了几种用于并发应用程序的数据结构。
我们要重点关注以下两种结构
- LinkedTransferQueue：适用于拥有生产者一消费者结构的程序中。
在这些应用程序中，有一个或多个数据生产者和数据消费者，然后这些生产者/消费者却共享着一个数据结构。
生产者将数据存放到数据结构中，消费者则从数据结构中取出数据。
如果数据结构为空，消费者被阻塞直到数据结构中有可用的数据。
如果数据结构已满，则生产者被阻塞直到数据结构有可用的空间可以存放生产者将要存放进来的数据
- PriorityBlockingQueue：在这个数据结构中，元素按顺序存储。
这些元素必须实现Comparable接口，并实现接口中定义的compareTo()方法。
当插入一个元素时，它会与已有元素进行比较直至找到它的位置

LinkedTransferQueue中的元素按到达的先后顺序进行存储，所以早到的被优先消费。
你可能面临这样的一个场景：你想开发一个生产者/消费者程序，数据是按某种优先级(而不是按到达的时间顺序)被消费

### 11 实现自己的原子对象
从Java 5开始就已经引入了原子变量(Atomic Variable)，它提供对单个变量的原子操作。
当线程使用原子变量执行操作时，类的实现包括了一个机制来检查操作是否在单步内结束。
简单来讲，就是操作获取变量值，然后通过本地变量来改变值，接着尝试改旧值为新值。
如果旧值未变，则执行改变。否则，方法重新执行

测试并发应用程序
========

- 监控Lock接口
- 监控Phaser类
- 监控执行器框架
- 监控Fork/Join池
- 输出高效的日志信息
- 使用FindBugs分析并发代码
- 配置Eclipse调试并发代码
- 配置NetBeans调试并发代码
- 使用MultithreadedTC测试并发代码

### 1 简介
对应用程序进行测试是一个关键的任务。
在将应用程序交付给最终客户使用前，必须检验应用程序的正确性。
我们使用测试流程来证明应用程序的正确性。
测试是软件开发的基本环节，也称为质量保证(Quality Assurance)过程，
可以找到大量文章介绍关于测试过程的不同方法，这些方法可用于应用程序开发当中

也有大量的类包(如单元测试框架JUnit)以及应用程序(如Apache JMetter)它们可用来以一种自动化方式测试Java应用，
在并发应用程序开发中进行测试则显得更加关键。
并发应用程序实际_上是多个线程共享数据结构并相互交互，这在很大程度上增加了测试的难度。
在测试并发应用程序时，要面对的最大的问题是线程的执行是不确定的(Non-Deterministic)，
我们无法保证线程执行的顺序，所以很难重现错误

在本章，我们将学习下列内容：
- 如何获取并发应用程序中元素的信息，这些信息有助于测试应用程序
- 如何使用IDE(Integrated Development Environment)和其他工具(如FindBugs)来测试应用程序
- 如何使用类库(如MultithreadedTC)来进行自动化测试

### 2 监控Lock接口
Lock接口是Java并发API同步代码块的基本机制之一。
它定义了临界区(Critical Section)。
临界区是同一时间只能被一个线程执行的共享资源的代码块。
这种机制是通过Lock接口和ReentrantLock类而实现的

### 3 监控Phaser类
Java并发API提供的最复杂最强大的功能之一是使用Phaser类执行并发阶段任务(Concurrent Phased Task)。
当有并发任务被分为几步时这一机制非常有用。
Phaser类提供了在每步结束时同步等待其他线程的机制，
因此，只有所有线程都执行完各自的第一步操作之后，才会开始执行接下来的第二步操作

### 4 监控执行器框架
执行器框架(Executor Framework)提供了一套机制把任务的实现和创建与管理执行这些任务的线程分开。
如果使用一个执行器(Executor)，必须实现Runnable对象并将其发送到执行器。
执行器负责管理线程，当有任务到达时，它将尝试使用线程池中的线程来执行这个任务，从而避免创建新的线程。
Executor接口和它的实现类ThreadPoolExecutor提供了这套机制

### 5 监控Fork/Join池
执行器框架(Executor Framework)提供了一套机制把任务的实现和创建与管理执行这些任务的线程分开。
Java 7引入了执行器框架的一个扩展，针对一些特定问题，
比其他解决方案(如直接使用Thread对象或Executor框架)具有更高的性能，
这就是Fork/Join框架(Fork/Join Framework)。
Fork/Join框架被设计用来解决能够被分解成更小任务的问题，
通过分治技术(Divide and Conquer Technique)，采用fork()和join()方法运行操作。
实现这种行为的主类是ForkJoinPool类

### 6 输出高效的日志信息
日志系统(Log System)是将信息输出到一个或多个目标上的一种机制

一个日志器(Logger)有下面几个组件
- 一个或多个处理器(Handler)：处理器决定目标和日志消息的格式。可把日志消息输出到控制台上、写到文件中或保存到数据库中
- 一个名称 (Name)：一般来说，类中的日志记录器的名称是基于它的包名和类名的
- 一个级别(Level)：日志消息有一个关联的级别来表示它的重要性。
日志记录器也有一个级别用来决定它要输出什么级别的消息。
日志记录器仅输出与它的级别相同重要或更重要的消息

使用日志系统有下面两个主要目的
- 当捕获到异常时尽可能多地输出信息，这有助于定位并解决错误
- 输出关于程序正在执行的类和方法的信息

### 7 使用FindBugs分析并发代码
静态代码分析工具(Static Code Analysis Tool)是一套通过分析应用程序的源代码来查出潜在错误的工具集。
这些工具，如Checkstyle、PMD或FindBugs，提供了预定义的最佳实践的规则集来分析源代码、查找源代码是否违反了这些规则。
这些工具的目标是，在应用程序正式投产前发现错误或定位性能瓶颈之处。
编程语言通常提供了这类工具，Java语言也不例外。
分析Java代码的工具之一是FindBugs。
它是一个开源工具，包含了一系列规则来分析Java并发代码

### 8 配置Eclipse调试并发代码
无论使用什么编程语言，如今几乎每个程序员都使用IDE集成开发环境来创建应用程序

IDE提供了大量已集成的有趣的功能，如
- 项目管理
- 代码自动生成
- 文档自动生成
- 集成版本控制系统
- 测试应用程序的调试器
- 创建项目或应用元素的各种向导

IDE中最有帮助的特性之一就是调试器，它可以一步步地执行应用程序并分析程序中的对象和变量的值。
如果使用Java编程语言，Eclipse是最流行的IDE之一。它有一个集成的调试器，用来测试应用程序。
缺省时，当你调试一个并发应用程序并且发现一个断点，调试器仅停止有断点的线程而其他线程继续执行

### 9 配置NetBeans调试并发代码
如今，开发应用程序时，选用相应的软件工具是非常必要的。
应用程序需要满足公司的质量标准，未来易于修改且费用低耗时少。
为了实现这个目标，使用同一界面下集成了多个工具(编译器和调试器)来促进应用程序开发的IDE极为重要。
如果使用Java编程语言，NetBeans也是最流行的IDE之一。 
它有一个集成的调试器，用来测试应用程序

### 10 使用MultithreadedTC测试并发代码
MultithreadedTC是一个测试并发应用程序的Java类库，它的主要目标是解决不确定的并发应用程序存在的问题。
而我们无法控制线程的执行顺序。
为了这个目标，MultithreadedTC用一个内部节拍器(metronome)来控制应用程序不同线程的执行顺序。
这些测试线程被实现为类的方法
