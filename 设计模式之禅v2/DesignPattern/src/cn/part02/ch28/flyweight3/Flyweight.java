package cn.part02.ch28.flyweight3;

//代码清单28-7 抽象享元角色
public abstract class Flyweight {
    //内部状态
    private String intrinsic;
    //外部状态
    protected final String Extrinsic;

    //要求享元角色必须接受外部状态
    public Flyweight(String _Extrinsic) {
        this.Extrinsic = _Extrinsic;
    }

    //定义业务操作
    public abstract void operate();

    //内部状态的getter/setter
    public String getIntrinsic() {
        return intrinsic;
    }

    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}

//注意，我们在抽象享元中对外部状态加上了final关键字，防止意外产生，什么意外？
//获得了一个外部状态，然后无意修改了一下，池就混乱了！