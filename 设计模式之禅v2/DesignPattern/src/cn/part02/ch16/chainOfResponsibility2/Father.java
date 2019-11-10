package cn.part02.ch16.chainOfResponsibility2;

//代码清单16-9 父亲类
public class Father extends Handler {

    //父亲只处理女儿的请求
    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }

    //父亲的答复
    protected void response(IWomen women) {
        System.out.println("--------女儿向父亲请示-------");
        System.out.println(women.getRequest());
        System.out.println("父亲的答复是:同意\n");
    }
}
