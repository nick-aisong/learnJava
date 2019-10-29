package cn.part02.ch16.chainOfResponsibilityImpl;


/**
 * Created by NKS on 2017/9/20.
 */
public abstract class Handler {

    private int level = 0;

    private Handler nextHandler;

    public Handler(int _level) {
        this.level = _level;
    }

    public final void HandleMessage(IWomen women) {
        if (women.getType() == this.level) {
            this.response(women);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.HandleMessage(women);
            } else {
                System.out.println("-----------没地方请示了，不做处理！---------\n");
            }
        }
    }

    public void setNext(Handler _handler) {
        this.nextHandler = _handler;
    }

    public abstract void response(IWomen women);

}
