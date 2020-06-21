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













###### 8.1.6 实用方法













###### 8.1.7 数组和辅助方法














##### 8.2 在 Java 集合框架中使用 lambda 表达式











###### 8.2.1 函数式方式
















###### 8.2.2 流 API














##### 8.3 小结














