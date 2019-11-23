package cn.part02.ch25.visitor3;

//代码清单25-16 场景类
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            //获得元素对象
            Element el = ObjectStruture.createElement();
            //接受访问者访问
            el.accept(new Visitor());
        }
    }
}