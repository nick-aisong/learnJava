package cn.nks.ch01_8;

public class Main {

	public static void main(String[] args) {

		Task task = new Task();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();

	}

}
