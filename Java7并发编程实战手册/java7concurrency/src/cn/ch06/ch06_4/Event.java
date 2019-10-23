package cn.ch06.ch06_4;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
// 1.创建名为Event的类并实现Delayed接口
public class Event implements Delayed {
	// 2.声明私有的名为startDate的Date属性
	private Date startDate;
	// 3.实现类构造器，初始化属性
	public Event(Date startDate) {
		this.startDate = startDate;
	}
	// 4.实现compareTo()方法。它接收一个Delayed对象为参数，并比较两者延迟值的大小
	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		}
		return 0;
	}
	// 5.实现getDelay()方法。返回当前对象的startDate和当前实际日期的间隔，这个间隔的单位是由传入参数TimeUnit指定的
	@Override
	public long getDelay(TimeUnit unit) {
		Date now = new Date();
		long diff = startDate.getTime() - now.getTime();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}
}
