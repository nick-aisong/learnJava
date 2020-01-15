package cn.exam05;

import org.junit.Test;

public class TestStep {
    @Test
    public void test() {
        long start = System.currentTimeMillis();
        System.out.println(f(40));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /*
    165580141
    566
    */

    //实现f(n)：求n步台阶，一共有几种走法
    public int f(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return f(n - 2) + f(n - 1);
    }
}
