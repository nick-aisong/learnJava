package ch03;

// 问：自增和自减运算符用于对变量进行加1和减1运算，自增和自减运算符既可以放在变量的前边，也可以放在变量的后边。
// 那么自增、自减运算符放在变量前后有什么区别呢?

// 答：自增(++)、自减(--)运算符是单目运算符，可以放在操作元之前，作元之后，对操作元进行加1、减1操作。
// 操作元必须是整数类型或浮点类型的变量。自增(或自减)运算符放在操作元之后，先使用变量的值，然后再对变量加1 (或减1)

// - 自增、自减运算符放在变量前后没有区别的情况
// 在使用自增和自减运算符时，自增和自减运算符放在变量之前和之后，单从变量自身来说是没有区别的，都是对变量进行加1和减1运算
public class AddAndSubstract {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        int c = 10;
        int d = 10;
        a++; //运算符放在变量a之后
        ++b; //运算符放在变量b之前
        c--; //运算符放在变量c之后
        --d; //运算符放在变量d之前
        System.out.println("a++运算后a的值是" + a);
        System.out.println("++b运算后b的值是" + b);
        System.out.println("c--运算后c的值是" + c);
        System.out.println("--d运算后d的值是" + d);

        example();
    }

    public static void example() {
        int a = 3;
        int b = 5;
        int c = 0;
        //先取b的值5与5相乘得25, 再对b进行加1, b变为6, 然后再取a的值3，并用25除以3得8,然后再对a加1, a变为4
        c = b++ * 5 / a++; //对变量进行自增运算，并参与运算
        System.out.println("a=" + a + "\tb=" + b + "\tc=" + c);
        //先取b的值6, 然后对a加1, a变为5, 并计算6与5的和为11, 然后对b加1, b变为7
        c = b++ + ++a; //对变量进行自增运算，并参与运算
        System.out.println("a=" + a + "\tb=" + b + "\tc=" + c);
        //先取a的值5, 然后对b加1, b变为8, 并计算5与8的差为-3，然后对a加1, a变为6
        c = a++ - ++b; //对变量进行自增运算，并参与运算
        System.out.println("a=" + a + "\tb=" + b + "\tc=" + c);
    }
}

// a++运算后a的值是11
// ++b运算后b的值是11
// c--运算后c的值是9
// --d运算后d的值是9
// a=4	b=6	c=8
// a=5	b=7	c=11
// a=6	b=8	c=-3