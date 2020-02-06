#### 第4章 走进JVM
Java源代码是怎么被机器识别并执行的呢?答案是Java虚拟机，即Java Virtual 
Machine，简称JVM。JVM提供商包括Sun、BEA、IBM 等。1999 年，Sun 公司发
布了由C/C++实现的HotSpot Java虚拟机。2006年，在JavaOne大会上开源了其相
关核心技术，启动OpenJDK项目，逐步形成了活跃的OpenJDK社区。2010年，Sun
公司被Oracle公司收购。Oracle的HotSpot JVM实现，是目前OpenJDK使用的主流
JVM，它采用解释与编译混合执行的模式，其JIT技术采用分层编译，极大地提升了
Java的执行速度；BEA的JRockit在2008年被并入HotSpot；IBM的J9也在2017年
开源，形成了现在的OpenJ9社区

随着互联网的蓬勃发展及AI时代的到来，Java 在这些计算领域占据着越来越重
要的地位。目前越来越多的高科技公司，比如阿里、谷歌、亚马逊等，都有独立的
JVM团队基于OpenJDK开发自己的定制版本。阿里拥有丰富的Java应用场景，覆盖
云计算、金融、物流、电商等众多领域，需要解决高并发、高可用、分布式的复合问
题。AlibabaJDK，简称AJDK，作为阿里Java体系的基石，支撑了阿里经济体内所有
的Java业务。AJDK力求在复杂的技术环境下，满足阿里经济体快速发展的业务需求，
并历经了多次双十一的考验。AJDK突破了多个技术难点，比如多租户JVM、Wisp
协程技术、大数据场景的ZenGC等

本章从字节码说起，分析类加载的过程，并结合内存布局，讲解对象创建与垃圾
回收等各个知识点

##### 4.1 字节码
第1章已介绍0与1是计算机仅能识别的信号，经过0与1的不同组合产生了数
字之上的操作。另外，通过不同的组合亦产生了各种字符。同样，可以通过不同的组
合产生不同的机器指令。在不同的时代，不同的厂商，机器指令组成的集合是不同的。
但毕竟CPU是底层基础硬件，指令集通常以扩展兼容的方式向前不断演进。而机器
码是离CPU指令集最近的编码，是CPU可以直接解读的指令，因此机器码肯定是与
底层硬件系统耦合的

如果某个程序因为不同的硬件平台需要编写多套代码，这是十分令人崩溃的。
Java的使命就是一次编写、到处执行。在不同操作系统、不同硬件平台上，均可以
不用修改代码即可顺畅地执行，如何实现跨平台?有一个声音在天空中回响：计算机
工程领域的任何问题都可以通过增加一个中间层来解决。因此，中间码应运而生，即
“字节码” (Bytecode)。Java所有的指令有200个左右，一个字节(8位)可以存
储256种不同的指令信息，一个这样的字节称为字节码(Bytecode)。在代码的执行
过程中，JVM将字节码解释执行，屏蔽对底层操作系统的依赖；JVM也可以将字节
码编译执行，如果是热点代码，会通过JIT动态地编译为机器码，提高执行效率。如
图4-1所示，十六进制表示的二进制流通常是一个操作指令。起始的4个字节非常特殊，
即绿色框的cafe babe是Gosling定义的一个魔法数，意思是Coffee Baby，其十进制
值为3405691582。它的作用是：标志该文件是一个Java类文件，如果没有识别到该
标志，说明该文件不是Java类文件或者文件已受损，无法进行加载。而红色框代表
当前版本号，0x37的十进制为55，是JDK11的内部版本号

纯数字的字节码阅读起来像天书一样难，当初汇编语言为了改进机器语言，使
用助记符来替代对应的数字指令。JVM在字节码上也设计了一套操作码助记符，使
用特殊单词来标记这些数字。如ICONST_0代表00000011，即十六进制数为0x03；
ALOAD_0代表00101010，即0x2a；POP代表01010111，即0x57。ICONST和
ALOAD的首字母表示具体的数据类型，如A代表引用类型变量，I代表int类型相关
操作，其他类型均是其类型的首字母，例如FLOAD_0、LLOAD_0、FCONST_0等。
字节码主要指令如下

1. 加载或存储指令

在某个栈帧中，通过指令操作数据在虛拟机栈的局部变量表与操作栈之间来回传
输，常见指令如下：

(1) 将局部变量加载到操作栈中。如ILOAD(将int类型的局部变量压入栈)
和ALOAD(将对象引用的局部变量压入栈)等

(2) 从操作栈顶存储到局部变量表。如ISTORE、ASTORE等

(3) 将常量加载到操作栈顶，这是极为高频使用的指令。如ICONST、
BIPUSH、SIPUSH、 LDC等

- ICONST加载的是-1 ~ 5的数(ICONST与BIPUSH的加载界限)

- BIPUSH，即Byte Immediate PUSH，加载-128 ~ 127 之间的数

- SIPUSH，即Short Immediate PUSH，加载-32768 ~ 32767之间的数

