#### 第 4 章 Java 类型系统

本章以基于类的面向对象编程为基础，介绍高效使用 Java 静态类型系统所需知道的其他概念。

> 静态类型语言的变量类型是确定的，如果把不兼容类型的值赋给变量，会导致编译时错误。Java 是一种静态类型语言。只在运行时检查类型兼容性的语言叫作动态类型语言，JavaScript 便是一种动态类型语言。

Java 的类型系统不仅涉及类和基本类型，还涉及与类的基本概念相关的其他引用类型，但这些引用类型有些不同，javac 或 JVM 往往会使用特殊的方式处理。

我们已经介绍了数组和类，它们是使用最广泛的两种 Java 引用类型。本章先介绍另一种重要的引用类型——接口。然后介绍 Java 的泛型，泛型在 Java 的类型系统中扮演着重要角色。掌握这些知识后，我们再介绍 Java 中编译时和运行时类型之间的区别。

为了完整介绍 Java 的引用类型，我们要介绍两种特殊的类和接口——枚举和注解。本章最后介绍嵌套类型和 Java 8 引入的 lambda 表达式。

下面先介绍接口。接口算是继类之后最重要的 Java 引用类型，而且是整个 Java 类型系统的重要组成。

##### 4.1 接口

第 3 章介绍了继承这个概念。我们知道，一个 Java 类只能继承一个类。这对我们要编写的面向对象程序来说是个相当严格的限制。Java 的设计者知道这一点，但他们也是为了确保 Java 实现面向对象编程的方式比其他语言（例如 C++）简单。

他们选择的方式是提出接口这个概念。和类一样，接口定义一种新的引用类型。如“接口”这个名称所示，接口的作用只是描绘 API，因此，接口提供类型的描述信息，以及实现这个 API 的类应该提供的方法（和签名）。

一般来说，Java 的接口不为它描述的方法提供实现代码。这些方法是强制要实现的——想实现接口的类必须实现这些方法。

不过，接口可能想把 API 中的某些方法标记为可选，如果实现接口的类不想实现就不用实现。这种机制通过 default 关键字实现，接口必须为可选的方法提供默认实现，未实现这些方法的类会使用默认实现。

> 接口中的可选方法是 Java 8 的新功能，之前的版本中没有。4.1.5 节会完整介绍如何使用可选方法（也叫默认方法）。

接口不能直接实例化，也不能创建这种接口类型的成员。接口必须通过类实现，而且类要提供所需的方法主体。

这个类的实例既属于这个类定义的类型，也属于这个接口定义的类型。不属于同一个类或超类的对象，通过实现同一个接口，也能属于同一种类型。

###### 4.1.1 定义接口

定义接口的方式和定义类差不多，不过所有（非默认的）方法都是抽象方法，而且关键字 class 要换成 interface。例如，下述代码定义了一个名为 Centered 的接口。第 3 章定义的 Shape 类如果想设定和读取形状的中心点坐标，就可以实现这个接口。
```java
interface Centered {
    void setCenter(double x, double y);
    double getCenterX();
    double getCenterY();
}
```
接口的成员有些限制。

- 接口中所有强制方法都隐式使用 abstract 声明，不能有方法主体，要使用分号。可以使用 abstract 修饰符，但一般习惯省略。
- 接口定义公开的 API。接口中的所有成员都隐式使用 public 声明，而且习惯省略不必要的 public 修饰符。如果在接口中使用 protected 或 private 定义方法，会导致编译时错误。
- 接口不能定义任何实例字段。字段是实现细节，而接口是规格不是实现。在接口中只能定义同时使用 static 和 final 声明的常量。
- 接口不能实例化，因此不定义构造方法。
- 接口中可以包含嵌套类型。嵌套类型隐式使用 public 和 static 声明。4.4 节会完整介绍嵌套类型。
- 从 Java 8 开始，接口中可以包含静态方法。Java 之前的版本不允许这么做，这被广泛认为是 Java 语言的一个设计缺陷。

###### 4.1.2 扩展接口

接口可以扩展其他接口，而且和类的定义一样，接口的定义可以包含一个 extends 子句。接口扩展另一个接口时，会继承父接口中的所有方法和常量，而且可以定义新方法和常量。不过，和类不同的是，接口的 extends 子句可以包含多个父接口。例如，下述接口扩展了其他接口：
```java
interface Positionable extends Centered {
    void setUpperRightCorner(double x, double y);
    double getUpperRightX();
    double getUpperRightY();
}
interface Transformable extends Scalable, Translatable, Rotatable {}
interface SuperShape extends Positionable, Transformable {}
```
扩展多个接口的接口，会继承每个父接口中的所有方法和常量，而且可以定义属于自己的方法和常量。实现这个接口的类必须实现这个接口直接定义的抽象方法，以及从所有父接口中继承的全部抽象方法。

###### 4.1.3 实现接口

类使用 extends 指定超类，类似地，类使用 implements 列出它支持的一个或多个接口。implements 是一个 Java 关键字，可以出现在类声明中，但要放在 extends 子句后面。implements 关键字后面是这个类要实现的一组接口，接口之间使用逗号分隔。

类在 implements 子句中声明接口时，表明这个类要为接口中的每个强制方法提供实现（即主体）。如果实现接口的类没有为接口中的每个强制方法提供实现，那么这个类从接口中继承未实现的抽象方法，而且这个类本身必须使用 abstract 声明。如果类实现多个接口，必须实现每个接口中的所有强制方法（否则这个类要使用 abstract 声明）。

下述代码展示了如何定义 CenteredRectangle 类，这个类扩展第 3 章定义的 Rectangle 类，而且实现 Centered 接口：
```java
public class CenteredRectangle extends Rectangle implements Centered {
    // 新实例字段
    private double cx, cy;
    
    // 构造方法
    public CenteredRectangle(double cx, double cy, double w, double h) {
        super(w, h);
        this.cx = cx;
        this.cy = cy;
    }
    
    // 继承了Rectangle类中的所有方法
    // 但要为Centered接口中的所有方法提供实现
    public void setCenter(double x, double y) { cx = x; cy = y; }
    public double getCenterX() { return cx; }
    public double getCenterY() { return cy; }
}
```
假设我们按照 CenteredRectangle 类的实现方式实现了 CenteredCircle 和 CenteredSquare 类。每个类都扩展 Shape 类，所以如前所示，这些类的实例都可以当成 Shape 类的实例。因为每个类都实现了 Centered 接口，所以这些实例还可以当成 Centered 类型的实例。下述代码演示了对象既可以作为类类型的成员，也可以作为接口类型的成员：
```java
Shape[] shapes = new Shape[3]; // 创建一个数组，保存形状对象

// 创建一些Centered类型的形状，存储在这个Shape[]类型的数组中
// 不用校正，因为都是放大转换
shapes[0] = new CenteredCircle(1.0, 1.0, 1.0);
shapes[1] = new CenteredSquare(2.5, 2, 3);
shapes[2] = new CenteredRectangle(2.3, 4.5, 3, 4);

// 计算这些形状的平均面积
// 以及到原点的平均距离
double totalArea = 0;
double totalDistance = 0;
for(int i = 0; i < shapes.length; i++) {
    totalArea += shapes[i].area(); // 计算这些形状的面积
    
    // 注意，一般来说，使用instanceof判断对象的运行时类型经常表明设计有问题
    if (shapes[i] instanceof Centered) { // 形状属于Centered类型
        // 注意，把Shape类型转换成Centered类型要校正
        // （不过，把CenteredSquare类型转换成Centered类型不用校正）
        Centered c = (Centered) shapes[i];
        
        double cx = c.getCenterX(); // 获取中心点的坐标
        double cy = c.getCenterY(); // 计算到原点的距离
        totalDistance += Math.sqrt(cx*cx + cy*cy);
    }
}
System.out.println("Average area: " + totalArea/shapes.length);
System.out.println("Average distance: " + totalDistance/shapes.length);
```

> 在 Java 中，接口和类一样，也是数据类型。如果一个类实现了一个接口，那么这个类的实例可以赋值给这个接口类型的变量。

看过这个示例之后，别错误地认为必须先把 CenteredRectangle 对象赋值给 Centered 类型的变量才能调用 setCenter() 方法，或者要先赋值给 Shape 类型的变量才能调用 area() 方法。CenteredRectangle 类定义了 setCenter() 方法，而且从超类 Rectangle 中继承了 area() 方法，所以始终可以调用这两个方法。

###### 4.1.4 实现多个接口

假设我们不仅想通过中心点摆放形状对象，也想通过右上角摆放形状对象，而且还想放大和缩小形状。还记得吗？虽然一个类只能扩展一个超类，但可以实现任意多个接口。假设我们已经定义好了合适的 UpperRightCornered 和 Scalable 接口，那么可以按照下述方式声明类：
```java
public class SuperDuperSquare extends Shape implements Centered, UpperRightCornered, Scalable {
    // 类的成员省略了
}
```
一个类实现多个接口只是表明这个类要实现所有接口中的全部抽象方法（即强制方法）。

