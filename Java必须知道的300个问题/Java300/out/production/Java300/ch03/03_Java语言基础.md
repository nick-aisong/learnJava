Java语言基础
========
- 问题11 char类型变量是否能保存一个汉字?
- 问题12 Java中是否有goto语句?
- 问题13 变量和常量有什么区别? 
- 问题14 语句 float num=3.14159 是否正确?
- 问题15 short和char类型的取值范围各是多少?
- 问题16 final参数可以修改吗? 
- 问题17 局部变量可以定义私有修饰类型吗?
- 问题18 表达式 3-2.6 == 0.4的值是什么?
- 问题19 自增、自减运算符放在变量前后的区别是什么?
- 问题20 用最有效率的方法算出2乘以16等于几? 
- 问题21 &和&&两个运算符的区别是什么?
- 问题22 运算符=与==有何区别?
- 问题23 能正确编译“short s=1;s=s+1;”吗?
- 问题24 表达式9/2与9/2.0的结果是否相等?
- 问题25 如何判断一个整数是奇数还是偶数?
- 问题26 不使用循环和条件语句如何求1+2+…+n的值?
- 问题27 复合赋值运算符+=计算的结果一定正确吗?
- 问题28 两个整数相乘的结果一定正确吗?
- 问题29 如何使用位运算符计算2的n次幂?
- 问题30 如何自定义类实现数值的四舍五入?

#### 问题11 char类型变量是否能保存一个汉字?
> 问：在Java语言中，可以使用char类型的变量来存储单个的字符，请问是否能用char类型的变量来存储一个汉字呢?

答：计算机中数据的存储是以字节为单位，常见的磁盘大小和文件大小分别以GB、MB、KB等来表示，
它们之间的换算单位是1GB=1024MB，1MB=1024KB，1KB=1024 byte (即字节)，每个字节的取值范围是-128~127，
它可以保存一个英文字符，包括字母、数字和英文标点。而汉字的存储是由两个字节保存的，
因为汉字的数量太多，它的编码范围远远超过一个字节的取值范围，所以必须使用双字节来表示。
在Java语言中，使用Unicode字符集对字符进行编码，可以存储65535个字符，所以Java的字符类型被定义为双字节，
因此在Java语言中可以使用char类型的变量来存储汉字

在Java语言中一个char类型的变量占16位(即两个字节)的存储空间，由于Java语言使用Unicode字符集对字符进行编码，
因此char类型的变量可以存储任何字符，这些字符可以是英文字母、汉字、日文片段以及其他许多国家的字符

#### 问题12 Java中是否有goto语句?
> 问：在编程时，许多语言都提供了goto语句，用于实现程序的跳转功能。请问Java语言是否提供了goto 语句?

答：goto语句源于C语言和Basic语言，主要在面向过程的程序设计语言中使用。
Java是面向对象的程序设计语言，并不需要goto语句，而是将goto作为Java的保留字

说明:虽然Java语言中还不能使用goto语句，但是在Java语言中也提供了跳转语句，
即在循环语句中使用带标签的continue语句和带标签的break语句，这样就可以实现循环的跳转功能

为了与其他程序设计语言相一致，Java语言将goto作为保留关键字，可能会在以后的版本中使用。
虽然goto目前只是Java的保留字,但是不可以用goto作为Java语言的变量名

#### 问题13 变量和常量有什么区别? 
> 问：在使用Java语言进行程序设计时，经常需要用到常量和变量来存储信息。请简单叙述变量和常量有什么区别?

答：变量和常量是编程语言中最基本的两个知识点，变量的值可以改变而常量的值在初始化以后是无法改变的。
常量在定义时要使用final关键字修饰。下面的代码段首先定义了一个int型的常量CONST,并赋值为10；
并定义了一个int型的变量num,其初始值为100，并输出变量的值；然后在程序运行时改变该变量的值为180，并输出改变后变量的值；
最后输出常量CONST的值

代码见 VarientAndConstant

