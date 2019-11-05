package ch03;

// 问：在数学中进行除法运算时，9/2与9/2.0的结果是相等的。那么在计算机中表达式9/2与9/2.0的结果是否相等呢?

// 答：整数与整数之间运算的结果必然是整数，就算是除法也会执行整除而舍弃小数。
// 所以9/2的结果是4，但是整数与浮点数的运算会转换为浮点数类型再进行运算，其结果应是浮点数类型，所以9/2.0的结果是4.5,所以9/2的结果与9/2.0的结果是不相等的
public class DivisionOperation {
    public static void main(String[] args) {
        System.out.println("整数9与整数2相除的结果如下: ");
        System.out.print("9/2=");
        //输出整数9与整数2相除的结果
        System.out.println(9 / 2);
        System.out.println();
        System.out.println("整数9与浮点数2.0相除的结果如下: ");
        System.out.print("9/2.0=");
        //输出整数9与浮点数2.0相除的结果
        System.out.println(9 / 2.0);
    }
}
// 整数9与整数2相除的结果如下:
// 9/2=4
//
// 整数9与浮点数2.0相除的结果如下:
// 9/2.0=4.5