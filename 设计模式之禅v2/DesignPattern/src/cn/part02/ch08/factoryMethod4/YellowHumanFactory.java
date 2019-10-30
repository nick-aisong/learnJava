package cn.part02.ch08.factoryMethod4;

public class YellowHumanFactory extends AbstractHumanFactory {
    public Human createHuman() {
        return new YellowHuman();
    }
}
