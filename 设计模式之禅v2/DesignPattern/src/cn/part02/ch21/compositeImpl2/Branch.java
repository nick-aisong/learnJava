package cn.part02.ch21.compositeImpl2;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/18.
 */
public class Branch extends Corp {

    ArrayList<Corp> subordinateList = new ArrayList<Corp>();

    public Branch(String _name, String _position, int _salary) {
        super(_name, _position, _salary);
    }

    public void addSubordinate(Corp corp) {
        corp.setParent(this); // 设置父节点
        this.subordinateList.add(corp);
    }

    public ArrayList<Corp> getSubordinate() {
        return this.subordinateList;
    }
}
