package cn.part02.ch18.strategy5;

public class Sub implements Calculator {
    //减法运算
    public int exec(int a, int b) {
        return a - b;
    }
}