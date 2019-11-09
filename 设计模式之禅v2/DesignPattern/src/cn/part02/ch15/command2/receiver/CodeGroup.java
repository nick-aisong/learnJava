package cn.part02.ch15.command2.receiver;

//代码清单15-4 代码组
public class CodeGroup extends Group {
    //客户要求代码组过去和他们谈
    public void find() {
        System.out.println("找到代码组...");
    }

    //客户要求增加一项功能
    public void add() {
        System.out.println("客户要求增加一项功能...");
    }

    //客户要求修改一项功能
    public void change() {
        System.out.println("客户要求修改一项功能...");
    }

    //客户要求删除一项功能
    public void delete() {
        System.out.println("客户要求删除一项功能...");
    }

    //客户要求给出变更计划
    public void plan() {
        System.out.println("客户要求代码变更计划...");
    }
}
