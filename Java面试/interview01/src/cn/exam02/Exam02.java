package cn.exam02;

public class Exam02 {
    public static void main(String[] args){
        Singleton1 s1 = Singleton1.INSTANCE;
        Singleton2 s2 = Singleton2.INSTANCE;
        Singleton3 s3 = Singleton3.INSTANCE;
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}

/*
cn.exam02.Singleton1@677327b6
INSTANCE
Singleton3 [info=test]
*/