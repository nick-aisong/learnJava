package cn.nks.chainOfResponsibilityImpl;

/**
 * Created by NKS on 2017/9/21.
 */
public class Son extends Handler {

    public Son() {
        super(3);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("--------母亲向儿子请示-------");
        System.out.println(women.getRequest());
        System.out.println("儿子的答复是：同意\n");
    }
}
