package cn.part02.ch16.chainOfResponsibility3;

//代码清单16-15 具体处理者
public class ConcreteHandler3 extends Handler {
    //定义自己的处理逻辑
    protected Response echo(Request request) {
        //完成处理逻辑
        return null;
    }

    //设置自己的处理级别
    protected Level getHandlerLevel() {
        //设置自己的处理级别
        return null;
    }
}