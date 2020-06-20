#### 第 5 章 Java 的面向对象设计

本章介绍如何使用 Java 的对象，涵盖 Object 类的重要方法、面向对象设计要略，以及异常处理机制的实现方式。本章会介绍一些设计模式，主要是解决软件设计中一些常见问题的最佳实践。本章末尾会介绍如何设计安全的程序，避免程序随时间的推移变得不一致。首先，我们要介绍 Java 调用和传值的约定，以及 Java 值的本性。

##### 5.1 Java 的值

Java 的值以及它们与类型系统的关系非常简单。Java 的值有两种类型——基本值和对象引用。

> 有些书把基本值称为“值类型”——把 Java 的对象引用当成值时，这个称呼会产生歧义。因此，只要涉及 Java 的八种非引用类型，我们都使用“基本值”这个术语。

只有这两种值才能赋值给变量。其实，值的一种定义方式是“可以赋值给变量或传入方法的东西”。C++ 和 C 语言程序员要注意，对象的内容不能赋值给变量，所以 Java 没有解除引用运算符或结构体。

基本值和对象引用的主要区别是，基本值不能修改——2 永远都是 2，而对象引用的内容一般都能修改——一般称这种修改为对象内容的变化（mutation）。

Java 试图简化一个经常会让 C++ 程序员困惑的概念，即“对象的内容”和“对象的引用”之间的区别。但也不能完全忽视这个区别，因此，程序员要理解 Java 平台中引用值的运作方式。

```
Java 是“引用传递”语言吗？

Java“通过引用”处理对象，但不能把这种处理方式和“引用传递”（passby
reference）搞混了。“引用传递”是一个术语，用于描述某些编程语言中方法的调用方
式。在引用传递语言中，值，甚至是基本值，不直接传入方法，而是把值的引用传入
方法。因此，如果方法修改了参数，方法返回后这一变化仍然存在。就算是基本类型，
也使用这种处理方式。

Java 不会这么做，Java 是“值传递”语言。不过，如果传入的值是引用类型，那么实
际传入的是引用副本。但是这和引用传递并不是一回事。如果 Java 是引用传递语言，
把引用类型的值传入方法时，传入的应该是引用的引用。
```
事实上，Java 使用值传递，这一点很容易证明。如下述代码所示，就算调用了 manipulate() 方法，变量 c 保存的值也没有变化，还是引用一个半径为2的 Circle 对象。如果 Java 是引用传递语言，那么 c 保存的值应该是一个半径为 3 的 Circle 对象。
```java
public void manipulate(Circle circle) {
    circle = new Circle(3);
}

Circle c = new Circle(2);
manipulate(c);
System.out.println("Radius: "+ c.getRadius());
```
如果我们谨慎对待这个区别，而且把对象引用当成 Java 的一种值，那么 Java 某些令人惊奇的其他功能就会显现出来。注意，有些旧资料对这一点的表述并不清晰。第 6 章介绍内存管理和垃圾回收机制时还会遇到 Java 值的这种特性。

##### 5.2 java.lang.Object 类的重要方法

前面说过，所有类都直接或间接扩展 java.lang.Object 类。这个类定义了很多有用的方法，而且你编写的类可以覆盖这些方法。示例 5-1 中的类覆盖了这些方法。这个示例之后的几节，说明各个方法的默认实现，以及为什么要覆盖。

