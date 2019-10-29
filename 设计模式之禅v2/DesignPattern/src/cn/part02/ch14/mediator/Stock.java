package cn.part02.ch14.mediator;

/**
 * Created by NKS on 2017/9/26.
 */
public class Stock {

    private static int COMPUTER_NUMBER = 100;

    public void increase(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER + number;
        System.out.println("库存数量为：" + COMPUTER_NUMBER);
    }

    public void decrease(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER - number;
        System.out.println("库存数量为：" + COMPUTER_NUMBER);
    }

    public int getStockNumber() {
        return COMPUTER_NUMBER;
    }

    public void clearStock() {
        Purchase purchase = new Purchase();
        Sale sale = new Sale();
        System.out.println("清理存货数量为：" + COMPUTER_NUMBER);
        sale.offSale();
        purchase.refuseBuyIBM();

    }

}
