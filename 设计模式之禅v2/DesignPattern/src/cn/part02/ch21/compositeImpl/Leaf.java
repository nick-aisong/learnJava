package cn.part02.ch21.compositeImpl;

/**
 * Created by NKS on 2017/9/18.
 */
public class Leaf implements ICorp {

    private String name = "";

    private String position = "";

    private int salary = 0;

    public Leaf(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getInfo() {
        String info = "";
        info = "姓名：" + this.name;
        info = info + "\t职位：" + this.position;
        info = info + "\t薪水：" + this.salary;
        return info;
    }
}
