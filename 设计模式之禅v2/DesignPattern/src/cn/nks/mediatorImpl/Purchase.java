package cn.nks.mediatorImpl;

/**
 * Created by NKS on 2017/9/26.
 */
public class Purchase extends AbstractColleague {

    public Purchase(AbstractMediator _mediator){
        super(_mediator);
    }

    public void buyIBMComputer(int number){
        super.mediator.execute("purchase.buy", number);
    }

    public void refuseBuyIBM(){
        System.out.println("不再采购IBM电脑");
    }

}
