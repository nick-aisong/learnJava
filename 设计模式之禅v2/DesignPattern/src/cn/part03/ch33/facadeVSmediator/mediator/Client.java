package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-20 场景类
public class Client {
    public static void main(String[] args) {
        //定义中介者
        Mediator mediator = new Mediator();
        //定义各个同事类
        IPosition position = new Position(mediator);
        ISalary salary = new Salary(mediator);
        ITax tax = new Tax(mediator);
        //职位提升了
        System.out.println("===职位提升===");
        position.promote();
    }
}