常量只在声明时进行定义，一旦定义就不允许在程序运行时再改变其值，常量一般都以大写字母表示，
并使用final关键字进行修饰;在程序运行时，有些内容的值需要经常改变，这时就要使用变量进行存储，
也就是说变量在程序运行时其值是可以改变的

#### 问题14 语句 float num=3.14159 是否正确?
> 问：在程序中经常需要使用浮点数，并通过变量来存储浮点数，以方便在程序中使用。
请说明定义并初始化浮点数的语句 float num=3.14159 是否正确?

答： Java语言提供了两种用于存储浮点数的基本类型，即float和double，
而Java语言默认的浮点类型是double，所以当在程序代码段中为float型的变量赋值3.14159时，
会被认为是double类型。由于double型数据占用的存储空间超出了float型数据的存储空间，
所以需要强制类型转换为float类型或者为浮点数添加字母f或F作为后缀，而语句float num=3.14159，
既没有强制类型转换，也没有为浮点数3.14159添加后缀f或F，所以语句float num=3.14159是错误的

为float型变量num正确赋值的代码如下:
```java
float num = 3.14159f;
float num = 3.14159F;
```
在为float型的变量赋值为浮点数时，建议尽量少用强制类型转换，因为当把高精度的double型数据，
强制类型转换为低精度的float型数据时，会有精度损失，而正确的做法则是在浮点数值后加后缀f或F,
这样就可以正确地为float型变量赋值了

#### 问题15 short和char类型的取值范围各是多少?
> 问：在使用Java语言进行程序设计时，经常需要使用short型和char型存储数值，请简述short型和char型的取值范围各是多少?

答：short是有符号的数据类型,也就是说它可以表示负数和正数,该类型在二进制编码中，把最高位数位作为符号位，用来标识整数的正负符号。
而char是字符类型，它主要用于表示指定编码的字符，而字符编码没有负数，所以字符类型被定义成无符号的类型，
也就是说字符类型的二进制编码中没有保留最高位作为符号标识用途，所以它的取值会比short类型所能表示的正数大一倍。

在Java语言中，short类型的取值范围是-32768~ 32767，而char字符类型的编码值的取值范围是0~65535

在程序中short型数值与char型数值可以相互转换，但是一定要注意这两种类型的取值范围，
否则可能会出现错误的结果，因此在转换时要注意对这两种类型取值范围的公共部分进行转换，
这样就可以最大限度地避免出现不必要的错误

#### 问题16 final参数可以修改吗? 
> 问：在学习Java时，会看到方法中有的参数使用关键字final进行了修饰。请问final修饰的参数是否允许修改呢?

答：参数被修饰成final,意味着该参数不能在方法体中被修改，所以一旦修改了方法体中的final参数，程序将无法通过编译
```java
public class Something {
    public int addOne(final int x) {
        return ++x; //改变final参数的值，出错
    }
}
```

在Java语言中，final修饰的变量其值是不允许在程序运行时改变的；final修饰的类是不允许有子类的；
final修饰的方法是不允许被重写的；同样final修饰的参数也是不允许被修改的

#### 问题17 局部变量可以定义私有修饰类型吗?
> 问：在Java语言中提供了多种修饰类型，如私有类型private、受保护的类型protected、公共类型public等，请问局部变量可以定义私有修饰类型吗?

答：在Java语言中，可以使用private、protected和public修饰类、成员变量和方法，但是不可以使用private、protected 和public修饰局部变量。
final可以用来修饰局部变量，但这样修饰后它会变为常量
```java
public class Something {
    void doSomething() {
        private String s = ""; //使用private修饰局部变量，出错
        int l = s.length();
    }
}
```
局部变量只在定义它的方法内部有效，并且不能使用private、protected 和public等修饰符进行修饰，当局部变量所在的方法调用结束后，
Java虚拟机将自动释放局部变量所占用的资源

#### 问题18 表达式 3-2.6 == 0.4的值是什么?
> 问：在进行程序设计时，经常需要输出一些表达式的值，因此需要对各种运算进行分析。
那么表达式3-2.6 == 0.4的值会是什么呢?

