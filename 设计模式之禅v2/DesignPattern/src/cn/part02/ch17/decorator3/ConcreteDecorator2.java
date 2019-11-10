package cn.part02.ch17.decorator3;

//代码清单17-11 具体构件
public class ConcreteDecorator2 extends Decorator {
    //定义被修饰者
    public ConcreteDecorator2(Component _component) {
        super(_component);
    }

    //定义自己的修饰方法
    private void method2() {
        System.out.println("method2修饰");
    }

    //重写父类的Operation方法
    public void operate() {
        super.operate();
        this.method2();
    }
}
