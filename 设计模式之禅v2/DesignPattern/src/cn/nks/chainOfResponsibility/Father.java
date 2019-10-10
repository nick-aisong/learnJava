package cn.nks.chainOfResponsibility;

/**
 * Created by NKS on 2017/9/20.
 */
public class Father implements IHandler {
    @Override
    public void HandleMessage(IWomen women) {
        System.out.println("女儿的请示是：" + women.getRequest());
        System.out.println("父亲的答复是:同意");
    }
}
