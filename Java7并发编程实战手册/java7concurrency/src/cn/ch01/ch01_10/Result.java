package cn.ch01.ch01_10;

// 1.创建一个名为Result的类。它存储先执行完的线程
// 声明一个私有字符串变量name,并生成读写这个值的方法
public class Result {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