- LDC，即Load Constant，在-2147483648 ~ 2147483647或者是字符串时，
JVM采用LDC指令压入栈中

```text
int a = -2:        L5
                       BIPUSH -2 // 在-1至5之外的数字使用BIPUSH指令加载
int b = -1:        L6
                       ICONST_M1 // -1，直接使用ICONST加载的最小值
int e = 20000:

int f = 40000;     L7
                        ICONST_0
                   L8
int i = 40000:          SIPUSH 20000
                   L9
                        LDC 40000 
```
2. 运算指令

对两个操作栈帧上的值进行运算，并把结果写入操作栈顶，如IADD、IMUL等

3. 类型转换指令

显式转换两种不同的数值类型。如I2L、D2F等

4. 对象创建与访问指令

根据类进行对象的创建、初始化、方法调用相关指令，常见指令如下：

(1) 创建对象指令。如NEW、NEWARRAY等

(2) 访问属性指令。如GETFIELD、PUTFIELD、GETSTATIC等

(3) 检查实例类型指令。如INSTANCEOF、CHECKCAST等

5. 操作栈管理指令

JVM提供了直接控制操作栈的指令，常见指令如下：

(1) 出栈操作。如POP即一个元素，POP2即两个元素

(2) 复制栈项元素并压入栈。如DUP

6. 方法调用与返回指令

常见指令如下：

(1) INVOKEVIRTUAL指令：调用对象的实例方法

(2) INVOKESPECIAL指令：调用实例初始化方法、私有方法、父类方法等

(3) INVOKESTATIC指令：调用类静态方法

(4) RETURN指令：返回VOID类型

7. 同步指令

JVM使用方法结构中的ACC_SYNCHRONIZED标志同步方法，指令集中有
MONITORENTER和MONITOREXIT支持synchronized语义

除字节码指令外，还包含一些额外信息。例如，LINENUMBER存储了字
节码与源码行号的对应关系，方便调试的时候正确地定位到代码的所在行；
LOCALVARIABLE存储当前方法中使用到的局部变量表

我们编写好的.java文件是源代码文件，并不能交给机器直接执行，需要将其编
译成为字节码甚至是机器码文件。那么静态编译器如何把源码转化成字节码呢?如图
4-2所示

词法解析是通过空格分隔出单词、操作符、控制符等信息，将其形成token信息流，
传递给语法解析器；在语法解析时，把词法解析得到的token信息流按照Java语法规
则组装成一棵语法树， 如图4-2虚线框所示；在语义分析阶段，需要检查关键字的使
用是否合理、类型是否匹配、作用域是否正确等；当语义分析完成之后，即可生成字
节码

字节码必须通过类加载过程加载到JVM环境后，才可以执行。执行有三种模式：
第一，解释执行；第二，JIT编译执行；第三，JIT编译与解释混合执行(主流JVM
默认执行模式)。混合执行模式的优势在于解释器在启动时先解释执行，省去编译时间。
随着时间推进，JVM通过热点代码统计分析，识别高频的方法调用、循环体、公共
模块等，基于强大的JIT动态编译技术，将热点代码转换成机器码，直接交给CPU
执行。JIT的作用是将Java字节码动态地编译成可以直接发送给处理器指令执行的机
器码。简要流程如图4-3所示

注意解释执行与编译执行在线上环境微妙的辩证关系。机器在热机状态可以承
受的负载要大于冷机状态(刚启动时)，如果以热机状态时的流量进行切流，可能使
处于冷机状态的服务器因无法承载流量而假死。在生产环境发布过程中，以分批的方
式进行发布，根据机器数量划分成多个批次，每个批次的机器数至多占到整个集群的
1/8。曾经有这样的故障案例：某程序员在发布平台进行分批发布，在输入发布总批
数时，误填写成分为两批发布。如果是热机状态，在正常情况下一半的机器可以勉强
承载流量，但由于刚启动的JVM均是解释执行，还没有进行热点代码统计和JIT动
态编译，导致机器启动之后，当前1/2发布成功的服务器马上全部宕机，此故障说明
了JIT的存在

###### 4.2 类加载过程
在冯·诺依曼定义的计算机模型中，任何程序都需要加载到内存才能与CPU进
行交流。字节码.class文件同样需要加载到内存中，才可以实例化类。“兵马未动，
粮草先行。”ClassLoader正是准备粮草的先行军，它的使命就是提前加载.class 类文
件到内存中。在加载类时，使用的是Parents Delegation Model，译为双亲委派模型，
这个译名有些不妥。如果意译的话，则译作“ 溯源委派加载模型”更加贴切

Java的类加载器是一个运行时核心基础设施模块，如图4-4 所示，主要是在启动
之初进行类的Load、Link和Init，即加载、链接、初始化

第一步，Load阶段读取类文件产生二进制流，并转化为特定的数据结构，初步
校验cafe babe魔法数、常量池、文件长度、是否有父类等，然后创建对应类的java.
lang.Class实例

