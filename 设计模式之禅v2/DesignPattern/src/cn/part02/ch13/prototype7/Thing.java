package cn.part02.ch13.prototype7;

import java.util.ArrayList;

public class Thing implements Cloneable {
    //定义一个私有变量
    private final ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    public Thing clone() {
        Thing thing = null;
        try {
            thing = (Thing) super.clone();
            // 报错：cannot assign a value to final variable 'arrayList'
            //this.arrayList = (ArrayList<String>) this.arrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }

    //设置HashMap的值
    public void setValue(String value) {
        this.arrayList.add(value);
    }

    //取得arrayList的值
    public ArrayList<String> getValue() {
        return this.arrayList;
    }
}
