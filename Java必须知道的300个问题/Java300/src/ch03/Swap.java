package ch03;

// 问：在进行程序设计时，如果要求类似1+2+…+n的值，通常是使用for、while、if、else、switch、case等关键字以及条件判断语句来实现，但是本题要求不能使用这些关键字。
// 该如何计算1+2+...+n的值呢?

// 答：本题的要求限制了所有循环语句和条件判断语句，但是唯独没有限制三元运算符(?:)，所以可以从这个运算符着手，并利用递归实现循环，从而完成计算1+2+…+n的值的功能
public class Swap {
    static int sum;

    public static int iSum(int n) {
        sum += n; //进行累加求和
        sum = n == 0 ? sum : iSum(--n); //进行递归调用
        return sum; //返回计算结果
    }

    public static void main(String[] args) {
        int n = 100; //定义并初始化变量
        int tSum = iSum(n); //计算连续整数的和
        System.out.println("1至" + n + "连续整数的和如下: ");
        System.out.println("1+2+..." + "+" + n + "=" + tSum);
    }
}

// 1至100连续整数的和如下:
// 1+2+...+100=5050