package cn.nks.compositeImpl2;

/**
 * Created by NKS on 2017/9/18.
 */
public abstract class Corp {

    private String name = "";

    private String position = "";

    private int salary = 0;

    // 父节点是谁
    private Corp parent = null;

    public Corp(String _name, String _position, int _salary) {
        this.name = _name;
        this.position = _position;
        this.salary = _salary;
    }

    public String getInfo() {
        String info = "";
        info = "姓名：" + this.name;
        info = info + "\t职位：" + this.position;
        info = info + "\t薪水：" + this.salary;
        return info;
    }

    // 设置父节点
    protected void setParent(Corp _parent){
        this.parent = _parent;
    }
    // 得到父节点
    public Corp getParent(){
        return this.parent;
    }

}
