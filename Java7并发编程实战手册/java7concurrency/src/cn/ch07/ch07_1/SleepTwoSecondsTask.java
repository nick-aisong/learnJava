package cn.ch07.ch07_1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// 8.创建名为SleepTwoSecondsTask的类并实现Callable接口，这个接口的泛型参数是String类
// 实现call()方法，使当前线程休眠2s，然后将当前日期转换为字符串,并返回
public class SleepTwoSecondsTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
}