答：首先对表达式3-2.6 == 0.4进行分析，该表达式有两个运算符，分别为减运算符-和关系运算符==，由于减运算符-的优先级高于关系运算符==，
所以该表达式将输出一个布尔值，即输出true或false，具体输出什么取决于3-2.6的值  

在Java中基本类型的浮点数运算是不精确的，如果在Java程序中输出3-2.6的值，可以看到输出的结果是0.399999999999999，这个值是不精确的，
所以表达式3-2.6 == 0.4的值是假，即false

代码见 ImpreciseResult、PrecisionResult

这是一个非常实用的问题，在实际应用中为了得到精确的计算结果，对于浮点数的运算一般不使用基本数据类型来实现，
而是使用BigDecimal类来实现，但是在使用BigDecimal创建对象时，构造方法的参数要使用双引号括起来，
而不能直接在构造方法中写浮点值创建BigDecimal对象

#### 问题19 自增、自减运算符放在变量前后的区别是什么?
> 问：自增和自减运算符用于对变量进行加1和减1运算，自增和自减运算符既可以放在变量的前边，也可以放在变量的后边。
那么自增、自减运算符放在变量前后有什么区别呢?

答：自增(++)、自减(--)运算符是单目运算符，可以放在操作元之前，作元之后，对操作元进行加1、减1操作。
操作元必须是整数类型或浮点类型的变量。自增(或自减)运算符放在操作元之后，先使用变量的值，然后再对变量加1 (或减1)

- 自增、自减运算符放在变量前后没有区别的情况  
在使用自增和自减运算符时，自增和自减运算符放在变量之前和之后，单从变量自身来说是没有区别的，都是对变量进行加1和减1运算

代码见 AddAndSubstract

#### 问题20 用最有效率的方法算出2乘以16等于几? 
> 问：在Java程序中，可以通过多种方法计算出2乘以16的值，例如，使用2 * 16或使用2 << 4。
那么算出2乘以16最有效率的方法是什么呢?

答： 由于在计算机中，位运算的效率是最高的，所以要想找出最有效率的运算方法，应该从位运算入手。
Java 语言的位运算中提供了左移、右移和无符号右移运算符，它们分别是 <<、>>、>>>。
左移运算会把操作数变大，它相当于把左侧操作数乘以2的n次幂，这个n是由右操作数决定的。
而右移运算恰恰相反，它实现的是除法

使用位运算符计算2乘以16，实现代码如下：
````java
int value = 2 << 4;
````
说明：上面的代码段中2 << 4就是将2进行左移4位运算，相当于2乘以2的4次方，执行后value的值是32,与2乘以16的值是完全相同的，
但是由于位运算的速度要比乘法运算的速度快，所以2乘以16等于几最有效率的计算方法应该写成2 << 4

这里主要考查的是读者对位运算的理解程度，以及对计算机组成原理的掌握程度。
计算机只能识别由0和1组成的二进制数据，位运算直接对数据进行二进制移位运算，而乘法运算则需要先在计算机中进行相应的转换，
然后进行计算才能得到正确的结果，因此速度要比左移位运算慢，但是由于计算机硬件和软件技术的提高，使用计算机计算的速度非常快，
因此少量数据的乘法运算和移位运算的区别基本体现不出来

#### 问题21 &和&&两个运算符的区别是什么?
> 问：在编程过程中，经常会用到运算符&和&&，这两个运算符都可以进行与运算。那么运算符&和&&有什么区别呢?

答： 位运算与逻辑运算的运算符比较相似，例如&和&&就只有字符数量的差别，但这只是在视觉上，它们的用途并不相同。
前者属于位运算，后者是逻辑运算，而逻辑运算主要是用于条件判断的。另外，&&运算符在左侧表达式为false时，不会再执行运算，从而提高运算速度。
&是位运算符，表示按位与运算; &&是逻辑运算符，表示逻辑与运算，&&运算符的效率更高

- 按位与运算的运算符&是双目运算符

