package cn.nks.factoryMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by NKS on 2017/9/13.
 */
public class HumanFactory {

    public static Human createHuman(Class c) {
        Human human = null;

        try {
            human = (Human) Class.forName(c.getName()).newInstance();

        } catch (InstantiationException e) {

            System.out.println("必须指定人种的颜色");
        } catch (IllegalAccessException e) {

            System.out.println("人种定义错误！");
        } catch (ClassNotFoundException e) {

            System.out.println("混蛋，你指定的人种找不到！");
        }
        return human;
    }

    public static Human createHuman() {
        Human human = null;
        List<Class> concreteHumanList = ClassUtils.getAllClassByInterface(Human.class);

        Random random = new Random();
        int rand = random.nextInt(concreteHumanList.size());
        human = createHuman(concreteHumanList.get(rand));

        return human;
    }

    public static HashMap<String, Human> humans = new HashMap<String, Human>();

    public static Human createHuman2(Class c) {
        Human human = null;

        try {
            if (humans.containsKey(c.getSimpleName())) {
                human = humans.get(c.getSimpleName());
                //System.out.println("--命中--");
            } else {
                human = (Human) Class.forName(c.getName()).newInstance();
                humans.put(c.getSimpleName(), human);
                System.out.println("--创建--");
            }
        } catch (InstantiationException e) {

            System.out.println(" 必须指定人种的颜色 ");
        } catch (IllegalAccessException e) {

            System.out.println(" 人种定义错误！ ");
        } catch (ClassNotFoundException e) {

            System.out.println(" 混蛋，你指定的人种找不到！ ");
        }

        return human;
    }
}
