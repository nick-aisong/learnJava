package cn.part02.ch21.composite;

import java.util.ArrayList;

//代码清单21-3 其他有分支的节点接口
public interface IBranch {
    //获得信息
    public String getInfo();

    //增加数据节点，例如研发部下设的研发一组
    public void add(IBranch branch);

    //增加叶子节点
    public void add(ILeaf leaf);

    //获得下级信息
    public ArrayList getSubordinateInfo();
}
