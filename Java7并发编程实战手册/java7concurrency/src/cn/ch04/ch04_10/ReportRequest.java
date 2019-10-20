package cn.ch04.ch04_10;

import java.util.concurrent.CompletionService;

// 6.创建一个名为ReportRequest的类，实现Runnable接口。这个类将模拟请求获取报告
public class ReportRequest implements Runnable {
	// 7.声明一个名为name的私有String属性，用来存储ReportRequest的名称
	private String name;
	// 8.声明一个名为service的私有CompletionService属性，这个CompletionService接口是泛型接口
	// 在这个示例中，我们采用String类作为泛型参数
	private CompletionService<String> service;
	// 9.实现类的构造器，并初始化这两个属性
	public ReportRequest(String name, CompletionService<String> service) {
		this.name = name;
		this.service = service;
	}
	// 10.实现run()方法。创建ReportGenerator对象，然后调用CompletionService对象的submit()方法将ReportGenerator对象发送给CompletionService对象
	@Override
	public void run() {
		ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
		service.submit(reportGenerator);
	}
}
