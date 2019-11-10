package cn.part02.ch18.strategy4;

//代码清单18-13 简化算法
public class Calculator {
    //加符号
    private final static String ADD_SYMBOL = "+";
    //减符号
    private final static String SUB_SYMBOL = "-";

    public int exec(int a, int b, String symbol) {
        return symbol.equals(ADD_SYMBOL) ? a + b : a - b;
    }
}