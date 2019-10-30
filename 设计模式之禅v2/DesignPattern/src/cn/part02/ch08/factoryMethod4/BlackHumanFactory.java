package cn.part02.ch08.factoryMethod4;

public class BlackHumanFactory extends AbstractHumanFactory {
    public Human createHuman() {
        return new BlackHuman();
    }
}
