package cn.nks.composite;

import java.util.ArrayList;


public interface IRoot {

    public String getInfo();

    public void add(IBranch branch);

    public void add(ILeaf leaf);

    public ArrayList getSubordinateInfo();

}
