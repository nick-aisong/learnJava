package cn.part02.ch24.memento5;

//代码清单24-12 融合备忘录的发起人角色
public class Originator_old implements Cloneable {
    //内部状态
    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //创建一个备忘录
    public Originator_old createMemento() {
        return this.clone();
    }

    //恢复一个备忘录
    public void restoreMemento(Originator_old _originator) {
        this.setState(_originator.getState());
    }

    //克隆当前对象
    @Override
    protected Originator_old clone() {
        try {
            return (Originator_old) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
