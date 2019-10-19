package cn.ch04.ch04_3;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// 1.创建名为FactorialCalculator的类，并实现Callable接口，接口的泛型参数为Integer类型
public class FactorialCalculator implements Callable<Integer> {
	// 2.声明一个名为number的私有Integer属性，存储任务即将用来计算的数字
	private Integer number;
	// 3.实现类的构造器，用来初始化类的属性
	public FactorialCalculator(Integer number) {
		this.number = number;
	}
	// 4.实现call()方法。这个方法返回FactorialCalculator类的number属性的阶乘:(Factorial)
	@Override
	public Integer call() throws Exception {
		// 5.创建并初始化在call()方法内使用的内部变量
		int result = 1;
		// 6.如果number值是0或1,则返回1；否则计算number的阶乘
		// 为了演示效果,在两个乘法之间，将任务休眠20毫秒
		if ((number == 0) || (number == 1)) {
			result = 1;
		} else {
			for (int i = 2; i < number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		// 7.在控制台输出操作的结果
		System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
		// 8.返回操作的结果
		return result;
	}
}
