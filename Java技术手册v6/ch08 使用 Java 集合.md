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

表8-1：实现Set接口的类



|类|内部表示|首次出现的版本|元素顺序|成员限制|基本操作|迭代性能|备注|
|----|----|----|----|----|----|----|----|
|HashSet|哈希表|1.2|无|无|O(1)|O(capacity)|最佳通用实现|
|LinkedHashSet|哈希链表|1.2|插入的顺序|无|O(1)|O(n)|保留插入的顺序|
|EnumSet|位域|5.0|枚举声明|枚举类型的值|O(1)|O(n)|只能保存不是null的枚举值|
|TreeSet|红黑树|1.2|升序排列|可比较|O(log(n))|O(n)|元素所属的类型要实现 Comparable 或Comparator 接口|
|CopyOnWriteArraySet|数组|5.0|插入的顺序|无|O(n)|O(n)|不使用同步方法也能保证线程安全|

TreeSet 类使用红黑树数据结构维护集，这个集中的元素按照 Comparable 对象的自然顺序升序迭代，或者按照 Comparator 对象指定的顺序迭代。其实，TreeSet 实现的是 Set 的子接口，SortedSet 接口。

SortedSet 接口提供了多个有趣的方法，这些方法都考虑到了元素是有顺序的，如下述代码所示：
```java
public static void testSortedSet(String[] args) {
    // 创建一个SortedSet对象
    SortedSet<String> s = new TreeSet<>(Arrays.asList(args));
    
    // 迭代集：元素已经自动排序
    for (String word : s) {
        System.out.println(word);
    }
    
    // 特定的元素
    String first = s.first(); // 第一个元素
    String last = s.last(); // 最后一个元素
    
    // 除第一个元素之外的其他所有元素
    SortedSet<String> tail = s.tailSet(first + '\0');
    System.out.println(tail);
    
    // 除最后一个元素之外的其他所有元素
    SortedSet<String> head = s.headSet(last);
    System.out.println(head);
    
    SortedSet<String> middle = s.subSet(first +'\0', last);
    System.out.println(middle);
}
```
> 必须加上 \0 字符，因为 tailSet() 等方法要使用某个元素后面的元素，对字符串来说，要在后面加上 NULL 字符（对应于 ASCII 中的 0）。

###### 8.1.3 List 接口

List 是一组有序的对象集合。列表中的每个元素都有特定的位置，而且 List 接口定义了一些方法，用于查询或设定特定位置（或叫索引）的元素。从这个角度来看，List 对象和数组类似，不过列表的大小能按需变化，以适应其中元素的数量。和集不同，列表允许出现重复的元素。

除了基于索引的 get() 和 set() 方法之外，List 接口还定义了一些方法，用于把元素添加定的索引，把元素从特定的索引移除，或者返回指定值在列表中首次出现或最后出现的索引。从 Collection 接口继承的 add() 和 remove() 方法，前者把元素添加到列表末尾，后者把指定值从列表中首次出现的位置移除。继承的 addAll() 方法把指定集合中的所有元素添加到列表的末尾，或者插入指定的索引。retainAll() 和 removeAll() 方法的表现与其他 Collection 对象一样，如果需要，会保留或删除多个相同的值。

List 接口没有定义操作索引范围的方法，但是定义了一个 subList() 方法。这个方法返回一个 List 对象，表示原列表指定范围内的元素。子列表会回馈父列表，只要修改了子列表，父列表立即就能察觉到变化。下述代码演示了 subList() 方法和其他操作 List 对象的基本方法：
```java
// 创建两个列表，供后面的代码使用
List<String> l = new ArrayList<String>(Arrays.asList(args));
List<String> words = Arrays.asList("hello", "world");

// 通过索引查询和设定元素
String first = l.get(0); // 列表的第一个元素
String last = l.get(l.size -1); // 列表的最后一个元素
l.set(0, last); // 把最后一个元素变成第一个元素

// 添加和插入元素
// add()方法既可以把元素添加到列表末尾，也可以把元素插入指定索引
l.add(first); // 把第一个词添加到列表末尾
l.add(0, first); // 再把第一个词添加到列表的开头
l.addAll(words); // 把一个集合添加到列表末尾
l.addAll(1, words); // 在第一个词之后插入一个集合

// 子列表：回馈原列表
List<String> sub = l.subList(1,3); // 第二个和第三个元素
sub.set(0, "hi"); // 修改l的第二个元素

// 子列表可以把操作限制在原列表索引的子范围内
String s = Collections.min(l.subList(0,4));
Collections.sort(l.subList(0,4));

// 子列表的独立副本不影响父列表
List<String> subcopy = new ArrayList<String>(l.subList(1,3));

// 搜索列表
int p = l.indexOf(last); // 最后一个词在哪个位置？
p = l.lastIndexOf(last); // 反向搜索

// 打印last在l中出现的所有索引。注意，使用了子列表
int n = l.size();
p = 0;
do {
    // 创建一个列表，只包含尚未搜索的元素
    List<String> list = l.subList(p, n);
    int q = list.indexOf(last);
    if (q == -1) break;
    System.out.printf("Found '%s' at index %d%n", last, p + q);
    p += q + 1;
} while(p < n);

// 从列表中删除元素
l.remove(last); // 把指定元素从首次出现的位置上删除
l.remove(0); // 删除指定索引对应的元素
l.subList(0, 2).clear(); // 使用subList()方法，删除一个范围内的元素
l.retainAll(words); // 删除所有不在words中的元素
l.removeAll(words); // 删除所有在words中的元素
l.clear(); // 删除所有元素
```
1. 遍历循环和迭代