###### 4.1.5 默认方法

Java 8 出现后，接口中的方法可以包含实现了。本节介绍这种方法——在接口描述的 API 中通过可选的方法表示，一般叫作默认方法。首先说明为什么需要这种默认机制。

1. 向后兼容性

Java 平台始终关注向后兼容性。这意味着，为前一版平台编写（或者已经编译）的代码在最新版平台中必须能继续使用。这个原则让开发团队坚信，升级 JDK 或 JRE 后不会破坏之前能正常运行的应用。

向后兼容性是 Java 平台的一大优势，但是为此，Java 平台有诸多约束。其中一个约束是，新发布的接口不能添加新的强制方法。

例如，假设我们要升级 Positionable 接口，添加获取和设定左下角顶点的功能：
```java
public interface Positionable extends Centered {
    void setUpperRightCorner(double x, double y);
    double getUpperRightX();
    double getUpperRightY();
    void setLowerLeftCorner(double x, double y);
    double getLowerLeftX();
    double getLowerLeftY();
}
```
重新定义接口之后，如果尝试在为旧接口编写的代码中使用这个新接口，不会成功，因为现有的代码中没有 setLowerLeftCorner()、getLowerLeftX() 和 getLowerLeftY() 这三个强制方法。

> 在你的代码中可以轻易地看到效果。编译一个依赖接口的类文件，在接口中添加一个新的强制方法，然后使用新版接口和旧的类文件尝试运行程序。你会看到程序崩溃，抛出 NoClassDefError 异常。

Java 8 的设计者注意到了这个缺陷，因为设计者的目标之一是升级 Java 核心中的集合库，引入使用 lambda 表达式的方法。

若想解决这个问题，需要一种新机制。这种机制必须要允许向接口中添加可选的新方法，而不破坏向后兼容性。

2. 实现默认方法

在接口中添加新方法而不破坏向后兼容性，这需要为接口的旧实现提供一些新实现，以便接口能继续使用。这个机制是默认方法，在 JDK 8 中首次添加到 Java 平台。

> 默认方法（有时也叫可选方法）可以添加到任何接口中。默认方法必须包含实现，即默认实现，写在接口定义中。

默认方法的基本行为如下：

- 实现接口的类可以（但不是必须）实现默认方法；
- 如果实现接口的类实现了默认方法，那么使用这个类中的实现；
- 如果找不到其他实现，就使用默认实现。

sort() 方法是默认方法的一例，JDK 8 把它添加到 java.util.List 接口中，定义如下：
```java
// 句法<E>是Java编写泛型的方式，详情参见下一节
// 如果不熟悉泛型，暂且忽略这个句法
interface List<E> {
    // 省略了其他成员
    public default void sort(Comparator<? super E> c) {
        Collections.<E>sort(this, c);
    }
}
```
因此，从 Java 8 开始，实现 List 接口的对象都有一个名为 sort() 的实例方法，使用合适的 Comparator 排序列表。因为返回类型是 void，所以我们猜测这是就地排序，而事实确实如此。

###### 4.1.6 标记接口

有时，定义全空的接口很有用。类实现这种接口时只需在 implements 子句中列出这个接口，而不用实现任何方法。此时，这个类的任何实例都是这个接口的有效实例。Java 代码可以使用 instanceof 运算符检查实例是否属于这个接口，因此这种技术是为对象提供额外信息的有力方式。

java.io.Serializable接口就是一种标记接口。实现Serializable接口的类告诉 ObjectOutputStream类，这个类的实例可以安全地序列化。java.util.RandomAccess也是标记接口：java.util.List接口实现了这个接口，表明这个接口能快速随机访问列表中的元素。例如，ArrayList类实现了RandomAccess接口，而LinkedList类没实现。注重随机访问操作性能的算法可以使用下述方式测试RandomAccess：
```java
// 排序任意长度的列表元素之前，我们或许想确认列表是否支持快速随机访问
// 如果不支持，先创建一个支持随机访问的副本再排序，速度可能更快
// 注意，使用java.util.Collections.sort()时不必这么做
List l = ...; // 随意一个列表
if (l.size() > 2 && !(l instanceof RandomAccess)) 
    l = new ArrayList(l);
sortListInPlace(l);
```
后面会看到，Java 的类型系统和类型的名称联系紧密，这种方式叫作名义类型（nominal typing）。标记接口是个很好的例子，因为它除了名称什么都没有。

##### 4.2 Java 泛型

Java 平台的一大优势是它提供的标准库。标准库提供了大量有用的功能，特别是实现了健壮的通用数据结构。这些实现使用起来相当简单，而且文档编写良好。这些是 Java 集合库，第 8 章会使用大量篇幅介绍。更完整的介绍参阅 Maurice Naftalin 和 Philip Wadler 合著的 Java Generics and Collections（http://shop.oreilly.com/product/9780596527754.do ，O’Reilly 出版）。

虽然这些库一直很有用，但在早期版本中有相当大的不足——数据结构（经常叫作容器）完全隐藏了存储其中的数据类型。

> 数据隐藏和封装是面向对象编程的重要原则，但在这种情况下，容器的不透明会为开发者带来很多问题。

本节先说明这个问题，然后介绍泛型是如何解决这个问题并让 Java 开发者的生活更轻松的。

###### 4.2.1 介绍泛型

如果想构建一个由 Shape 实例组成的集合，可以把这个集合保存在一个 List 对象中，如下所示：
```java
List shapes = new ArrayList(); // 创建一个List对象，保存形状

// 指定中心点，创建一些形状，保存在这个列表中
shapes.add(new CenteredCircle(1.0, 1.0, 1.0));
// 这是合法的Java代码，但不是好的设计方式
shapes.add(new CenteredSquare(2.5, 2, 3));

// List::get()返回Object对象，所以要想得到CenteredCircle对象，必须校正
CenteredCircle c = (CentredCircle)shapes.get(0);

// 下面这行代码会导致运行时失败
CenteredCircle c = (CentredCircle)shapes.get(1);
```
上述代码有个问题，为了取回有用的形状对象形式，必须校正，因为 List 不知道其中的对象是什么类型。不仅如此，其实可以把不同类型的对象放在同一个容器中，一切都能正常运行，但是如果做了不合法的校正，程序就会崩溃。

我们真正需要的是一种知道所含元素类型的 List。这样，如果把不合法的参数传给 List 的方法，javac 就能检测到，导致编译出错，而不用等到运行时才发现问题。

为了解决这个问题，Java 提供了一种句法，指明某种类型是一个容器，这个容器中保存着其他引用类型的实例。容器中保存的负载类型（payload type）在尖括号中指定：
```java
// 创建一个由CenteredCircle对象组成的List
List<CenteredCircle> shapes = new ArrayList<CenteredCircle>();

// 指定中心点，创建一些形状，保存在这个列表中
shapes.add(new CenteredCircle(1.0, 1.0, 1.0));

// 下面这行代码会导致编译出错
shapes.add(new CenteredSquare(2.5, 2, 3));

// List<CenteredCircle>::get()返回一个CenteredCircle对象，无需校正
CenteredCircle c = shapes.get(0);
```
这种句法能让编译器捕获大量不安全的代码，根本不能靠近运行时。当然，这正是静态类型系统的关键所在——使用编译时信息协助排除大量运行时问题。

容器类型一般叫作泛型（generic type），使用下述方式声明：
```java
interface Box<T> {
    void box(T t);
    T unbox();
}
```
上述代码表明，Box 接口是通用结构，可以保存任意类型的负载。这不是一个完整的接口，更像是一系列接口的通用描述，每个接口对应的类型都能用在 T 的位置上。

###### 4.2.2 泛型和类型参数

我们已经知道如何使用泛型增强程序的安全性——使用编译时信息避免简单的类型错误。本节深入介绍泛型的特性。

\<T\> 句法有个专门的名称——类型参数（type parameter）。因此，泛型还有一个名称——参
数化类型（parameterized type）。这表明，容器类型（例如 List）由其他类型（负载类型）
参数化。把类型写为 Map\<String, Integer\> 时，我们就为类型参数指定了具体的值。

定义有参数的类型时，要使用一种不对类型参数做任何假设的方式指定具体的值。所以 List 类型使用通用的方式 List\<E\> 声明，而且自始至终都使用类型参数 E 作占位符，代表程序员使用 List 数据结构时负载的真实类型。

> 类型参数始终代表引用类型。类型参数的值不能使用基本类型。

类型参数可以在方法的签名和主体中使用，就像是真正的类型一样，例如：
```java
interface List<E> extends Collection<E> {
    boolean add(E e);
    E get(int index);
    // 其他方法省略了
}
```
注意，类型参数 E 既可以作为返回类型的参数，也可以作为方法参数类型的参数。我们不假设负载类型有任何具体的特性，只对一致性做了基本假设，即存入的类型和后来取回的类型一致。

###### 4.2.3 菱形句法

