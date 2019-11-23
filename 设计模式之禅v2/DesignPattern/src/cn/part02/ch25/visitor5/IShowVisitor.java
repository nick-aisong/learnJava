package cn.part02.ch25.visitor5;

//代码清单25-20 展示表接口
public interface IShowVisitor extends IVisitor {
    //展示报表
    public void report();
}