操作集合有种重要的方式：依次处理每个元素，这种方式叫迭代。这种处理数据结构的方式很古老，但时至今日依旧很有用（尤其是用来处理少量数据的集合），而且易于理解。迭代最适合使用 for 循环，而且使用 List 对象演示最简单，如下所示：
```java
ListCollection<String> c = new ArrayList<String>();
// ……把一些字符串添加到c中
for(String word : c) {
    System.out.println(word);
}
```
这段代码的作用很明显——一次读取 c 中的一个元素，然后把这个元素当成变量传入循环主体。说得更正式一些，这段代码的作用是迭代数组或集合（或者其他实现 java.lang.Iterable 接口的对象）中的元素。每次迭代都会把数组或 Iterable 对象中的一个元素赋值给你声明的循环变量，然后执行循环主体，一般都会处理表示元素的循环变量。迭代的过程中不需要使用循环计数器或 Iterator 对象，遍历循环会自动迭代，你无需担心初始化或终止循环的方式是否正确。

这种 for 循环一般称作遍历循环。我们来看一下它的运作方式。下述代码重写了前面的 for 循环（作用等效），显示了迭代过程中真正会使用的方法：
```java
// 使用for循环迭代
for(Iterator<String> i = c.iterator(); i.hasNext();) {
    System.out.println(i.next());
}
```
Iterator 对象 i 从集合上获取，用于逐个读取集合中的元素。在 while 循环中也可以使用 Iterator 对象：
```java
// 使用while循环迭代集合中的元素
// 有些集合种类（例如列表）能保障迭代的顺序，有些则不能
Iterator<String> iterator = c.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```
关于遍历循环的句法，还有一些注意事项。

- 前面说过，expression 必须是数组或实现 java.lang.Iterable 接口的对象。编译时必须知道 expression 的类型，这样才能生成合适的循环代码。
- 数组或 Iterable 对象中元素的类型必须与 declaration 中声明的变量类型兼容，这样才能赋值。如果使用的 Iterable 对象没有使用元素的类型参数化，那么变量必须声明为 Object 类型。
- declaration 一般只包含变量的类型和名称，不过也可以包含 final 修饰符和任何适当的注解（参见第 4 章）。final 的作用是避免循环变量使用循环赋予它的数组或集合元素之外的值，以此强调不能通过循环变量修改数组或集合。
- 遍历循环的循环变量的声明必须是循环的一部分，变量的类型和名称都要指明。不能像 for 循环那样，使用循环之外声明的变量。

为了深入理解遍历循环处理集合的方式，我们要了解两个接口——java.util.Iterator 和 java.lang.Iterable：
```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}
```
Iterator 接口定义了一种迭代集合或其他数据结构中元素的方式。迭代的过程是这样的：只要集合中还有更多的元素（hasNext() 方法返回 true），就调用 next() 方法获取集合中的下一个元素。有序集合（例如列表）的迭代器一般能保证按照顺序返回元素。无序集合（例如 Set）只能保证不断调用 next() 方法返回集中的所有元素，没有遗漏也没有重复，不过没有特定的顺序。

> Iterator 接口中的 next() 方法有两个作用：把集合的游标向前移动，然后返回集合的前一个头值。如果使用不可变的风格编程，这两个操作可能导致问题，因为 next() 其实会修改集合。

引入 Iterable 接口是为了支持遍历循环。类实现这个接口是为了表明它提供了 Iterator 对象，可以迭代：
```java
public interface Iterable<E> {
    java.util.Iterator<E> iterator();
}
```
如果某个对象是 Iterable\<E\> 类型，表明它有一个能返回 Iterator\<E\> 对象的 iterator() 方法，而 Iterator\<E\> 对象有一个 next() 方法，能返回一个 E 类型的对象。

> 注意，如果使用遍历循环迭代 Iterable\<E\> 对象，循环变量必须是 E 类型，或者是 E 类型的超类或实现的接口。

例如，迭代 List\<String\> 对象中的元素时，循环变量必须声明为 String 类型或超类 Object 类型，或者 String 实现的某个接口：CharSequence、Comparable 或 Serializable。

2. 随机访问列表中的元素

我们一般期望实现 List 接口的类能高效迭代，而且所用时间和列表的大小成正比。然而，不是所有列表都能高效地随机访问任意索引上的元素。按顺序访问的列表，例如 LinkedList 类，提供了高效的插入和删除操作，但降低了随机访问性能。提供高效随机访问的类都实现了标记接口 RandomAccess，因此，如果需要确定是否能高效处理列表，可以使用 instanceof 运算符测试是否实现了这个接口：
```java
// 随便创建一个列表，供后面的代码处理
List<?> l = ...;

// 测试能否高效随机访问
// 如果不能，先使用副本构造方法创建一个支持高效随机访问的副本，然后再处理
if (!(l instanceof RandomAccess)) l = new ArrayList<?>(l);
```
在 List 对象上调用 iterator() 方法会得到一个 Iterator 对象，这个对象按照元素在列表中的顺序迭代各元素。List 实现了 Iterable 接口，因此列表可以像其他集合一样使用遍历循环迭代。

如果只想迭代列表中的部分元素，可以使用 subList() 方法创建子列表：
```java
List<String> words = ...; // 创建一个列表，供下面的代码迭代

// 迭代除第一个元素之外的所有元素
for(String word : words.subList(1, words.size ))
    System.out.println(word);
```
表 8-2 总结了 Java 平台中五种通用的 List 实现。Vector 和 Stack 类已经过时，别再用了。CopyOnWriteArrayList 类在 java.util.concurrent 包中，只适合在多线程环境中使用。

表8-2：实现List接口的类

|类|表示方式|首次出现的版本|随机访问|备注|
|-|-|-|-|-|
|ArrayList|数组|1.2|能|最佳全能实现|
|LinkedList|双向链表|1.2|否|高效插入和删除|
|CopyOnWriteArrayList|数组|5.0|能|线程安全；遍历快，修改慢|
|Vector|数组|1.0|能|过时的类；同步的方法。不要使用|
|Stack|数组|1.0|能|扩展|Vector|类；添加了|push()、pop() 和|peek() 方法。过时了，用 Deque 替代|