按位与的运算规则：当两个整数进行按位与运算时，如果对应位都是1，则结果为1,否则结果为0。如果参加运算的两个数精度不同，则结果转换为精度较高的数据类型。
下面以3和6进行按位与运算为例讲解按位与的运算过程:  

1. 整数3的二进制表示为: 00000000 00000000 00000000 00000011  
2. 整数6的二进制表示为: 00000000 00000000 00000000 00000110  
3. 按位与运算的结果为2: 00000000 00000000 00000000 00000010  

说明：由于3和6的二进制数右边第2位都是1,所以计算结果中右边第2位也是1，而3和6的二进制数其他对应位不都为1,所以其他对应位的计算结果都为0

- 逻辑与运算符&&是双目运算符，用于完成两个表达式的逻辑与运算

逻辑与的运算规则：当用&&连接的两个表达式都为真时，整个表达式为真，否则为假

运算符&用于对两个数以二进制的形式,按位进行逻辑与运算；而运算符&&通常用于条件表达式中，对两个或多个关系表达式进行逻辑与运算，并且运算符&&是短路运算符，
也就是当&&之前的表达式为false时，就不再对&&之后的表达式进行计算了，而是直接返回假值false,所以运算符&&的效率更高

#### 问题22 运算符=与==有何区别?
> 问：运算符=与==在程序中经常使用，请简述运算符=与=有什么区别?

答：这两个运算符经常被混淆，很多程序开发人员在相等条件判断时使用了赋值运算符,即=符号，从而造成程序错误，所以关于这两个运算符的面试题出现的几率很高。
=是赋值运算符，用于为变量或常量赋值，而==则是表示相等关系的关系运算符，通常用于进行条件判断

- 赋值运算符

赋值运算符=是双目运算符，其作用就是为变量赋值,符号是=及其扩展运算符+=、-=、*=、/=、%=、&=和^=等。其中=用于把右边表达式的值赋值给左边的变量；
而扩展运算符的作用是用左边变量的值和右边表达式的值进行相应运算后，再把运算结果赋值给左边的变量

- 关系运算符

关系运算符用于比较两个值的大小关系，运算结果是逻辑值真(true) 或逻辑值假(false)。关系运算符包括大于(>)、小于(<)、大于等于(>=)、小于等于(<=)、等于(==)和不等于(!=) 6个

该问题主要考查读者对Java基础知识的掌握程度,对于初学者来说，在进行等于条件判断时，经常使用赋值运算符=对两个表达式进行大小比较，这就导致条件判断出错，
因为进行条件判断时，表示相等关系应该使用关系运算符==，这也是初学者经常犯的错误，例如判断a与b相等关系的正确写法是if(a==b){},而初学者经常会写成if(a=b){}，
这样程序就会出错

#### 问题23 能正确编译“short s=1;s=s+1;”吗?
> 问：在进行程序设计时，经常需要进行一些算术运算，有的运算能够通过编译器编译，而有的运算则不能通过编译器编译。
那么能正确编译“short s = 1;s = s+ 1;”吗?

答：Java语言中默认的整数类型为int，代码中s是short类型的变量，但是与它相加的整数1却是默认数据类型int，
那么相加的结果也会是int类型，int类型必须经过强制类型转换才能赋值给short类型的变量  

因此下面的语句将无法正确编译，因为s与int类型数据求和后是int类型的数值，无法赋值给short类型的变量

本问题主要考查读者对不同类型数据的计算以及对数值默认类型的掌握,在Java语言中，整数的默认类型是int型，浮点数的默认类型是double型。
对于不同类型的数值进行计算时，结果总是转换为精度最高的数据类型，因此将计算结果赋值给变量时，这个变量的类型的精度不能低于参加计算的精度最高的数据类型，
否则就会发生编译错误

#### 问题24 表达式9/2与9/2.0的结果是否相等?
> 问：在数学中进行除法运算时，9/2与9/2.0的结果是相等的。那么在计算机中表达式9/2与9/2.0的结果是否相等呢?

