package cn.part05.ch37.mvc;

//代码清单37-11 模型行为分发接口
public interface IActionDispather {
    //根据Action的名字，返回处理结果
    public String actionInvoke(String actionName);
}