这个示例使用了前一章介绍的多个类型系统的扩展功能。首先，这个示例使用参数化类型（或叫泛型）实现 Comparable 接口。其次，这个示例使用 @Override 注解，强调（并让编译器确认）某些方法覆盖了 Object 类中对应的方法。
```java
// 示例 5-1：一个类，覆盖 Object 类的重要方法
// 这个类表示圆形，位置和半径都不能改变
public class Circle implements Comparable<Circle> {
    // 这些字段存储圆心的坐标和圆的半径
    // 使用private是为了封装数据，使用final是为了禁止修改
    private final int x, y, r;
    
    // 基本构造方法：使用指定的值初始化字段
    public Circle(int x, int y, int r) {
        if (r < 0) throw new IllegalArgumentException("negative radius");
        this.x = x; this.y = y; this.r = r;
    }
    
    // 这个“副本构造方法”创建一个副本，可代替clone()方法
    public Circle(Circle original) {
        x = original.x; // 直接从源对象中复制字段的值
        y = original.y;
        r = original.r;
    }
    
    // 公开的访问器方法，用于访问私有字段
    // 这也是为了封装数据
    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }
    
    // 返回对象的字符串表示形式
    @Override 
    public String toString() {
        return String.format("center=(%d,%d); radius=%d", x, y, r);
    }
    
    // 测试与另一个对象是否相等
    @Override 
    public boolean equals(Object o) {
       // 引用同一个对象？
       if (o == this) return true;
       // 类型不对，但不是null？
       if (!(o instanceof Circle)) return false;
       Circle that = (Circle) o; // 校正为Circle类型
       if (this.x == that.x && this.y == that.y && this.r == that.r)
           return true; // 如果所有字段的值都相等
       else
           return false; // 如果字段的值不相等
    }
    
    // 有哈希码的对象才能在哈希表中使用
    // 相等的对象必须具有相等的哈希码
    // 不相等的对象可以具有相等的哈希码，但要尽量避免出现这种情况
    // 因为我们覆盖了equals()方法，所以必须覆盖这个方法
    @Override 
    public int hashCode() {
        int result = 17;// 这个哈希码算法出自Joshua Bloch写的Effective Java
        result = 37*result + x;
        result = 37*result + y;
        result = 37*result + r;
        return result;
    }
    
    // 这个方法由Comparable接口定义
    // 比较这个Circle对象和另一个Circle对象
    // 如果这个对象小于另一个对象，返回负数
    // 如果这个对象等于另一个对象，返回零
    // 如果这个对象大于另一个对象，返回正数
    // Circle对象按照从上到下、从左到右排序，然后再比较半径的大小
    public int compareTo(Circle that) {
        // y坐标较大的圆较小
        long result = (long)that.y - this.y;
        // 如果y坐标相同，再比较x坐标
        if (result==0) result = (long)this.x - that.x;
        // 如果x坐标相同，再比较半径
        if (result==0) result = (long)this.r - that.r;
        
        // 相减时必须使用long类型的值，因为较大的正数和较小的负数
        // 之差可能会溢出int类型的取值范围。但是不能返回long类型的值
        // 因此返回表示符号的int类型值
        return Long.signum(result);
    }
}
```

###### 5.2.1 toString() 方法

toString() 方法的作用是返回对象的文本表示形式。连接字符串或使用 System.out.println() 等方法时，会自动在对象上调用这个方法。给对象提供文本表示形式，十分利于调试或记录日志，而且精心编写的 toString() 方法还能给报告生成等任务提供帮助。

Object 类中的 toString() 方法返回的字符串由对象所属的类名和对象的十六进制形式哈希码（由 hashCode() 方法计算得到，本章稍后会介绍）组成。这个默认的实现方式提供了对象的类型和标识两个基本信息，但一般并没什么用。示例 5-1 定义的 toString() 方法，返回一个人类可读的字符串，包含 Circle 类每个字段的值。

###### 5.2.2 equals() 方法

== 运算符测试两个引用是否指向同一个对象。如果要测试两个不同的对象是否相等，必须使用 equals() 方法。任何类都能覆盖 equals() 方法，定义专用的相等比较方式。Object.equals() 方法直接使用 == 运算符，只有两个对象是同一个对象时，才判定二者相等。

仅当两个不同的 Circle 对象的全部字段都相等时，示例 5-1 中定义的 equals() 方法才判定二者相等。注意，这个 equals() 方法先使用 == 运算符测试对象是否相同（一项优化措施），然后使用 instanceof 运算符检查另一个对象的类型，因为 Circle 对象只能和另一个 Circle 对象相等，而且 equals() 方法不能抛出 ClassCastException 异常。注意，instanceof 运算符还能排除 null：只要左侧操作数是 null，instanceof 运算符的计算结果就是 false。

###### 5.2.3 hashCode() 方法

只要覆盖了 equals() 方法，就必须覆盖 hashCode() 方法。hashCode() 方法返回一个整数，用于哈希表数据结构。如果两个对象经 equals() 方法测试是相等的，它们就要具有相同的哈希码。不相等的对象要具有不相等的哈希码（为了哈希表的操作效率），这一点很重要，但不是强制要求，最低要求是不相等的对象不能共用一个哈希码。为了满足最低要求，hashCode() 方法要使用稍微复杂的算法或位操作。

Object.hashCode() 方法和 Object.equals() 方法协同工作，返回对象的哈希码。这个哈希码基于对象的身份生成，而不是对象的相等性。（如果需要使用基于身份的哈希码，可以通过静态方法 System.identityHashCode() 获取 Object.hashCode() 方法的返回值。）

> 如果覆盖了 equals() 方法，必须覆盖 hashCode() 方法，这样才能保证相等的对象具有相同的哈希码。如果不这么做，程序可能会出现难以排查的问题。