###### 8.1.4 Map 接口

映射（map）是一系列键值对，一个键对应一个值。Map 接口定义了用于定义和查询映射的 API。Map 接口属于 Java 集合框架，但没有扩展 Collection 接口，因此 Map 只是一种集合，而不是 Collection 类型。Map 是参数化类型，有两个类型变量。类型变量 K 表示映射中键的类型，类型变量 V 表示键对应的值的类型。例如，如果有个映射，其键是 String 类型，对应的值是 Integer 类型，那么这个映射可以表示为 Map<String,Integer>。

Map 接口定义了几个最有用的方法：put() 方法定义映射中的一个键值对，get() 方法查询指定键对应的值，remove() 方法把指定的键及对应的值从映射中删除。一般来说，实现 Map 接口的类都要能高效执行这三个基本方法：一般应该运行在常数时间中，而且绝不能比在对数时间中运行的性能差。

Map 的重要特性之一是，可以视作集合。虽然 Map 对象不是 Collection 类型，但映射的键可以看成 Set 对象，映射的值可以看成 Collection 对象，而映射的键值对可以看成由 Map.Entry 对象组成的 Set 对象。（Map.Entry 是 Map 接口中定义的嵌套接口，表示一个键值对。）

下述示例代码展示了如何使用 get()、put() 和 remove() 等方法操作 Map 对象，还演示了把 Map 对象视作集合后的一些常见用法：
```java
// 新建一个空映射
Map<String,Integer> m = new HashMap();

// 不可变的映射，只包含一个键值对
Map<String,Integer> singleton = Collections.singletonMap("test", -1);

// 注意，极少使用下述句法显式指定通用方法emptyMap()的参数类型
// 得到的映射不可变
Map<String,Integer> empty = Collections.<String,Integer>emptyMap();

// 使用put()方法填充映射，把数组中的元素映射到元素的索引上
String[] words = { "this", "is", "a", "test" };
for(int i = 0; i < words.length; i++) {
    m.put(words[i], i); // 注意，int会自动装包成Integer
}

// 一个键只能映射一个值
// 不过，多个键可以映射同一个值
for(int i = 0; i < words.length; i++) {
    m.put(words[i].toUpperCase(), i);
}

// putAll()方法从其他映射中复制键值对
m.putAll(singleton);
// 使用get()方法查询映射
for(int i = 0; i < words.length; i++) {
    if (m.get(words[i]) != i) throw new AssertionError();
}

// 测试映射中是否有指定的键和值
m.containsKey(words[0]); // true
m.containsValue(words.length); // false

// 映射的键、值和键值对都可以看成集合
Set<String> keys = m.keySet();
Collection<Integer> values = m.values();
Set<Map.Entry<String,Integer>> entries = m.entrySet();

// 映射和上述几个集合都有有用的toString()方法
System.out.printf("Map: %s%nKeys: %s%nValues: %s%nEntries: %s%n", m, keys, values, entries);
 
// 可以迭代这些集合
// 多数映射都没定义迭代的顺序（不过SortedMap定义了）
for(String key : m.keySet()) System.out.println(key);
for(Integer value: m.values()) System.out.println(value);

// Map.Entry<K,V>类型表示映射中的一个键值对
for(Map.Entry<String,Integer> pair : m.entrySet()) {
    // 打印键值对
    System.out.printf("'%s' ==> %d%n", pair.getKey(), pair.getValue());
    // 然后把每个Entry对象的值增加1
    pair.setValue(pair.getValue() + 1);
}

// 删除键值对
m.put("testing", null); // 映射到null上可以“擦除”一个键值对
m.get("testing"); // 返回null
m.containsKey("testing"); // 返回true：键值对仍然存在
m.remove("testing"); // 删除键值对
m.get("testing"); // 还是返回null
m.containsKey("testing"); // 这次返回false

// 也可以把映射视作集合，然后再删除
// 不过，向集合中添加键值对时不能这么做
m.keySet().remove(words[0]); // 等同于m.remove(words[0]);

// 删除一个值为2的键值对——这种方式一般效率不高，用途有限
m.values().remove(2);
// 删除所有值为4的键值对
m.values().removeAll(Collections.singleton(4));
// 只保留值为2和3的键值对
m.values().retainAll(Arrays.asList(2, 3));

// 还可以通过迭代器删除
Iterator<Map.Entry<String,Integer>> iter = m.entrySet().iterator();
while(iter.hasNext()) {
    Map.Entry<String,Integer> e = iter.next();
    if (e.getValue() == 2) iter.remove();
}

// 找出两个映射中都有的值
// 一般来说，addAll()和retainAll()配合keySet()和values()使用，可以获取交集和并集
Set<Integer> v = new HashSet<Integer>(m.values());
v.retainAll(singleton.values());

// 其他方法
m.clear(); // 删除所有键值对
m.size(); // 返回键值对的数量：目前为0
m.isEmpty(); // 返回true
m.equals(empty); // true：实现Map接口的类覆盖了equals()方法
```
Map 接口有一些通用和专用的实现，表 8-3 对此做了总结。和之前一样，完整的细节参见 JDK 文档和 javadoc。在表 8-3 中，除了 ConcurrentHashMap 和 ConcurrentSkipListMap 两个类在 java.util.concurrent 包中，其他类都在 java.util 包中。

表8-3：实现Map接口的类

