package ch03;

import java.math.BigDecimal;

public class PrecisionResult {
    public static void main(String[] args) {
        BigDecimal x = new BigDecimal("3"); //创建3的BigDecimal对象
        BigDecimal y = new BigDecimal("2.6"); //创建2.6的BigDecimal对象
        BigDecimal z = x.subtract(y); //计算3与2.6的差值
        double value = z.doubleValue(); //将计算结果转换为基本数据类型double型
        System.out.println(value); //输出计算结果
        System.out.println(value == 0.4); //输出比较结果
    }
}

// 0.4
// true

// 为了得到精确的浮点数运算结果，可以使用BigDecimal类来实现