package cn.part03.ch33.facadeVSmediator.mediator;

//代码清单33-16 税收接口
public interface ITax {
    //税收上升
    public void raise();

    //税收下降
    public void drop();
}