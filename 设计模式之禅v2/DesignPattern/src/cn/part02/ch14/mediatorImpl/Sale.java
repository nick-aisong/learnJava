package cn.part02.ch14.mediatorImpl;

import java.util.Random;

/**
 * Created by NKS on 2017/9/27.
 */
public class Sale extends AbstractColleague {

    public Sale(AbstractMediator _mediator) {
        super(_mediator);
    }

    public void sellIBMComputer(int number){
        System.out.println("销售IBM电脑"+number+"台");
        super.mediator.execute("sale.sell", number);
    }

    public int getSaleStatus(){
        Random rand = new Random(System.currentTimeMillis());
        int saleStatus = rand.nextInt(100);
        System.out.println("IBM电脑的销售情况为："+saleStatus);
        return saleStatus;
    }

    public void offSale(){
        super.mediator.execute("sale.offsell");
    }
}