第二步，Link阶段包括验证、准备、解析三个步骤。验证是更详细的校验，比
如final是否合规、类型是否正确、静态变量是否合理等；准备阶段是为静态变量分
配内存，并设定默认值，解析类和方法确保类与类之间的相互引用正确性，完成内存
结构布局

第三步，Init阶段执行类构造器\<clinit\>方法，如果赋值运算是通过其他类的静
态方法来完成的，那么会马上解析另外一个类，在虚拟机栈中执行完毕后通过返回值
进行赋值

类加载是一个将.class字节码文件实例化成Class对象并进行相关初始化的过程。
在这个过程中，JVM会初始化继承树上还没有被初始化过的所有父类，并且会执行
这个链路上所有未执行过的静态代码块、静态变量赋值语句等。某些类在使用时，也
可以按需由类加载器进行加载

全小写的class是关键字，用来定义类，而首字母大写的Class，它是所有class的类。
这句话理解起来有难度，是因为类已经是现实世界中某种事物的抽象，为什么这个抽
象还是另外一个类Class的对象?示例代码如下：
```java
public class ClassTest {
    // 数组类型有一个魔法属性: length来获取数组长度
    private static int[] array = new int[3];
    private static int length = array.length;
    
    // 任何小写class定义的类，也有一个魔法属性: class, 来获取此类的大写Class类对象
    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;
    
    public static void main (String[] args) throws Exception {
        // 通过newInstance方法创建One和Another的类对象    (第1处)
        One oneObject = one.newInstance();
        oneObject.call();
        
        Another anotherObject = another.newInstance();
        anotherobject.speak();
        
        // 通过one这个大写的Class对象，获取私有成员属性对象Field    (第2处)
        Field privateFieldInOne = one.getDeclaredField("inner");
        
        // 设置私有对象可以访问和修改    (第3处)
        privateFieldInOne.setAccessible(true);
        
        privateFieldInOne.set(oneObject, "world changed.");
        // 成功修改类的私有属性inner变量值为world changed.
        System.out.println(oneobject.getInner());
    }
}

class One {
    private String inner = "time flies.";
    
    public void call() {
        System.out.println("hello world.");
    }

    public String getInner() {
        return inner;
    }
}

class Another {
    public void speak() {
        System.out.println("easy coding.");
    }
}
```
执行结果如下：
```text
hello world.
easy coding.
world changed.
```
- 第1处说明：Class类下的newInstance()在JDK9中已经置为过时，使用
getDeclaredConstructor().newInstance()的方式。这里着重说明一下new与
newInstance的区别。new是强类型校验，可以调用任何构造方法，在使用
new操作的时候，这个类可以没有被加载过。而Class类下的newInstance
是弱类型，只能调用无参数构造方法，如果没有默认构造方法，就抛
出InstantiationException异常；如果此构造方法没有权限访问，则抛出
IllegalAccessException异常。Java通过类加载器把类的实现与类的定义进行
解耦，所以是实现面向接口编程、依赖倒置的必然选择

- 第2处说明：可以使用类似的方式获取其他声明，如注解、方法等，如图4-5
所示

- 第3处说明：private成员在类外是否可以修改?通过setAccessible(true)操作，
即可使用大写Class类的set方法修改其值。如果没有这一步,则抛出如下异常：
```text
Exception in thread "main" IllegalAccessException: class ClassTest cannot access a
member of class com.alibaba.easy.coding.classloader.One with modifiers "private" at base/
jdk.internal.reflect.Reflection.newlllegalAccessException(Reflection.java:352)
```
通过以上示例，对于Class这个“类中之王”，不会有恐惧心理了吧?那么回到
类加载中，类加载器是如何定位到具体的类文件并读取的呢?

类加载器类似于原始部落结构，存在权力等级制度。最高的一层是家族中威望最
高的Bootstrap，它是在JVM启动时创建的，通常由与操作系统相关的本地代码实现，
是最根基的类加载器，负责装载最核心的Java类，比如Object、System、String等；
第二层是在JDK9版本中，称为Platform ClassLoader，即平台类加载器，用以加载一
些扩展的系统类，比如XML、加密、压缩相关的功能类等，JDK9 之前的加载器是
Extension ClassLoader；第三层是Application ClassLoader的应用类加载器，主要是加
载用户定义的CLASSPATH路径下的类。第二、三层类加载器为Java语言实现，用
户也可以自定义类加载器。查看本地类加载器的方式如下：
```java
// 正在使用的类加载器: jdk.internal.loader.ClassLoaders$AppClassLoader@69d0a
ClassLoader c = TestWhoLoad.class.getClassLoader();
// AppClassLoader 的父加载器是PlatformClassLoader
ClassLoader c1 = c.getParent();
// PlatformClassLoader 的父加载器是Bootstrap：它是使用C++来实现的，返回null
ClassLoader c2 = c1.getParent();
```
代码上方的注释内容为JDK11的执行结果。在JDK8环境中，执行结果如下：
```text
sun.misc.Launcher\$AppClassLoader@14dad5dc
sun.misc.Launcher\$ExtClassLoader@6e0be858
null
```
AppClassLoader的Parent为Bootstrap，它是通过C/C++实现的，并不存在于
JVM体系内，所以输出为null，类加载器具有等级制度，但是并非继承关系，以组合
的方式来复用父加载器的功能，这也符合组合优先原则，详细的双亲委派模型如图4-6
所示