在示例 5-1 中，因为 equals() 方法根据三个字段的值判断对象是否相等，所以 hashCode() 方法也基于这三个字段计算对象的哈希码。从 hashCode() 方法的代码中可以明确看出，如果两个 Circle 对象的字段值都相等，那么它们的哈希码也相等。

注意，示例 5-1 中的 hashCode() 方法没有直接相加三个字段的值，返回总和。这种实现方式算是合理，但还不够，因为如果两个圆的半径一样，但 x 和 y 坐标对调，哈希码依然相同。多次相乘和相加后，哈希码的值域会变大，因此能显著降低两个不相等的 Circle 对象具有相同哈希码的可能性。Joshua Bloch 写的 Effective Java（Addison Wesley 出版）一书对如何合理编写 hashCode() 方法提供了一个有用的攻略，和示例 5-1 中使用的类似。

###### 5.2.4 Comparable::compareTo() 方法

示例5-1包含一个compareTo()方法。这个方法由java.lang.Comparable接口而不是 Object 定义。但是这个方法经常要实现，所以也放在这一节介绍。Comparable 接口和其中的 compareTo() 方法用于比较类的实例，方式类似于比较数字的 <、<=、> 和 >= 运算符。如果一个类实现了 Comparable 接口，就可以比较一个实例是小于、大于还是等于另一个实例。这也表明，实现 Comparable 接口的类可以排序。

因为 compareTo() 方法不在 Object 类中声明，所以由每个类自行决定实例能否排序。如果能排序就定义 compareTo() 方法，实现实例排序的方式。示例 5-1 定义的排序方式把 Circle 对象当成一个页面中的单词，然后再比较。首先从上到下排序圆：y 坐标大的圆小于 y 坐标小的圆。如果两个圆的 y 坐标相同，再从左到右排序：x 坐标小的圆小于 x 坐标大的圆。如果两个圆的 x 坐标和 y 坐标都相同，那就比较半径：半径小则圆也小。注意，按照这种排序方式，只有圆的三个字段都相等圆才相等。因此，compareTo() 方法定义的排序方式和 equals() 方法定义的相等条件是一致的。这么做非常合乎情理（但不是强制要求）。

compareTo() 方法返回一个 int 类型的值，这个值需要进一步说明。如果当前对象（this）小于传入的对象，compareTo() 方法应该返回一个负数；如果两个对象相等，应该返回 0；如果当前对象大于传入的对象，应该返回一个正数。

###### 5.2.5 clone() 方法

Object 类定义了一个名为 clone() 的方法，这个方法的作用是返回一个对象，并把这个对象的字段设为和当前对象一样。clone() 方法不常用，原因有两个。其一，只有类实现了java.lang.Cloneable 接口，这个方法才有用。Cloneable 接口没有定义任何方法（是个标记接口），因此若想实现这个接口，只需在类签名的 implements 子句中列出这个接口即可。其二，clone() 方法声明为 protected，因此，如果想让其他类复制你的对象，你的类必须实现 Cloneable 接口，并覆盖 clone() 方法，而且要把 clone() 方法声明为 public。

示例 5-1 中的 Circle 类没有实现 Cloneable 接口，而是定义一个副本构造方法，用于创建 Circle 对象的副本：
```java
Circle original = new Circle(1, 2, 3); // 普通构造方法
Circle copy = new Circle(original); // 副本构造方法
```
clone() 方法很难正确实现，而副本构造方法实现起来更容易也更安全。若想让 Circle 类可克隆，要在 implements 子句中加入 Cloneable，然后在类的主体中添加下述方法：
```java
@Override public Object clone() {
    try { return super.clone(); }
    catch(CloneNotSupportedException e) { throw new AssertionError(e); }
}
```

##### 5.3 面向对象设计要略

本节介绍 Java 面向对象设计的几个相关技术，但不是很全面，只是为了展示一些示例。建议读者再阅读其他资料，例如前面提到的 Joshua Bloch 写的 Effective Java 一书。

本节先介绍 Java 定义常量时使用的良好实践，然后介绍使用 Java 的面向对象能力进行建模和领域对象设计的几种方式，最后介绍 Java 中一些常用设计模式的实现方式。

###### 5.3.1 常量

前面说过，常量可以在接口中定义。实现某个接口的任何类都会继承这个接口中定义的常量，而且使用起来就像是直接在类中定义的一样。重点是，这么做不需要在常量前加上接口的名称，也不需要以任何形式实现常量。

如果要在多个类中使用一组常量，更适合在一个接口中定义这些常量，需要使用这些常量的类实现这个接口即可。例如，客户端类和服务器类在实现网络协议时，就可以把细节（例如连接和监听的端口号）存储在一些符号常量中。举个实例，java.io.ObjectStreamConstants 接 口。 这 个 接 口 为对象序列化协议定义了一些常量，ObjectInputStream 和 ObjectOutputStream 类都实现了这个接口。

