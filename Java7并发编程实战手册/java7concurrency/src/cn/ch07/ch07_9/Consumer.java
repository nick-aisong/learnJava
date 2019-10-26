package cn.ch07.ch07_9;
// 26.实现一个名为Consumer的类，并实现Runnable接口
public class Consumer implements Runnable {
	// 27.声明一个名为buffer的私有MyPriorityTransferQueue属性
	// 它的泛型参数是Event类，用来获得由这个类消费的事件
	private MyPriorityTransferQueue<Event> buffer;
	// 28.实现类构造器来初始化它的属性
	public Consumer(MyPriorityTransferQueue<Event> buffer) {
		this.buffer = buffer;
	}
	// 29.实现run()方法。使用take()方法消费1002个Event(例子中所有产生的事件)
	// 并在控制台输出产生事件的线程的名称以及事件的优先级priority
	@Override
	public void run() {
		for (int i = 0; i < 1002; i++) {
			try {
				Event value = buffer.take();
				System.out.printf("Consumer: %s: %d\n", value.getThread(), value.getPriority());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