低层次的当前类加载器，不能覆盖更高层次类加载器已经加载的类。如果低层次
的类加载器想加载一个未知类， 要非常礼貌地向上逐级询问：“请问，这个类已经加
载了吗?”被询问的高层次类加载器会自问两个问题：第一， 我是否已加载过此类?
第二，如果没有，是否可以加载此类?只有当所有高层次类加载器在两个问题上的答
案均为“否”时，才可以让当前类加载器加载这个未知类。如图4-6所示，左侧绿色
箭头向上逐级询问是否已加载此类，直至Bootstrap ClassLoader，然后向下逐级尝试
是否能够加载此类，如果都加载不了，则通知发起加载请求的当前类加载器，准予加
载。在右侧的三个小标签里，列举了此层类加载器主要加载的代表性类库，事实上不
止于此。通过如下代码可以查看Bootstrap所有已经加载的类库：
```java
URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
for (java.net.URL url : urLs) {
    System.out.println(url.toExternalForm());
}
```
执行结果如下:
```text
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/lib/rt.jar
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/lib/resourcesjar
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/lib/sunrsasign.jar
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/lib/jsse.jar
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/lib/jce.jar
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/lib/charsets.jar
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/lib/jf.jar
file:/Library/Java/JavaVirtualMachines/jdk11/Contents/Home/jre/classes
```
Bootstrap加载的路径可以追加，不建议修改或删除原有加载路径。在JVM中增
加如下启动参数，则能通过Class.forName正常读取到指定类，说明此参数可以增加
Bootstrap的类加载路径：
```java
-Xbootclasspath/a:/Users/yangguanbao/book/easyCoding/byJdk11/src
```
如果想在启动时观察加载了哪个jar包中的哪个类，可以增加-XX:+TraceClassLoading
参数，此参数在解决类冲突时非常实用，毕竟不同的JVM环境对于加载类的顺序并
非是一致的。有时想观察特定类的加载上下文，由于加载的类数量众多，调试时很难
捕捉到指定类的加载过程，这时可以使用条件断点功能。比如，想查看HashMap的
加载过程，在loadClass处打个断点，并且在condition框内输入如图4-7所示条件

在学习了类加载器的实现机制后，知道双亲委派模型并非强制模型，用户可以自
定义类加载器，在什么情况下需要自定义类加载器呢?

(1) 隔离加载类。在某些框架内进行中间件与应用的模块隔离，把类加载到不
同的环境。比如，阿里内某容器框架通过自定义类加载器确保应用中依赖的jar包不
会影响到中间件运行时使用的jar包

(2) 修改类加载方式。类的加载模型并非强制，除Bootstrap外，其他的加载并
非一定要引入，或者根据实际情况在某个时间点进行按需进行动态加载

(3) 扩展加载源。比如从数据库、网络，甚至是电视机机顶盒进行加载

(4) 防止源码泄露。Java代码容易被编译和篡改，可以进行编译加密。那么类
加载器也需要自定义，还原加密的字节码

实现自定义类加载器的步骤：继承ClassLoader，重写findClass()方法，调用
defineClass()方法。一个简单的类加载器实现的示例代码如下：
```java
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNot FoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }
    
    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类
    }
}

public static void main(String[] args) {
    CustomClassLoader customClassLoader = new CustomClassLoader();
    try {
        Class<?> clazz = Class.forName ("One", true, customClassLoader);
        Object obj = clazz.newInstance();
        System.out.println(obj.getClass().getClassLoader());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```
执行结果如下：
```text
classloader.CustomClassLoader@5e481248
```
由于中间件一般都有自己的依赖jar包，在同一个工程内引用多个框架时，往往
被迫进行类的仲裁。按某种规则jar包的版本被统一指定， 导致某些类存在包路径、
类名相同的情况，就会引起类冲突，导致应用程序出现异常。主流的容器类框架都会
自定义类加载器，实现不同中间件之间的类隔离，有效避免了类冲突

###### 4.3 内存布局
内存是非常重要的系统资源，是硬盘和CPU的中间仓库及桥梁，承载着操作系统和
应用程序的实时运行。JVM内存布局规定了Java在运行过程中内存申请、分配、管理的
策略，保证了JVM的高效稳定运行。不同的JVM对于内存的划分方式和管理机制存在
着部分差异。结合JVM虚拟机规范，来探讨一下经典的JVM内存布局，如图4-8所示

1. Heap(堆区)