从接口中继承常量的主要好处是，能减少输入的代码量，因为无需指定定义常量的类型。但是，除了 ObjectStreamConstants 接口之外，并不推荐这么做。常量是实现细节，不该在类签名的 implements 子句中声明。

更好的方式是在类中定义常量，而且使用时要输入完整的类名和常量名。使用 import static 指令从定义常量的类中导入常量，可以减少输入的代码量。详情参见 2.10 节。

###### 5.3.2 用接口还是抽象类

Java 8 的出现从根本上改变了 Java 的面向对象编程模型。在 Java 8 以前，接口纯粹是 API 规范，不包含实现。如果接口有大量实现，往往会导致代码重复。

为了解决这个问题，Java 设计者开发了一种代码模式。这个模式实现的基础是，抽象类无需完全抽象，可以包含部分实现，供子类使用。某些情况下，很多子类都可以沿用抽象超类提供的方法实现。

这种模式由两部分组成：一部分是一个接口，为基本方法制定 API 规范；另一部分是一个抽象类，初步实现这些方法。java.util.List 接口和与之匹配的 java.util.AbstractList 类是个很好的例子。JDK 提供的 List 接口的两个主要实现（ArrayList 和 LinkedList），都是 AbstractList 类的子类。下面再举个例子：
```java
// 这是个简单的接口，表示可以放入一个矩形框中的形状
// 只要类想被当成RectangularShape类型，就可以从零开始实现这些方法
public interface RectangularShape {
    void setSize(double width, double height);
    void setPosition(double x, double y);
    void translate(double dx, double dy);
    double area();
    boolean isInside();
}
// 这是上述接口的部分实现
// 很多子类都可以以这些实现为基础
public abstract class AbstractRectangularShape implements RectangularShape {
    // 形状的位置和尺寸
    protected double x, y, w, h;
    // 接口中部分方法的默认实现
    public void setSize(double width, double height) {
        w = width; h = height;
    }
    public void setPosition(double x, double y) {
        this.x = x; this.y = y;
    }
    public void translate (double dx, double dy) { x += dx; y += dy; }
}
```
Java 8 引入的默认方法显著改变了这种情况。4.1.5 节说过，现在，接口可以包含实现代码。这意味着，如果定义的抽象类型（例如 Shape）可能有多个子类型（例如 Circle、Rectangle、Square），要面临一个抉择：用接口还是抽象类。因为接口和抽象类有很多类似的特性，所以有时并不确定应该使用哪个。

记住，如果一个类扩展了抽象类就不能再扩展其他类，而且接口依然不能包含任何非常量字段。也就是说，在 Java 中如何使用面向对象技术，仍有一些限制。

接口和抽象类之间的另一个重要区别和兼容性有关。如果你定义的一个接口是公开 API 的一部分，而后来想在接口中添加一个新的强制方法，那么已经实现这个接口的所有类都会出问题——也就是说，接口中添加的新方法必须声明为默认方法，并提供实现。但是，如果使用抽象类，可以放心添加非抽象方法，而不用修改已经扩展这个抽象类的类。

> 在这两种情况下，添加新方法都可能与子类中名称和签名相同的方法起冲突——此时，子类中的方法优先级更高。鉴于此，添加新方法时一定要谨慎，如果方法名对某个类型而言“显而易见”，或者方法可能有多个意义，尤其要小心。

一般来说，需要制定 API 规范时，推荐选择接口。接口中的强制方法不是默认方法，因为它们是 API 的一部分，实现方要提供有效的实现。当方法是真正可选的，或者只有一种可能的实现方式时，才应该使用默认方法。提供函数组合功能的 java.util.function.Function 接口是第二种情况的一例——函数只能使用一种标准方式组合，而且令人难以置信的是，只要方式合理就能覆盖默认的 compose() 方法。

最后，我要说一下，以前只注明接口中哪些方法是“可选的”，如果程序员不想实现这些方法就直接抛出 java.lang.UnsupportedOperationException 异常。这种做法问题多多，不要在新代码中使用。

###### 5.3.3 实例方法还是类方法

实例方法是面向对象编程的关键特性之一，但并不是说应该避免使用类方法。很多情况下，完全有理由定义类方法。

> 记住，在 Java 中，类方法使用关键字 static 声明，而且“静态方法”和“类方法”这两个术语指的是同一个概念。

