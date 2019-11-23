package cn.part02.ch29.bridge2.abstraction;

import cn.part02.ch29.bridge2.implementor.Product;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class Corp {

    private Product product;

    public Corp(Product product) {
        this.product = product;
    }

    public void makeMoney() {
        this.product.beProducted();
        this.product.beSelled();
    }


}