Heap是OOM故障最主要的发源地，它存储着几乎所有的实例对象，堆由垃圾
收集器自动回收，堆区由各子线程共享使用。通常情况下，它占用的空间是所有内存
区域中最大的，但如果无节制地创建大量对象，也容易消耗完所有的空间。堆的内存
空间既可以固定大小，也可以在运行时动态地调整，通过如下参数设定初始值和最大
值，比如-Xms256M -Xmx1024M，其中-X表示它是JVM运行参数，ms 是memory
start的简称，mx是memory max的简称，分别代表最小堆容量和最大堆容量。但是
在通常情况下，服务器在运行过程中，堆空间不断地扩容与回缩，势必形成不必要的
系统压力，所以在线上生产环境中，JVM的Xms和Xmx设置成一样大小，避免在
GC后调整堆大小时带来的额外压力

堆分成两大块：新生代和老年代。对象产生之初在新生代，步入暮年时进入老年
代，但是老年代也接纳在新生代无法容纳的超大对象。新生代=1个Eden区+2个
Survivor区。绝大部分对象在Eden区生成，当Eden区装填满的时候，会触发Young
Garbage Collection，即YGC。垃圾回收的时候，在Eden区实现清除策略，没有被引
用的对象则直接回收。依然存活的对象会被移送到Survivor区，这个区真是名副其
实的存在。Survivor区分为S0和S1两块内存空间，送到哪块空间呢?每次YGC的
时候，它们将存活的对象复制到未使用的那块空间，然后将当前正在使用的空间完
全清除，交换两块空间的使用状态。如果YGC要移送的对象大于Survivor区容量的
上限，则直接移交给老年代。假如一些没有进取心的对象以为可以一直在新生代的
Survivor区交换来交换去，那就错了。每个对象都有一个计数器，每次YGC都会加
1。-XX:MaxTenuringThreshold参数能配置计数器的值到达某个阈值的时候，对象从
新生代晋升至老年代。如果该参数配置为1，那么从新生代的Eden区直接移至老年代。
默认值是15，可以在Survivor区交换14次之后，晋升至老年代。与图4-8匹配的对
象晋升流程图如图4-9所示

图4-9中，如果Survivor区无法放下，或者超大对象的阈值超过上限，则尝试
在老年代中进行分配；如果老年代也无法放下，则会触发Full Garbage Collection，即
FGC。如果依然无法放下，则抛出OOM。堆内存出现OOM的概率是所有内存耗尽
异常中最高的。出错时的堆内信息对解决问题非常有帮助，所以给JVM设置运行参
数-XX:+HeapDumpOnOutOfMemoryError，让JVM遇到OOM异常时能输出堆内信息，
特别是对相隔数月才出现的OOM异常尤为重要

在不同的JVM实现及不同的回收机制中，堆内存的划分方式是不一样的

2. Metaspace(元空间)

本书源码解析和示例代码基本采用JDK11版本，JVM则为Hotspot。早在JDK8
版本中，元空间的前身Perm区已经被淘汰。在JDK7及之前的版本中，只有Hotspot
才有Perm区，译为永久代，它在启动时固定大小，很难进行调优，并且FGC时会移
动类元信息。在某些场景下，如果动态加载类过多，容易产生Perm区的OOM。比
如某个实际Web工程中，因为功能点比较多，在运行过程中，要不断动态加载很多
的类，经常出现致命错误：
```text
"Exception in thread 'dubbo client x.x connector' java.lang.OutOfMemoryError:PermGen
space"
```
为了解决该问题，需要设定运行参数-XX:MaxPermSize=1280m，如果部署到
新机器上，往往会因为JVM参数没有修改导致故障再现。不熟悉此应用的人排查
问题时往往苦不堪言，除此之外，永久代在垃圾回收过程中还存在诸多问题。所
以，JDK8使用元空间替换永久代。在JDK8及以上版本中，设定MaxPermSize参
数，JVM在启动时并不会报错，但是会提示：Java HotSpot 64Bit Server VM warning:
ignoring option MaxPermSize=2560m; support was removed in 8.0

区别于永久代，元空间在本地内存中分配。在JDK8里，Perm区中的所有内容
中字符串常量移至堆内存，其他内容包括类元信息、字段、静态属性、方法、常量等
都移动至元空间内，比如图4-10中的Object类元信息、静态属性System.out、整型
常量10000000等。图4-10中显示在常量池中的String，其实际对象是被保存在堆内
存中的

3. JVM Stack(虚拟机栈)

栈(Stack)是一个先进后出的数据结构，就像子弹的弹夹，最后压入的子弹先发射，
压在底部的子弹最后发射，撞针只能访问位于顶部的那一颗子弹

相对于基于寄存器的运行环境来说，JVM是基于栈结构的运行环境。栈结构移
植性更好，可控性更强。JVM中的虚拟机栈是描述Java方法执行的内存区域，它是
线程私有的。栈中的元素用于支持虛拟机进行方法调用，每个方法从开始调用到执行
完成的过程，就是栈帧从入栈到出栈的过程。在活动线程中，只有位于栈顶的帧才是
有效的，称为当前栈帧。正在执行的方法称为当前方法，栈帧是方法运行的基本结构。
在执行引擎运行时，所有指令都只能针对当前栈帧进行操作。而StackOverflowError
表示请求的栈溢出，导致内存耗尽，通常出现在递归方法中。JVM能够横扫千军，
虚拟机栈就是它的心腹大将，当前方法的栈帧，都是正在战斗的战场，其中的操作栈
是参与战斗的士兵。操作栈的压栈与出栈如图4-11所示

