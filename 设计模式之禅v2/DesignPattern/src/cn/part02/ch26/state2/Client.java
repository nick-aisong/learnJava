package cn.part02.ch26.state2;

/**
 * Created by NKS on 2017/9/24.
 */
public class Client {

    public static void main(String[] args) {
        ILift lift = new Lift();

        lift.setState(ILift.STOPPING_STATE);

        lift.open();

        lift.close();

        lift.run();

        lift.stop();
    }
}
