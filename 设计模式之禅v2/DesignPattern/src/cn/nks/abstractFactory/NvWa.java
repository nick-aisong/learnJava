package cn.nks.abstractFactory;

/**
 * Created by NKS on 2017/9/14.
 */
public class NvWa {

    public static void main(String[] args){
        HumanFactory maleHumanFactory = new MaleHumanFactory();
        HumanFactory femaleHumanFactory = new FemaleHumanFactory();

        Human maleYellowHuman = maleHumanFactory.createYellowHuman();
        Human femaleYellowHuman = femaleHumanFactory.createYellowHuman();

        maleYellowHuman.cry();
        maleYellowHuman.laugh();
        maleYellowHuman.talk();
        maleYellowHuman.sex();

        femaleYellowHuman.cry();
        femaleYellowHuman.laugh();
        femaleYellowHuman.talk();
        femaleYellowHuman.sex();
    }
}
