package cn.ch06.ch06_5;

// 1.创建名为Contact的类
public class Contact {
    // 2.定义两个私有字符串属性name和phone
    private String name;
    private String phone;

    // 3.实现类构建器来初始化属性
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // 4.实现返回name和phone属性值的方法
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