例如，对 Circle 类而言，你可能经常要计算圆的面积。此时只需要半径，而不用创建一个 Circle 对象来表示这个圆。因此，使用类方法更便利：
```java
public static double area(double r) { return PI * r * r; }
```
一个类完全可以定义多个同名方法，只要参数不同就行。上述 area() 方法是个类方法，因此没有表示 this 的隐式参数，但必须有一个参数用于指定圆的半径——就是这个参数把这个方法和同名实例方法区分开的。

下面再举个例子，说明应该使用实例方法还是类方法。假如我们要定义一个名为 bigger() 的方法，比较两个 Circle 对象，看哪一个半径较大。我们可以把 bigger() 定义为实例方法，如下所示：
```java
// 比较隐式参数“this”表示的圆和显示参数“that”表示的圆
// 返回较大的那个圆
public Circle bigger(Circle that) {
    if (this.r > that.r) return this;
    else return that;
}
```
我们还可以把 bigger() 定义为类方法，如下所示：
```java
// 比较圆a和b，返回半径较大的那个
public static Circle bigger(Circle a, Circle b) {
    if (a.r > b.r) return a;
    else return b;
}
```
如果有两个 Circle 对象，x 和 y，我们既可以使用实例方法，也可以使用类方法判断哪个圆较大。不过，调用这两个方法的句法有显著区别：
```java
// 实例方法，也可以使用y.bigger(x)
Circle biggest = x.bigger(y);
Circle biggest = Circle.bigger(x, y); // 静态方法
```
两个方法都能很好地完成比较操作，而且从面向对象设计的角度来看，没有哪个方法“更正确”。从外观上看，实例方法更像是面向对象，但调用句法有点不对称。遇到这种情况时，使用实例方法还是类方法完全由设计方式而定。在实际情况中，应该有一种方式更自然。

关于System.out.println()

前面，我们多次遇到 System.out.println() 方法。这个方法的作用是把输出显示在终端窗口或控制台中。我们还没说明为什么这个方法的名称这么长、这么笨拙，也没有说明两个点号的作用。现在，你已经理解了类字段和实例字段，以及类方法和实例方法，那么再理解这个方法就容易了。System 是一个类，这个类有一个公开的类字段 out，这个字段的值是一个类型为 java.io.PrintStream 的对象，而这个对象有一个名为 println() 的实例方法。

我们可以使用静态导入指令 import static java.lang.System.out;，把这个方法的名称稍微变短一点儿，使用 out.println() 引用这个打印方法。不过，既然这是个实例方法，其名称还可以进一步缩短。

###### 5.3.4 合成还是继承

面向对象设计时，继承不是唯一可选择的技术。对象可以包含其他对象的引用，因此，一个大型概念单元可以由多个小型组件组成——这种技术叫合成（composition）。与此有关的一个重要技术是委托（delegation）：某个特定类型的对象保存一个引用，指向一个兼容类型的附属对象，而且把所有操作都交给这个附属对象完成。这种技术一般使用接口类型实现，如下面的示例所示，这个示例构建软件公司的雇员架构模型：
```java
public interface Employee {
    void work();
}

public class Programmer implements Employee {
    public void work() { /* 计算机编程 */ }
}

public class Manager implements Employee {
    private Employee report;
    
    public Manager(Employee staff) {
        report = staff;
    }
    
    public Employee setReport(Employee staff) {
        report = staff;
    }
    
    public void work() {
        report.work();
    }
}
```
在这个示例中，Manager 类把 work() 操作委托给直接下属完成，Manager 对象没有做任何实际工作。这种模式有些变体，发出委托的类完成一些工作，委托对象只完成部分工作。

另一个有用的相关技术是修饰模式（decorator pattern）。这种模式提供了扩展对象功能的能力，在运行时也能扩展，但设计时要稍微付出一些额外劳动。下面举个例子说明修饰模式。这个例子为快餐店出售的墨西哥卷饼建模，简单起见，只修饰卷饼的一个属性——价格：
```java
// 墨西哥卷饼的基本接口
interface Burrito {
    double getPrice();
}

// 具体实现——标准尺寸卷饼
public class StandardBurrito implements Burrito {
    private static final double BASE_PRICE = 5.99;
    
    public double getPrice() {
        return BASE_PRICE;
    }
}

// 超大尺寸卷饼
public class SuperBurrito implements Burrito {
    private static final double BASE_PRICE = 6.99;
    
    public double getPrice() {
        return BASE_PRICE;
    }
}
```
这个例子涵盖了在售的墨西哥卷饼——两种不同尺寸、不同价格的卷饼。下面我们来增强这个例子，提供两种可选的配料——墨西哥辣椒和鳄梨酱。设计的关键是使用一个抽象类，让这两个可选的配料扩展：
```java
/*
 * 这个类是Burrito接口的修饰器
 * 表示墨西哥卷饼可选的配料
 */
public abstract class BurritoOptionalExtra implements Burrito {
    private final Burrito burrito;
    private final double price;
    
    // 这个构造方法声明为protected，目的是保护默认构造方法
    // 以及避免劣质的客户端代码直接实例化这个基类
    protected BurritoOptionalExtra(Burrito toDecorate, double myPrice) {
        burrito = toDecorate;
        price = myPrice;
    }
    
    public final double getPrice() {
        return (burrito.getPrice() + price);
    }
}
```
> 把 BurritoOptionalExtra 类声明为 abstract，并把构造方法声明为 protected，这样只有创建子类的实例才能获得有效的 BurritoOptionalExtra 对象，因为子类提供了公开的构造方法（这样也能避免客户端代码设定配料的价格）。

