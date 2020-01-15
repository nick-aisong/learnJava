01自增变量
========
```java
package cn.exam01;

public class Exam01 {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}

/*
i=4
j=1
k=11
*/
```
### 小结
- 赋值= ,最后计算
- =右边的从左到右加载值依次压入操作数栈
- 实际先算哪个,看运算符优先级
- 自增、自减操作都是直接修改变量的值,不经过操作数栈
- 最后的赋值之前,临时结果也是存储在操作数栈中
- 建议:《JVM虚拟机规范》关于指令的部分
