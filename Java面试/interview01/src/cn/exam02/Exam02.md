02单例设计模式
========

编程题:写一个Singleton示例

### 什么是Singleton?
- Singleton:在Java中 即指单例设计模式，它是软件开发中最常用的设计模式之一
- 单:唯一
- 例:实例
- 单例设计模式，即某个类在整个系统中只能有一个实例对象可被获取和使用的代码模式
- 例如:代表JVM运行环境的Runtime类

### 要点
- 一是某个类只能有一个实例
  - 构造器私有化

- 二是它必须自行创建这个实例
  - 含有一个该类的静态变量来保存这个唯一的实例

- 三是它必须自行向整个系统提供这个实例
  - (1)直接暴露 (2)用静态变量的get方法获取

### 几种常见形式

- 饿汉式:直接创建对象,不存在线程安全问题（类加载器保证）
  - 直接实例化饿汉式(简洁直观)
  - 枚举式(最简洁)
  - 静态代码块饿汉式(适合复杂实例化)
  
- 懒汉式:延迟创建对象
  - 线程不安全(适用于单线程)
  - 线程安全(适用于多线程)
  - 静态内部类形式(适用于多线程)

```java
package cn.exam02;

/*
 * 饿汉式:
 * 在类初始化的时候直接创建实例对象，不管你是否需要这个对象都会创建
 *
 * (1)构造器私有化
 * (2)自行创建，并且用静态变量保存
 * (3)向外提供这个实例
 * (4)强调这是一个单例，我们可以用final修改
 *
 * JDK1.5
 */
public class Singleton1 {

    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){
    }

    // 如果外部只需要调用这个类的静态方法，也会new这个单例类，如果用不到，有些浪费资源
    public static void test(){

    }
}
```
```java
package cn.exam02;

/**
 * 枚举类型：表示该类型的对象是有限的几个
 * 我们可以限定为一个，就成了单例
 *
 * JDK1.7
 */
public enum Singleton2 {
    INSTANCE
}
```
```java
package cn.exam02;

import java.io.IOException;
import java.util.Properties;

public class Singleton3 {

    public static final Singleton3 INSTANCE;
    private String info;

    // 应对复杂加载情况
    static {
        Properties pro = new Properties();
        try {
            // 读取src直接目录下的资源
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        INSTANCE = new Singleton3(pro.getProperty("info"));
    }

    private Singleton3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3 [info=" + info + "]";
    }
}
```
```java
package cn.exam02;

/*
 * 懒汉式:
 * 延迟创建这个实例对象
 *
 * (1)构造器私有化
 * (2)用一个静态变量保存这个唯一的实例
 * (3)提供一个静态方法，获取这个实例对象
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4(){
    }

    // 线程不安全，对于单一线程是可以的
    public static Singleton4 getInstance(){
        if (instance == null){
            instance = new Singleton4();
        }
        return instance;
    }
}
```
```java
package cn.exam02;

public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {
    }

    // 线程不安全，对于单一线程是可以的
    public static Singleton5 getInstance() {
        if (instance == null) { // 为了提高性能，省的每次进入synchronized
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
```
```java
package cn.exam02;

/**
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * 静态内部类不会随着外部类的加载和初始化而初始化，它是要单独去加载和初始化的
 * 因为是在内部类加载和初始化时，创建的，因此是线程安全的
 */
public class Singleton6 {
    private static Singleton6 instance;

    private Singleton6() {
    }

    private static class Inner {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return Inner.INSTANCE;
    }
}
```
