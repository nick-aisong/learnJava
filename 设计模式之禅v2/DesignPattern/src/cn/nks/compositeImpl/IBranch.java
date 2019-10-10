package cn.nks.compositeImpl;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/18.
 */
public interface IBranch {

    public void addSubordinate(ICorp corp);

    public ArrayList<ICorp> getSubordinate();

}
