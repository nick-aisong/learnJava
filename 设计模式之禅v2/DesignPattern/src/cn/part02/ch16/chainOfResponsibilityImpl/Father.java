package cn.part02.ch16.chainOfResponsibilityImpl;

/**
 * Created by NKS on 2017/9/21.
 */
public class Father extends Handler {

    public Father() {
        super(1);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("--------女儿向父亲请示-------");
        System.out.println(women.getRequest());
        System.out.println("父亲的答复是:同意\n");
    }
}