创建泛型的实例时，赋值语句的右侧会重复类型参数的值。一般情况下，这个信息是不必要的，因为编译器能推导出类型参数的值。在 Java 的现代版本中，可以使用菱形句法省略重复的类型值。

下面通过一个示例说明如何使用菱形句法，这个例子改自之前的示例：
```java
// 使用菱形句法创建一个由CenteredCircle对象组成的List
List<CenteredCircle> shapes = new ArrayList<>();
```
对这种冗长的赋值语句来说，这是个小改进，能少输入几个字符。本章末尾介绍 lambda 表达式时会再次讨论类型推导。

###### 4.2.4 类型擦除

4.1.5 节说过，Java 平台十分看重向后兼容性。Java 5 添加的泛型又是一个会导致向后兼容性问题的新语言特性。

问题的关键是，如何让类型系统既能使用旧的非泛型集合类又能使用新的泛型集合类。设计者选择的解决方式是使用校正：
```java
List someThings = getSomeThings();
// 这种校正不安全，但我们知道someThings的内容确实是字符串
List<String> myStrings = (List<String>)someThings;
```
上述代码表明，作为类型，List 和 Lis\t<String\> 是兼容的，至少在某种程度上是兼容的。Java 通过类型擦除实现这种兼容性。这表明，泛型的类型参数只在编译时可见——javac 会去掉类型参数，而且在字节码中不体现出来。

> 注 1：会保留泛型的一些细微踪迹，在运行时通过反射能看到。

> 非泛型的 List 一般叫作原始类型（raw type）。就算现在有泛型了，Java 也完全能处理类型的原始形式。不过，这么做几乎就表明代码的质量不高。

类型擦除机制扩大了 javac 和 JVM 使用的类型系统之间的区别，4.6 节会详细说明。

类型擦除还能禁止使用某些其他定义方式，如果没有这个机制，代码看起来是合法的。在下述代码中，我们想使用两个稍微不同的数据结构计算订单数量：
```java
// 不会编译
interface OrderCounter {
    // 把名称映射到由订单号组成的列表上
    int totalOrders(Map<String, List<String>> orders);
 
    // 把名称映射到目前已下订单的总数上
    int totalOrders(Map<String, Integer> orders);
}
```
看起来这是完全合法的 Java 代码，但其实无法编译。问题是，这两个方法虽然看起来像是常规的重载，但擦除类型后，两个方法的签名都变成了：
```java
int totalOrders(Map);
```
擦除类型后剩下的只有容器的原始类型，在这个例子中是 Map。运行时无法通过签名区分这两个方法，所以，Java 语言规范把这种句法列为不合法的句法。

###### 4.2.5 通配符

参数化类型，例如 ArrayList\<T\>，不能实例化，即不能创建这种类型的实例。这是因为 \<T\> 是类型参数，只是真实类型的占位符。只有为类型参数提供具体的值之后（例如 ArrayList\<String\>），这个类型才算完整，才能创建这种类型的对象。

如果编译时不知道我们要使用什么类型，就会出现问题。幸好，Java 类型系统能调解这种
问题。在 Java 中，有“未知类型”这个明确的概念，使用 \<?\> 表示。这是一种最简单的
Java 通配符类型（wildcard type）。

涉及未知类型的表达式可以这么写：
```java
ArrayList<?> mysteryList = unknownList();
Object o = mysteryList.get(0);
```
这是完全有效的 Java 代码——ArrayList\<?\> 和 ArrayList\<T\> 不一样，前者是变量可以使用的完整类型。我们对 mysteryList 的负载类型一无所知，但这对我们的代码来说不是问题。在用户的代码中使用未知类型时，有些限制。例如，下面的代码不会编译：
```java
// 不会编译
mysteryList.add(new Object());
```
原因很简单，我们不知道mysteryList的负载类型。例如，如果mysteryList是 ArrayList\<String\>类型的实例，那么就不能把Object对象存入其中。

始终可以存入容器的唯一一个值是 null，因为我们知道 null 可能是任何引用类型的值。但这没什么用，因此，Java 语言规范禁止实例化负载为未知类型的容器类型，例如：
```java
// 不会编译
List<?> unknowns = new ArrayList<?>();
```
使用未知类型时有必要问这么一个问题：“List\<String\> 是 List\<Object\> 的子类型吗？”即，能否编写如下的代码：
```java
// 这么写合法吗？
List<Object> objects = new ArrayList<String>();
```
乍看起来，这么写完全可行，因为 String 是 Object 的子类，所以我们知道集合中的任何一个 String 类型元素都是有效的 Object 对象。不过，看看下述代码：
```java
// 这么写合法吗？
List<Object> objects = new ArrayList<String>();
// 如果合法，那下面这行代码呢？
objects.add(new Object());
```
既然 objects 的类型声明为 List\<Object\>，那么就能把 Object 实例存入其中。然而，这个实例保存的是字符串，尝试存入的 Object 对象与其不兼容，因此这个操作在运行时会失败。

上述问题的答案是，虽然下述代码是合法的（因为 String 类继承 Object 类）：
```java
Object o = new String("X");
```
但并不意味着泛型容器类型对应的语句也合法：
```java
// 不会编译
List<Object> objects = new ArrayList<String>();
```
换种方式说，即 List\<String\> 不是 List\<Object\> 的子类型。如果想让容器的类型具有父子关系，需要使用未知类型：
```java
// 完全合法
List<?> objects = new ArrayList<String>();
```
这表明，List\<String\> 是 List\<?\> 的子类型。不过，使用上述这种赋值语句时，会丢失一些类型信息。例如，get() 方法的返回类型现在实际上是 Object。还要注意，不管 T 的值是什么，List\<?\> 都不是 List\<T\> 的子类型。

未知类型有时会让开发者困惑，问些引人深思的问题，例如：“为什么不使用 Object 代替未知类型？”不过，如前文所述，为了实现泛型之间的父子关系，必须有一种表示未知类型的方式。

1. 受限通配符

其实，Java 的通配符类型不止有未知类型一种，还有受限通配符（bounded wildcard）这个概念。受限通配符也叫类型参数约束条件，作用是限制类型参数的值能使用哪些类型。受限通配符描述几乎不知道是什么类型的未知类型的层次结构，其实想表达的是这种意思：“我不知道到底是什么类型，但我知道这种类型实现了 List 接口。”在类型参数中，这句话表达的意思可以写成 ? extends List。这为程序员提供了一线希望，至少知道可以使用的类型要满足什么条件，而不是对类型一无所知。

> 不管限定使用的类型是类还是接口，都要使用 extends 关键字。

这是类型变体（type variance）的一个示例。类型变体是容器类型之间的继承关系和负载类型的继承关系有所关联的理论基础。

- 类型协变

这表示容器类型之间和负载类型之间具有相同的关系。这种关系通过 extends 关键字表示。

- 类型逆变

这表示容器类型之间和负载类型之间具有相反的关系。这种关系通过 super 关键字表示。

容器类型作为类型的制造者或使用者时会体现这些原则。例如，如果 Cat 类扩展 Pet 类，那么 List\<Cat\> 是 List\<? extends Pet\> 的子类型。这里，List 是 Cat 对象的制造者，应该使用关键字 extends。

如果容器类型只是某种类型实例的使用者，就应该使用 super 关键字。

> Joshua Bloch 把这种用法总结成“Producer Extends, Consumer Super”原则（简称 PECS，“制造者使用 extends，使用者使用 super”）。

第 8 章会看到，Java 集合库大量使用了协变和逆变。大量使用这两种变体的目的是确保泛型“做正确的事”，以及表现出的行为不会让开发者诧异。

2. 数组协变

在早期的 Java 版本中，集合库还没有出现，容器类型的类型变体问题在 Java 的数组中也有体现。没有类型变体，即使 sort() 这样简单的方法也很难使用有效的方式编写：
```java
Arrays.sort(Object[] a);
```
基于这个原因，Java 的数组可以协变——尽管这么做让静态类型系统暴露出了缺陷，但在 Java 平台的早期阶段仍是必要之恶：
```java
// 这样写完全合法
String[] words = {"Hello World!"};
Object[] objects = words;

// 哦，天哪，运行时错误
objects[0] = new Integer(42);
```
最近对现代开源项目的研究表明，数组协变极少使用，几乎可以断定为编程语言的设计缺陷。 因此，编写新代码时，应该避免使用数组协变。

> Raoul-Gabriel Urma and Janina Voigt,“Using the OpenJDK to Investigate Covariance in Java”, Java Magazine (May/June 2012):44–47.

3. 泛型方法

泛型方法是参数可以使用任何引用类型实例的方法。

例如，下述方法模拟 C 语言中 ,（逗号）运算符的功能。这个运算符一般用来合并有副作用的表达式。
```java
// 注意，这个类不是泛型类
public class Utils
    public static <T> T comma(T a, T b) {
        return a;
    }
}
```
虽然这个方法的定义中使用了类型参数，但所在的类不需要定义为泛型类。使用这种句法是为了表明这个方法可以自由使用，而且返回类型和参数的类型一样。

