package cn.nks.stateImpl2;

/**
 * Created by NKS on 2017/9/24.
 */
public class RunningState extends LiftState {
    @Override
    public void open() {
        //do nothing
    }

    @Override
    public void close() {
        //do nothing
    }

    @Override
    public void run() {
        System.out.println("电梯上下跑...");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState);
        super.context.getLiftState().stop();
    }
}
