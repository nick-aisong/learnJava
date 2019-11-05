package ch03;
// #### 问题18 表达式 3-2.6 == 0.4的值是什么?
// 问：在进行程序设计时，经常需要输出一些表达式的值，因此需要对各种运算进行分析。
// 那么表达式3-2.6 == 0.4的值会是什么呢?

// 答：首先对表达式3-2.6 == 0.4进行分析，该表达式有两个运算符，分别为减运算符-和关系运算符==，由于减运算符-的优先级高于关系运算符==，
// 所以该表达式将输出一个布尔值，即输出true或false，具体输出什么取决于3-2.6的值

// 在Java中基本类型的浮点数运算是不精确的，如果在Java程序中输出3-2.6的值，可以看到输出的结果是0.399999999999999，这个值是不精确的，
// 所以表达式3-2.6 == 0.4的值是假，即false
public class ImpreciseResult {
    public static void main(String[] args) {
        System.out.println(3 - 2.6); //输出3与2.6的差值
        System.out.println(3 - 2.6 == 0.4); //输出比较结果
    }
}

// 0.3999999999999999
// false

// 这是由于基本数据类型浮点数运算的不精确性造成的