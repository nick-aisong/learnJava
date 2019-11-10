package cn.part02.ch18.strategy3;

//代码清单18-11 最直接的加减法
public class Calculator {
    //加符号
    private final static String ADD_SYMBOL = "+";
    //减符号
    private final static String SUB_SYMBOL = "-";

    public int exec(int a, int b, String symbol) {
        int result = 0;
        if (symbol.equals(ADD_SYMBOL)) {
            result = this.add(a, b);
        } else if (symbol.equals(SUB_SYMBOL)) {
            result = this.sub(a, b);
        }
        return result;
    }

    //加法运算
    private int add(int a, int b) {
        return a + b;
    }

    //减法运算
    private int sub(int a, int b) {
        return a - b;
    }
}