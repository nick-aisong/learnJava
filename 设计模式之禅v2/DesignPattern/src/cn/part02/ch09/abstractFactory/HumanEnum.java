package cn.part02.ch09.abstractFactory;

/**
 * Created by NKS on 2017/9/14.
 */
public enum HumanEnum {

    YellowMaleHuman("cn.part02.ch09.abstractFactory.YellowMaleHuman"),
    YellowFemaleHuman("cn.part02.ch09.abstractFactory.YellowFemaleHuman"),
    WhiteMaleHuman("cn.part02.ch09.abstractFactory.WhiteMaleHuman"),
    WhiteFemaleHuman("cn.part02.ch09.abstractFactory.WhiteFemaleHuman"),
    BlackMaleHuman("cn.part02.ch09.abstractFactory.BlackMaleHuman"),
    BlackFemaleHuman("cn.part02.ch09.abstractFactory.BlackFemaleHuman");

    private String value = "";

    private HumanEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
