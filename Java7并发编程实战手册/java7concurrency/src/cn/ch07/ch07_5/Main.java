package cn.ch07.ch07_5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

//定制运行在定时线程池中的任务
public class Main {

	public static void main(String[] args) throws Exception {
		MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
		Task task = new Task();
		System.out.printf("Main: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		executor.schedule(task, 1, TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(3);
		task = new Task();
		System.out.printf("Main: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(10);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.printf("Main: End of the program.\n");
	}

}
