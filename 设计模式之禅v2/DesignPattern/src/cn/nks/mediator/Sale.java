package cn.nks.mediator;

import java.util.Random;

/**
 * Created by NKS on 2017/9/26.
 */
public class Sale {

    public void sellIBMComputer(int number) {

        Stock stock = new Stock();
        Purchase purchase = new Purchase();

        if (stock.getStockNumber() < number) {
            purchase.buyIBMComputer(number);
        }

        System.out.println("销售IBM电脑" + number + "台");
        stock.decrease(number);
    }

    public int getSaleStatue() {
        Random rand = new Random(System.currentTimeMillis());
        int saleStatus = rand.nextInt(100);
        System.out.println("IBM电脑的销售情况为：" + saleStatus);
        return saleStatus;
    }

    public void offSale() {
        Stock stock = new Stock();
        System.out.println("折价销售IBM电脑" + stock.getStockNumber() + "台");
    }

}