|类|表示方式|首次出现的版本|null键|null值|备注|
|-|-|-|-|-|-|
|HashMap|哈希表|1.2|是|是|通用实现|
|ConcurrentHashMap|哈希表|5.0|否|否|通用的线程安全实现；参见 ConcurrentMap 接口|
|ConcurrentSkipListMap|哈希表|6.0|否|否|专用的线程安全实现；参见 ConcurrentNavigableMap 接口|
|EnumMap|数组|5.0|否|是|键是枚举类型|
|LinkedHashMap|哈希表加 列表|1.4|是|是|保留插入或访问顺序|
|TreeMap|红黑树|1.2|否|是|按照键排序。操作耗时为 O(log(n))。参见 SortedMap 接口|
|IdentityHashMap|哈希表|1.4|是|是|比较时使用 ==，而不使用 equals()|
|WeakHashMap|哈希表|1.2|是|是|不会阻止垃圾回收键|
|Hashtable|哈希表|1.0|否|否|过时的类；同步的方法。不要使用|
|Properties|哈希表|1.0|否|否|使用 String 类的方法扩展 Hashtable 接口|

java.util.concurrent 包中的 ConcurrentHashMap 和 ConcurrentSkipListMap 两个类实现了同一个包中的 ConcurrentMap 接口。ConcurrentMap 接口扩展 Map 接口，而且定义了一些对多线程编程很重要的原子操作方法。例如，putIfAbsent() 方法，它的作用和 put() 方法类似，不过，仅当指定的键没有映射到其他值上时，才会把键值对添加到映射中。

TreeMap 类实现 SortedMap 接口。这个接口扩展 Map 接口，添加了一些方法，利用这种映射的有序特性。SortedMap 接口和 SortedSet 接口非常相似。firstKey() 和 lastKey() 方法分别返回 keySet() 所得集的第一个和最后一个键。而 headMap()、tailMap() 和 subMap() 方法都返回一个新映射，由原映射特定范围内的键值对组成。

###### 8.1.5 Queue 接口和 BlockingQueue 接口

队列（queue）是一组有序的元素，提取元素时按顺序从队头读取。队列一般按照插入元素的顺序实现，因此分成两类：先进先出（first-in, first-out，FIFO）队列和后进先出（last-in, first-out，LIFO）队列。

> LIFO 队列也叫栈（stack），Java 提供了 Stack 类，但强烈不建议使用——应该使用实现 Deque 接口的类。

队列也可以使用其他顺序： 优先队列（priority queue）根据外部 Comparator 对象或 Comparable 类型元素的自然顺序排序元素。与 Set 不同的是，Queue 的实现往往允许出现重复的元素。而与 List 不同的是，Queue 接口没有定义处理任意索引位元素的方法，只有队列的头一个元素能访问。Queue 的所有实现都要具有一个固定的容量：队列已满时，不能再添加元素。类似地，队列为空时，不能再删除元素。很多基于队列的算法都会用到满和空这两个状态，所以 Queue 接口定义的方法通过返回值表明这两个状态，而不会抛出异常。具体而言，peek() 和 poll() 方法返回 null 表示队列为空。因此，多数 Queue 接口的实现不允许用 null 作元素。

阻塞式队列（blocking queue）是一种定义了阻塞式 put() 和 take() 方法的队列。put() 方法的作用是把元素添加到队列中，如果需要，这个方法会一直等待，直到队列中有存储元素的空间为止。而 take() 方法的作用是从队头移除元素，如果需要，这个方法会一直等待，直到队列中有元素可供移除为止。阻塞式队列是很多多线程算法的重要组成部分，因此 BlockingQueue 接口（扩展 Queue 接口）在 java.util.concurrent 包中定义。

队列不像集、列表和映射那么常用，只在特定的多线程编程风格中会用到。这里，我们不举实例，而是试着厘清一些令人困惑的队列插入和移除操作。

1. 把元素添加到队列中

- add()方法

这个方法在 Collection 接口中定义，只是使用常规的方式添加元素。对有界的队列来说，如果队列已满，这个方法可能会抛出异常。

- offer()方法

这个方法在 Queue 接口中定义，但是由于有界的队列已满而无法添加元素时，这个方法返回 false，而不会抛出异常。

BlockingQueue 接口定义了一个超时版 offer() 方法，如果队列已满，会在指定的时间内等待空间。这个版本和基本版一样，成功插入元素时返回 true，否则返回 false。

- put()方法

这个方法在 BlockingQueue 接口中定义，会阻塞操作：如果因为队列已满而无法插入元素，put() 方法会一直等待，直到其他线程从队列中移除元素，有空间插入新元素为止。

2. 把元素从队列中移除

- remove()方法

Collection 接口中定义了 remove() 方法，把指定的元素从队列中移除。除此之外，Queue 接口还定义了一个没有参数的 remove() 方法，移除并返回队头的元素。如果队列为空，这个方法会抛出 NoSuchElementException 异常。

- poll()方法

这个方法在 Queue 接口中定义，作用和 remove() 方法类似，移除并返回队头的元素，不过，如果队列为空，这个方法会返回 null，而不抛出异常。

BlockingQueue 接口定义了一个超时版 poll() 方法，在指定的时间内等待元素添加到空队列中。

- take()方法

这个方法在 BlockingQueue 接口中定义，用于删除并返回队头的元素。如果队列为空，这个方法会等待，直到其他线程把元素添加到队列中为止。

- drainTo()方法

这个方法在 BlockingQueue 接口中定义，作用是把队列中的所有元素都移除，然后把这
些元素添加到指定的 Collection 对象中。这个方法不会阻塞操作，等待有元素添加到
队列中。这个方法有个变体，接受一个参数，指定最多移除多少个元素。

3. 查询

就队列而言，“查询”的意思是访问队头的元素，但不将其从队列中移除。

- element()方法

这个方法在 Queue 接口中定义，其作用是返回队头的元素，但不将其从队列中移除。如果队列为空，这个方法抛出 NoSuchElementException 异常。

- peek()方法

这个方法在 Queue 接口中定义，作用和 element() 方法类似，但队列为空时，返回 null。

> 使用队列时，最好选定一种处理失败的方式。例如，如果想在操作成功之前一直阻塞，应该选择 put() 和 take() 方法；如果想检查方法的返回值，判断操作是否成功，应该选择 offer() 和 poll() 方法。

