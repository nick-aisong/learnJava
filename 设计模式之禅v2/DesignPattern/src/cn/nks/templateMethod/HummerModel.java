package cn.nks.templateMethod;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class HummerModel {

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    //public abstract void run();

    final public void run() {
        this.start();

        this.engineBoom();

        //this.alarm();
        if (this.isAlarm()){
            this.alarm();
        }

        this.stop();
    }

    //钩子方法，默认喇叭是会响的
    protected boolean isAlarm(){
        return true;
    }

}
