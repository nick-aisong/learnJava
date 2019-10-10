package cn.nks.abstractFactory;

/**
 * Created by NKS on 2017/9/14.
 */
public abstract class AbstractHumanFactory implements HumanFactory {

    protected Human createHuman(HumanEnum humanEnum){
        Human human = null;

        if (!humanEnum.getValue().equals("")){
            try {
                human = (Human)Class.forName(humanEnum.getValue()).newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return human;
    }

}
