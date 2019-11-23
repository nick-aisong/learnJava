package cn.part02.ch26.state3;

/**
 * Created by NKS on 2017/9/24.
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());

        context.open();
        context.close();
        context.run();
        context.stop();
    }

}
