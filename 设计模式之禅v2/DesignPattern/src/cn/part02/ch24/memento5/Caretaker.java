package cn.part02.ch24.memento5;

//代码清单24-13 备忘录管理员角色
public class Caretaker {
    //发起人对象
    private Originator_old originator;

    public Originator_old getOriginator() {
        return originator;
    }

    public void setOriginator(Originator_old originator) {
        this.originator = originator;
    }
}