package cn.part02.ch16.chainOfResponsibility;

//代码清单16-3 有处理权的人员接口
public interface IHandler {
    //一个女性（女儿、妻子或者母亲）要求逛街，你要处理这个请求
    public void HandleMessage(IWomen women);
}
