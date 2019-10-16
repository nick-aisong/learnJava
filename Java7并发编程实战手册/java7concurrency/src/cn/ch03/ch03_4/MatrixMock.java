package cn.ch03.ch03_4;

import java.util.Random;

// 1.通过实现两个辅助类来开始这个范例。创建一个矩阵类MatrixMock，用来生成一个1~10组成的随机矩阵，线程将从这个矩阵中查找指定的数字

public class MatrixMock {

	// 2.声明私有二维数组data
	private int[][] data;

	// 3.实现构造器。构造器的传入参数是矩阵的行数，每行的长度，要寻找的数字。这三个参数都是int型的
	public MatrixMock(int size, int length, int number) {
		// 4.初始化构造器使用的变量和对象
		int counter = 0;
		data = new int[size][length];
		// 5.用随机数字为矩阵赋值。每生成一个数字，就用它跟要查找的数字进行比较。如果一致，就将计数器counter加1
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = random.nextInt(10);
				if (data[i][j] == number) {
					counter++;
				}
			}
		}
		// 6.将在矩阵中查找到的次数打印到控制台。这个信息将用来检查线程是否得到了正确的结果
		System.out.printf("Mock: There are %d ocurrences of number %d in generated data.\n", counter, number);
	}

	// 7.实现getRow()方法。这个方法的传入参数是int型的矩阵行序号，如果矩阵中存在这个行，就返回行数据，如果不存在就返回null
	public int[] getRow(int row) {
		if ((row >= 0) && (row < data.length)) {
			return data[row];
		}
		return null;
	}
}
