package cn.part02.ch08.factoryMethod4;

public class WhiteHumanFactory extends AbstractHumanFactory {
    public Human createHuman() {
        return new WhiteHuman();
    }
}