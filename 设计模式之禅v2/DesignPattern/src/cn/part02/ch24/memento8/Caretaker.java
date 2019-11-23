package cn.part02.ch24.memento8;

//代码清单24-24 备忘录管理者
public class Caretaker {
    //备忘录对象
    private IMemento memento;

    public IMemento getMemento() {
        return memento;
    }

    public void setMemento(IMemento memento) {
        this.memento = memento;
    }
}