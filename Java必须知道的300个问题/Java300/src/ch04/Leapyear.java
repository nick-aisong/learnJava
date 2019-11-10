package ch04;

import java.util.Scanner;

// 问：地球绕太阳一圈称之为一年，所用时间是365天5小时48分46秒，取365天为一年4年将多出23小时15分6秒，将近一天，所以4年设一闰日(2月29日)，该年称为闰年。
// 如何应用if语句判断某一年 是否为闰年?
public class Leapyear {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个年份：");
        long year;
        try {
            year = scan.nextLong();
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                System.out.println(year + "是闰年！");
            } else {
                System.out.println(year + "不是闰年！");
            }
        } catch (Exception e) {
            System.out.println("您输入的不是有效的年份！");
        }
    }
}
