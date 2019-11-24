package cn.part02.ch28.flyweight3;

//代码清单28-8 具体享元角色
public class ConcreteFlyweight1 extends Flyweight {
    //接受外部状态
    public ConcreteFlyweight1(String _Extrinsic) {
        super(_Extrinsic);
    }

    //根据外部状态进行逻辑处理
    public void operate() {
        //业务逻辑
    }
}
