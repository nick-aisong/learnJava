package cn.ch01.ch01_7;

import java.util.Date;

// 1.创建Event类。这个类只存放满足本范例需要的信息
// 声明两个私有属性，一个日期类型的属性date；另一个字符串型的属性event
// 并生成这两个属性的读写方法
public class Event {

	private Date date;
	private String event;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
