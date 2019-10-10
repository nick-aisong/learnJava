package cn.nks.state;

/**
 * Created by NKS on 2017/9/24.
 */
public class Client {

    public static void main(String[] args) {
        ILift lift = new Lift();

        lift.open();

        lift.close();

        lift.run();

        lift.stop();
    }

}
