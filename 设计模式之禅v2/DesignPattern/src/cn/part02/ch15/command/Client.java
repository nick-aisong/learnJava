package cn.part02.ch15.command;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("-------------客户要求增加一个需求-----------------");
        Group rg = new RequirementGroup();
        rg.find();
        rg.add();
        rg.plan();

        System.out.println("-------------客户要求删除一个页面-----------------");
        Group pg = new PageGroup();
        pg.find();
        pg.delete();
        pg.plan();
    }
}
