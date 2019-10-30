package cn.part02.ch08.factoryMethod3;
// 代码清单8-13 简单工厂模式中的工厂类

//去掉了AbstractHumanFactory抽象类，同时把createHuman方法设置为静态类型，简化了类的创建过程
//变更的源码仅仅是HumanFactory和NvWa类

//HumanFactory类仅有两个地方发生变化：去掉继承抽象类，并在createHuman前增加static关键字
public class HumanFactory {
    public static <T extends Human> T createHuman(Class<T> c) {
        //定义一个生产出的人种
        Human human = null;
        try {
            //产生一个人种
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误！");
        }
        return (T) human;
    }
}