下面测试一下上述实现方式：
```java
Burrito lunch = new Jalapeno(new Guacamole(new SuperBurrito()));
// 这个墨西哥卷饼的总价应该是$8.09
System.out.println("Lunch cost: "+ lunch.getPrice());
```
修饰模式使用非常广泛，不仅局限于 JDK 中的实用类。第 10 章介绍 Java I/O 时会见到更多使用修饰器的示例。

###### 5.3.5 字段继承和访问器

Java 为设计状态的继承时可能遇到的问题提供了多种解决方案。程序员可以选择用 protected 修饰字段，允许子类直接访问这些字段（也可以设定字段的值）。或者，可以提供访问器方法，直接读取对象的字段（如果需要，也可以设定字段的值），这么做仍能有效封装数据，而且可以把字段声明为 private。

我们再看一下第 3 章末尾举的 PlaneCircle 示例，这里明确展示了字段继承：
```java
public class Circle {
    // 这是通用的常量，所以要保证声明为public
    public static final double PI = 3.14159;
    protected double r; // 通过受保护字段继承的状态
    
    // 限制半径取值的方法
    protected void checkRadius(double radius) {
        if (radius < 0.0)
            throw new IllegalArgumentException("radius may not < 0");
    }
    
    // 非默认的构造方法
    public Circle(double r) {
        checkRadius(r);
        this.r = r;
    }
    
    // 公开的数据访问器方法
    public double getRadius() { return r; }
    
    public void setRadius(double r) {
        checkRadius(r);
        this.r = r;
    }
    
    // 操作实例字段的方法
    public double area() { return PI * r * r; }
    public double circumference() { return 2 * PI * r; }
}

public class PlaneCircle extends Circle {
    // 自动继承了Circle类的字段和方法
    // 因此只要在这里编写新代码
    // 新实例字段，存储圆心的位置
    private final double cx, cy;
    
    // 新构造方法，用于初始化新字段
    // 使用特殊的句法调用构造方法Circle()
    public PlaneCircle(double r, double x, double y) {
        super(r); // 调用超类的构造方法Circle()
        this.cx = x; // 初始化实例字段cx
        this.cy = y; // 初始化实例字段cy
    }
    
    public double getCentreX() {
        return cx;
    }
    
    public double getCentreY() {
        return cy;
    }
    
    // area()和circumference()方法继承自Circle类
    // 新实例方法，检查点是否在圆内
    // 注意，这个方法使用了继承的实例字段r
    public boolean isInside(double x, double y) {
        double dx = x - cx, dy = y - cy;
        // 勾股定理
        double distance = Math.sqrt(dx*dx + dy*dy);
        return (distance < r); // 返回true或false
    }
}
```
除了上述方式之外，还可以使用访问器方法重写 PlaneCircle 类，如下所示：
```java
public class PlaneCircle extends Circle {
    // 其他代码和前面一样
    // 超类Circle的r字段可以声明为private，因为现在不直接访问r字段了
    // 注意，现在使用访问器方法getRadius()
    public boolean isInside(double x, double y) {
        double dx = x - cx, dy = y - cy; // 到圆心的距离
        double distance = Math.sqrt(dx*dx + dy*dy); // 勾股定理
        return (distance < getRadius());
    }
}
```
上述两种方式都是合法的 Java 代码，但有一些区别。3.5 节说过，在类外部可写的字段，一般不是建模对象状态的正确方式。在 5.5 节和 6.5 节会看到，这么做其实会对程序的运行状态造成不可恢复的损坏。

糟糕的是，Java 中的 protected 关键字允许子类和同一个包中的类访问字段（和方法）。加之任何人都能把自己编写的类放入任何指定的包（不含系统包），这就意味着，在 Java 中使用继承的受保护状态有潜在缺陷。