答：整数与整数之间运算的结果必然是整数，就算是除法也会执行整除而舍弃小数。
所以9/2的结果是4，但是整数与浮点数的运算会转换为浮点数类型再进行运算，其结果应是浮点数类型，所以9/2.0的结果是4.5,所以9/2的结果与9/2.0的结果是不相等的

代码见 DivisionOperation

由于整数除法与浮点数除法的输出结果存在很大的差别，所以在进行程序设计时，要由于整数除法与浮点数除法的输出结果存在很大的差别，所以在进行程序设计时，
要之又慎，如果有必要可以考虑使用BigDecimal类提供的方法实现高精度的算术运算

#### 问题25 如何判断一个整数是奇数还是偶数?
> 问：在进行程序开发时，经常需要通过判断一个整数是奇数还是偶数，来实现一些特殊的效果和功能，例如，让表格的奇数行显示一种背景颜色，偶数行显示另一种背景颜色。 
那么该如何判断一个整数是奇数还是偶数呢?

答： 判断奇数与偶数要从该整数与2的余数入手，如果该整数与2的余数是1；说明该整数是奇数，如果余数是0，说明该整数是偶数。
在Java语言中求余数的运算符是%

假设有一个整数N，那么判断N是奇数还是偶数，可以通过N%2的结果来实现，如果结果等于1就说明N是奇数，否则N就是偶数

代码见 OddEven

判断奇数和偶数在实际生产和生活中也经常使用，具有重大意义，因此程序中也经常需要对奇数和偶数进行处理，例如在使用循环输出信息时，
可以在循环变量是奇数时，输出一种符号，是偶数时输出另一种符号

#### 问题26 不使用循环和条件语句如何求1+2+…+n的值?
> 问：在进行程序设计时，如果要求类似1+2+…+n的值，通常是使用for、while、if、else、switch、case等关键字以及条件判断语句来实现，但是本题要求不能使用这些关键字。
该如何计算1+2+...+n的值呢?

答：本题的要求限制了所有循环语句和条件判断语句，但是唯独没有限制三元运算符(?:)，所以可以从这个运算符着手，并利用递归实现循环，从而完成计算1+2+…+n的值的功能

代码见 Swap

本题考查读者对基础知识掌握的熟练程度，以及对知识的综合应用能力。由于本题限制了循环语句的使用，所以应该想到递归可以实现循环的功能，因此可以考虑用递归实现计算1+2+…+n的值

#### 问题27 复合赋值运算符+=计算的结果一定正确吗?
> 问：在Java语言中赋值运算符有=，及其扩展运算符+=、-=、*=、|=等复合赋值运算符，这些复合赋值运算符同样可以实现加、减、乘、除等四则运算。
请问复合赋值运算符+=计算的结果一定正确吗?

答：通过复合赋值运算符可以实现运算和为变量赋值的操作，但是如果不慎重使用复合赋值运算符，就可能会计算出错误的结果，这对于一些重要的计算将会造成严重损失,
例如银行的存取款业务是不允许有任何差错的，否则将会造成无法挽回的经济损失

```java
short money1 = 30000; //声明short型变量
int money2 = 35000; //声明int型变量
money1 += money2; //用+=运算符计算money1与money2的和，并赋值给money1
System.out.println(money1); //输出变量moneyl的值
```
说明：上面示例输出变量money1的值为-536，并不是预期的结果65000， 这是因为在执行语句money1+=money2时，由于计算结果的值超出了short型数据的取值范围，所以自动将高位截掉，因而产生了错误的结果。
因此，在使用复合赋值运算符时，一定要特别小心，如果这个示例在软件中计算的是金额，损失可就大了

对于上面的示例，如果将语句money1+=money2写成语句moneyl=money1+money2，这样程序在编译时就会产生错误，因此就不会出现上面的错误结果了

上面的两个示例，归根结底都是由于发生溢出而产生的错误。所以，在编程时一定要考虑是否会发生溢出现象，对于可能发生溢出的情况，必须要进行处理，否则就会出现意想不到的错误结果，甚至会给企业造成严重损失

