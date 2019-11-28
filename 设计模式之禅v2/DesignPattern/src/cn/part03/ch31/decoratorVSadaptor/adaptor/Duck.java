package cn.part03.ch31.decoratorVSadaptor.adaptor;

//代码清单31-13 鸭类接口
public interface Duck {
    //会叫
    public void cry();

    //鸭子的外形
    public void desAppearance();

    //描述鸭子的其他行为
    public void desBehavior();
}