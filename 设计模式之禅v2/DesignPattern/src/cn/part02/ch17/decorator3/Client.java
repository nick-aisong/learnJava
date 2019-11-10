package cn.part02.ch17.decorator3;

//代码清单17-14 场景类
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        //第一次修饰
        component = new ConcreteDecorator1(component);
        //第二次修饰
        component = new ConcreteDecorator2(component);
        //修饰后运行
        component.operate();
    }
}

// method1 修饰
// do Something
// method2修饰
