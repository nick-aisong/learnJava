03类初始化和实例初始化等
========

### 考点?
- 类初始化过程
- 实例初始化过程
- 方法的重写

### 类初始化过程
①一个类要创建实例需要先加载并初始化该类
- main方法所在的类需要先加载和初始化

①实例初始化就是执行<init>()方法

- <init>()方法可能重载有多个，有几个构造器就有几个<init>方法
- <init>()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成
- 非静态实例变量显示赋值代码和非静态代码块代码从上到下顺序执行，而对应构造器的代码最后执行
- 每次创建实例对象，调用对应构造器，执行的就是对应的<init>方法

###方法的重写Override

①哪些方法不可以被重写
- final方法
- 静态方法
- private等子类中不可见方法

②对象的多态性
- 子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
- 非静态方法默认的调用对象是this
- this对象在构造器或者说<init>方法中就是正在创建的对象（创建A就是A，其父类初始化不算是“要创建的类”）

```java
package cn.exam03;

/*
 * 父类的初始化<clinit>
 *（1）j = method();
 *（2）父类的静态代码块  （static的成员变量，代码块是顺序初始化的，位置变了，初始化顺序会变）
 *
 * 父类的实例化方法：
 * （1）super()（最前）
 * （2）i = test();
 * （3）父类的非静态代码块
 * （4）父类的无参构造（最后）
 *
 * 非静态方法前面其实有一个默认的对象this
 * this构造器（或<init>）它表示的是正在创建的对象，因为这里是在创建Son对象，所以
 * test()执行的是子类重写的代码（面向对象多态）
 *
 * 这里i=test()执行的是子类重写的test()方法
 */
public class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }

    Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}
```
```java
package cn.exam03;

/*
 *子类的初始化<clinit>
 *（1）j = method();
 *（2）子类的静态代码块
 *
 * 先初始化父类：(5)(1)
 * 初始化子类：(10)(6)
 *
 * 子类的实例化方法：
 * （1）super()（最前）(9)(3)(2)
 * （2）i = test();(9)
 * （3）子类的非静态代码块(8)
 * （4）子类的无参构造（最后）(7)
 *
 * 因为创建了两个Son对象，因此实例化方法<init>执行两次
 * (9)(3)(2)(9)(8)(7)
 */
public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }

    Son() {
        // super();  // 写或不写都在，在子类构造器中一定会调用父类的构造器
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}

/*
(5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
(9)(3)(2)(9)(8)(7)
*/
```