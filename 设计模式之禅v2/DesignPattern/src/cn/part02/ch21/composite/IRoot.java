package cn.part02.ch21.composite;

import java.util.ArrayList;


public interface IRoot {

    public String getInfo();

    public void add(IBranch branch);

    public void add(ILeaf leaf);

    public ArrayList getSubordinateInfo();

}