4. 使用和设计泛型

使用 Java 的泛型时，有时要从两方面思考问题。

- 使用者

使用者要使用现有的泛型库，还要编写一些相对简单的泛型类。对使用者来说，要理解类型擦除的基本知识，因为如果不知道运行时对泛型的处理方式，会对几个 Java 句法感到困惑。

- 设计者

使用泛型开发新库时，设计者需要理解泛型的更多功能。规范中有一些难以理解的部分，例如要完全理解通配符和“capture-of”错误消息 等高级话题。

> 指通配符类型导致的错误消息，例如 set(int,capture of ?) in java.util.List<capture of ?> cannot be applied to (int,java.lang.Object)。

泛型是 Java 语言规范中最难理解的部分之一，潜藏很多极端情况，并不需要每个开发者都完全理解，至少初次接触 Java 的类型系统时没必要。

###### 4.2.6 编译时和运行时类型

假设有如下的代码片段：
```java
List<String> l = new ArrayList<>();
System.out.println(l);
```
我们可以问这个问题：l 是什么类型？答案取决于在编译时（即 javac 看到的类型）还是运行时（JVM 看到的类型）问这个问题。

javac 把 l 看成 List-of-String 类型，而且会用这个类型信息仔细检查句法错误，例如不能使用 add() 方法添加不合法的类型。

而 JVM 把 l 看成 ArrayList 类型的对象，这一点可以从 println() 语句的输出中证实。因为要擦除类型，所以运行时 l 是原始类型。

因此，编译时和运行时的类型稍微有些不同。某种程度上，这个不同点是，运行时类型既比编译时类型精确，又没有编译时类型精确。

运行时类型没有编译时类型精确，因为没有负载类型的信息——这个信息被擦除了，得到的运行时类型只是原始类型。

编译时类型没有运行时类型精确，因为我们不知道 l 的具体类型到底是什么，只知道是一种和 List 兼容的类型。

##### 4.3 枚举和注解

Java 有两种特殊形式的类和接口，在类型系统中扮演着特定的角色。这两种类型是枚举类型（enumerated type）和注解类型（annotation type），一般直接称为枚举和注解。

###### 4.3.1 枚举

枚举是类的变种，功能有限，而且允许使用的值很少。

例如，假设我们想定义一个类型，表示三原色红绿蓝，而且希望这个类型只有这三个可以使用的值。我们可以使用 enum 关键字定义这个类型：
```java
public enum PrimaryColor {
    // 实例列表末尾的分号是可选的
    RED, GREEN, BLUE
}
```
PrimaryColor 类型的实例可以按照静态字段的方式引用：PrimaryColor.RED、PrimaryColor.GREEN 和 PrimaryColor.BLUE。

> 在其他语言中，例如 C++，枚举一般使用整数常量实现，但 Java 采用的方式能提供更好的类型安全性和灵活性。例如，因为枚举是特殊的类，所以可以拥有成员，即字段和方法。如果字段或方法有主体，那么实例列表后面必须加上分号。

例如，假设我们要定义一个枚举，包含前几个正多边形（等边等角的形状），而且想为这些形状指定一些属性（在方法中指定）。我们可以使用接收一个参数的枚举实现这个需求，如下所示：
```java
public enum RegularPolygon {
    // 有参数的枚举必须使用分号
    TRIANGLE(3), SQUARE(4), PENTAGON(5), HEXAGON(6);
    
    private Shape shape;
    
    public Shape getShape() {
        return shape;
    }
    
    private RegularPolygon(int sides) {
        switch (sides) {
            case 3:
                // 假设这些形状的构造方法接收的参数是边长和角度
                shape = new Triangle(1,1,1,60,60,60);
                break;
            case 4:
                shape = new Rectangle(1,1);
                break;
            case 5:
                shape = new Pentagon(1,1,1,1,1,108,108,108,108,108);
                break;
            case 6:
                shape = new Hexagon(1,1,1,1,1,1,120,120,120,120,120,120);
                break;
        }
    }
}
```
参数（在这个例子中只有一个参数）传入构造方法，创建单个枚举实例。因为枚举实例由 Java 运行时创建，而且在外部不能实例化，所以把构造方法声明为私有方法。

枚举有些特殊的特性：

- 都（隐式）扩展 java.lang.Enum 类；
- 不能泛型化；
- 可以实现接口；
- 不能被扩展；
- 如果枚举中的所有值都有实现主体，那么只能定义为抽象方法；
- 只能有一个私有（或使用默认访问权限）的构造方法。

###### 4.3.2 注解

注解是一种特殊的接口。如名称所示，其作用是注解 Java 程序的某个部分。

例如 @Override 注解。在前面的一些示例中你可能见到过这个注解，想知道它有什么作用。

简单来说，什么作用也没有。这个答案或许会让你感到诧异。

说得稍微详细（也轻率）一点儿，注解没有直接作用，@Override 只是为注解的方法提供额外的信息，注明这个方法覆盖了超类中的方法。

注解能为编译器和集成开发环境（Integrated Development Environment，IDE）提供有用的提示。如果开发者把方法的名称拼写错了，而这个方法本来是要覆盖超类的方法，那么，在这个名称拼错的方法上使用 @Override 注解，可以提醒编译器什么地方出错了。

注解不能改变程序的语义，只能提供可选的元信息。严格说来，这意味着注解不能影响程序的执行，只能为编译器和其他预执行阶段提供信息。

Java 平台在 java.lang 中定义了为数不多的基本注解。一开始只支持 @Override、@Deprecated 和 @SuppressWarnings，这三个注解的作用分别是：注明方法是覆盖的，注明方法废弃了，以及静默编译器生成的警告。

后来，Java 7 增加了 @SafeVarargs（为变长参数方法提供增强的警告静默功能），Java 8 增加了 @FunctionalInterface。@FunctionalInterface 表示接口可以用作 lambda 表达式的目标。这是个很有用的标记注解，但不是必须使用的，后文会介绍。

和普通的接口相比，注解有些特殊的特性：

- 都（隐式）扩展 java.lang.annotation.Annotation 接口；
- 不能泛型化；
- 不能扩展其他接口；
- 只能定义没有参数的方法；
- 不能定义会抛出异常的方法；
- 方法的返回类型有限制；
- 方法可以有一个默认返回值。

###### 4.3.3 自定义注解

自定义在自己的代码中使用的注解类型没什么难度。开发者可以使用 @interface 关键字定义新的注解类型，与定义类和接口的方式差不多。

> 自定义注解的关键是使用“元注解”。元注解是特殊的注解，用来注解新（自定义）注解类型的定义。

元注解在 java.lang.annotation 包中定义。开发者使用元注解指定新的注解类型能在哪里使用，以及编译器和运行时如何处理注解。

创建新的注解类型时，必须使用两个基本的元注解——@Target 和 @Retention。这两个注解接受的值都在枚举中定义。

@Target 元注解指明自定义的新注解能在 Java 源码的什么地方使用。可用的值在枚举ElementType 中定义， 包括：TYPE、FIELD、METHOD、PARAMETER、CONSTRUCTOR、LOCAL_VARIABLE、ANNOTATION_TYPE、PACKAGE、TYPE_PARAMETER 和 TYPE_USE。

另一个元注解 @Retention 指明 javac 和 Java 运行时如何处理自定义的注解类型。可使用的值有三个，在枚举 RetentionPolicy 中定义。

- SOURCE

使用这个保留原则的注解，编译时会被 javac 丢弃。

- CLASS

表示注解会出现在类文件中，但运行时 JVM 无法访问。这个值很少使用，但有时会在 JVM 字节码的离线分析工具中见到。

- RUNTIME

表示用户的代码在运行时（使用反射）能访问这个注解。