虛拟机栈通过压栈和出栈的方式，对每个方法对应的活动栈帧进行运算处理，方
法正常执行结束，肯定会跳转到另一个栈帧上。在执行的过程中，如果出现异常，会
进行异常回溯，返回地址通过异常处理表确定。栈帧在整个JVM体系中的地位颇高，
包括局部变量表、操作栈、动态连接、方法返回地址等

(1) 局部变量表

局部变量表是存放方法参数和局部变量的区域。相对于类属性变量的准备阶段和
初始化阶段来说，局部变量没有准备阶段，必须显式初始化。如果是非静态方法，则
在index[0]位置上存储的是方法所属对象的实例引用，随后存储的是参数和局部变量。
字节码指令中的STORE指令就是将操作栈中计算完成的局部变量写回局部变量表的
存储空间内

(2) 操作栈

操作栈是一个初始状态为空的桶式结构栈。在方法执行过程中，会有各种指令往
栈中写入和提取信息。JVM的执行引擎是基于栈的执行引擎，其中的栈指的就是操
作栈。字节码指令集的定义都是基于栈类型的栈的深度在方法元信息的stack属性中，
下面用一段简单的代码说明操作栈与局部变量表的交互：
```java
public int simpleMethod() {
    int x = 13;
    int y = 14;
    int z = x + y;
    
    return z;
}
```
详细的字节码操作顺序如下：
```text
public simpleMethod();
descriptor: () I
flags: ACC_PUBLIC 
Code:
  stack=2, locals=4, args_Size=1 // 最大栈深度为2,局部变量个数为4
    BIPUSH 13    // 常量13压入操作栈
    ISTORE_1     // 并保存到局部变量表的slot_1中    (第1处)
    
    BIPUSH 14    // 常量14压入操作栈，注意是BIPUSH 
    ISTORE_2     // 并保存到局部变量表的slot_2中
    
    ILOAD_1      // 把局部变量表的slot_1元素(int x)压入操作栈
    ILOAD 2      // 把局部变量表的slot_2元素(int y)压入操作栈
    IADD         // 把上方的两个数都取出来，在CPU里加一下，并压回操作栈的栈顶
    ISTORE_3     // 把栈顶的结果存储到局部变量表的slot_3中
    
    ILOAD_3
    IRETURN      // 返回栈顶元素值
```
第1处说明：局部变量表就像一个中药柜， 里面有很多抽屉，依次编号为0，1，2，
3，... ，n，字节码指令ISTORE_1就是打开1号抽屉，把栈顶中的数13存进去。栈
是一个很深的竖桶，任何时候只能对桶口元素进行操作，所以数据只能在栈项进行存
取。某些指令可以直接在抽屉里进行，比如inc指令，直接对抽屉里的数值进行+1
操作。程序员面试过程中，常见的i++和++i的区别，可以从字节码上对比出来，如
表4-1所示

在表4-1左列中，iload_1 从局部变量表的第1号抽屉里取出一个数，压入栈顶，
下一步直接在抽屉里实现+1的操作，而这个操作对栈顶元素的值没有影响。所以
istore_2只是把栈顶元素赋值给a；表格右列，先在第1号抽屉里执行+1操作，然
后通过iload_1把第1号抽屉里的数压入栈顶，所以istore_2存入的是+1之后的值

这里延伸一个信息，i++并非原子操作。即使通过volatile关键字进行修饰，多
个线程同时写的话，也会产生数据互相覆盖的问题

(3) 动态连接

每个栈帧中包含一个在常量池中对当前方法的引用，目的是支持方法调用过程的
动态连接

(4) 方法返回地址

方法执行时有两种退出情况：第一，正常退出，即正常执行到任何方法的返回字
节码指令，如RETURN、IRETURN、 ARETURN等；第二，异常退出。无论何种退
出情况，都将返回至方法当前被调用的位置。方法退出的过程相当于弹出当前栈帧，
退出可能有三种方式：

- 返回值压入上层调用栈帧
- 异常信息抛给能够处理的栈帧
- PC计数器指向方法调用后的下一条指令

4. Native Method Stacks(本地方法栈)

本地方法栈(Native Method Stack)在JVM内存布局中，也是线程对象私有的，
但是虚拟机栈“主内”，而本地方法栈“主外”。这个“内外”是针对JVM来说的，
本地方法栈为Native方法服务。线程开始调用本地方法时，会进入一个不再受JVM
约束的世界。本地方法可以通过JNI(Java Native Interface)来访问虛拟机运行时的
数据区，甚至可以调用寄存器，具有和JVM相同的能力和权限。当大量本地方法出
现时，势必会削弱JVM对系统的控制力，因为它的出错信息都比较黑盒。对于内存
不足的情况，本地方法栈还是会抛出native heap OutOfMemory