LinkedList 类也实现了 Queue 接口，提供的是无界 FIFO 顺序，插入和移除操作需要常数时间。LinkedList 对象可以使用 null 作元素，不过，当列表用作队列时不建议使用 null。

java.util 包中还有另外两个 Queue 接口的实现。一个是 PriorityQueue 类，这种队列根据 Comparator 对象排序元素，或者根据 Comparable 类型元素的 compareTo() 方法排序元素。PriorityQueue 对象的队头始终是根据指定排序方式得到的最小值。另外一个是 ArrayDeque 类，实现的是双端队列，一般在需要用到栈的情况下使用。

java.util.concurrent 包中也包含一些 BlockingQueue 接口的实现，目的是在多线程编程环境中使用。有些实现很高级，甚至无需使用同步方法。

###### 8.1.6 实用方法

java.util.Collections 类定义了一些静态实用方法，用于处理集合。其中有一类方法很重要，是集合的包装方法：这些方法包装指定的集合，返回特殊的集合。包装集合的目的是把集合本身没有提供的功能绑定到集合上。包装集合能提供的功能有：线程安全性、写保护和运行时类型检查。包装集合都以原来的集合为后盾，因此，在包装集合上调用的方法其实会分派给原集合的等效方法完成。这意味着，通过包装集合修改集合后，改动也会体现在原集合身上；反之亦然。

第一种包装方法为包装的集合提供线程安全性。java.util 包中的集合实现，除了过时的 Vector 和 Hashtable 类之外，都没有 synchronized 方法，不能禁止多个线程并发访问。如果需要使用线程安全的集合，而且不介意同步带来的额外开销，可以像下面这样创建集合：
```java
List<String> list = Collections.synchronizedList(new ArrayList<String>());
Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
Map<String,Integer> map = Collections.synchronizedMap(new HashMap<String,Integer>());
```
第二种包装方法创建的集合对象不能修改底层集合，得到的集合是只读的，只要试图修改集合的内容，就会抛出 UnsupportedOperationException 异常。如果要把集合传入方法，但不允许修改集合，也不允许使用任何方式改变集合的内容，就可以使用这种包装集合：
```java
List<Integer> primes = new ArrayList<Integer>();
List<Integer> readonly = Collections.unmodifiableList(primes);
// 可以修改primes列表
primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
// 但不能修改只读的包装集合
readonly.add(23); // 抛出UnsupportedOperationException异常
```
java.util.Collections 类还定义了用来操作集合的方法。其中最值得关注的是排序和搜索集合元素的方法：
```java
Collections.sort(list);
// 必须先排序列表中的元素
int pos = Collections.binarySearch(list, "key");
```
Collections 类中还有些方法值得关注：
```java
// 把list2中的元素复制到list1中，覆盖list1
Collections.copy(list1, list2);
// 使用对象o填充list
Collections.fill(list, o);
// 找出集合c中最大的元素
Collections.max(c);

// 找出集合c中最小的元素
Collections.min(c);
Collections.reverse(list); // 反转列表
Collections.shuffle(list); // 打乱列表
```
你最好全面熟悉 Collections 和 Arrays 类中的实用方法，这样遇到常见任务时就不用自己动手实现了。

特殊的集合

除了包装方法之外，java.util.Collections 类还定义了其他实用方法，一些用于创建只包含一个元素的不可变集合实例，一些用于创建空集合。singleton()、singletonList() 和singletonMap() 方法分别返回不可变的 Set、List 和 Map 对象，而且只包含一个指定的对象或键值对。如果要把单个对象当成集合传入方法，可以使用这些方法。

Collections 类还定义了一些返回空集合的方法。如果你编写的方法要返回一个集合，遇到没有返回值的情况时，一般最好返回空集合，而不要返回 null 等特殊的值：
```java
Set<Integer> si = Collections.emptySet();
List<String> ss = Collections.emptyList();
Map<String,Integer> m = Collections.emptyMap();
```
最后还有个 nCopies() 方法。这个方法返回一个不可变的 List 对象，包含指定数量个指定对象的副本：
```java
List<Integer> tenzeros = Collections.nCopies(10, 0);
```

###### 8.1.7 数组和辅助方法

由对象组成的数组和集合的作用类似，而且二者之间可以相互转换：
```java
String[] a ={ "this", "is", "a", "test" }; // 一个数组
// 把数组当成大小不可变的列表
List<String> l = Arrays.asList(a);
// 创建一个大小可变的副本
List<String> m = new ArrayList<String>(l);

// asList()是个变长参数方法，所以也可以这么做：
Set<Character> abc = new HashSet<Character>(Arrays.asList('a', 'b', 'c'));

// Collection接口定义了toArray()方法。不传入参数时，这个方法创建
// Object[]类型的数组，把集合中的元素复制到数组中，然后返回这个数组
// 把set中的元素存入数组
Object[] members = set.toArray();
// 把list中的元素存入数组
Object[] items = list.toArray();
// 把map的键存入数组
Object[] keys = map.keySet().toArray();
// 把map的值存入数组
Object[] values = map.values().toArray();

// 如果不想返回Object[]类型的值，可以把一个所需类型的数组传入toArray()方法
// 如果传入的数组不够大，会再创建一个相同类型的数组
// 如果传入的数组太大，复制集合元素后剩余的位置使用null填充
String[] c = l.toArray(new String[0]);
```
除此之外，还有一些有用的辅助方法，用于处理 Java 数组。为了完整性，这里也会介绍。

