package cn.part03.ch30.abstractFactoryVSbuilder.builder;

//代码清单30-27 宝马车建造车间
public class BMWBuilder extends CarBuilder {
    public String buildEngine() {
        return super.getBlueprint().getEngine();
    }

    public String buildWheel() {
        return super.getBlueprint().getWheel();
    }
}