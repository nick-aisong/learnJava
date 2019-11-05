package ch03;
// #### 问题13 变量和常量有什么区别?
// 问：在使用Java语言进行程序设计时，经常需要用到常量和变量来存储信息。请简单叙述变量和常量有什么区别?

// 答：变量和常量是编程语言中最基本的两个知识点，变量的值可以改变而常量的值在初始化以后是无法改变的。
// 常量在定义时要使用final关键字修饰。下面的代码段首先定义了一个int型的常量CONST,并赋值为10；
// 并定义了一个int型的变量num,其初始值为100，并输出变量的值；然后在程序运行时改变该变量的值为180，并输出改变后变量的值；
// 最后输出常量CONST的值
public class VarientAndConstant {
    public static void main(String[] args) {
        final int CONST = 10; //定义并初始化常量
        int num = 100; //定义并初始化变量
        System.out.println("变量num的初始值是: " + num);
        num = 180; //改变变量的值为180
        System.out.println("改变后变量num的值是: " + num);
        System.out.println("常量CONST的值是: " + CONST);
        // CONST= 100;  //试图在程序运行时改变常量的值，出错
        // Error:(17, 9) java: 无法为最终变量CONST分配值
    }
}
