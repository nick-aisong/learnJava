package cn.nks.bridgeImpl.abstraction;

import cn.nks.bridgeImpl.implementor.Product;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class Corp {

    private Product product;

    public Corp(Product product) {
        this.product = product;
    }

    public void makeMoney(){
        this.product.beProducted();
        this.product.beSelled();
    }


}
