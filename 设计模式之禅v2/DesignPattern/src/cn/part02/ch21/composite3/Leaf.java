package cn.part02.ch21.composite3;

//代码清单21-15 树叶节点
public class Leaf extends Corp {
    //就写一个构造函数，这个是必需的
    public Leaf(String _name, String _position, int _salary) {
        super(_name, _position, _salary);
    }
}
