package cn.part02.ch26.state3;

//代码清单26-8 敞门状态
public class OpenningState extends LiftState {
    //开启当然可以关闭了，我就想测试一下电梯门开关功能
    public void close() {
        //状态修改
        super.context.setLiftState(Context.closeingState);
        //动作委托为CloseState来执行
        super.context.getLiftState().close();
    }

    //打开电梯门
    public void open() {
        System.out.println("电梯门开启...");
    }

    //门开着时电梯就运行跑，这电梯，吓死你！
    public void run() {
        //do nothing;
    }

    //开门还不停止？
    public void stop() {
        //do nothing;
    }
}
