package cn.part02.ch21.composite;

import java.util.ArrayList;

//代码清单21-1 根节点接口
public interface IRoot {
    //得到总经理的信息
    public String getInfo();

    //总经理下边要有小兵，那要能增加小兵，比如研发部总经理，这是个树枝节点
    public void add(IBranch branch);

    //那要能增加树叶节点
    public void add(ILeaf leaf);

    //既然能增加，那还要能够遍历，不可能总经理不知道他手下有哪些人
    public ArrayList getSubordinateInfo();
}