下面看个示例。这是个简单的注解，名为 @Nickname。开发者使用这个注解为方法指定一个昵称，运行时使用反射可以找到这个方法。
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Nickname {
    String[] value() default {};
}
```
定义注解要做的就这么多——先指明注解能出现在哪里，然后是保留原则，最后是注解的名称。因为我们要给一个方法起昵称，所以还要在这个注解上定义一个方法。撇开这一点，自定义注解是个非常简单的任务。

除了两个基本的元注解之外，还有两个元注解：@Inherited 和 @Documented。实际使用中很少见到这两个注解，它们的详细说明参见 Java 平台的文档。

###### 4.3.4 类型注解

Java 8 为枚举 ElementType 添加了两个新值：TYPE_PARAMETER 和 TYPE_USE。添加这两个值后，注解能在以前不能出现的地方使用了，例如使用类型的所有地方。现在，开发者可以编写如下的代码：
```java
@NotNull String safeString = getMyString();
```
@NotNull 传达的额外类型信息可在特殊的类型检查程序中使用，用于检测问题（对这个例子来说，可能抛出 NullPointerException 异常），还能执行额外的静态分析。Java 8 基本版自带了一些插入式类型检查程序，还提供了一个框架，开发者和库的作者可以使用这个框架自己编写类型检查程序。

本节介绍了 Java 的枚举和注解类型。下面介绍 Java 类型系统的另一个重要组成部分——嵌套类型。

##### 4.4 嵌套类型

目前，书中见到的类、接口和枚举类型都定义为顶层类型。也就是说，都是包的直接成员，独立于其他类型。不过，类型还可以嵌套在其他类型中定义。这种类型是嵌套类型（nested type），一般称为“内部类”，是 Java 语言的一个强大功能。

嵌套类型有两个独立的目的，但都和封装有关。

- 如果某个类型需要特别深入地访问另一个类型的内部实现，可以嵌套定义这个类型。作为成员类型的嵌套类型，其访问方式与访问成员变量和方法的方式一样，而且能打破封装的规则。

- 某个类型可能只在特定的情况下需要使用，而且只在非常小的代码区域使用。这个类型应该密封在一个小范围内，因为它其实是实现细节的一部分，应该封装在一个系统的其他部分无法接触到的地方。

嵌套类型也可以理解为通过某种方式和其他类型绑定在一起的类型，不作为完全独立的实体真实存在。类型能通过四种不同的方式嵌套在其他类型中。

- 静态成员类型

静态成员类型是定义为其他类型静态成员的类型。嵌套的接口、枚举和注解始终都是静态成员类型（就算不使用 static 关键字也是）。

- 非静态成员类

“非静态成员类型”就是没使用 static 声明的成员类型。只有类才能作为非静态成员类型。

- 局部类

局部类是在 Java 代码块中定义的类，只在这个块中可见。接口、枚举和注解不能定义为局部类型。

- 匿名类

匿名类是一种局部类，但对 Java 语言来说没有有意义的名称。接口、枚举和注解不能定义为匿名类型。

“嵌套类型”这个术语虽然正确且准确，但开发者并没有普遍使用，大多数 Java 程序员使用的是一个意义模糊的术语——“内部类”。根据语境的不同，这个术语可以指代非静态成员类、局部类或匿名类，但不能指代静态成员类型，因此使用“内部类”这个术语时无法区分指代的是哪种嵌套类型。

虽然表示各种嵌套类型的术语并不总是那么明确，但幸运的是，从语境中一般都能确定应该使用哪种句法。

下面详细介绍这四种嵌套类型。每种类型都用单独的一节介绍其特性，使用时的限制，以及专用的 Java 句法。介绍完这四种嵌套类型之后，还有一节说明嵌套类型的运作方式。

###### 4.4.1 静态成员类型

静态成员类型和普通的顶层类型很像，但为了方便，把它嵌套在另一个类型的命名空间中。静态成员类型有如下的基本特性。

- 静态成员类型类似于类的其他静态成员：静态字段和静态方法；
- 静态成员类型和所在类的任何实例都不关联（即没有 this 对象）；
- 静态成员类型只能访问所在类的静态成员；
- 静态成员类型能访问所在类型中的所有静态成员（包括其他静态成员类型）；
- 不管使不使用 static 关键字，嵌套的接口、枚举和注解都隐式声明为静态类型；
- 接口或注解中的嵌套类型也都隐式声明为静态类型；
- 静态成员类型可以在顶层类型中定义，也可以嵌入任何深度的其他静态成员类型中；
- 静态成员类型不能在其他嵌套类型中定义。

下面通过一个简单的例子介绍静态成员类型的句法。示例 4-1 定义了一个辅助接口，是所在类的静态成员。这个示例还展示了如何在定义这个接口的类内部以及外部的类中使用这个接口。注意，在外部类中要使用这个接口在层次结构中的名称。
```java
// 示例 4-1：定义和使用一个静态成员接口
// 用链表实现堆栈的类
public class LinkedStack {
     // 这个静态成员接口定义如何链接对象
     // static关键字是可选的，因为所有嵌套接口都是静态类型
     static interface Linkable {
         public Linkable getNext();
         public void setNext(Linkable node);
     }
     
     // 链表的头节点是一个Linkable对象
     Linkable head;
     
     // 方法主体省略了
     public void push(Linkable node) { ... }
     
     public Object pop() { ... }
}

// 这个类实现前面定义的静态成员接口
class LinkableInteger implements LinkedStack.Linkable {
    // 这里是节点的数据和构造方法
    int i;
    public LinkableInteger(int i) { this.i = i; }
    
    // 这些是实现这个接口所需的数据和方法
    LinkedStack.Linkable next;
    
    public LinkedStack.Linkable getNext() { return next; }
    
    public void setNext(LinkedStack.Linkable node) { next = node; }
}
```

静态成员类型的特性

静态成员类型能访问所在类型中的所有静态成员，包括私有成员。反过来也成立：所在类型的方法能访问静态成员类型中的所有成员，包括私有成员。静态成员类型甚至能访问任何其他静态成员类型中的所有成员，包括这些类型的私有成员。静态成员类型使用其他静态成员时，无需使用所在类型的名称限定成员的名称。

> 静态成员类型不能和任何一个外层类同名。而且，静态成员类型只能在顶层类型和其他静态成员类型中定义，也就是说，静态成员类型不能在任何成员类、局部类或匿名类中定义。

顶层类型可以声明为 public 或对包私有（即声明时没使用 public 关键字）。但是把顶层类型声明为 private 或 protected 都没什么意义——protected 和对包私有其实一样，而任何其他类型都不能访问声明为 private 的顶层类。

然而，静态成员类型是一种成员，因此所在类型中的成员能使用的访问控制修饰符，静态成员类型都能使用。这些修饰符对静态成员类型来说，作用与用在类型的其他成员上一样。前面说过，接口（和注解）的所有成员都隐式声明为 public，所以嵌套在接口或注解类型中的静态成员类型不能声明为 protected 或 private。

例如，在示例 4-1 中，Linkable 接口声明为 public，因此任何想存储 LinkedStack 对象的类都可以实现这个接口。

在所在类外部，静态成员类型的名称由外层类型的名称和内层类型的名称组成（例如，LinkedStack.Linkable）。

大多数情况下，这种句法有助于提醒内层类和所在的类型有内在联系。不过，Java 语言允许使用 import 指令直接或间接导入静态成员类型：
```java
import pkg.LinkedStack.Linkable; // 导入指定的嵌套类型
// 导入LinkedStack中的所有嵌套类型
import pkg.LinkedStack.*;
```
导入后，引用嵌套类型时就不用包含外层类型的名称了（例如，可以直接使用 Linkable）。

> 还可以使用 import static 指令导入静态成员类型。import 和 import static 的详细说明参阅 2.10 节。

但是，导入嵌套类型模糊了这个类型和外层类型之间的关系，而这种关系往往很重要，因此很少这么做。

###### 4.4.2 非静态成员类

非静态成员类声明为外层类或枚举类型的成员，而且不使用 static 关键字：

- 如果把静态成员类型比作类字段或类方法，那么非静态成员类可以比作实例字段或实例方法；
- 只有类才能作为非静态成员类型；
- 一个非静态成员类的实例始终关联一个外层类型的实例；
- 非静态成员类的代码能访问外层类型的所有字段和方法（静态和非静态的都能访问）；
- 为了让非静态成员类访问外层实例，Java 提供了几个专用的句法。

示例 4-2 展示了如何定义和使用成员类。这个示例以前面定义的 LinkedStack 类为基础，增加了 iterator() 方法。这个方法返回一个实现 java.util.Iterator 接口的实例，枚举栈中的元素。实现这个接口的类定义为一个成员类。
```java
// 示例 4-2：通过成员类实现的迭代器
import java.util.Iterator;

public class LinkedStack {
    // 静态成员接口
    public interface Linkable {
        public Linkable getNext();
        public void setNext(Linkable node);
    }
    // 链表的头节点
    private Linkable head;
    
    // 方法主体省略了
    public void push(Linkable node) { ... }
    public Linkable pop() { ... }
    
    // 这个方法返回一个Iterator对象，供LinkedStack类使用
    public Iterator<Linkable> iterator() { return new LinkedIterator(); }
    // 实现Iterator接口
    // 定义为一个非静态成员类
    protected class LinkedIterator implements Iterator<Linkable> {
        Linkable current;
        
        // 构造方法用到了外层类的一个私有字段
        public LinkedIterator() { current = head; }
        
        // 下面三个方法由Iterator接口定义
        public boolean hasNext() { return current != null; }
        
        public Linkable next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Linkable value = current;
            current = current.getNext();
            return value;
        }
    
