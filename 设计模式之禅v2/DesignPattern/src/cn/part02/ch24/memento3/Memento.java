package cn.part02.ch24.memento3;

//代码清单24-4 备忘录
public class Memento {
    //男孩的状态
    private String state = "";

    //通过构造函数传递状态信息
    public Memento(String _state) {
        this.state = _state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}