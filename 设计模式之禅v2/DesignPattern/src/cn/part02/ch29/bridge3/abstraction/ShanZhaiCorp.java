package cn.part02.ch29.bridge3.abstraction;

import cn.part02.ch29.bridge3.implementor.Product;

//代码清单29-12 山寨公司
public class ShanZhaiCorp extends Corp {
    //产什么产品，不知道，等被调用的才知道
    public ShanZhaiCorp(Product product) {
        super(product);
    }

    //狂赚钱
    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚钱呀...");
    }
}
