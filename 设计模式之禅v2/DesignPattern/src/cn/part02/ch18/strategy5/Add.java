package cn.part02.ch18.strategy5;

//代码清单18-15 具体策略
public class Add implements Calculator {
    //加法运算
    public int exec(int a, int b) {
        return a + b;
    }
}