重点说一下JNI类本地方法，最著名的本地方法应该是System.
currentTimeMillis()，JNI使Java深度使用操作系统的特性功能，复用非Java代码。
但是在项目过程中，如果大量使用其他语言来实现JNI，就会丧失跨平台特性，威胁
到程序运行的稳定性。假如需要与本地代码交互，就可以用中间标准框架进行解耦，
这样即使本地方法崩溃也不至于影响到JVM的稳定。当然，如果要求极高的执行效率、
偏底层的跨进程操作等，可以考虑设计为JNI调用方式

5. Program Counter Register(程序计数寄存器)

在程序计数寄存器(Program Counter Register, PC) 中，Register的命名源于
CPU的寄存器，CPU只有把数据装载到寄存器才能够运行。寄存器存储指令相关的
现场信息，由于CPU时间片轮限制，众多线程在并发执行过程中，任何一个确定的
时刻，一个处理器或者多核处理器中的一个内核，只会执行某个线程中的一条指令。
这样必然导致经常中断或恢复，如何保证分毫无差呢?每个线程在创建后，都会产生
自己的程序计数器和栈帧，程序计数器用来存放执行指令的偏移量和行号指示器等，
线程执行或恢复都要依赖程序计数器。程序计数器在各个线程之间互不影响，此区域
也不会发生内存溢出异常

最后，从线程共享的角度来看，堆和元空间是所有线程共享的，而虚拟机栈、本
地方法栈、程序计数器是线程内部私有的，从这个角度看一下 Java内存结构，如图4-12
所示

###### 4.4 对象实例化
Java是面向对象的静态强类型语言，声明并创建对象的代码很常见，根据某个类
声明一个引用变量指向被创建的对象，并使用此引用变量操作该对象。在实例化对象
的过程中，JVM中发生了什么化学反应呢?

(1) 下面从最简单的Object ref = new Object();代码进行分析，利用javap
-verbose -p命令查看对象创建的字节码如下：
```text
stack=2, locals=1, args_size=0
      NEW java/lang/Object
      DUP
      INVOKESPECIAL java/lang/0bject.<init> ()V
      ASTORE 1
      LocalVariableTable:
          Start    Length    Slot    Name    Signature
            8         1       0       ref    Ljava/lang/Object;
```
- NEW：如果找不到Class对象，则进行类加载。加载成功后，则在堆中分配内存，
从Object开始到本类路径上的所有属性值都要分配内存。分配完毕之后，进
行零值初始化。在分配过程中，注意引用是占据存储空间的，它是一个变量，
占用4个字节。这个指令完毕后，将指向实例对象的引用变量压入虛拟机栈顶

- DUP：在栈顶复制该引用变量，这时的栈顶有两个指向堆内实例对象的引用
变量。如果\<init\>方法有参数，还需要把参数压入操作栈中。两个引用变量
的目的不同，其中压至底下的引用用于赋值，或者保存到局部变量表，另一
个栈顶的引用变量作为句柄调用相关方法

- INVOKESPECIAL：调用对象实例方法，通过栈顶的引用变量调用\<init\>方法。
\<clinit\>是类初始化时执行的方法，而\<init\>是对象初始化时执行的方法

(2) 前面所述是从字节码的角度看待对象的创建过程，现在从执行步骤的角度
来分析：

- 确认类元信息是否存在。当JVM接收到new指令时，首先在metaspace内检
查需要创建的类元信息是否存在。若不存在，那么在双亲委派模式下，使用
当前类加载器以ClassLoader + 包名 + 类名为Key进行查找对应的.class 文件。
如果没有找到文件，则抛出ClassNotFoundException异常；如果找到，则进
行类加载，并生成对应的Class类对象

- 分配对象内存。首先计算对象占用空间大小，如果实例成员变量是引用变
量，仅分配引用变量空间即可，即4个字节大小，接着在堆中划分一块内存
给新对象。在分配内存空间时，需要进行同步操作，比如采用CAS(Compare
And Swap)失败重试、区域加锁等方式保证分配操作的原子性

- 设定默认值。成员变量值都需要设定为默认值，即各种不同形式的零值

- 设置对象头。设置新对象的哈希码、GC信息、锁信息、对象所属的类元信息等。
这个过程的具体设置方式取决于JVM实现

- 执行init方法。初始化成员变量，执行实例化代码块，调用类的构造方法，
并把堆内对象的首地址赋值给引用变量

###### 4.5 垃圾回收
Java会对内存进行自动分配与回收管理，使上层业务更加安全，方便地使用内存
实现程序逻辑。在不同的JVM实现及不同的回收机制中，堆内存的划分方式是不一
样的。这里简要介绍垃圾回收(GarbageCollection, GC)。垃圾回收的主要目的是
清除不再使用的对象，自动释放内存

GC是如何判断对象是否可以被回收的呢?为了判断对象是否存活，JVM引入了
GC Roots。如果一个对象与GC Roots之间没有直接或间接的引用关系，比如某个失
去任何引用的对象，或者两个互相环岛状循环引用的对象等，判决这些对象“死缓”
是可以被回收的。什么对象可以作为GC Roots呢?比如：类静态属性中引用的对象、
常量引用的对象、虛拟机栈中引用的对象、本地方法栈中引用的对象等

