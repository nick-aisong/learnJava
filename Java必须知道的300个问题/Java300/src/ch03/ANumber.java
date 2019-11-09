package ch03;

// 问：在工作中，有时需要计算2的n次幂。如此次数越多，计算起来就越容易出现错误，例如计算2的20次幂的结果很容易出现多乘或少乘的情况。
// 那么在程序中该如何使用位运算符计算2的20次幂呢?

// 答：创建一个ANumber类，通过左移操作实现计算2的20次幂操作
public class ANumber {
    public static void main(String[] args) {
        int result = 1 << 20; //位移运算
        System.out.println("2的20次幂为: " + result); //输 出运算结果
    }
}

// 2的20次幂为: 1048576