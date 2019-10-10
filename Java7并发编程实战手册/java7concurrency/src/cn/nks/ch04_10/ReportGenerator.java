package cn.nks.ch04_10;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ReportGenerator implements Callable<String> {

	private String sender;
	private String title;

	public ReportGenerator(String sender, String title) {
		this.sender = sender;
		this.title = title;
	}

	@Override
	public String call() throws Exception {
		long duration = (long) (Math.random() * 10);
		System.out.printf("%s_%s: ReportGenerator: Generating a report during %s seconds\n", this.sender, this.title,
				duration);
		TimeUnit.SECONDS.sleep(duration);
		String ret = sender + ": " + title;
		return ret;
	}

}
