package cn.ch03.ch03_1;

public class Main {

	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Job(printQueue), "Thread" + i);
		}
		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}
	}

}
