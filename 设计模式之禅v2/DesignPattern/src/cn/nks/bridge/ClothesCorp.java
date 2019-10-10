package cn.nks.bridge;

/**
 * Created by NKS on 2017/9/16.
 */
public class ClothesCorp extends Corp {
    @Override
    protected void produce() {
        System.out.println("服装公司生产衣服...");
    }

    @Override
    protected void sell() {
        System.out.println("服装公司出售衣服...");
    }

    public void makeMoney() {
        super.makeMoney();
        System.out.println("服装公司赚小钱...");
    }
}
