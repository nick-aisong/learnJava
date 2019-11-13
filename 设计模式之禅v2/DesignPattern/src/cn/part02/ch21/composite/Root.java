package cn.part02.ch21.composite;

import java.util.ArrayList;

//代码清单21-2 根节点的实现
public class Root implements IRoot {
    //保存根节点下的树枝节点和树叶节点，Subordinate的意思是下级
    private ArrayList subordinateList = new ArrayList();
    //根节点的名称
    private String name = "";
    //根节点的职位
    private String position = "";
    //根节点的薪水
    private int salary = 0;

    //通过构造函数传递进来总经理的信息
    public Root(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    //增加树枝节点
    public void add(IBranch branch) {
        this.subordinateList.add(branch);
    }

    //增加叶子节点，比如秘书，直接隶属于总经理
    public void add(ILeaf leaf) {
        this.subordinateList.add(leaf);
    }

    //得到自己的信息
    public String getInfo() {
        String info = "";
        info = "名称：" + this.name;
        info = info + "\t职位：" + this.position;
        info = info + "\t薪水： " + this.salary;
        return info;
    }

    //得到下级的信息
    public ArrayList getSubordinateInfo() {
        return this.subordinateList;
    }
}
