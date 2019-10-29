package cn.part02.ch14.mediatorImpl;

/**
 * Created by NKS on 2017/9/26.
 */
public class AbstractColleague {

    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator _mediator) {
        this.mediator = _mediator;
    }
}
