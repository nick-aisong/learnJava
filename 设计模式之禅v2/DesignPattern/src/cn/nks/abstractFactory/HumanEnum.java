package cn.nks.abstractFactory;

/**
 * Created by NKS on 2017/9/14.
 */
public enum HumanEnum {

    YellowMaleHuman("cn.nks.abstractFactory.YellowMaleHuman"),
    YellowFemaleHuman("cn.nks.abstractFactory.YellowFemaleHuman"),
    WhiteMaleHuman("cn.nks.abstractFactory.WhiteMaleHuman"),
    WhiteFemaleHuman("cn.nks.abstractFactory.WhiteFemaleHuman"),
    BlackMaleHuman("cn.nks.abstractFactory.BlackMaleHuman"),
    BlackFemaleHuman("cn.nks.abstractFactory.BlackFemaleHuman");

    private String value = "";

    private HumanEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