> Java 没有提供只能在声明成员的类和子类中访问成员的机制。

鉴于上述原因，在子类中一般最好使用（公开的或受保护的）访问器方法访问状态——除非把继承的状态声明为 final，才完全可以使用继承的受保护状态。

###### 5.3.6 单例

单例模式（singleton pattern）是人们熟知的另一个设计模式，用来解决只需要为类创建一个实例这种设计问题。Java 为单例模式提供了多种实现方式。这里我们要使用一种稍微复杂的方式，但有个好处，它能十分明确地表明为了安全实现单例模式要做些什么：
```java
public class Singleton {
    private final static Singleton instance = new Singleton();
    private static boolean initialized = false;
    
    // 构造方法
    private Singleton() {
        super();
    }
    
    private void init() {
        /* 做初始化操作 */
    }
    
    // 这个方法是获取实例引用的唯一方式
    public static synchronized Singleton getInstance() {
        if (initialized) return instance;
        instance.init();
        initialized = true;
        return instance;
    }
}
```
为了有效实现单例模式，重点是要确保不能有超过一种创建实例的方式，而且要保证不能获取处于未初始化状态的对象引用（本章稍后会详细说明这一点）。为此，我们需要一个声明为 private 的构造方法，而且只调用一次。在这个 Singleton 类中，我们只在初始化私有静态变量 instance 时才调用构造方法。而且，我们还把创建唯一一个 Singleton 对象的操作和初始化操作分开，把初始化操作放入私有方法 init() 中。

使用这种机制，获取 Singleton 的唯一实例只有一种方式——通过静态辅助方法 getInstance()。getInstance() 方法检查标志 initialized，确认对象是否已经处于激活状态。如果没有激活，getInstance() 方法调用 init() 方法激活对象，然后把 initialized 设为 true，所以下次请求创建 Singleton 的实例时，不会再做进一步初始化操作。

最后还要注意，getInstance() 方法使用 synchronized 修饰。第 6 章会详细说明这么做的意义和原因。现在，你只需知道，加上 synchronized 是为了防止在多线程程序中使用 Singleton 时得到意外的结果。

> 单例虽然是最简单的模式之一，但经常过度使用。如果使用得当，单例是很有用的技术，但如果一个程序中有太多单例类，往往表明代码设计得不好。

单例模式有一些弊端：难测试，难与其他类分开。而且，在多线程代码中使用时需要小心。虽然如此，单例模式仍然很重要，开发者要熟练掌握，别不小心重新发明轮子。单例模式一般用于管理配置，但是现代的代码经常使用自动为程序员提供单例的框架（一般是依赖注入），而不是自己动手编写 Singleton 类（或类似的类）。

##### 5.4 异常和异常处理

2.6.3 节介绍过已检异常和未检异常。本节进一步讨论异常在设计方面的问题，以及如何在你自己的代码中使用异常。

记住，在 Java 中，异常是对象。这个对象的类型是 java.lang.Throwable，更准确地说是Throwable 类的子类，更具体地描述发生的异常是什么类型。Throwable 类有两个标准子类：java.lang.Error 和 java.lang.Exception。Error 类的子类对应的异常表示不可恢复的问题，例如，虚拟机耗尽了内存，或类文件损坏了，无法读取。这种异常可以捕获并处理，但很少这么做——这种异常就是前面提到的未检异常。

而 Exception 类的子类对应的异常表示没那么严重的状况，可以捕获并处理，例如：java.io.EOFException， 表示到达文件的末尾；java.lang.ArrayIndexOutOfBoundsException，表示程序尝试读取的元素超出了数组的末端。这种异常是第 2 章介绍过的已检异常（RuntimeException 的子类是个例外，仍然属于未检异常）。本书使用“异常”这个术语指代所有异常对象，不管是 Exception 类型还是 Error 类型。

因为异常是对象，所以可以包含数据，而且异常所属的类可以定义方法，操作这些数据。Throwable 类及其所有子类都包含一个 String 类型的字段，存储一个人类可读的错误消息，描述发生的异常状况。这个字段的值在创建异常对象时设定，可以使用 getMessage() 方法从异常对象中读取。多数异常都只包含这个消息，但少数异常还包含其他数据。例如，java.io.InterruptedIOException 异常包含一个名为 bytesTransferred 的字段，表示在异常状况中断传输之前完成了多少输入或输出。

自己设计异常时，要考虑建模异常对象需要哪些额外信息。这些信息一般是针对中断的操作和遇到的异常状况（例如前面的 java.io.InterruptedIOException 异常）。