        public void remove() { throw new UnsupportedOperationException(); }
    }
}
```
注意，LinkedIterator 类嵌套在 LinkedStack 类中。因为 LinkedIterator 是辅助类，只在 LinkedStack 类中使用，所以在离外层类很近的地方定义，能清晰地表达设计意图——介绍嵌套类型时说过这一点。

1. 成员类的特性

与实例字段和实例方法一样，非静态成员类的每个实例都和外层类的一个实例关联。也就是说，成员类的代码能访问外层类实例的所有实例字段和实例方法（以及静态成员），包括声明为 private 的实例成员。

这个重要的特性在示例 4-2 中已经体现出来了。下面再次列出构造方法 LinkedStack.LinkedIterator()：
```java
public LinkedIterator() { current = head; }
```
这一行代码把内层类的 current 字段设为外层类中 head 字段的值。即便 head 是外层类的私有字段，也不影响这行代码的正常运行。

非静态成员类和类的任何成员一样，可以使用一个标准的访问控制修饰符。在示例 4-2 中，LinkedIterator 类声明为 protected，所以使用 LinkedStack 类的代码（不同包）不能访问LinkedIterator 类，但是 LinkedStack 的子类可以访问。

2. 成员类的限制

成员类有两个重要的限制。

- 非静态成员类不能和任何外层类或包同名。这是一个重要的规则，但不适用于字段和方法。

- 非静态成员类不能包含任何静态字段、方法或类型，不过可以包含同时使用 static 和 final 声明的常量字段。

> 静态成员是顶层结构，不和任何特定的对象关联，而非静态成员类和外层类的实例关联。在成员类中定义顶层静态成员会让人困惑，因此禁止这么做。

3. 成员类的句法

成员类最重要的特性是可以访问外层对象的实例字段和方法。从示例 4-2 中的构造方法 LinkedStack.LinkedIterator() 可以看出这一点：
```java
public LinkedIterator() { current = head; }
```
在这个示例中，head 字段是外层 LinkedStack 类的字段，我们把这个字段的值赋值给 LinkedIterator 类的 current 字段（current 是非静态成员类的一个成员）。

如果想使用 this 显式引用，就要使用一种特殊的句法，显式引用 this 对象表示的外层实果想在这个构造方法中显式引用，可以使用下述句法：
```java
public LinkedIterator() { this.current = LinkedStack.this.head; }
```
这种句法的一般形式是 classname.this，其中 classname 是外层类的名称。注意，成员类套的层级不限。然而，因为成员类不能和任何外层类同名，所以，在 this 前面使用外层类的名称是引用任何外层实例最好的通用方式。

> 仅当引用的外层类成员被成员类的同名成员遮盖时才必须使用这种特殊的句法。

4. 作用域和继承关系比较

我们注意到，顶层类可以扩展成员类。介绍非静态成员类之后，对任何类来说，都必须考虑两种独立的层次结构。第一种是从超类到子类的继承层次结构，定义成员类继承的字段和方法。第二种是从外层类到内层类的包含层次结构，定义在成员类作用域中的字段和方法（因此成员类可以访问这些字段和方法）。

你要熟悉这两种层次结构的特性和经验法则：

- 这两种层次结构完全相互独立，一定不能混淆；
- 避免命名冲突，即超类的字段或方法不能和外层类的字段或方法同名；
- 如果出现了命名冲突，那么继承的字段或方法取代外层类的同名字段或方法；
- 继承的字段和方法作用域在继承它们的类中，会取代外层作用域中的同名字段和方法；
- 为了避免混淆继承层次结构和包含层次结构，包含层次结构不要太深；
- 如果类嵌套超过两层，可能导致更大的混乱；
- 如果类的继承层次结构很深（即有很多祖先），可以考虑不把它定义为非静态成员类，而是定义为顶层类。

###### 4.4.3 局部类

局部类在一个 Java 代码块中声明，不是类的成员。只有类才能局部定义，接口、枚举类型和注解类型都必须是顶层类型或静态成员类型。局部类往往在方法中定义，但也可以在类的静态初始化程序或实例初始化程序中定义。

因为所有 Java 代码块都在类中，所以局部类都嵌套在外层类中。因此，局部类和成员类有很多共同的特性。局部类往往更适合看成完全不同的嵌套类型。

> 第 5 章会详细说明什么时候适合使用局部类，什么时候适合使用 lambda 表达式。

局部类的典型特征是局部存在于一个代码块中。和局部变量一样，局部类只在定义它的块中有效。示例 4-3 修改 LinkedStack 类的 iterator() 方法，把 LinkedIterator 类从成员类改成局部类。

这样修改之后，我们把 LinkedIterator 类的定义移到离使用它更近的位置，希望更进一步提升代码的清晰度。简单起见，示例 4-3 只列出了 iterator() 方法，没有写出包含它的整个 LinkedStack 类。
```java
// 示例 4-3：定义和使用一个局部类
// 这个方法返回一个Iterator对象，供LinkedStack类使用
public Iterator<Linkable> Iterator() {
    // 把LinkedIterator定义为局部类
    class LinkedIterator implements Iterator<Linkable> {
        Linkable current;
        
        // 构造方法用到了外层类的一个私有字段
        public LinkedIterator() { current = head; }
        
        // 下面三个方法由Iterator接口定义
        public boolean hasNext() { return current != null; }
        
        public Linkable next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Linkable value = current;
            current = current.getNext();
            return value;
        }
        public void remove() { throw new UnsupportedOperationException(); }
    }
    
    // 创建并返回前面定义的类的一个实例
    return new LinkedIterator();
}
```

1. 局部类的特性

局部类有如下两个有趣的特性：

- 和成员类一样，局部类和外层实例关联，而且能访问外层类的任何成员，包括私有成员；
- 除了能访问外层类定义的字段之外，局部类还能访问局部方法的作用域中声明为 final 的任何局部变量、方法参数和异常参数。

2. 局部类的限制

局部类有如下限制。

- 局部类的名称只存在于定义它的块中，在块的外部不能使用。（但是要注意，在类的作用域中创建的局部类实例，在这个作用域之外仍能使用。稍后本节会详细说明这种情况。）
- 局部类不能声明为 public、protected、private 或 static。
- 与成员类的原因一样，局部类不能包含静态字段、方法或类。唯一的例外是同时使用 static 和 final 声明的常量。
- 接口、枚举类型和注解类型不能局部定义。
- 局部类和成员类一样，不能与任何外层类同名。
- 前面说过，局部类能使用同一个作用域中的局部变量、方法参数和异常参数，但这些变量或参数必须声明为 final。这是因为，局部类实例的生命周期可能比定义它的方法的执行时间长很多。

> 局部类用到的每个局部变量都有一个私有内部副本（这些副本由 javac 自动生成）。只有把局部变量声明为 final 才能保证局部变量和私有副本始终保持一致。

3. 局部类的作用域

介绍非静态成员类时，我们知道，成员类能访问继承自超类的任何成员以及外层类定义的任何成员。这对局部类来说也成立，但局部类还能访问声明为 final 的局部变量和参数。示例 4-4 展示了局部变量能访问的不同字段和变量种类：
```java
// 示例 4-4：局部类能访问的字段和变量
class A { protected char a = 'a'; }
class B { protected char b = 'b'; }

public class C extends A {
    private char c = 'c'; // 私有字段，对局部类可见
    public static char d = 'd';
    public void createLocalObject(final char e)
    {
        final char f = 'f';
        int i = 0; // i没声明为final，局部类不能使用
        class Local extends B
        {
            char g = 'g';
            public void printVars()
            {
                // 这个类能访问所有下述字段和变量
                System.out.println(g); // (this.g) g是这个类的字段
                System.out.println(f); // f是声明为final的局部变量
                System.out.println(e); // e是声明为final的局部参数
                System.out.println(d); // (C.this.d) d是外层类的字段
                System.out.println(c); // (C.this.c) c是外层类的字段
                System.out.println(b); // b是这个类继承的字段
                System.out.println(a); // a是外层类继承的字段
            }
        }
        Local l = new Local(); // 创建局部类的实例
        l.printVars(); // 然后在这个实例上调用printVars()方法
    }
}
```

###### 4.4.4 词法作用域和局部变量

局部变量在一个代码块中定义，这个代码块是这个变量的作用域，在这个作用域之外无法访问这个局部变量，局部变量也不复存在。花括号划定块的边界，花括号中的任何代码都能使用这个块中定义的局部变量。

这种作用域是词法作用域，定义变量能在哪一块源码种使用。程序员一般可以把这种作用域理解为暂时存在的事物，而不能认为局部变量的存在时间是从 JVM 开始执行代码块开始，到退出代码块为止。像这样理解局部变量和它的作用域一般是合理的。

但是，局部类的出现把这个局面搅乱了。注意，局部类的实例可能在 JVM 退出定义这个局部类的代码块后依然存在，这就是原因。

> 也就是说，如果创建了局部类的一个实例，那么，JVM 执行完定义这个类的代码块后，实例不会自动消失。因此，即便这个类在局部定义，但这个类的实例能跳出定义它的地方。

这可能会导致一些效果，让某些初次接触的开发者惊讶。这是因为，局部类能使用局部变量，而且会从不复存在的词法作用域中创建变量值的副本。这一点从下述代码种可以看出：
```java
public class Weird {
    // 静态成员接口，下面会用到
    public static interface IntHolder { public int getValue(); }
    
