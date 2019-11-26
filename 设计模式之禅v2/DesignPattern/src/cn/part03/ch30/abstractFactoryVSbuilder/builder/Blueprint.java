package cn.part03.ch30.abstractFactoryVSbuilder.builder;

//代码清单30-26 生产蓝图
public class Blueprint {
    //车轮的要求
    private String wheel;
    //引擎的要求
    private String engine;

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}

//这和一个具体的产品Car类是一样的？错，不一样！它是一个蓝图，是一个可以参考的
//模板，有一个蓝图可以设计出非常多的产品，如有一个R系统的奔驰商务车设计蓝图，我们
//就可以生产出一系列的奔驰车。它指导我们的产品生产，而不是一个具体的产品
