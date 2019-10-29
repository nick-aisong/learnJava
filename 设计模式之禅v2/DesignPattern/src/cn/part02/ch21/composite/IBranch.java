package cn.part02.ch21.composite;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/18.
 */
public interface IBranch {

    public String getInfo();

    public void add(IBranch branch);

    public void add(ILeaf leaf);

    public ArrayList getSubordinateInfo();
}