    public static void main(String[] args) {
        IntHolder[] holders = new IntHolder[10];
        for(int i = 0; i < 10; i++) {
            final int fi = i;
        
            // 局部类
            class MyIntHolder implements IntHolder {
                // 使用前面定义的final变量
                public int getValue() { return fi; }
            }
            holders[i] = new MyIntHolder();
        }
        // 局部类不在作用域中了，因此不能使用
        // 但是在数组中保存有这个类的10个有效实例
        // 局部变量fi现在已经不在作用域中了
        // 但仍然在那10个对象getValue()方法的作用域中
        // 因此，可以在每个对象上调用getValue()方法，打印fi的值
        // 下述代码打印数字0到9
        for(int i = 0; i < 10; i++) {
            System.out.println(holders[i].getValue());
        }
    }
}
```
为了理解这段代码，要记住一点，局部类中方法的词法作用域与解释器进出定义局部类的代码块没有任何联系。

局部类的各个实例用到的每个 final 局部变量，都会自动创建一个私有副本，因此，得到的效果是，创建实例时，这个实例拥有一个所在作用域的私有副本。

> 局部类 MyIntHolder 有时也叫闭包（closure）。用更一般的 Java 术语来说，闭包是一个对象，它保存作用域的状态，并让这个作用域在后面可以继续使用。

在某些编程风格中闭包是有用的。不同的编程语言使用不同的方式定义和实现闭包，Java 通过局部类、匿名类和 lambda 表达式实现闭包。

###### 4.4.5 匿名类

匿名类是没有名称的局部类，使用 new 运算符在一个简洁的表达式中定义和实例化。局部类是 Java 代码块中的一个语句，而匿名类是一个表达式，因此可以包含在大型表达式中，例如方法调用表达式。

为了完整介绍嵌套类，这里涵盖了匿名类，但在 Java 8 之后，大多数情况下都把匿名类换成了 lambda 表达式（参见本章的小结）。

示例 4-5 在 LinkedStack 类的 iterator() 方法中使用匿名类实现 LinkedIterator 类。和示例 4-4 对比一下，示例 4-4 使用局部类实现了同一个类。
```java
// 示例 4-5：使用匿名类实现的枚举功能
public Iterator<Linkable> iterator() {
     // 匿名类在return语句中定义
     return new Iterator<Linkable>() {
          Linkable current;
         // 把构造方法换成实例初始化程序
         { current = head; }
     
         // 下面三个方法由Iterator接口定义
         public boolean hasNext() { return current != null; }
         public Linkable next() {
             if (current == null)
                 throw new java.util.NoSuchElementException();
             Linkable value = current;
             current = current.getNext();
             return value;
         }
     public void remove() { throw new UnsupportedOperationException(); }
     }; // 注意，需要使用分号，结束return语句
}
```
可以看出，定义匿名类和创建这个类的实例使用 new 关键字，后面跟着某个类的名称和放在花括号里的类主体。如果 new 关键字后面是一个类的名称，那么这个匿名类是指定类的子类。如果 new 关键字后面是一个接口的名称，如前面的示例所示，那么这个匿名类实现指定的接口，并且扩展 Object 类。

> 匿名类使用的句法无法指定 extends 子句和 implements 子句，也不能为这个类指定名称。

因为匿名类没有名称，所以不能在类主体中定义构造方法。这是匿名类的一个基本限制。定义匿名类时，在父类后面的括号中指定的参数，会隐式传给父类的构造方法。匿名类一般用于创建构造方法不接受任何参数的简单类的子类，所以，在定义匿名类的句法中，括号经常都是空的。前面示例中的匿名类实现一个接口并扩展 Object 类。因为构造方法 Object() 不接受参数，所以括号是空的。

匿名类的限制

匿名类就是一种局部类，所以二者的限制一样。除了使用 static final 声明的常量之外，匿名类不能定义任何静态字段、方法和类。接口、枚举类型和注解类型不能匿名定义。而且，和局部类一样，匿名类不能声明为 public、private、protected 或 static。

定义匿名类的句法既定义了这个类也实例化了这个类。如果每次执行外层块时创建的实例不止一个，那么就不能用匿名类代替局部类。

因为匿名类没有名称，所以无法为匿名类定义构造方法。如果类需要构造方法，必须使用局部类。不过，经常可以使用实例初始化程序代替构造方法。

虽然实例初始化程序（3.3.4 节介绍过）不仅限于在匿名类中使用，但就是为了这个目的才把这种功能引入 Java 语言的。匿名类不能定义构造方法，所以只有一个默认构造方法。使用实例初始化程序可以打破匿名类不能定义构造方法这个限制。

###### 4.4.6 嵌套类型的运作方式

前面几节说明了这四种嵌套类型的特性和行为。对于嵌套类型，尤其只是为了使用，你要知道的就这么多。不过，理解嵌套类型的运作方式后能更好地理解嵌套类型。

> 引入嵌套类型后，Java 虚拟机和 Java 类文件的格式并没有变化。对 Java 解释器而言，并没有所谓的嵌套类型，所有类都是普通的顶层类。

为了让嵌套类型看起来是在另一个类中定义的，Java 编译器会在它生成的类中插入隐藏字段、方法和构造方法参数。这些隐藏字段和方法经常称为合成物（synthetic）。

你可以使用反汇编程序 javap（第 13 章会介绍）反汇编某些嵌套类型的类文件，了解为了支持嵌套类型，编译器用了什么技巧。

为了实现嵌套类型，javac 把每个嵌套类型编译为单独的类文件，得到的其实是顶层类。编译得到的类文件使用特殊的命名约定，这些名称一般在用户的代码中无法创建。

在第一个 LinkedStack 类的示例（示例 4-1）中，定义了一个名为 Linkable 的静态成员接口。编译这个 LinkedStack 类时，编译器会生成两个类文件，第一个是预期的 LinkedStack.class。

不过，第二个类文件名为 LinkedStack$Linkable.class，其中，$ 由 javac 自动插入。这个类文件中包含的就是静态成员接口 Linkable 的实现。

因为嵌套类型编译成普通的顶层类，所以不能直接访问外层类型中有特定权限的成员。因此，如果静态成员类型使用了外层类型的私有成员（或具有其他权限的成员），编译器会生成合成的访问方法（具有默认的包访问权限），然后把访问私有成员的表达式转换成调用合成方法的表达式。

这四种嵌套类型的类文件使用如下命名约定。

- （静态或非静态）成员类型

根据 EnclosingType$Member.class 格式命名成员类型的类文件。

- 匿名类

因为匿名类没有名称，所以类文件的名称由实现细节决定。Oracle/OpenJDK 中的 javac 使用数字表示匿名类的名称（例如 EnclosingType$1.class）。

- 局部类

局部类的类文件综合使用前两种方式命名（例如 EnclosingType$1Member.class）。

下面简单介绍一些实现细节，看一下 javac 如何为每种嵌套类型提供所需的合成访问能力。

1. 非静态成员类的实现

非静态成员类的每个实例都和一个外层类的实例关联。为了实现这种关联，编译器为每个成员类定义了一个名为 this$0 的合成字段。这个字段的作用是保存一个外层实例的引用。

编译器为每个非静态成员类的构造方法提供了一个额外的参数，用于初始化这个字段。每次调用成员类的构造方法时，编译器都会自动把这个额外参数的值设为外层类的引用。

2. 局部类和匿名类的实现

局部类之所以能访问外层类的字段和方法，原因和非静态成员类一模一样：编译器把一个外层类的隐藏引用传入局部类的构造方法，并且把这个引用存储在编译器合成的一个私有字段中。和非静态成员类一样，局部类也能使用外层类的私有字段和方法，因为编译器会插入任何所需的访问器方法。

局部类和成员类的不同之处在于，局部类能访问所在块中的局部变量。不过这种能力有个重要的限制，即局部类只能访问声明为 final 的局部变量和参数。这个限制的原因从实现中可以清楚地看出来。

局部类之所以能使用局部变量，是因为 javac 自动为局部类创建了私有实例字段，保存局部类用到的各个局部变量的副本。

编译器还在局部类的构造方法中添加了隐藏的参数，初始化这些自动创建的私有字段。其实，局部类没有访问局部变量，真正访问的是局部变量的私有副本。如果在局部类外部能修改局部变量，就会导致不一致性。

> 注 4：第 6 章介绍内存管理和可变的状态时还会讨论这个话题。

##### 4.5 lambda 表达式

Java 8 引入的功能中，最让人期盼的是 lambda 表达式。lambda 表达式以字面量的形式把少量代码直接写在程序中，而且让 Java 编程更符合函数式风格。

其实，lambda 表达式的很多功能都能使用嵌套类型通过回调和处理程序等模式实现，但使用的句法总是非常冗长，尤其是，就算只需要在回调中编写一行代码，也要完整定义一个新类型。

第 2 章见过，lambda 表达式的句法是一个参数列表和方法主体，如下所示：
```java
(p, q) -> { /* 方法主体 */ }
```
这种句法能通过一种十分紧凑的方式表示简单的方法，而且能很大程度上避免使用匿名类。

> 组成方法的各个部分，lambda 表达式几乎都有，不过显然，lambda 表达式没有名称。其实，有些开发者喜欢把 lambda 表达式当成“匿名方法”。

例如，java.io.File 类的 list() 方法。这个方法列出一个目录中的文件，但在返回列表之前，要把每个文件的名称传给 FilenameFilter 对象，而这个对象必须由你提供。FilenameFilter 对象用于接受或拒绝各个文件。

使用匿名类可以按照如下的方式定义一个 FilenameFilter 类，只列出文件名以 .java 结尾的文件：
```java
File dir = new File("/src"); // 列出这个目录中的文件

