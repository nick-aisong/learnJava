package cn.part03.ch30.abstractFactoryVSbuilder.builder;

//代码清单30-30 场景类
public class Client {
    public static void main(String[] args) {
        //定义出导演类
        Director director = new Director();
        //给我一辆奔驰车SUV
        System.out.println("===制造一辆奔驰SUV===");
        ICar benzSuv = director.createBenzSuv();
        System.out.println(benzSuv);
        //给我一辆宝马商务车
        System.out.println("\n===制造一辆宝马商务车===");
        ICar bmwVan = director.createBMWVan();
        System.out.println(bmwVan);
        //给我一辆混合车型
        System.out.println("\n===制造一辆混合车===");
        ICar complexCar = director.createComplexCar();
        System.out.println(complexCar);
    }
}

//===制造一辆奔驰SUV===
//获得生产蓝图
//车的轮子是：benz的轮胎
//车的引擎是：benz的引擎
//
//===制造一辆宝马商务车===
//获得生产蓝图
//车的轮子是：BMW的轮胎
//车的引擎是：BMW的引擎
//
//===制造一辆混合车===
//获得生产蓝图
//车的轮子是：benz的轮胎
//车的引擎是：BMW的引擎
