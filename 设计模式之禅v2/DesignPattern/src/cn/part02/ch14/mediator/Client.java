package cn.part02.ch14.mediator;

//代码清单14-4 场景类
public class Client {

    public static void main(String[] args) {
        //采购人员采购电脑
        System.out.println("------采购人员采购电脑--------");
        Purchase purchase = new Purchase();
        purchase.buyIBMcomputer(100);
        //销售人员销售电脑
        System.out.println("\n------销售人员销售电脑--------");
        Sale sale = new Sale();
        sale.sellIBMComputer(1);
        //库房管理人员管理库存
        System.out.println("\n------库房管理人员清库处理--------");
        Stock stock = new Stock();
        stock.clearStock();
    }
}

//------采购人员采购电脑--------
//IBM电脑的销售情况为：64
//采购IBM电脑：50台
//
//------销售人员销售电脑--------
//销售IBM电脑1台
//库存数量为：99
//
//------库房管理人员清库处理--------
//清理存货数量为：99
//折价销售IBM电脑99台
//不再采购IBM电脑