// 现在调用list()方法，参数的值是一个使用匿名类实现的FilenameFilter
String[] filelist = dir.list(new FilenameFilter() {
    public boolean accept(File f, String s) {
        return s.endsWith(".java");
    }
});
```
使用 lambda 表达式，上述代码可以简化成：
```java
File dir = new File("/src"); // 列出这个目录中的文件
String[] filelist = dir.list((f,s) -> { return s.endsWith(".java"); });
```
对目录中的每个文件来说，都会执行 lambda 表达式中的代码。如果这个方法的返回值是 true，对应的文件就会出现在输出中，即存入数组 filelist 中。

这种模式叫过滤器，即使用一个代码块测试容器中的元素是否匹配某个条件，并且只返回能通过条件的元素。过滤器是函数式编程的标准技术之一，稍后会详细说明。

###### 4.5.1 转换 lambda 表达式

javac 遇到 lambda 表达式时会把它解释为一个方法的主体，这个方法具有特定的签名。不过，是哪个方法呢？

为了解决这个问题，javac 会查看周围的代码。lambda 表达式必须满足以下条件才算是合法的 Java 代码：

- lambda 表达式必须出现在期望使用接口类型实例的地方；
- 期望使用的接口类型必须只有一个强制方法；
- 这个强制方法的签名要完全匹配 lambda 表达式。

如果满足上述条件，编译器会创建一个类型，实现期望使用的接口，然后把 lambda 表达式的主体当作强制方法的实现。

说得稍微复杂一点儿，这么做是为了保持 Java 类型系统的名义（基于名称）纯粹性。也就是说，lambda 表达式会被转换成正确接口类型的实例。

有些开发者还喜欢使用“单一抽象方法”（Single Abstract Method，SAM）类型这个术语表示 lambda 表达式转换得到的接口类型。这表明，若想在 lambda 表达式机制中使用某个接口，这个接口必须只有一个非默认方法。

> 虽然 lambda 表达式和匿名类有很多相似之处，但 lambda 表达式并不只是匿名类的语法糖。其实，lambda 表达式使用方法句柄（第 11 章介绍）和一个特殊的新 JVM 字节码 invokedynamic 实现。

从上述讨论可以看出，Java 8 添加的 lambda 表达式经过精心设计，以适应 Java 现有的类型系统——这个系统十分注重名义类型。

###### 4.5.2 方法引用

前面说过，可以把 lambda 表达式看成没有名称的方法。对下面的 lambda 表达式来说：
```java
// 实际上这行代码可以写得更简短，因为有类型推导
(MyObject myObj) -> myObj.toString()
```
会自动转换成对 @FunctionalInterface 接口的实现，这个接口只有一个非默认方法，这个方法接受一个 MyObject 类型的参数，返回值类型为 String。不过，这里的样板代码太多，所以 Java 8 提供了一种句法，可以让这种 lambda 表达式更易于阅读和编写：
```java
MyObject::toString
```
这种简写形式叫方法引用（method reference），使用现有的方法作为 lambda 表达式。方法引用就像是使用现有的方法，但会忽略方法的名称，所以能作为 lambda 表达式使用，而且能使用往常的方式自动转换。

###### 4.5.3 函数式编程

Java 实质上是面向对象语言。不过，引入 lambda 表达式后，可以更轻易地编写符合函数式风格的代码。

> 关于函数式语言由什么组成，没有明确的定义，但至少有一个共识：函数式语言最起码要能把函数当成值，存入变量。

Java（从 1.1 版起）一直都能通过内部类表示函数，但句法很复杂，代码结构不清晰。lambda 表达式大大简化了这种句法，因此，越来越多的开发者会在 Java 代码中寻求使用函数式编程风格，这是很自然的，而且现在做起来也更容易。

Java 开发者初尝函数式编程时有可能会使用如下三个非常有用的基本习语。

- map()

map() 用于列表和类似列表的容器。运作原理是，传入一个函数，应用于集合中的各个元素，得到一个新集合。新集合中保存的是在各个元素上执行函数后得到的结果。这意味着，map() 可能会把一种类型的集合转换成另一种类型的集合。

- filter()

说明如何把匿名类实现的 FilenameFilter 换成 lambda 表达式实现时，见过使用 filter() 的示例。filter() 基于某种条件生成一个集合的子集。注意，在函数式编程中，一般会生成新集合，而不直接修改现有的集合。

- reduce()

reduce() 有几种不同的形式，执行的是聚合运算，除了叫化简之外，还可以叫合拢、累计或聚合。基本原理是，提供一个初始值和聚合函数（或化简函数），然后在各个元素上执行这个化简函数，在化简函数遍历整个集合的过程中会得到一系列中间值（类似于“累积计数”），最后得到一个最终结果。

Java 完全支持这些重要的函数式习语（除此之外还有几个）。第 8 章会稍微深入地说明这些习语的实现方式，届时会介绍 Java 的数据结构和集合，以及抽象流。抽象流是实现这些习语的基础。

对 lambda 表达式的介绍到此结束，下面是一些注意事项。值得注意的是，最好把 Java 看成轻度支持函数式编程的语言。Java 不是专门的函数式语言，也不想变成函数式语言。Java 的某些特性决定了它不可能是函数式语言，具体而言有以下几点。

- Java 没有结构类型，因此没有“真正的”函数类型。每个 lambda 表达式都会自动转换成适当的名义类型。
- 类型擦除在函数式编程中会导致问题——高阶函数的类型安全性会丢失。
- Java 天生可改变（第 6 章会介绍）——一般认为，可变性是函数式语言极不需要的特性。

抛开这些，能轻易使用基本的函数式编程风格，尤其是 map()、filter() 和 reduce() 等习语，是 Java 社区向前迈出的一大步。这些习语非常有用，因此绝大多数 Java 开发者都不需要也不会错过纯正函数式语言提供的高级功能。

##### 4.6 小结

了解 Java 的类型系统之后，我们对 Java 平台的数据类型有了清晰的全局性认识。Java 的类型系统具有如下特性。

- 名义

Java 类型的名称至关重要。Java 不允许使用其他语言支持的结构类型。

- 静态

所有 Java 变量在编译时都知道类型。

- 面向对象/命令式

Java 代码是面向对象的，所有代码都放在方法中，而方法放在类中。但是，Java 有基本类型，因此并非“一切皆对象”。

- 轻度函数式

Java 支持一些常用的函数式习语，但这只是为了给程序员提供方便，别无其他目的。

- 适度的类型推导

Java 为代码（即便是程序员新手写的代码）易读性做了优化，就算信息有重复，也倾向于明确表达意图。

- 极力向后兼容

Java 是一门主要针对商业应用的语言，所以向后兼容性和保护现有代码是关注的重点。

- 类型擦除

Java 允许使用参数化类型，但这些信息在运行时不可用。

这些年 Java 的类型系统一直在进化（尽管缓慢且谨慎），引入 lambda 表达式之后，变得和其他主流编程语言一样了。lambda 表达式和默认方法的引入是 Java 5 发布以来这个平台最大的变化，除此之外，还引入了泛型和注解等相关的革新。

默认方法是 Java 实现面向对象编程方式的重大转变，这或许是 Java 语言面世以来最大的一次转变。从 Java 8 开始，接口可以包含实现代码。这从根本上改变了 Java 的本质——以前 Java 只支持单一继承，现在则可以多重继承（只是表面上如此，其实并没有状态的多重继承）。

尽管做了这些革新，Java 的类型系统还是没有（也不打算比）Scala 和 Haskell 等语言的类型系统强大。Java 的类型系统偏向于简单、易读，为新人提供一个平缓的学习曲线。

Java 还从过去十年里出现的其他语言中获益良多。Scala 是一种静态类型语言，但通过使用类型推导，看起来很像是动态类型语言。Java 从中受到了启发，不过和其他语言的设计哲学不一样。

虽然等了好久 Java 才支持 lambda 表达式，但争论最终停歇了，Java 仍然是人们更好的选择。广大的普通 Java 程序员是否需要这些增加的功能（源于 Scala 等语言的高级且缺少名义的类型系统），以及 Java 8 的轻度函数式编程（例如，map()、filter() 和 reduce() 等）是否能满足大多数开发者的需求，还有待日后观察。这注定是一段有趣的旅程。