有了判断对象是否存活的标准后，再了解一下垃圾回收的相关算法。最基础的为
“标记-清除算法”，该算法会从每个GC Roots出发，依次标记有引用关系的对象，
最后将没有被标记的对象清除。但是这种算法会带来大量的空间碎片，导致需要分配
一个较大连续空间时容易触发FGC。为了解决这个问题，又提出了“标记-整理算
法”，该算法类似计算机的磁盘整理，首先会从GC Roots出发标记存活的对象，然
后将存活对象整理到内存空间的一端，形成连续的已使用空间，最后把已使用空间之
外的部分全部清理掉，这样就不会产生空间碎片的问题。“ Mark-Copy”算法，为了
能够并行地标记和整理将空间分为两块，每次只激活其中一块，垃圾回收时只需把存
活的对象复制到另一块未激活空间上，将未激活空间标记为已激活，将已激活空间标
记为未激活，然后清除原空间中的原对象。堆内存空间分为较大的Eden和两块较小
的Survivor，每次只使用Eden和Survivor区的一块。这种情形下的"Mark-Copy" 减
少了内存空间的浪费。“Mark-Copy"现作为主流的YGC算法进行新生代的垃圾回收

垃圾回收器(GarbageCollector)是实现垃圾回收算法并应用在JVM环境中的内
存管理模块。当前实现的垃圾回收器有数十种，本节只介绍Serial、CMS、G1三种

Serial回收器是一个主要应用于YGC的垃圾回收器，采用串行单线程的方式完
成GC任务，其中“Stop The World”简称STW，即垃圾回收的某个阶段会暂停整个
应用程序的执行。FGC的时间相对较长，频繁FGC会严重影响应用程序的性能。主
要流程如图4-13所示

CMS回收器(Concurrent Mark Sweep Collector)是回收停顿时间比较短、目前
比较常用的垃圾回收器。它通过初始标记(InitialMark)、并发标记(Concurrent
Mark)、重新标记(Remark)、并发清除(Concurrent Sweep)四个步骤完成垃圾回
收工作。第1、3步的初始标记和重新标记阶段依然会引发STW，而第2、4步的并
发标记和并发清除两个阶段可以和应用程序并发执行，也是比较耗时的操作，但并不
影响应用程序的正常执行。由于CMS采用的是“标记-清除算法”，因此产生大量：
的空间碎片。为了解决这个问题，CMS可以通过配置-XX:+UseCMSCompactAtFullCo 
llection参数，强制JVM在FGC完成后对老年代进行压缩，执行一次空间碎片整理，
但是空间碎片整理阶段也会引发STW。为了减少STW次数，CMS还可以通过配置-
XX:+CMSFullGCsBeforeCompaction=n参数，在执行了n次FGC后，JVM再在老年
代执行空间碎片整理

Hotspot在JDK7中推出了新一代G1(Garbage-First Garbage Collector)垃圾回收，
通过-XX:+UseG1GC参数启用。和CMS相比，G1具备压缩功能，能避免碎片问题，
G1的暂停时间更加可控。性能总体还是非常不错的，简要结构如图4-14所示

G1将Java堆空间分割成了若干相同大小的区域，即region， 包括Eden、
Survivor、Old、Humongous 四种类型。其中，Humongous是特殊的Old类型，专门
放置大型对象。这样的划分方式意味着不需要一个连续的内存空间管理对象。G1将
空间分为多个区域，优先回收垃圾最多的区域。G1采用的是“Mark-Copy”，有非常
好的空间整合能力，不会产生大量的空间碎片。Gl的一大优势在于可预测的停顿时间，
能够尽可能快地在指定时间内完成垃圾回收任务。在JDK11中，已经将G1设为默认
垃圾回收器，通过jstat命令可以查看垃圾回收情况，如图4-15所示，在YGC时S0/
S1并不会交换

S0/S1的功能由G1中的Survivor region来承载。通过GC日志可以观察到完整
的垃圾回收过程如下，其中就有Survivor regions的区域从0个到1个
```text
[0.530s][info ][gc,start   ] GC(0) Pause Initial Mark (G1 Humongous Allocation)
[0.530s][info ][gc,task    ] GC(0) Using 4 workers of 4 for evacuation
[0.535s][info ][gc,heap    ] GC(0) Eden regions:2->0(152)
[0.535s][info ][gc,heap    ] GC(0) Survivor regions:0->1(2)
[0.535s][info ][gc,heap    ] GC(0) Old regions:0->0
[0.535s][info ][gc,heap    ] GC(0) Humongous regions:115->39
[0.535s][info ][gc,metaspace ] GC(0) Metaspace:6001K->6001K(1056768K)
```
红色标识的为G1中的四种region (Eden、Survivor、Old、Humongous)，都处于Heap中。G1执行时使用4个worker并发执行，在初始标记时，还是会触发STW，如第一步所示的Pause
