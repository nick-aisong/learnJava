package cn.ch05.ch05_5;

import java.util.Random;

// 1.创建一个名为ArrayGenerator的类。这个类将生成一个指定大小的随机整数数组
// 实现generateArray()方法，它将生成数字数组，接收一个int参数表示数组的大小
public class ArrayGenerator {
    public int[] generateArray(int size) {
        int array[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }
}