package cn.nks.iterator;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/16.
 */
public class Boss {

    public static void main(String[] args) {
        ArrayList<IProject> projectList = new ArrayList<IProject>();

        //增加星球大战项目
        projectList.add(new Project("星球大战项目", 10, 100000));
        //增加扭转时空项目
        projectList.add(new Project("扭转时空项目", 100, 10000000));
        //增加超人改造项目
        projectList.add(new Project("超人改造项目", 10000, 1000000000));

        //这边100个项目
        for (int i = 4; i < 104; i++) {
            projectList.add(new Project("第" + i + "个项目", i * 5, i * 1000000));
        }

        for (IProject project : projectList) {
            System.out.println(project.getProjectInfo());
        }

    }
}