在应用设计中使用异常时要做些权衡。使用已检异常的话，意味着编译器能处理（或顺着调用堆栈向上冒泡）可能恢复或重试的已知状况，还意味着更难忘记处理错误，因此能减少由于忘记处理错误状况而导致系统在生产环境中崩溃的几率。

另一方面，就算理论上某些状况建模为已检异常，有些应用也无法从这些状况中恢复。例如，如果一个应用需要读取在文件系统特定位置存储的配置文件，而应用启动时找不到这个文件，尽管 java.io.FileNotFoundException 是已检异常，但除了打印错误消息并退出之外，这个应用别无他法。遇到这种情况时，假若强制处理或冒泡无法恢复的异常，近乎于背道而驰。

设计异常机制时，应该遵循下述良好的做法：

- 考虑要在异常中存储什么额外状态——记住，异常也是对象；
- Exception 类有四个公开的构造方法，一般情况下，自定义异常类时这四个构造方法都要实现，可用于初始化额外的状态，或者定制异常消息；
- 不要在你的 API 中自定义很多细致的异常类——Java I/O 和反射 API 都因为这么做了而受人诟病，所以别让使用这些包时的情况变得更糟；
- 别在一个异常类型中描述太多状况——例如，实现 JavaScript 的 Nashorn 引擎（Java 8 的新功能）一开始有超多粗制滥造的异常，不过在发布之前修正了。

最后，还要避免使用两种处理异常的反模式：
```java
// 不要捕获异常而不处理
try {
    someMethodThatMightThrow();
} catch(Exception e){
}

// 不要捕获，记录日志后再重新抛出异常
try {
    someMethodThatMightThrow();
} catch(SpecificException e){
    log(e);
    throw e;
}
```
第一个反模式直接忽略近乎一定需要处理的异常状况（甚至没有在日志中记录）。这么做会增大系统其他地方出现问题的可能性——出现问题的地方可能会离原来的位置很远。

第二个反模式只会增加干扰——虽然记录了错误消息，但没真正处理发生的问题——在系统高层的某部分代码中还是要处理这个问题。

##### 5.5 Java 编程的安全性

有些编程语言被称为类型安全语言，但程序员使用这个术语时要表达的意思却很宽松。“类型安全”有很多解读和定义方式，而且各种方式之间并不都有关联。对我们要讨论的话题来说，类型安全最适合理解为编程语言的一个属性，其作用是避免运行时把数据识别为错误的类型。类型安全与否是相对的，正确的理解方式应该是，一门语言比（或没有）另一门语言安全，而不能直接断定一门语言是绝对安全的或绝对不安全的。

Java 的类型系统是静态的，能避免很多问题，例如，如果程序员试图把不兼容的值赋值给变量，会导致编译出错。但是，Java 的类型安全并不完美，因为任何两种引用类型之间都可以通过校正相互转换——如果值之间不兼容，这种转换在运行时会失败，抛出 ClassCastException 异常。

本书所说的安全性和更宽泛的正确性分不开，也就是说，我们要站在程序的角度，而不是语言的角度来探讨安全性。这强调了一个问题，即代码的安全不是由任何一门广泛使用的语言决定的，而要由程序员付出足够的努力（并严格遵守编程准则），确保写出的代码真正安全且正确。

为了得到安全的程序，我们要使用图 5-1 表示的抽象状态模型。安全的程序具有以下特征：

- 所有对象在创建后都处于一种合法状态；
- 外部可访问的方法在合法状态之间转换对象；
- 外部可访问的方法绝对不能返回状态不一致的对象；
- 弃用对象之前，外部可访问的方法必须把对象还原为合法状态。

<img src="img\图 5-1：程序的状态转换.png" style="zoom:50%;" />

其中，“外部可访问”的方法是指声明为 public、protected 或者对包私有的方法。上述特征为安全的程序定义了一个合理的模型，而且按照这种方式定义的抽象类型，它的方法能保证状态的一致性。满足上述条件的程序就可以称为“安全的程序”，而不用管程序使用何种语言实现。

> 私有方法不用保证对象在使用前后都处于合法状态，因为私有方法不能在外部代码中调用。

你可能想到了，如果想在大量代码中让状态模型和方法都满足上述特征，需要付出相当多的精力。对 Java 等语言来说，因为程序员能直接创建由多个线程执行的抢占式多任务程序，所以要付出的精力更多。

介绍完 Java 的面向对象设计要略之后，关于 Java 语言和平台还有一个方面需要扎实地理解——内存管理和并发编程。这是 Java 平台最复杂的知识，但掌握之后会获益良多。这是下一章要讨论的话题，介绍完之后本书第一部分也就结束了。
