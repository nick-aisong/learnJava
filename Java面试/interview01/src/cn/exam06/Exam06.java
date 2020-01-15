package cn.exam06;

public class Exam06 {
    static int s; // 成员变量，类变量
    int i; // 成员变量，实例变量
    int j; // 成员变量，实例变量

    {
        int i = 1; //非静态代码块中的局部变量，i；要是i是this.i，就是指向的成员变量i了
        i++;
        j++;
        s++;
    }

    public void test(int j) { // 形参，局部变量，j
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) { // 形参，局部变量，args
        Exam06 obj1 = new Exam06(); // 局部变量，obj1
        Exam06 obj2 = new Exam06(); // 局部变量，obj2
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }
}

/*
2,1,5
1,1,5
*/