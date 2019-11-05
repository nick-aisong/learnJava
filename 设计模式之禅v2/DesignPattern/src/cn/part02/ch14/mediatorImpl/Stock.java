package cn.part02.ch14.mediatorImpl;

/**
 * Created by NKS on 2017/9/27.
 */
public class Stock extends AbstractColleague {

    public Stock(AbstractMediator _mediator) {
        super(_mediator);
    }

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
        super.mediator.execute("stock.clear");
        System.out.println("清理存货数量为：" + COMPUTER_NUMBER);
    }
}
