package cn.part02.ch23.facade4;

//代码清单23-10 新增门面
public class Facade2 {
    //引用原有的门面
    private Facade facade = new Facade();

    //对外提供唯一的访问子系统的方法
    public void methodB() {
        this.facade.methodB();
    }
}
