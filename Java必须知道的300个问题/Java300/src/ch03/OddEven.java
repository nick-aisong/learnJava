package ch03;

// 问：在进行程序开发时，经常需要通过判断一个整数是奇数还是偶数，来实现一些特殊的效果和功能，例如，让表格的奇数行显示一种背景颜色，偶数行显示另一种背景颜色。
// 那么该如何判断一个整数是奇数还是偶数呢?

// 答： 判断奇数与偶数要从该整数与2的余数入手，如果该整数与2的余数是1；说明该整数是奇数，如果余数是0，说明该整数是偶数。
// 在Java语言中求余数的运算符是%

// 假设有一个整数N，那么判断N是奇数还是偶数，可以通过N%2的结果来实现，如果结果等于1就说明N是奇数，否则N就是偶数
public class OddEven {
    public static void main(String[] args) {
        int num = 99; //需要判断奇偶的数
        isOddOrEven(num); //调用方法判断奇偶数
        num = 100; //需要判断奇偶的数
        isOddOrEven(num); //调用方法判断奇偶数
    }

    /**
     * 判断奇偶数的方法
     *
     * @param num 被判断的参数
     */
    public static void isOddOrEven(int num) {
        if (num % 2 == 1) {
            //余数为1,表示奇数
            System.out.println("整数" + num + "是奇数。");
        } else {
            //否则表示偶数
            System.out.println("整数" + num + "是偶数。");
        }
    }
}

// 整数99是奇数。
// 整数100是偶数。