java.lang.System 类定义了一个 arraycopy() 方法，作用是把一个数组中的指定元素复制到另一个数组的指定位置。这两个数组的类型必须相同，甚至可以是同一个数组：
```java
char[] text = "Now is the time".toCharArray();
char[] copy = new char[100];
// 从text的第4个元素开始，复制10个字符到copy中
// 这10个字符的位置从copy[0]开始
System.arraycopy(text, 4, copy, 0, 10);

// 把一些元素向后移，留出位置插入其他元素
System.arraycopy(copy, 3, copy, 6, 7);
```
Arrays 类还定义了一些有用的静态方法：
```java
int[] intarray = new int[] { 10, 5, 7, -3 }; // 由整数组成的数组
Arrays.sort(intarray); // 原地排序数组
// 在索引2找到值7
int pos = Arrays.binarySearch(intarray, 7);
// 未找到：返回负数
pos = Arrays.binarySearch(intarray, 12);

// 由对象组成的数组也能排序和搜索
String[] strarray = new String[] { "now", "is", "the", "time" };
Arrays.sort(strarray); // 排序的结果：{ "is", "now", "the", "time" }

// Arrays.equals()方法比较两个数组中的所有元素
String[] clone = (String[]) strarray.clone();
boolean b1 = Arrays.equals(strarray, clone); // 是的，两个数组相等

// Arrays.fill()方法用于初始化数组的元素
// 一个空数组，所有元素都是0
byte[] data = new byte[100];
// 把元素都设为-1
Arrays.fill(data, (byte) -1);
// 把第5-9个元素设为-2
Arrays.fill(data, 5, 10, (byte) -2);
```
在 Java 中，数组可以视作对象，也可以按照对象的方法处理。假如有个对象 o，可以使用类似下面的代码判断这个对象是否为数组。如果是，则判断是什么类型的数组：
```java
Class type = o.getClass();
if (type.isArray()) {
    Class elementType = type.getComponentType();
}
```

##### 8.2 在 Java 集合框架中使用 lambda 表达式

Java 8 引入 lambda 表达式的一个主要原因是大幅修改集合 API，让 Java 开发者使用更现代化的编程风格。在 Java 8 发布之前，使用 Java 处理数据结构的方式有点过时。现在，很多语言都支持把集合看成一个整体，而不用打散后再迭代。

事实上，很多 Java 开发者已经使用了替代的数据结构库，获取他们认为集合 API 缺乏的表现力和生产力。升级集合 API 的关键是引入参数能使用 lambda 表达式的新方法，定义需要做什么，而不用管具体怎么做。

> 有默认方法这个新语言特性的支持，才能在现有的接口中添加新方法（详情参见 4.1.6 节）。没有这个新机制的话，集合接口的原有实现在 Java 8 中不能编译，而且在 Java 8 的运行时中加载时无法链接。

本节简要介绍如何在 Java 集合框架中使用 lambda 表达式。完整的说明参阅 Richard Warburton 写的《Java 8 函数式编程》一书（O’Reilly 出版，http://shop.oreilly.com/product/0636920030713.do 。

###### 8.2.1 函数式方式

Java 8 想实现的方式源于函数式编程语言和风格。我们在 4.5.2 节已经介绍过一些关键的模式，这里会再次介绍，并举些例子。

1. 过滤器

这个模式把集合中的每个元素代入一段代码（返回 true 或 false），然后使用“通过测试”（即代入元素的那段代码返回 true）的元素构建一个新集合。

例如，下面这段代码处理一个由猫科动物名组成的集合，选出是老虎的元素：
```java
String[] input = {"tiger", "cat", "TIGER", "Tiger", "leopard"};
List<String> cats = Arrays.asList(input);
String search = "tiger";
String tigers = cats.stream()
    .filter(s -> s.equalsIgnoreCase(search))
    .collect(Collectors.joining(", "));
System.out.println(tigers);
```
上述代码的关键是对 filter() 方法的调用。filter() 方法的参数是一个 lambda 表达式，这个 lambda 表达式接受一个字符串参数，返回布尔值。整个 cats 集合中的元素都会代入这个表达式，然后创建一个新集合，只包含老虎（不过有些使用大写）。

filter() 方法的参数是一个 Predicate 接口的实例。Predicate 接口在新包 java.util.function 中定义。这是个函数式接口，只有一个非默认方法，因此特别适合 lambda 表达式。

注意，最后还调用了 collect() 方法。这个方法是流 API 的重要部分，作用是在 lambda 表达式执行完毕后“收集”结果。下一节会深入介绍这个方法。

Predicate 接口有一些十分有用的默认方法，例如用于合并判断条件的逻辑操作方法。假如想把豹子纳入老虎种群，可以使用 or() 方法：
```java
Predicate<String> p = s -> s.equalsIgnoreCase(search);
Predicate<String> combined = p.or(s -> s.equals("leopard"));
String pride = cats.stream()
    .filter(combined)
    .collect(Collectors.joining(", "));
System.out.println(pride);
```
注意，必须显式创建 Predicate\<String\> 类型的对象 p，这样才能在 p 上调用默认方法 or()，并把另一个 lambda 表达式（也会自动转换成 Predicate\<String\> 类型的对象）传给 or() 方法。

2. 映射

Java 8 中的映射模式使用 java.util.function 包中的新接口 Function\<T, R\>。这个接口和 Predicate\<T\> 接口一样，是函数式接口，因此只有一个非默认方法——apply()。映射模式把一种类型元素组成的集合转换成另一种类型元素组成的集合。这一点在 API 中就体现出来了，因为 Function\<T, R\> 接口有两个不同的类型参数，其中，类型参数 R 的名称表示这个方法的返回类型。

下面看一个使用 map() 方法的示例代码：
```java
List<Integer> namesLength = cats.stream()
    .map(String::length)
    .collect(Collectors.toList());
System.out.println(namesLength);
```
3. 遍历

映射和过滤器模式的作用是以一个集合为基础，创建另一个集合。在完全支持函数式编程的语言中，除了这种方式之外，还需要 lambda 表达式的主体处理各个元素时不影响原来的集合。用计算机科学的术语来说，这意味着 lambda 表达式的主体“不能有副作用”。

