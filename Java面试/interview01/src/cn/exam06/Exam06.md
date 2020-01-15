06成员变量与局部变量
========

```java
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
```

### 考点?

- 就近原则
- 变量的分类
  - 成员变量:类变量、实例变量
  - 局部变量

- 非静态代码块的执行:每次创建实例对象都会执行
- 方法的调用规则:调用一次执行一次

局部变量与成员变量的区别:

①声明的位置
- 局部变量:方法体{}中，形参，代码块{}中
- 成员变量:类中方法外
  - 类变量:有static修饰
  - 实例变量:没有static修饰

②修饰符
- 局部变量: final
- 成员变量: public、protected、private、final、static、volatile、transient

③值存储的位置
- 局部变量:栈
- 实例变量:堆
- 类变量:方法区

④作用域
- 局部变量:从声明处开始，到所属的}结束.
- 实例变量:在当前类中“this. ”(有时this.可以缺省)，在其他类中“对象名.”访问
- 类变量:在当前类中“类名.”(有时类名.可以省略)，在其他类中“类名.”或“对象名.”访问

⑤生命周期
- 局部变量:每一个线程，每一次调用执行都是新的生命周期
- 实例变量:随着对象的创建而初始化，随着对象的被回收而消亡，每一个对象的实例变量是独立的
- 类变量:随着类的初始化而初始化，随着类的卸载而消亡，该类的所有对象的类变量是共享的
