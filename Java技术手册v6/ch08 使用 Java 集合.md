#### 第 8 章 使用 Java 集合

本章介绍 Java 支持的一种基本数据结构——Java 集合。集合是很多（也可能是多数）编程方式的抽象，是程序员基本工具包的重要组成部分。因此，本章是全书最重要的章节之一，几乎所有 Java 程序员都要了解这些知识。

本章介绍基本的接口和类型层次结构，说明怎么使用这些接口，还会讨论总体设计要略。本章还涵盖处理集合的“经典”方式和全新方式（使用 Java 8 引入的流 API 和 lambda 表达式）。

##### 8.1 介绍集合 API

Java 集合是一系列泛型接口，描述最常见的数据类型格式。Java 为每一种典型的数据结构都提供了多种实现方式，而且这些类型都通过接口实现，因此开发团队可以自行开发专用的实现方式，在自己的项目中使用。

Java 集合定义了两种基本的数据结构，一种是 Collection，表示一组对象的集合；另一种是 Map，表示对象间的一系列映射或关联关系。Java 集合的基本架构如图 8-1 所示。

<img src="img\图 8-1：集合类及其继承关系.png" style="zoom:50%;" />

在这种架构中，Set 是一种 Collection，不过其中没有重复的对象；List 也是一种 Collection，其中的元素按顺序排列（不过可能有重复）。

SortedSet 和 SortedMap 是特殊的集和映射，其中的元素按顺序排列。

Collection、Set、List、Map、SortedSet 和 SortedMap 都是接口，不过 java.util 包定义了多个具体实现，例如基于数组和链表的列表，基于哈希表或二叉树的映射和集。除此之外，还有两个重要的接口，Iterator 和 Iterable，用于遍历集合中的对象，稍后会介绍。

###### 8.1.1 Collection 接口

Collection\<E\> 是参数化接口，表示由泛型 E 对象组成的集合。这个接口定义了很多方法，用来把对象添加到集合中，把对象从集合中移除，测试对象是否在集合中，以及遍历集合中的所有元素。还有一些方法可以把集合中的元素转换成数组，以及返回集合的大小。

> Collection 集合可以允许其中的元素有重复，也可以禁止有重复；可以强制使用特定的顺序，也可以不强制有顺序。

Java 集合框架之所以提供 Collection 接口，是因为常见的数据结构都会用到这些特性。JDK 提供的 Set、List 和 Queue 都是 Collection 的子接口。下述代码展示了可以在 Collection 对象上执行的操作：
```java
// 创建几个集合，供后面的代码使用
Collection<String> c = new HashSet<>(); // 一个空集

// 稍后会介绍这些实用方法
// 注意，使用时要留意一些细节
Collection<String> d = Arrays.asList("one", "two");
Collection<String> e = Collections.singleton("three");

// 向集合中添加一些元素
// 如果集合的内容变化了，这些方法返回true
// 这种表现对不允许重复的Set类型很有用
c.add("zero"); // 添加单个元素
c.addAll(d); // 添加d中的所有元素

// 复制集合：多数实现都有副本构造方法
Collection<String> copy = new ArrayList<String>(c);

// 把元素从集合中移除。
// 除了clear()方法之外，如果集合的内容变化了，都返回true
c.remove("zero"); // 移除单个元素
c.removeAll(e); // 移除一组元素
c.retainAll(d); // 移除不在d中的所有元素
c.clear(); // 移除所有元素

// 获取集合的大小
boolean b = c.isEmpty(); // c是空的，所以返回true
int s = c.size(); // 现在c的大小是0

// 使用前面创建的副本复原集合
c.addAll(copy);

// 测试元素是否在集合中。测试基于equals()方法，而不是==运算符
b = c.contains("zero"); // true
b = c.containsAll(d); // true

// 多数Collection实现都有toString()方法
System.out.println(c);

// 使用集合中的元素创建一个数组。
// 如果迭代器能保证特定的顺序，数组就有相同的顺序
// 得到的数组是个副本，而不是内部数据结构的引用
Object[] elements = c.toArray();

// 如果想把集合中的元素存入String[]类型的数组，必须在参数中指定这个类型
String[] strings = c.toArray(new String[c.size()]);

// 或者传入一个类型为String[]的空数组，指定所需的类型
// toArray()方法会为这个数组分配空间
strings = c.toArray(new String[0]);
```
记住，上述各个方法都能用于 Set、List 或 Queue。这几个子接口可能会对集合中的元素做些限制或有顺序上的约束，但都提供了相同的基本方法。

> 修改集合的方法，例如 add()、remove()、clear() 和 retainAll()，是可选的 API。不过，这个规则在很久以前就定下了，那时认为如果不提供这些方法，明智的做法是抛出  UnsupportedOperationException 异常。因此，某些实现（尤其是只读方法）可能会抛出未检异常。

Collection 和 Map 及其子接口都没扩展 Cloneable 或 Serializable 接口。不过，在 Java 集合框架中，实现集合和映射的所有类都实现了这两个接口。

有些集合对其可以包含的元素做了限制。例如，有的集合禁止使用 null 作为元素。EnumSet 要求其中的元素只能是特定的枚举类型。

如果尝试把禁止使用的元素添加到集合中，会抛出未检异常，例如 NullPointerException 或 ClassCastException。检查集合中是否包含禁止使用的元素，可能也会抛出这种异常，或者仅仅返回 false。

###### 8.1.2 Set 接口

集（set）是无重复对象组成的集合：不能有两个引用指向同一个对象，或两个指向 null 的引用，如果对象 a 和 b 的引用满足条件 a.equals(b)，那么这两个对象也不能同时出现在集中。多数通用的 Set 实现都不会对元素排序，但并不禁止使用有序集（SortedSet 和 LinkedHashSet 就有顺序）。而且集与列表等有序集合不同，一般认为，集的 contains 方法，不论以常数时间还是以对数时间评判 ，运行效率都高。

> 注 1：常数时间和对数时间是定量描述算法运行时间的两种方式。

除了 Collection 接口定义的方法之外，Set 没有再定义其他方法，但对这些方法做了额外的限制。Set 接口要求 add() 和 addAll() 方法必须遵守无重复原则：如果集中已经有某个元素，就不能再次添加。前面说过，Collection 接口定义的 add() 和 addAll() 方法，在改变集合时返回 true，否则返回 false。对 Set 对象而言，也会返回 true 或 false，因为不能重复意味着添加元素不一定会修改集。

表 8-1 列出了实现 Set 接口的类，而且总结了各个类的内部表示方式、排序特性、对成员的限制，以及 add()、remove()、contains 等基本操作和迭代的性能。这些类的详细信息，请参见各自的文档。注意，CopyOnWriteArraySet 类在 java.util.concurrent 包中，其他类则在 java.util 包中。还要注意，java.util.BitSet 类没有实现 Set 接口，这个类过时了，用于紧凑而高效地表示布尔值组成的列表，但不是 Java 集合框架的一部分。





###### 8.1.3 List 接口












###### 8.1.4 Map 接口












###### 8.1.5 Queue 接口和 BlockingQueue 接口













###### 8.1.6 实用方法













###### 8.1.7 数组和辅助方法














##### 8.2 在 Java 集合框架中使用 lambda 表达式











###### 8.2.1 函数式方式
















###### 8.2.2 流 API














##### 8.3 小结














