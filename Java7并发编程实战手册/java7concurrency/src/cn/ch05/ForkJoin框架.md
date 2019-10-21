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