当然，在 Java 中经常需要处理可变的数据，所以新的集合 API 提供了一个方法，在遍历集合时修改元素——forEach() 方法。这个方法的参数是一个 Consumer\<T\> 类型的对象。Consumer\<T\> 是函数式接口，要求使用副作用执行操作（然而，到底会不会真得修改数据不是那么重要）。因此，能转换成 Consumer\<T\> 类型的 lambda 表达式，其签名为 (T t) ->void。下面是一个使用 forEach() 方法的简单示例：
```java
List<String> pets = Arrays.asList("dog", "cat", "fish", "iguana", "ferret");
pets.stream().forEach(System.out::println);
```
在这个示例中，我们只是把集合中的每个元素打印出来。不过，我们把 lambda 表达式写成了一种特殊的方法引用。这种方法引用叫受限的方法引用（bound method reference），因为需要指定对象（这里指定的对象是 System.out，System 类的公开静态字段）。这个方法引用和下面的 lambda 表达式等效：
```java
s -> System.out.println(s);
```
当然，根据方法的签名，这样写能明确表明 lambda 表达式要转换成一个实现 Consumer\<? super String\> 接口类型的实例。

> 不是说 map() 或 filter() 方法一定不能修改元素。不要使用这两个方法修改元素，这只是一种约定，每个 Java 程序员都要遵守。

在结束本节之前，还有最后一个函数式技术要介绍。这种技术把集合中的元素聚合成一个值，详情参见下一小节。

下面介绍 reduce() 方法。这个方法实现的是化简模式，包含一系列相关的类似运算，有时也称为合拢或聚合运算。

在Java8中，reduce()方法有两个参数：一个是初始值，一般叫作单位值（或零值）；另一个参数是一个函数，逐步执行。这个函数属于 BinaryOperator\<T\> 类型。BinaryOperator\<T\> 也是函数式接口，有两个类型相同的参数，返回值也是同一类型。reduce() 方法的第二个参数是一个 lambda 表达式，接受两个参数。在 Java 的文档中，reduce() 方法的签名是：
```java
T reduce(T identity, BinaryOperator<T> aggregator);
```
reduce() 方法的第二个参数可以简单地理解成，在处理流的过程中“累积计数”：首先合并单位值和流中的第一个元素，得到第一个结果，然后再合并这个结果和流中的第二个元素，以此类推。

把 reduce() 方法的实现设想成下面这样有助于理解其作用：
```java
public T reduce(T identity, BinaryOperator<T> aggregator) {
    T runningTotal = identity;
    for (T element : myStream) {
       runningTotal = aggregator.apply(runningTotal, element);
    }
    return result;
}
```
> 实际上，reduce() 方法的实现比这复杂得多，如果数据结构和运算有需要，甚至还可以并行执行。

下面看一个使用 reduce() 方法的简单示例，这个示例计算几个质数之和：
```java
double sumPrimes = ((double)Stream.of(2, 3, 5, 7, 11, 13, 17, 19, 23)
    .reduce(0, (x, y) -> {return x + y;}));
System.out.println("Sum of some primes: " + sumPrimes);
```
你可能注意到了，本节举的所有示例中，都在 List 实例上调用了 stream() 方法。这是集合 API 演进的一部分——一开始选择这种方式是因为部分 API 有这方面的需求，但后来证实，这是极好的抽象。下面详细讨论流 API。

###### 8.2.2 流 API

库的设计者之所以引入流 API，是因为集合核心接口的大量实现已经广泛使用。这些实现在 Java 8 和 lambda 表达式之前就已存在，因此没有执行任何函数式运算的方法。更糟的是，map() 和 filter() 等方法从未出现在集合 API 的接口中，实现这些接口的类可能已经使用这些名称定义了方法。

为了解决这个问题，设计者引入了一层新的抽象——Stream。Stream 对象可以通过 stream() 方法从集合对象上生成。设计者引入这个全新的 Stream 对象是为了避免方法名冲突，这的确在一定程度上减少了冲突的几率，因为只有包含 stream() 方法的实现才会受到影响。

在处理集合的新方式中，Stream 对象的作用和 Iterator 对象类似。总体思想是让开发者把一系列操作（也叫“管道”，例如映射、过滤器或化简）当成一个整体运用在集合上。具体执行的各个操作一般使用 lambda 表达式表示。

在管道的末尾需要收集结果，或者再次“具化”为真正的集合。这一步使用 Collector 对象完成，或者以“终结方法”（例如 reduce()）结束管道，返回一个具体的值，而不是另一个流。总的来说，处理集合的新方式类似下面这样：
```
        stream()   filter()   map()   collect()
Collection -> Stream -> Stream -> Stream -> Collection
```
Stream 类相当于一系列元素，一次访问一个元素（不过有些类型的流也支持并行访问，可以使用多线程方式处理大型集合）。Stream 对象和 Iterator 对象的工作方式一样，依次读取每个元素。

和 Java 中的大多数泛型类一样，Stream 类也使用引用类型参数化。不过，多数情况下，其实需要使用基本类型，尤其是 int 和 double 类型构成的流，但是又没有 Stream\<int\> 类型，所以 java.util.stream 包提供了专用的（非泛型）类，例如 IntStream 和 DoubleStream。这些类是 Stream 类的基本类型特化，其 API 和一般的 Stream 类十分类似，不过在适当的情况下会使用基本类型的值。

例如，在前面 reduce() 方法的示例中，多数时候，在管道中使用的其实就是 Stream 类的基本类型特化。

1. 惰性求值

其实，流比迭代器（甚至是集合）通用，因为流不用管理数据的存储空间。在早期的 Java 版本中，总是假定集合中的所有元素都存在（一般存储在内存中），不过有些处理方式也能避开这个问题，例如坚持在所有地方都使用迭代器，或者让迭代器即时构建元素。可是，这些方式既不十分便利，也不那么常用。

