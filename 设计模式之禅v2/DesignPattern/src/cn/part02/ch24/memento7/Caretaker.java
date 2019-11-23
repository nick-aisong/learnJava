package cn.part02.ch24.memento7;

import java.util.HashMap;

//代码清单24-20 备忘录管理员
public class Caretaker {
    //容纳备忘录的容器
    private HashMap<String, Memento> memMap = new HashMap<String, Memento>();

    public Memento getMemento(String idx) {
        return memMap.get(idx);
    }

    public void setMemento(String idx, Memento memento) {
        this.memMap.put(idx, memento);
    }
}