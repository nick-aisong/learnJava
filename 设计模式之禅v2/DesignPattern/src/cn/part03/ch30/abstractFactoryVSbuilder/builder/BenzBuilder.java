package cn.part03.ch30.abstractFactoryVSbuilder.builder;

//代码清单30-28 奔驰车建造车间
public class BenzBuilder extends CarBuilder {
    public String buildEngine() {
        return super.getBlueprint().getEngine();
    }

    public String buildWheel() {
        return super.getBlueprint().getWheel();
    }
}