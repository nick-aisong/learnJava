package cn.ch06.ch06_2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

// 1.创建名为Client的类，并实现Runnable接口
public class Client implements Runnable {
	// 2.声明一个私有的LinkedBlockingDeque属性requestList，并指定它的泛型参数是String型的
	private LinkedBlockingDeque<String> requestList;
	// 3.实现类的构造器来初始化属性
	public Client(LinkedBlockingDeque<String> requestList) {
		this.requestList = requestList;
	}
	// 4.实现run()方法。使用requestList对象的put()方法，每两秒向列表requestList中插入5个字符串。重复3次
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				StringBuilder request = new StringBuilder();
				request.append(i);
				request.append(":");
				request.append(j);
				try {
					requestList.put(request.toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("Client: %s at %s. Size: %d\n", request, new Date(), requestList.size());
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Client: End.\n");
	}
}
