package cn.ch06.ch06_5;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
// 本例将要学习如何使用ConcurrentSkipListMap类实现对联系人对象的映射

// 10.实现范例主类Main，并添加main()方法

// 使用线程安全可遍历映射
public class Main {

    public static void main(String[] args) {
        // 11.创建一个ConcurrentSkipListMap对象，命名为map，并指定泛型参数为String和Conctact类
        ConcurrentSkipListMap<String, Contact> map;
        map = new ConcurrentSkipListMap<>();
        // 12. 创建一个长度为25的线程数组，用来存放Task对象
        Thread[] threads = new Thread[25];
        int counter = 0;
        // 13.创建25个task对象，每个task对象的标识符是一个大写字符。分别以这些task对象创建运行线程并启动
        for (char i = 'A'; i < 'Z'; i++) {
            Task task = new Task(map, String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }
        // 14.使用join()方法等待所有线程执行完成
        for (int i = 0; i < 25; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 15.使用firstEntry()方法取得map中的第一个元素。将这个数据输出到控制台
        System.out.printf("Main: Size of the map: %d\n", map.size());

        Map.Entry<String, Contact> element;
        Contact contact;

        element = map.firstEntry();
        contact = element.getValue();
        System.out.printf("Main: First Entry: %s: %s\n", contact.getName(), contact.getPhone());
        // 16.使用lastEntry()方法取得map中的最后一个元素。将这个数据输出到控制台
        element = map.lastEntry();
        contact = element.getValue();
        System.out.printf("Main: Last Entry: %s: %s\n", contact.getName(), contact.getPhone());
        // 17.使用subMap()取得map的一个子映射。将这些数据输出到控制台
        System.out.printf("Main: Submap from A1996 to B1002: \n");
        ConcurrentNavigableMap<String, Contact> submap = map.subMap("A1996", "B1002");
        do {
            element = submap.pollFirstEntry();
            if (element != null) {
                contact = element.getValue();
                System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
            }
        } while (element != null);
    }
}

// 本节中，我们实现了用Task类在一个可遍历的映射中存放Contact对象
// 每一个contact对象都有一个名称，即创建的task对象的标识ID，和一个介于1,000到2,000的电话号码
// 拼接这两个值作为contact对象的键。每个Task对象将生成1,000个contact对象，并使用put()方法将它们存放在可遍历的映射中

// 备注：如果插入的元素的键值已经存在，就用新插入的值覆盖已有的值

// Main类的main()方法创建了25个Task对象，使用介于A到Z之间的字符作为ID，接下来，使用其提供的方法从映射中获取数据
// firstEntry()方法返回一个Map.Entry对象，含有映射中的第一个元素
// 这个方法不会从映射中移除元素。Map.Entry对象包含键值和元素
// 使用getValue()方法就能够获取元素。使用getKey()就能够获取元素的键值
// lastEntry()方法返回一个Map.Entry对象，含有映射中的最后一个元素
// subMap()方法返回含有映射中部分元素的ConcurrentNavigableMap对象

// 在本例中，元素的键值介于A1996到B1002之间。可以使用pollFirst()方法来处理subMap()方法获取的元素
// 这个方法会返回并移除子映射中的第一个Map.Entry对象

// ConcurrentSkipListMap类还提供了其他的方法
// ·headMap(K toKey)： K是在ConcurrentSkipListMap对象的泛型参数里用到的键。这个方法返回映射中所有键值小于参数值toKey的子映射
// ·tailMap(K fromKey)： K是在ConcurrentSkipListMap对象的泛型参数里用到的键。这个方法返回映射中所有键值大于参数值fromKey的子映射
// ·putIfAbsent(K key, V value)： 如果映射中不存在键key，那么就将key和value保存到映射中
// ·polLastEntry()： 返回并移除映射中的最后一个Map.Entry对象
// ·replace(K key, V value)：如果映射中已经存在键key，则用参数中的value替换现有的值

// Main: Size of the map: 25000
// Main: First Entry: A: 1000
// Main: Last Entry: Y: 1999
// Main: Submap from A1996 to B1002:
// A: 1996
// A: 1997
// A: 1998
// A: 1999
// B: 1000
// B: 1001