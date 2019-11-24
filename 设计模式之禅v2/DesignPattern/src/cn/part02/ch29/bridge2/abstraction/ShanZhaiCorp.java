package cn.part02.ch29.bridge2.abstraction;

import cn.part02.ch29.bridge2.implementor.Product;

public class ShanZhaiCorp extends Corp {
    public ShanZhaiCorp(Product product) {
        super(product);
    }

    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚钱呀...");
    }
}
