package cn.nks.mediator;

/**
 * Created by NKS on 2017/9/26.
 */
public class Purchase {

    public void buyIBMComputer(int number) {

        Stock stock = new Stock();

        Sale sale = new Sale();

        int saleStatus = sale.getSaleStatue();

        if (saleStatus > 80) {
            System.out.println("采购IBM电脑:" + number + "台");
            stock.increase(number);
        } else {
            int buyNumber = number / 2;
            System.out.println("采购IBM电脑：" + buyNumber + "台");
            stock.increase(buyNumber);
        }
    }

    public void refuseBuyIBM() {
        System.out.println("不再采购IBM电脑");
    }

}
