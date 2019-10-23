package cn.ch06.ch06_5;

import java.util.concurrent.ConcurrentSkipListMap;

// 5.创建名为Task的类，并实现Runnable接口
public class Task implements Runnable {
    // 6.声明一个私有的ConcurrentSkipListMap类型map，并指定其泛型参数是String和Contact类
    private ConcurrentSkipListMap<String, Contact> map;
    // 7.声明一个私有的名为id的字符串属性，用来存放当前task的ID
    private String id;

    // 8.实现类构建器来初始化属性
    public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
        this.map = map;
        this.id = id;
    }
    // 9.实现run()方法。使用task的ID和一个递增的数来创建1000个Contact对象，并调用map对象的put()方法将这些contact对象添加到map中
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact(id, String.valueOf(i + 1000));
            map.put(id + contact.getPhone(), contact);
        }
    }
}
