package cn.part05.ch37.mvc;

//代码清单37-13 抽象Action
public abstract class ActionSupport {
    public final static String SUCCESS = "success";
    public final static String FAIL = "fail";

    //默认的执行方法
    public String execute() {
        return SUCCESS;
    }
}