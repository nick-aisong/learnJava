package cn.ch04.ch04_5;

// 1.创建一个名为Result的类，用来存储范例中并发任务产生的结果
public class Result {

	// 2.声明两个私有属性。一个名为name的String属性，一个名为value的int属性
	private String name;
	private int value;

	// 3.实现对应的get()和set()方法来设置和返回name和value属性
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
