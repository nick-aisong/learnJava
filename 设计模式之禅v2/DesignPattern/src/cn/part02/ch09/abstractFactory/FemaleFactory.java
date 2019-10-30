package cn.part02.ch09.abstractFactory;

//代码清单9-8 生产女性的八卦炉
public class FemaleFactory implements HumanFactory {
    //生产出黑人女性
    public Human createBlackHuman() {
        return new FemaleBlackHuman();
    }

    //生产出白人女性
    public Human createWhiteHuman() {
        return new FemaleWhiteHuman();
    }

    //生产出黄人女性
    public Human createYellowHuman() {
        return new FemaleYellowHuman();
    }
}