#### 问题28 两个整数相乘的结果一定正确吗?
> 问：在编写Java程序时，经常需要进行各种运算，例如，将两个int型整数的乘积，赋值给一个long型的变量。请问这种运算的计算结果一定是正确的吗?

答：在使用Java进行程序设计时，当为一个long型变量赋值一个整数常量时，如果这个值超出了int型数据的取值范围，程序就会出现编译错误，但是有一种情况程序不会出错，
就是将多个int型数据进行算术运算的结果赋值给long型变量，即使这个运算结果超出了int型数据的取值范围，程序也不会出现编译错误

说明：一个int型数据在内存中占4个字节的存储空间，其取值范围是-2^31 ~ 2^31-1,也就是在-2147483648 ~ 2147483647之间的整数。
而一个long型数据在内存中占8个字节的存储空间，其取值范围是-2^63 ~ 2^63-1之间的整数，也就是在-9223372036854775808 ~ 9223372036854775807之间的整数

- 出现编译错误
```java
long num = 2147483648 ; //为long型变量num赋值
```
上面示例为long型变量num赋值2147483648，由于2147483648超出了int型数据的取值范围，所以程序发生了编译错误。
为了能够正确赋值，需要在数值2147483648后加小写字母l或大写字母L,这样该示例就不会出错了

- 能通过编译，但结果错误
```java
long num = 2147483647 * 10; //将两个整数常量的乘积，赋值给long型变量num
System.out.println(num); //输出变量num的值
```
上面示例输出long型变量num的值为-10，并不是我们期待的结果21474836470，这是由于参加计算的两个整数并没有超出int型数据的取值范围，所以不会产生编译错误；
结果错误是由于当这两个数进行乘积运算时，是以int型数据进行计算的，所以计算结果也应该是int类型，由于计算结果超出了int型数据的取值范围，所以程序发生了溢出，
但是程序会保留这个溢出的结果，所以输出了-10

- 既能通过编译，又能保证结果正确
```java
long num = 2147483647L * 10; //将两个整数常量的乘积，赋值给long型变量num
System.out.println(num); //输出变量num的值
```
上面示例输出long型变量num的值为21474836470，这正是我们所期待的正确结果，这是由于在参加计算的两个整数中的第一个整数常量后添加了大写字母L，
这样表示要进行的运算是按长整数进行的，所以程序不会产生溢出，因此可以计算出正确的结果

本题重在强调在程序设计时，一定要非常细心，如果稍有偏差，就会给企业和个人造成严重损失，例如在银行、金融等系统，如果计算结果错误将会给银行和金融单位造成巨大的损失，
因此在学习基础知识时，一定要认真细致，将每个细节都学精学透，这样以后才能真正成为一名优秀的程序员

#### 问题29 如何使用位运算符计算2的n次幂?
> 问：在工作中，有时需要计算2的n次幂。如此次数越多，计算起来就越容易出现错误，例如计算2的20次幂的结果很容易出现多乘或少乘的情况。
那么在程序中该如何使用位运算符计算2的20次幂呢?

答：创建一个ANumber类，通过左移操作实现计算2的20次幂操作

代码见 Swap

Java的位运算有操作二进制数据的能力，其中控制二二进制数位左移与右移操作是相对复杂而高级的，它在一些特殊运算中起关键作用

#### 问题30 如何自定义类实现数值的四舍五入?
> 问：在程序中经常需要对数值进行舍入处理，而Math类提供的rint()和round()方法，并不能满足实际应用中的所有需要，因此可以自定义一个类来实现四舍五入功能。
请问该如何自定义类来实现数值的四舍五入呢?

答：为了能够根据需要保留相应的小数位数，并进行四舍五入处理，可以创建一个自定义类RoundTool，在该类中创建能够根据需要进行四舍五入处理的round()方法，以满足不同的需要

代码见 RoundTool

本题通过自定义类实现了数值的四舍五入功能，可以锻炼读者独立思考和解决问题的能力，对于以后的软件开发工作将有很大的帮助