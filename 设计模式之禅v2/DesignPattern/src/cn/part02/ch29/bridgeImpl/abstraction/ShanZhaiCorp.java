package cn.part02.ch29.bridgeImpl.abstraction;

import cn.part02.ch29.bridgeImpl.implementor.Product;

/**
 * Created by NKS on 2017/9/16.
 */
public class ShanZhaiCorp extends Corp {
    public ShanZhaiCorp(Product product) {
        super(product);
    }

    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚钱呀...");
    }
}