然而，流是管理数据的一种抽象，不关心存储细节。因此，除了有限的集合之外，流还能处理更复杂的数据结构。例如，使用 Stream 接口可以轻易实现无限流，处理一切平方数。实现方式如下所示：
```java
public class SquareGenerator implements IntSupplier {
    private int current = 1;
    
    @Override
    public synchronized int getAsInt() {
        int thisResult = current * current;
        current++;
        return thisResult;
    }
}

IntStream squares = IntStream.generate(new SquareGenerator());
PrimitiveIterator.OfInt stepThrough = squares.iterator();
for (int i = 0; i < 10; i++) {
    System.out.println(stepThrough.nextInt());
}
System.out.println("First iterator done...");

// 只要想就可以一直这样进行下去……
for (int i = 0; i < 10; i++) {
    System.out.println(stepThrough.nextInt());
}
```
通过构建上述无限流，我们能得出一个重要结论：不能使用 collect() 这样的方法。这是因为无法把整个流具化为一个集合（在创建所需的无限个对象之前就会耗尽内存）。因此，我们采取的方式必须在需要时才从流中取出元素。其实，我们需要的是按需读取下一个元素的代码。为了实现这种操作，需要使用一个关键技术——惰性求值（lazy evaluation）。这个技术的本质是，需要时才计算值。

> 惰性求值对 Java 来说是个重大的变化，在 JDK 8 之前，表达式赋值给变量（或传入方法）后会立即计算它的值。这种立即计算值的方式我们已经熟知，术语叫“及早求值”（eager evaluation）。在多数主流编程语言中，“及早求值”都是计算表达式的默认方式。

幸好，实现惰性求值的重担几乎都落在了库的编写者身上，开发者则轻松得多，而且使用流 API 时，大多数情况下 Java 开发者都无需仔细考虑惰性求值。下面以一个示例结束对流的讨论。这个示例使用 reduce() 方法计算几个莎士比亚语录的平均单词长度：
```java
String[] billyQuotes = {"For Brutus is an honourable man",
  "Give me your hands if we be friends and Robin shall restore amends",
  "Misery acquaints a man with strange bedfellows"};
List<String> quotes = Arrays.asList(billyQuotes);

// 创建一个临时集合，保存单词
List<String> words = quotes.stream()
    .flatMap(line -> Stream.of(line.split(" +")))
    .collect(Collectors.toList());
long wordCount = words.size();

// 校正为double类型只是为了避免Java按照整数方式计算除法
double aveLength = ((double) words.stream()
    .map(String::length)
    .reduce(0, (x, y) -> {return x + y;})) / wordCount;
System.out.println("Average word length: " + aveLength);
```
这个示例用到了 flatMap() 方法。在这个示例中，向 flatMap() 方法传入一个字符串 line，得到的是一个由字符串组成的流，流中的数据是拆分一句话得到的所有单词。然后再“整平”这些单词，把处理每句话得到的流都合并到一个流中。

这样做的目的是把每句话都拆分成单个单词，然后再组成一个总流。为了计算单词数量，我们创建了一个对象 words。其实，在管道处理流的过程中会“中断”，再次具化，把单词存入集合，在流操作恢复之前获取单词的数量。

这一步完成之后，下一步是化简运算，先计算所有语录中的单词总长度，然后再除以已经获取的单词数量。记住，流是惰性抽象，如果要执行及早操作（例如，计算流下面的集合大小），得重新具化为集合。

2. 处理流的实用默认方法

借着引入流 API 的机会，Java 8 向集合库引入了一些新方法。现在 Java 已经支持默认方法，因此可以向集合接口中添加新方法，而不会破坏向后兼容性。

新添加的方法中有一些是“基架方法”，用于创建抽象的流，例如 Collection::stream、Collection::parallelStream 和 Collection::spliterator （这个方法可以细分为 List::spliterator 和Set::spliterator）。

另一些则是“缺失方法”，例如 Map::remove 和 Map::replace。List::sort 也属于“缺失方法”，在 List 接口中的定义如下所示：
```java
// 其实是把具体操作交给Collections类的辅助方法完成
public default void sort(Comparator<? super E> c) {
    Collections.<E>sort(this, c);
}
```
Map::putIfAbsent 也是缺失方法，根据 java.util.concurrent 包中 ConcurrentMap 接口的同名方法改写。

另一个值得关注的缺失方法是 Map::getOrDefault，程序员使用这个方法能省去很多检查 null 值的繁琐操作，因为如果找不到要查询的键，这个方法会返回指定的值。

其余的方法则使用 java.util.function 接口提供额外的函数式技术。

- Collection::removeIf

这个方法的参数是一个 Predicate 对象，它会迭代整个集合，把满足判断条件的元素移除。

- Map::forEach

这个方法只有一个参数，是一个 lambda 表达式；而这个 lambda 表达式有两个参数（一个是键的类型，一个是值的类型），返回 void。这个 lambda 表达式会转换成 BiConsumer 对象，应用在映射中的每个键值对上。

- Map::computeIfAbsent

这个方法有两个参数：键和 lambda 表达式。lambda 表达式的作用是把键映射到值上。如果映射中没有指定的键（第一个参数），那就使用 lambda 表达式计算一个默认值，然后存入映射。

（其他值得学习的方法：Map::computeIfPresent、Map::compute 和 Map::merge。）

##### 8.3 小结

本章介绍了 Java 集合库，也说明了如何开始使用 Java 实现的基本和经典数据结构。我们学习了通用的 Collection 接口，以及 List、Set 和 Map 接口；学习了处理集合的原始迭代方式，也介绍了 Java 8 从函数式编程语言借鉴来的新方式。最后，我们学习了流 API，发现这种新方式更通用，而且处理复杂的编程概念时比经典方式更具表现力。

我们继续学习。下一章继续讨论数据，会介绍一些常见任务的处理方式，例如处理文本和数字数据，还会介绍 Java 8 引入的新日期和时间库。
