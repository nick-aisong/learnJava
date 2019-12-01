package cn.part05.ch38.servant2;

//代码清单38-37 雇工类
public class Servant {
    //服务内容
    public void service(IServiced serviceFuture) {
        serviceFuture.serviced();
    }
}