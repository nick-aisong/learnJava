package cn.part02.ch24.memento7;

import java.util.HashMap;

//代码清单24-18 备忘录角色
public class Memento {
    //接受HashMap作为状态
    private HashMap<String, Object> stateMap;

    //接受一个对象，建立一个备份
    public Memento(HashMap<String, Object> map) {
        this.stateMap = map;
    }

    public HashMap<String, Object> getStateMap() {
        return stateMap;
    }

    public void setStateMap(HashMap<String, Object> stateMap) {
        this.stateMap = stateMap;
    }
}